package com.mesquitestudio.gobmx;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mesquitestudio.adapters.TabPageAdapter;
import com.mesquitestudio.fragments.AdditionalFragment;
import com.mesquitestudio.fragments.InformationFragment;
import com.mesquitestudio.fragments.ResolutionFragment;
import com.mesquitestudio.fragments.ServicesFragment;
import com.mesquitestudio.models.Services;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulmoreno on 9/29/14.
 */
public class InformationServices extends FragmentActivity implements View.OnClickListener{

    public static final int INFORMATION = 0, SERVICE = 1, RESOLUTION = 2, ADDITIONAL = 3;
    ViewPager pager;
    public final static String SERVICE_DATA = "SERVICE_DATA";
    private static Services service_data;
    static boolean rated = false;

    private List<Fragment> fragments;

    private TabPageAdapter pageAdapter;
    private ActionBar bar;

    ImageView btnInformation;
    ImageView btnService;
    ImageView btnResolution;
    ImageView btnAdditional;

    TextView tvTitle;

    InformationFragment informationFragment;
    ServicesFragment servicesFragment;
    ResolutionFragment resolutionFragment;
    AdditionalFragment additionalFragment;

    public Services getServiceData() {
        return service_data;
    }
    public void setRated(boolean response) {
        this.rated = response;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_services);

        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setTitle(R.string.title_activity_services);
        getActionBar().setCustomView(R.layout.title_centered);

        tvTitle = (TextView) findViewById(R.id.lblTitle_activity);

        Bundle bundle = getIntent().getExtras();
        service_data = (Services) bundle.get(SERVICE_DATA);

        try {
            tvTitle.setText(service_data.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }

        pager = (ViewPager)findViewById(R.id.pager);
        btnInformation = (ImageView) findViewById(R.id.btnInformation);
        btnService = (ImageView) findViewById(R.id.btnService);
        btnResolution = (ImageView) findViewById(R.id.btnResolution);
        btnAdditional = (ImageView) findViewById(R.id.btnAdditional);

        btnInformation.setTag(INFORMATION);
        btnInformation.setOnClickListener(this);

        btnService.setTag(SERVICE);
        btnService.setOnClickListener(this);

        btnResolution.setTag(RESOLUTION);
        btnResolution.setOnClickListener(this);

        btnAdditional.setTag(ADDITIONAL);
        btnAdditional.setOnClickListener(this);

        fragments = getFragments();

        pageAdapter = new TabPageAdapter(getSupportFragmentManager(), fragments);

        pager.setAdapter(pageAdapter);
        pager.setOffscreenPageLimit(1);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        btnInformation.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_inf_2));
                        btnService.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_ser));
                        btnResolution.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_res));
                        btnAdditional.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_add));
                        break;
                    case 1:
                        btnInformation.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_inf));
                        btnService.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_ser_2));
                        btnResolution.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_res));
                        btnAdditional.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_add));
                        break;
                    case 2:
                        btnInformation.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_inf));
                        btnService.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_ser));
                        btnResolution.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_res_2));
                        btnAdditional.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_add));
                        break;
                    case 3:
                        btnInformation.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_inf));
                        btnService.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_ser));
                        btnResolution.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_res));
                        btnAdditional.setImageDrawable(getResources().getDrawable(R.drawable.ic_tab_add_2));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    @Override
    public void onClick(View v) {
        int key = (Integer) v.getTag();
        switch (key) {
            case INFORMATION:
                pager.setCurrentItem(0);
                break;
            case SERVICE:
                pager.setCurrentItem(1);
                break;
            case RESOLUTION:
                pager.setCurrentItem(2);
                break;
            case ADDITIONAL:
                pager.setCurrentItem(3);
                break;
        }
    }

    private List<Fragment> getFragments() {
        List<Fragment> flist = new ArrayList<Fragment>();

        informationFragment = new InformationFragment();
        servicesFragment = new ServicesFragment();
        resolutionFragment = new ResolutionFragment();
        additionalFragment = new AdditionalFragment();

        flist.add(informationFragment);
        flist.add(servicesFragment);
        flist.add(resolutionFragment);
        flist.add(additionalFragment);
        return flist;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (rated) {
            AlertDialog alert = new AlertDialog.Builder(InformationServices.this).create();
            alert.setMessage(getString(R.string.alert_rate_success));
            alert.setButton(Dialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alert.show();
            rated = false;
        }
    }
}
