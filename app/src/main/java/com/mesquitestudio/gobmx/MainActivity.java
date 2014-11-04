package com.mesquitestudio.gobmx;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends Activity {

    final static int PICK_REQUEST = 1;

    static SharedPreferences preferences;
    static Calendar calendar;

    Button btnDependency, btnServices, btnOpinion;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = Calendar.getInstance();
        preferences = getSharedPreferences("App", 0);

        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setCustomView(R.layout.title_centered);

        TextView tvTitle = (TextView) findViewById(R.id.lblTitle_activity);
        tvTitle.setText(R.string.app_name);

        btnDependency = (Button) findViewById(R.id.btnDependency);
        btnDependency.setOnClickListener(new OnClick());

        btnServices = (Button) findViewById(R.id.btnServices);
        btnServices.setOnClickListener(new OnClick());

        btnOpinion = (Button) findViewById(R.id.btnOpinion);
        btnOpinion.setOnClickListener(new OnClick());
    }

    class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {

                case R.id.btnDependency:
                    intent = new Intent(getApplicationContext(), PortalActivity.class);
                    startActivity(intent);
                    break;

                case R.id.btnServices:
                    intent = new Intent(getApplicationContext(), ServicesActivity.class);
                    startActivity(intent);
                    break;

                case R.id.btnOpinion:
                    if (preferences.getInt("day", 0) == calendar.get(Calendar.DAY_OF_YEAR)) {

                        alert = new AlertDialog.Builder(MainActivity.this).create();
                        alert.setMessage(getString(R.string.alert_opinion_votes));
                        alert.setButton(Dialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        alert.show();
                    } else {
                        intent = new Intent(getApplicationContext(), OpinionActivity.class);
                        startActivityForResult(intent, PICK_REQUEST);
                    }
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_REQUEST) {
            if (resultCode == RESULT_OK) {
                alert = new AlertDialog.Builder(MainActivity.this).create();
                alert.setMessage(getString(R.string.alert_vote_success));
                alert.setButton(Dialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.show();
            }
        }
    }
}
