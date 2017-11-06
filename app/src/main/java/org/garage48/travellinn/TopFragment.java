package org.garage48.travellinn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TopFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);

        String strings[] = {"Person 1","Person 2","Person 3","Person 4","Person 5","Person 6","Person 7"};
        ListView lv = (ListView) view.findViewById(R.id.top_list);
        lv.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strings));
        lv.setAdapter(new UsersAdapter(Users.List, getActivity()));

        return view;
    }
}