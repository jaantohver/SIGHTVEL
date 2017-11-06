package org.garage48.travellinn;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.maps.android.ui.IconGenerator;
import com.ujuizi.ramani.api.android.RSMapServices;
import com.ujuizi.ramani.api.android.RamaniListener;

public class MapsFragment extends Fragment implements OnMapReadyCallback, RamaniListener.onAPICheckDone, GoogleMap.OnMarkerClickListener {

    GoogleMap mMap;
    RSMapServices mRSMapServices;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Map");

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        return rootView;
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            bmp= (Bitmap) extras.get("data");

            IconGenerator icg = new IconGenerator(getActivity());
            m.setIcon(BitmapDescriptorFactory.fromBitmap(icg.makeIcon("Physicum (3)")));
        }
    }

    public static Bitmap bmp = null;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mRSMapServices = new RSMapServices ();
        mRSMapServices.apiKey("18ab60f455c25d885eea722d9f389236", "jaantohver", getActivity().getApplicationContext(), this);

        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},1);
        }

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                2000,
                10f, new android.location.LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
                        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom( ll, 15);
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                });

        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude);

            LatLng coordinate = new LatLng(latitude, longitude);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
            mMap.animateCamera(yourLocation);
        }

        IconGenerator icg = new IconGenerator(getActivity());

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(58.3835809,26.7170644))
                .icon(BitmapDescriptorFactory.fromBitmap(icg.makeIcon("Zavood (2)")))).setTag("Zavood");

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(58.3815583,26.7197021))
                .icon(BitmapDescriptorFactory.fromBitmap(icg.makeIcon("Keller (1)")))).setTag("Keller");

        m = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(58.3664035,26.6885706)));

        m.setIcon(BitmapDescriptorFactory.fromBitmap(icg.makeIcon("Physicum (2)")));
        m.setTag("Physicum");

        mMap.setOnMarkerClickListener(this);
    }

    Marker m;

    @Override
    public void isSuccess(boolean b) {
        if (b) {
            TileProvider tp = mRSMapServices.getMap("ddl.s2cloudless3857");
            if (tp != null) {
                mMap.addTileOverlay(new TileOverlayOptions().tileProvider(tp));
            }
            TileProvider lights = mRSMapServices.getMap("simS3seriesNighttimeLightsGlob/brightness");
            if (tp != null) {
                mMap.addTileOverlay(new TileOverlayOptions().tileProvider(lights).transparency(0.5f));
            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Intent intent = new Intent(getActivity(), LocationPhotosActivity.class);
        intent.putExtra("LOC", (String)marker.getTag());

        if (marker.getTag().equals("Physicum") && bmp != null) {
            intent.putExtra("BitmapImage", bmp);
        }

        startActivity(intent);

        return false;
    }
}