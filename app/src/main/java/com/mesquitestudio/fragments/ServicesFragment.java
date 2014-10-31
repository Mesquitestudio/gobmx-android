package com.mesquitestudio.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.koushikdutta.ion.Ion;
import com.mesquitestudio.gobmx.InformationServices;
import com.mesquitestudio.gobmx.R;
import com.mesquitestudio.gobmx.RatingCampaign;
import com.mesquitestudio.gobmx.SiteServiceActivity;
import com.mesquitestudio.models.Services;

import java.util.Calendar;

public class ServicesFragment extends Fragment{

    static Services service_data;

    Button btnPhone, btnSchedule, btnTransact, btnRateUs;
    ImageView image;
    TextView txtAddress;

    static final LatLng MARK = new LatLng(25.6789175, -100.2494315);
    static private GoogleMap map = null;
    MapFragment fMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service_data = ((InformationServices) getActivity()).getServiceData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_services, container, false);

        if (service_data != null) {

            int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity().getApplicationContext());
            if (status == ConnectionResult.SUCCESS) {

                map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

                UiSettings settings = map.getUiSettings();
                settings.setZoomControlsEnabled(false);
                settings.setCompassEnabled(false);
                settings.setAllGesturesEnabled(false);

                CameraPosition center = CameraPosition.fromLatLngZoom(MARK, 15);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(center);
                Marker mark = map.addMarker(new MarkerOptions()
                        .position(MARK)
                        .title(""));
                map.moveCamera(cameraUpdate);
            }

            image = (ImageView) view.findViewById(R.id.ivTest);
            Ion.with(image).load(service_data.getImageUrl());

            txtAddress = (TextView) view.findViewById(R.id.txtAddress);
            txtAddress.setText(service_data.getAddress());

            btnPhone = (Button) view.findViewById(R.id.btnSPhone);
            btnPhone.setText(service_data.getPhone());
            btnPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isCallServiceUp()) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:"+service_data.getPhone()));
                        startActivity(intent);
                    } else {
                        AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
                        alert.setTitle(R.string.alert_error);
                        alert.setTitle(R.string.alert_callServiceOff);
                        alert.show();
                    }
                }
            });

            btnSchedule = (Button) view.findViewById(R.id.btnSSchedule);
            btnSchedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_EDIT);
                    intent.setType("vnd.android.cursor.item/event");
                    intent.putExtra("beginTime", Calendar.getInstance().getTimeInMillis());
                    intent.putExtra("endTime", Calendar.getInstance().getTimeInMillis() + 60 * 60 * 1000 * 24);
                    intent.putExtra("title", service_data.getName());
                    intent.putExtra("description", service_data.getAddress());
                    startActivity(intent);
                }
            });

            btnTransact = (Button) view.findViewById(R.id.btnSService);
            btnTransact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), SiteServiceActivity.class);
                    intent.putExtra("SERVICE_DATA", service_data);
                    startActivity(intent);
                }
            });

            btnRateUs = (Button) view.findViewById(R.id.btnSRating);
            btnRateUs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), RatingCampaign.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("SERVICE_DATA", service_data);
                    startActivity(intent);
                }
            });
        }

        return view;
    }

    private boolean isCallServiceUp() {
        TelephonyManager tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null && tm.getSimState() == TelephonyManager.SIM_STATE_READY;
    }

    @Override
    public void onDestroyView() {
        try {
            Fragment fragment = (getFragmentManager().findFragmentById(R.id.map));
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.remove(fragment);
            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroyView();
    }
}
