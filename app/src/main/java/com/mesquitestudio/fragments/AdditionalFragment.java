package com.mesquitestudio.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.mesquitestudio.adapters.AdditionalAdapter;
import com.mesquitestudio.gobmx.InformationServices;
import com.mesquitestudio.gobmx.R;
import com.mesquitestudio.models.Additional;
import com.mesquitestudio.models.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdditionalFragment extends Fragment {

    static Services service_data;

    List<Additional> additionalList = new ArrayList<Additional>();
    List<String> groupList;
    HashMap<String, List<String>> childList;
    ExpandableListView elvAdditional;
    AdditionalAdapter adapter;

    public AdditionalFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service_data = ((InformationServices) getActivity()).getServiceData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_additional, container, false);
        if (service_data != null) {
            elvAdditional = (ExpandableListView) view.findViewById(R.id.elvAdditional);
            loadData();
        }
        return view;
    }

    public void loadData() {
        groupList = new ArrayList<String>();
        childList = new HashMap<String, List<String>>();
        additionalList = service_data.getAdditionalList();

        if (additionalList.size() != 0) {
            for (int i = 0; i < additionalList.size(); i++) {
                String information = additionalList.get(i).getInformation();
                String detail = additionalList.get(i).getDetail();

                groupList.add(information);
                List<String> tempList = new ArrayList<String>();
                tempList.add(detail);
                childList.put(information, tempList);
            }
        }
        adapter = new AdditionalAdapter(getActivity(), groupList, childList);
        elvAdditional.setAdapter(adapter);
    }

}
