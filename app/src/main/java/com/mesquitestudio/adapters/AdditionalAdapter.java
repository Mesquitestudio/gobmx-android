package com.mesquitestudio.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.mesquitestudio.gobmx.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by paulmoreno on 9/30/14.
 */
public class AdditionalAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> groupList;
    private HashMap<String, List<String>> childList;

    public AdditionalAdapter(Context context, List<String> groupList, HashMap<String, List<String>> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return this.groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childList.get(this.groupList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.childList.get(this.groupList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {

        String titleGroup = (String) getGroup(groupPosition);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listrow_group, null);
        }

        TextView lblGroup_title = (TextView) view.findViewById(R.id.lblItem);

        lblGroup_title.setTypeface(null, Typeface.BOLD);
        lblGroup_title.setText(titleGroup);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {

        String txtChild = (String) getChild(groupPosition, childPosition);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listrow_details, null);
        }

        TextView lblChild_text = (TextView) view.findViewById(R.id.lblSubItem);
        lblChild_text.setText(txtChild);

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
