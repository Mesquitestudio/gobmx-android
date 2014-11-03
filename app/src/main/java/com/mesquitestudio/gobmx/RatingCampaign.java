package com.mesquitestudio.gobmx;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mesquitestudio.models.Services;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paulmoreno on 10/27/14.
 */
public class RatingCampaign extends Activity {

    EditText etTitle;
    EditText etComments;
    TextView tvRate, tvTitle;
    ImageView btnTwitter;
    ImageView btnFacebook;
    ImageView btnStar1, btnStar2, btnStar3, btnStar4, btnStar5;
    Button btnSend;

    static AlertDialog alert;
    static int rateStar;
    static boolean boolFb = false, boolTwt = false;
    static Services service_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_campaign);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        Bundle bundle = getIntent().getExtras();
        service_data = (Services) bundle.get("SERVICE_DATA");
        if (service_data != null) {
            getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getActionBar().setCustomView(R.layout.title_centered);
            tvTitle = (TextView) findViewById(R.id.lblTitle_activity);
            tvTitle.setText(service_data.getName());
            rateStar = 0;
            alert = new AlertDialog.Builder(RatingCampaign.this).create();
            tvRate = (TextView) findViewById(R.id.tvRateStar);
            etComments = (EditText) findViewById(R.id.etComments);
            etTitle = (EditText) findViewById(R.id.etTitle);
            btnFacebook = (ImageView) findViewById(R.id.ivFB);
            btnFacebook.setOnClickListener(new OnClick());
            btnTwitter = (ImageView) findViewById(R.id.ivTwt);
            btnTwitter.setOnClickListener(new OnClick());
            btnStar1 = (ImageView) findViewById(R.id.btnStar1);
            btnStar2 = (ImageView) findViewById(R.id.btnStar2);
            btnStar3 = (ImageView) findViewById(R.id.btnStar3);
            btnStar4 = (ImageView) findViewById(R.id.btnStar4);
            btnStar5 = (ImageView) findViewById(R.id.btnStar5);
            btnStar1.setOnClickListener(new OnClick());
            btnStar2.setOnClickListener(new OnClick());
            btnStar3.setOnClickListener(new OnClick());
            btnStar4.setOnClickListener(new OnClick());
            btnStar5.setOnClickListener(new OnClick());
            btnSend = (Button) findViewById(R.id.btnSendR);
            btnSend.setOnClickListener(new OnClick());
        }
    }

    class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ivFB:
                    if (!boolFb) {
                        boolFb = true;
                        btnFacebook.setImageDrawable(getResources().getDrawable(R.drawable.ic_fb_on));
                    } else {
                        boolFb = false;
                        btnFacebook.setImageDrawable(getResources().getDrawable(R.drawable.ic_fb_off));
                    }
                    break;
                case R.id.ivTwt:
                    if (!boolTwt) {
                        boolTwt = true;
                        btnTwitter.setImageDrawable(getResources().getDrawable(R.drawable.ic_twitter_on));
                    } else {
                        boolTwt = false;
                        btnTwitter.setImageDrawable(getResources().getDrawable(R.drawable.ic_twitter_off));
                    }
                    break;
                case R.id.btnStar1:
                    btnStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gold));
                    btnStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gray));
                    btnStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gray));
                    btnStar4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gray));
                    btnStar5.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gray));
                    rateStar = 1;
                    tvRate.setText(R.string.rate_star1);
                    break;
                case R.id.btnStar2:
                    btnStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gold));
                    btnStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gold));
                    btnStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gray));
                    btnStar4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gray));
                    btnStar5.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gray));
                    rateStar = 2;
                    tvRate.setText(R.string.rate_star2);
                    break;
                case R.id.btnStar3:
                    btnStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gold));
                    btnStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gold));
                    btnStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gold));
                    btnStar4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gray));
                    btnStar5.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gray));
                    rateStar = 3;
                    tvRate.setText(R.string.rate_star3);
                    break;
                case R.id.btnStar4:
                    btnStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gold));
                    btnStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gold));
                    btnStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gold));
                    btnStar4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gold));
                    btnStar5.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gray));
                    rateStar = 4;
                    tvRate.setText(R.string.rate_star4);
                    break;
                case R.id.btnStar5:
                    btnStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gold));
                    btnStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gold));
                    btnStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gold));
                    btnStar4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gold));
                    btnStar5.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_gold));
                    rateStar = 5;
                    tvRate.setText(R.string.rate_star5);
                    break;
                case R.id.btnSendR:
                    if (rateStar != 0) {
                        if (etTitle.getText().toString().length() >= 5) {
                            if (etComments.getText().toString().length() >= 10) {
                                String url = "http://gobmx.mesquitestudio.com/ws/ranking?title=" + etTitle.getText() + "&description=" + etComments.getText() + "&score=" + rateStar + "&id_service=" + service_data.getId();
                                Log.d("event", url);
                                RequestQueue queue = Volley.newRequestQueue(RatingCampaign.this);
                                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        InformationServices instance = new InformationServices();
                                        instance.setRated(true);
                                        setResult(RESULT_OK);
                                        finish();
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String, String> params = new HashMap<String, String>();
                                        params.put("title", etTitle.getText().toString());
                                        params.put("description", etComments.getText().toString());
                                        params.put("score", rateStar + "");
                                        params.put("id", service_data.getId() + "");
                                        return params;
                                    }
                                };
                                queue.add(request);
                            } else {
                                alert.setTitle(R.string.alert_error);
                                alert.setMessage(getString(R.string.alert_comment_null));
                                alert.show();
                            }
                        } else {
                            alert.setTitle(R.string.alert_error);
                            alert.setMessage(getString(R.string.alert_title_null));
                            alert.show();
                        }
                    } else {
                        alert.setTitle(R.string.alert_error);
                        alert.setMessage(getString(R.string.alert_rateStar));
                        alert.show();
                    }
                    break;
            }
        }
    }
}
