package com.mesquitestudio.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mesquitestudio.adapters.ResolutionAdapter;
import com.mesquitestudio.gobmx.InformationServices;
import com.mesquitestudio.gobmx.R;
import com.mesquitestudio.models.Resolution;
import com.mesquitestudio.models.Services;

import java.util.ArrayList;
import java.util.List;

public class ResolutionFragment extends Fragment {

    static Services service_data;

    List<Resolution> resolutionList = new ArrayList<Resolution>();;
    ListView lvResolution;
    ResolutionAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service_data = ((InformationServices) getActivity()).getServiceData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resolution, container, false);

        if (service_data != null) {

            lvResolution = (ListView) view.findViewById(R.id.lvResolution);
            resolutionList = service_data.getResolutionList();

            if (resolutionList.size() != 0) {
                adapter = new ResolutionAdapter(getActivity().getApplicationContext(), resolutionList);
                lvResolution.setAdapter(adapter);
            }
        }
        return view;
    }
}
