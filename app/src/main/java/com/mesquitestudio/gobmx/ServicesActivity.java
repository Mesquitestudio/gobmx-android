package com.mesquitestudio.gobmx;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.mesquitestudio.adapters.ServiceAdapter;
import com.mesquitestudio.models.Additional;
import com.mesquitestudio.models.Cost;
import com.mesquitestudio.models.Document;
import com.mesquitestudio.models.Resolution;
import com.mesquitestudio.models.Services;

import java.util.ArrayList;
import java.util.List;


public class ServicesActivity extends Activity {

    ListView listView;
    List<Services> services_list;
    EditText searchBox;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setTitle(R.string.title_activity_services);
        getActionBar().setCustomView(R.layout.title_centered_w_g);

        tvTitle = (TextView) findViewById(R.id.lblTitle_activity);
        tvTitle.setText("Tramites y Servicios");

        searchBox = (EditText) findViewById(R.id.searchBox);
        searchBox.setOnEditorActionListener(new onSearchActionListener());
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    getSData();
                }
            }
        });

        listView = (ListView) findViewById(R.id.lvServices);
        listView.setOnItemClickListener(new ItemClickListener());

        getSData();
    }

    void getSData() {
        services_list = new ArrayList<Services>();
        Ion.with(getApplicationContext())
                .load("http://gobmx.mesquitestudio.com/ws/service")
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        if (e == null && result != null) {
                            JsonObject json = result.getResult();
                            JsonArray serviceArray = json.getAsJsonArray("service");

                            for (Object object : serviceArray) {
                                ArrayList<Document> documents_list = new ArrayList<Document>();
                                ArrayList<Cost> costs_list = new ArrayList<Cost>();
                                ArrayList<Resolution> resolutions = new ArrayList<Resolution>();
                                ArrayList<Additional> aditionals = new ArrayList<Additional>();
                                JsonObject service_object = (JsonObject) object;
                                String name = service_object.get("name").getAsString();
                                String dependency = service_object.get("dependency").getAsString();
                                int id = service_object.get("id").getAsInt();
                                String html = service_object.get("text").getAsString();
                                String imageUrl = service_object.get("imageurl").getAsString();
                                String phone = service_object.get("phone").getAsString();
                                String web = service_object.get("web").getAsString();
                                String address = service_object.get("address").getAsString();
                                Services servicesModel = new Services(id, name, dependency, html, imageUrl, phone, web, address);
                                JsonArray documents = service_object.getAsJsonArray("document");
                                for (Object ob : documents) {
                                    JsonObject joDocuments = (JsonObject) ob;
                                    int id_doc = joDocuments.get("id").getAsInt();
                                    String name_doc = joDocuments.get("name").getAsString();
                                    String description = joDocuments.get("description").getAsString();
                                    Document documentModel = new Document(id_doc, name_doc, description);
                                    documents_list.add(documentModel);
                                    servicesModel.setDocumentList(documents_list);
                                }
                                JsonArray costs = service_object.getAsJsonArray("cost");
                                for (Object o : costs) {
                                    JsonObject joCost = (JsonObject) o;
                                    String type_cost = joCost.get("type").getAsString();
                                    String amount_cost = joCost.get("amount").getAsString();
                                    Cost costModel = new Cost(type_cost, amount_cost);
                                    costs_list.add(costModel);
                                    servicesModel.setCostsList(costs_list);
                                }
                                JsonArray resolution = service_object.getAsJsonArray("resolution");
                                for (Object o : resolution) {
                                    JsonObject jo = (JsonObject) o;
                                    String question = jo.get("question").getAsString();
                                    String answer = jo.get("answer").getAsString();
                                    Resolution resolutionModel = new Resolution(question, answer);
                                    resolutions.add(resolutionModel);
                                    servicesModel.setResolutionList(resolutions);
                                }
                                JsonArray additional = service_object.getAsJsonArray("additional");
                                for (Object o : additional) {
                                    JsonObject jo = (JsonObject) o;
                                    String information = jo.get("information").getAsString();
                                    String detail = jo.get("detail").getAsString();
                                    Additional additionalModel = new Additional(information, detail);
                                    aditionals.add(additionalModel);
                                    servicesModel.setAditionalList(aditionals);
                                }
                                services_list.add(servicesModel);
                            }
                            ServiceAdapter adapter = new ServiceAdapter(getApplicationContext(), services_list);
                            listView.setAdapter(adapter);
                        }
                    }
                });
    }

    void getSSData(final String text) {
        services_list = new ArrayList<Services>();
        Ion.with(getApplicationContext())
                .load("http://gobmx.mesquitestudio.com/ws/search")
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        if (e == null && result != null) {
                            JsonObject json = result.getResult();
                            JsonArray serviceArray = json.getAsJsonArray("service");
                            for (Object object : serviceArray) {
                                ArrayList<Document> documents_list = new ArrayList<Document>();
                                ArrayList<Cost> costs_list = new ArrayList<Cost>();
                                ArrayList<Resolution> resolutions = new ArrayList<Resolution>();
                                ArrayList<Additional> additionals = new ArrayList<Additional>();
                                JsonObject service_object = (JsonObject) object;
                                String name = service_object.get("name").getAsString();
                                String dependency = service_object.get("dependency").getAsString();
                                int id = service_object.get("id").getAsInt();
                                String html = service_object.get("text").getAsString();
                                String imageUrl = service_object.get("imageurl").getAsString();
                                String phone = service_object.get("phone").getAsString();
                                String web = service_object.get("web").getAsString();
                                String address = service_object.get("address").getAsString();
                                Services servicesModel = new Services(id, name, dependency, html, imageUrl, phone, web, address);
                                JsonArray documents = service_object.getAsJsonArray("document");
                                for (Object ob : documents) {
                                    JsonObject joDocuments = (JsonObject) ob;
                                    int id_doc = joDocuments.get("id").getAsInt();
                                    String name_doc = joDocuments.get("name").getAsString();
                                    String description = joDocuments.get("description").getAsString();
                                    Document documentModel = new Document(id_doc, name_doc, description);
                                    documents_list.add(documentModel);
                                    servicesModel.setDocumentList(documents_list);
                                }
                                JsonArray costs = service_object.getAsJsonArray("cost");
                                for (Object o : costs) {
                                    JsonObject joCost = (JsonObject) o;
                                    String type_cost = joCost.get("type").getAsString();
                                    String amount_cost = joCost.get("amount").getAsString();
                                    Cost costModel = new Cost(type_cost, amount_cost);
                                    costs_list.add(costModel);
                                    servicesModel.setCostsList(costs_list);
                                }
                                JsonArray resolution = service_object.getAsJsonArray("resolution");
                                for (Object o : resolution) {
                                    JsonObject jo = (JsonObject) o;
                                    String question = jo.get("question").getAsString();
                                    String answer = jo.get("answer").getAsString();
                                    Resolution resolutionModel = new Resolution(question, answer);
                                    resolutions.add(resolutionModel);
                                    servicesModel.setResolutionList(resolutions);
                                }
                                JsonArray additional = service_object.getAsJsonArray("additional");
                                for (Object o : additional) {
                                    JsonObject jo = (JsonObject) o;
                                    String information = jo.get("information").getAsString();
                                    String detail = jo.get("detail").getAsString();
                                    Additional additionalModel = new Additional(information, detail);
                                    additionals.add(additionalModel);
                                    servicesModel.setAditionalList(additionals);
                                }
                                services_list.add(servicesModel);
                            }
                            ServiceAdapter adapter = new ServiceAdapter(getApplicationContext(), services_list);
                            listView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    class onSearchActionListener implements TextView.OnEditorActionListener{
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(searchBox.getWindowToken(), 0);
                getSSData(v.getText().toString());
                return true;
            }
            return false;
        }
    }

    class ItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ServicesActivity.this, InformationServices.class);
            intent.putExtra(InformationServices.SERVICE_DATA, services_list.get(position));
            startActivity(intent);
        }
    }
}
