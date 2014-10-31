package com.mesquitestudio.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mesquitestudio.gobmx.R;
import com.mesquitestudio.models.Services;

import java.util.List;

/**
 * Created by paulmoreno on 10/2/14.
 */
public class ServiceAdapter extends BaseAdapter {

    Context context;
    List<Services> services_list;

    public ServiceAdapter(Context context, List<Services> services_list) {
        this.context = context;
        this.services_list = services_list;
    }

    @Override
    public int getCount() {
        return services_list.size();
    }

    @Override
    public Object getItem(int position) {
        return services_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cell_services, null);
        }

        Services serviceModel = services_list.get(position);

        TextView titleService = (TextView) view.findViewById(R.id.title_Service);
        titleService.setText(serviceModel.getName());

        TextView txtDependency = (TextView) view.findViewById(R.id.txtDependency);
        txtDependency.setText(serviceModel.getDependency());

        return view;
    }
}
