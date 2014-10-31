package com.mesquitestudio.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.mesquitestudio.adapters.AditionalAdapter;
import com.mesquitestudio.gobmx.InformationServices;
import com.mesquitestudio.gobmx.R;
import com.mesquitestudio.models.Aditional;
import com.mesquitestudio.models.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdditionalFragment extends Fragment {

    static Services service_data;

    List<Aditional> aditionalList = new ArrayList<Aditional>();
    List<String> groupList;
    HashMap<String, List<String>> childList;
    ExpandableListView elvAdditional;
    AditionalAdapter adapter;

    public AdditionalFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service_data = ((InformationServices) getActivity()).getServiceData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aditional, container, false);
        if (service_data != null) {
            elvAdditional = (ExpandableListView) view.findViewById(R.id.elvAdditional);
            loadData();
        }
        return view;
    }

    public void loadData() {
        groupList = new ArrayList<String>();
        childList = new HashMap<String, List<String>>();
        aditionalList = service_data.getAditionalList();

        if (aditionalList.size() != 0) {
            for (int i = 0; i < aditionalList.size(); i++) {
                String information = aditionalList.get(i).getInformation();
                String detail = aditionalList.get(i).getDetail();

                groupList.add(information);
                List<String> tempList = new ArrayList<String>();
                tempList.add(detail);
                childList.put(information, tempList);
            }
        }
        adapter = new AditionalAdapter(getActivity(), groupList, childList);
        elvAdditional.setAdapter(adapter);
    }

}
