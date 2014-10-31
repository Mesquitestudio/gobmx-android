package com.mesquitestudio.gobmx;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.mesquitestudio.models.Portal;


public class PortalWebView extends Activity {

    public final static String HDN_URL = "url";
    TextView tvTitle;
    Portal portal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setCustomView(R.layout.title_centered);

        tvTitle = (TextView) findViewById(R.id.lblTitle_activity);
        Bundle bundle = getIntent().getExtras();
        portal = (Portal) bundle.get(HDN_URL);

        String name = portal.getName();
        tvTitle.setText(name);

        String url = portal.getUrl();

        WebView wvGovern = (WebView) findViewById(R.id.webview);
        wvGovern.setInitialScale(75);
        wvGovern.loadUrl(url);
    }
}
