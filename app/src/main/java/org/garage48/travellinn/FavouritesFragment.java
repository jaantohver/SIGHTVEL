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
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FavouritesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Your favourites in Tartu");

        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        ListView lv = (ListView) view.findViewById(R.id.favourites_list);
        lv.setAdapter(new LocationsAdapter(Locations.List, getActivity()));

        FloatingActionButton share = (FloatingActionButton) view.findViewById(R.id.fab_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((FavouritesFragment.this).getActivity(), FriendsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
