package com.mesquitestudio.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mesquitestudio.gobmx.R;
import com.mesquitestudio.models.Campaign;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulmoreno on 9/26/14.
 */
public class CampaignAdapter extends ArrayAdapter {

    Context context;
    List<Campaign> campaign_list;

    public CampaignAdapter(Context context, int textViewResourceId, ArrayList<Campaign> campaign_list) {
        super(context, textViewResourceId, campaign_list);
        this.campaign_list = new ArrayList<Campaign>();
        this.campaign_list.addAll(campaign_list);
    }

    @Override
    public int getCount() {
        return campaign_list.size();
    }

    @Override
    public Object getItem(int i) {
        return campaign_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cell_campaign, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) view.findViewById(R.id.lblTitle_campaign);
            holder.intCount = (TextView) view.findViewById(R.id.txtCount_rating);
            holder.checkBox = (CheckBox) view.findViewById(R.id.optionChk);
            view.setTag(holder);
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = (CheckBox) v;
                    Campaign campaign = (Campaign) checkBox.getTag();
                    campaign.setSelected(false);
                    notifyDataSetChanged();
                }
            });
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Campaign campaign = campaign_list.get(i);
        holder.txtTitle.setText(campaign.getCampaignName());
        holder.intCount.setText(campaign.getCampaignRating()+"");
        holder.checkBox.setChecked(campaign.isSelected());
        holder.checkBox.setTag(campaign);
        return view;
    }

    private class ViewHolder{
        TextView txtTitle;
        TextView intCount;
        CheckBox checkBox;
    }
}
