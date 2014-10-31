package com.mesquitestudio.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mesquitestudio.gobmx.R;
import com.mesquitestudio.models.Resolution;

import java.util.List;

/**
 * Created by paulmoreno on 10/6/14.
 */
public class ResolutionAdapter extends BaseAdapter {

    Context context;
    List<Resolution> resolutions;

    public ResolutionAdapter(Context context, List<Resolution> resolutions) {
        this.context = context;
        this.resolutions = resolutions;
    }

    @Override
    public int getCount() {
        return resolutions.size();
    }

    @Override
    public Object getItem(int position) {
        return resolutions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cell_resolution, null);
        }

        Resolution resolution = resolutions.get(position);

        TextView txtQuestion = (TextView) view.findViewById(R.id.txtQuestion);
        txtQuestion.setText(resolution.getQuestion());

        TextView txtAnswer = (TextView) view.findViewById(R.id.txtAnswer);
        txtAnswer.setText(resolution.getAnswer());

        return view;
    }
}
