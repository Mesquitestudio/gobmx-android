package com.mesquitestudio.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mesquitestudio.gobmx.R;
import com.mesquitestudio.models.Cost;

import java.util.List;

/**
 * Created by paulmoreno on 10/6/14.
 */
public class CostAdapter extends BaseAdapter {

    Context context;
    List<Cost> costs;

    public CostAdapter(Context context, List<Cost> costs) {
        this.context = context;
        this.costs = costs;
    }

    @Override
    public int getCount() {
        return costs.size();
    }

    @Override
    public Object getItem(int position) {
        return costs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cell_cost, null);
        }

        Cost cost = costs.get(position);

        TextView txtType = (TextView) view.findViewById(R.id.type_cost);
        txtType.setText(cost.getType());

        TextView txtAmount = (TextView) view.findViewById(R.id.amount_cost);
        txtAmount.setText(cost.getAmount());

        return view;
    }
}
