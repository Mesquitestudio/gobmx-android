package com.mesquitestudio.gobmx;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.mesquitestudio.adapters.CampaignAdapter;
import com.mesquitestudio.models.Campaign;

import java.util.ArrayList;
import java.util.Calendar;

public class OpinionActivity extends Activity {

    ArrayList<Campaign> campaign_list = new ArrayList<Campaign>();
    CampaignAdapter adapter;
    SharedPreferences preferences;
    static int day;
    Calendar calendar;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion);

        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_YEAR);
        preferences = getSharedPreferences("App", 0);

        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setCustomView(R.layout.title_centered);

        TextView tvTitle = (TextView) findViewById(R.id.lblTitle_activity);
        tvTitle.setText("Danos tu opinión");

        loadIn();

        Button btnSend = (Button) findViewById(R.id.btnSendC);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonArray array = new JsonArray();
                JsonObject object;
                JsonObject campaign = new JsonObject();

                for (int i = 0; i < campaign_list.size(); i++) {
                    if (campaign_list.get(i).isSelected()) {
                        object = new JsonObject();
                        try {
                            object.addProperty("id", campaign_list.get(i).getCampaignId());
                            object.addProperty("name", campaign_list.get(i).getCampaignName());
                        } catch (JsonIOException e) {
                            e.printStackTrace();
                        }
                        array.add(object);
                    }
                }
                if (array.size() != 0) {
                    try {
                        campaign.add("campaign", array);
                        sending(campaign);
                    } catch (JsonIOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    void loadIn() {
        try {
            Ion.with(getApplicationContext())
                    .load("http://gobmx.mesquitestudio.com/ws/campaign")
                    .asJsonObject()
                    .withResponse()
                    .setCallback(new FutureCallback<Response<JsonObject>>() {
                        @Override
                        public void onCompleted(Exception e, Response<JsonObject> result) {
                            if (e == null && result != null) {
                                JsonObject json = result.getResult();
                                JsonArray campaign_array = json.getAsJsonArray("campaign");
                                for (Object object : campaign_array) {
                                    JsonObject campaign_jo = (JsonObject) object;

                                    String txtTitle = campaign_jo.get("title").getAsString();
                                    int txtCount = campaign_jo.get("votes").getAsInt();
                                    int txtId = campaign_jo.get("id").getAsInt();

                                    Campaign campaignModel = new Campaign(txtId, txtTitle, txtCount);
                                    campaign_list.add(campaignModel);
                                }

                                adapter = new CampaignAdapter(getApplicationContext(), R.layout.cell_campaign, campaign_list);
                                listView = (ListView) findViewById(R.id.listview);
                                listView.setAdapter(adapter);

                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        if (campaign_list.get(position).isSelected()) {
                                            campaign_list.get(position).setSelected(false);
                                        } else {
                                            campaign_list.get(position).setSelected(true);
                                        }
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            } else {
                                e.printStackTrace();
                            }
                        }
                    });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    boolean sending(final JsonObject jo) {
        Ion.with(OpinionActivity.this)
                .load("POST", "http://gobmx.mesquitestudio.com/ws/voto")
                .setJsonObjectBody(jo)
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        if (e == null && result != null) {
                            preferences.edit().putInt("day", day).apply();
                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                });
        return true;
    }
}
