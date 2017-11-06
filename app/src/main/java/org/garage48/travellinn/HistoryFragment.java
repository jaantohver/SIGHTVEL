package org.garage48.travellinn;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Your history in Tartu");

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        ListView lv = (ListView) view.findViewById(R.id.history_list);
        lv.setAdapter(new LocationsAdapter(Locations.List, getActivity()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), LocationPhotosActivity.class);

                if (i == 0) {
                    intent.putExtra("LOC", "Physicum");

                    if (MapsFragment.bmp != null) {
                        intent.putExtra("BitmapImage", MapsFragment.bmp);
                    }
                } else if (i==1) {
                    intent.putExtra("LOC", "Keller");
                } else if (i==2) {
                    intent.putExtra("LOC", "Zavood");
                }

                startActivity(intent);
            }
        });

        FloatingActionButton share = (FloatingActionButton) view.findViewById(R.id.fab_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((HistoryFragment.this).getActivity(), FriendsActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

}
