package org.garage48.travellinn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jaan on 04/11/2017.
 */

public class LocationsAdapter extends ArrayAdapter<Location> {
    private ArrayList<Location> dataSet;
    Context mContext;

    public LocationsAdapter (ArrayList<Location> data, Context context) {
        super(context, R.layout.cell_location, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Location location = getItem(position);

        final View result;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cell_location, parent, false);
        }

        ((TextView)convertView.findViewById(R.id.name)).setText(location.name);
        ((ImageView)convertView.findViewById(R.id.image)).setImageResource(location.image);


        return convertView;
    }
}