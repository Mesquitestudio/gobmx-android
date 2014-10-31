package com.mesquitestudio.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mesquitestudio.gobmx.R;
import com.mesquitestudio.models.Portal;

import java.util.List;

/**
 * Created by paulmoreno on 9/29/14.
 */
public class PortalAdapter extends BaseAdapter {

    Context context;
    List<Portal> portal_list;

    public PortalAdapter(Context context, List<Portal> portal_list) {
        this.portal_list = portal_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return portal_list.size();
    }

    @Override
    public Object getItem(int position) {
        return portal_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cell_portal, null);
        }

        Portal portalModel = portal_list.get(position);

        TextView btnTitle = (TextView) view.findViewById(R.id.btn_empty);
        btnTitle.setText(portalModel.getName());

        return view;
    }
}
