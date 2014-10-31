package com.mesquitestudio.gobmx;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.mesquitestudio.models.Portal;
import com.mesquitestudio.models.Services;


public class SiteServiceActivity extends Activity {

    public final static String SERVICE_DATA = "SERVICE_DATA";
    static Services service_data;

    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setCustomView(R.layout.title_centered);
        tvTitle = (TextView) findViewById(R.id.lblTitle_activity);
        Bundle bundle = getIntent().getExtras();
        service_data = (Services) bundle.get(SERVICE_DATA);
        tvTitle.setText(service_data.getDependency());
        WebView wvGovern = (WebView) findViewById(R.id.webview);
        wvGovern.setInitialScale(75);
        wvGovern.loadUrl(service_data.getWeb());
    }
}
