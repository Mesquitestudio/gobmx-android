package com.mesquitestudio.gobmx;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.mesquitestudio.adapters.PortalAdapter;
import com.mesquitestudio.models.Portal;

import java.util.ArrayList;
import java.util.List;


public class PortalActivity extends Activity {

    List<Portal> portal_list = new ArrayList<Portal>();
    ListView listView;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);

        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setCustomView(R.layout.title_centered);

        tvTitle = (TextView) findViewById(R.id.lblTitle_activity);
        tvTitle.setText(R.string.title_activity_portal);

        listView = (ListView) findViewById(R.id.lvPortal);
        listView.setOnItemClickListener(new onItemClickListener());

        getSData();
    }

    void getSData() {
        Ion.with(getApplicationContext())
                .load("http://gobmx.mesquitestudio.com/ws/link")
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        if (e == null && result != null) {
                            JsonObject json = result.getResult();
                            JsonArray portalArray = json.getAsJsonArray("link");
                            for (Object object : portalArray) {
                                JsonObject portal_object = (JsonObject)object;
                                String btnTitle = portal_object.get("title").getAsString();
                                String hdnUrl = portal_object.get("address").getAsString();
                                Portal portalModel = new Portal(hdnUrl, btnTitle);
                                portal_list.add(portalModel);
                            }
                            PortalAdapter adapter = new PortalAdapter(getApplicationContext(), portal_list);
                            listView.setAdapter(adapter);
                        }
                    }
                });
    }

    class onItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(PortalActivity.this, PortalWebView.class);
            intent.putExtra(PortalWebView.HDN_URL, portal_list.get(position));
            startActivity(intent);
        }
    }

}
