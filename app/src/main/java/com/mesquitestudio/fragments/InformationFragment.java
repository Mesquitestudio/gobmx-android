package com.mesquitestudio.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mesquitestudio.adapters.CostAdapter;
import com.mesquitestudio.adapters.ServicesAdapter;
import com.mesquitestudio.gobmx.InformationServices;
import com.mesquitestudio.gobmx.R;
import com.mesquitestudio.models.Cost;
import com.mesquitestudio.models.Document;
import com.mesquitestudio.models.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InformationFragment extends Fragment {

    static Services service_data;

    int groupSize, lastExpandedGroupPosition, currentGroupPosition;

    TextView tvDocumentsTitle;
    LinearLayout llPDocuments, llDescription, llDocuments, llCost;
    ViewGroup.LayoutParams params, paramsInit;
    ImageView btnShowMore, btnShowMore2;
    WebView wvDescription;
    Boolean descriptionFlag = true, documentsFlag = true;

    ServicesAdapter listAdapter;
    ExpandableListView expandableListView;
    List<String> groupList;
    HashMap<String, List<String>> childList;

    List<Cost> costs_list = new ArrayList<Cost>();
    ListView listView;
    List<Document> document_list = new ArrayList<Document>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service_data = ((InformationServices) getActivity()).getServiceData();
    }

    void initParams() {
        params = wvDescription.getLayoutParams();
        params.height = wvDescription.getContentHeight();
        descriptionFlag = true;
        params.height = 200;
        llDescription.setLayoutParams(params);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        if (service_data != null) {

            llPDocuments = (LinearLayout) view.findViewById(R.id.llPDocuments);
            tvDocumentsTitle = (TextView) view.findViewById(R.id.tvDocumentsTitle);

            wvDescription = (WebView) view.findViewById(R.id.wvDesciption);
            wvDescription.setWebViewClient(new WebViewClient());
            wvDescription.setHorizontalScrollbarOverlay(false);
            wvDescription.setVerticalScrollbarOverlay(false);
            wvDescription.setScrollContainer(false);
            wvDescription.setBackgroundColor(Color.TRANSPARENT);
            wvDescription.setHorizontalScrollBarEnabled(false);
            wvDescription.loadData(service_data.getHtml(), "text/html", null);

            paramsInit = wvDescription.getLayoutParams();

            llDescription = (LinearLayout) view.findViewById(R.id.llDescription);
            llDescription.setOnClickListener(new OnClick());

            llCost = (LinearLayout) view.findViewById(R.id.llCost);

            initParams();

            llDocuments = (LinearLayout) view.findViewById(R.id.llDocuments);

            expandableListView = (ExpandableListView) view.findViewById(R.id.elvDocuments);
            expandableListView.setOnGroupCollapseListener(new GroupCollapseListener());
            expandableListView.setOnGroupExpandListener(new GroupExpandListener());
            expandableListView.setOnChildClickListener(new ChildClickListener());
            expandableListView.setOnTouchListener(new TouchListener());
            expandableListView.setOnGroupClickListener(new GroupClickListener());

            listView = (ListView) view.findViewById(R.id.lvCost);

            btnShowMore = (ImageView) view.findViewById(R.id.btnShowMore);
            btnShowMore.setOnClickListener(new OnClick());
            btnShowMore2 = (ImageView) view.findViewById(R.id.btnShowMore2);
            btnShowMore2.setOnClickListener(new OnClick());

            getSData();

            params = expandableListView.getLayoutParams();

            groupSize = (listAdapter.getGroupCount() > 3) ? 3 : listAdapter.getGroupCount();
            params.height = 140 * groupSize;
            llDocuments.setLayoutParams(params);
        }

        return view;
    }

    public void documentsFlag() {

        params = expandableListView.getLayoutParams();
        params.height = expandableListView.getHeight();

        if (documentsFlag) {

            documentsFlag = false;
            groupSize = listAdapter.getGroupCount();
            params.height = 140 * groupSize;

            llDocuments.setLayoutParams(params);
            btnShowMore2.setImageDrawable(getResources().getDrawable(R.drawable.ic_show_less));
        } else {

            if(expandableListView.isGroupExpanded(currentGroupPosition)){
                expandableListView.collapseGroup(currentGroupPosition);
            }
            documentsFlag = true;
            groupSize = listAdapter.getGroupCount();
            groupSize = (groupSize > 3) ? 3 : listAdapter.getGroupCount();
            params.height = groupSize * 140;

            llDocuments.setLayoutParams(params);
            btnShowMore2.setImageDrawable(getResources().getDrawable(R.drawable.ic_showmore));
        }
    }

    class TouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    }

    class ChildClickListener implements ExpandableListView.OnChildClickListener{
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            return false;
        }
    }

    class GroupClickListener implements ExpandableListView.OnGroupClickListener {

        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

            currentGroupPosition = groupPosition;
            params = expandableListView.getLayoutParams();
            params.height = 140 * listAdapter.getGroupCount();
            params.height += 30 * listAdapter.getChildrenCount(groupPosition);
            llDocuments.setLayoutParams(params);
            return false;
        }
    }

    class GroupExpandListener implements ExpandableListView.OnGroupExpandListener{
        @Override
        public void onGroupExpand(int groupPosition) {

            for (int i = 0; i < groupList.size(); i++) {
                if (i != groupPosition) {
                    expandableListView.collapseGroup(i);
                } else {
                    lastExpandedGroupPosition = groupPosition;
                    documentsFlag = false;
                    btnShowMore2.setImageDrawable(getResources().getDrawable(R.drawable.ic_show_less));
                }
            }
        }
    }

    class GroupCollapseListener implements ExpandableListView.OnGroupCollapseListener {
        @Override
        public void onGroupCollapse(int groupPosition) {

            if (currentGroupPosition == groupPosition) {
                params = expandableListView.getLayoutParams();
                params.height = expandableListView.getHeight();

                documentsFlag = false;
                groupSize = listAdapter.getGroupCount();
                params.height = 140 * groupSize;

                llDocuments.setLayoutParams(params);
                btnShowMore2.setImageDrawable(getResources().getDrawable(R.drawable.ic_show_less));
            }
        }
    }

    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.btnShowMore:
                    params = wvDescription.getLayoutParams();
                    params.height = wvDescription.getContentHeight();
                    if (descriptionFlag) {
                        descriptionFlag = false;
                        params.height = params.height * 2;
                        llDescription.setLayoutParams(params);
                        btnShowMore.setImageDrawable(getResources().getDrawable(R.drawable.ic_readless));
                    } else {
                        descriptionFlag = true;
                        params.height = 200;
                        llDescription.setLayoutParams(params);
                        btnShowMore.setImageDrawable(getResources().getDrawable(R.drawable.ic_read_more));
                    }
                    break;

                case R.id.btnShowMore2:
                    documentsFlag();
                    break;
            }
        }
    }

    public void getSData() {

        groupList = new ArrayList<String>();
        childList = new HashMap<String, List<String>>();

        document_list = service_data.getDocumentList();
        costs_list = service_data.getCostsList();

        for (int i = 0; i < document_list.size(); i ++) {
            String name = document_list.get(i).getName();
            String description = document_list.get(i).getDescription();

            groupList.add(name);
            List<String> TempList = new ArrayList<String>();
            TempList.add(description);
            childList.put(name, TempList);
        }
        listAdapter = new ServicesAdapter(getActivity(), groupList, childList);
        expandableListView.setAdapter(listAdapter);

        CostAdapter adapter = new CostAdapter(getActivity(), costs_list);
        listView.setAdapter(adapter);

        params = listView.getLayoutParams();
        params.height = listView.getHeight();
        int costSize = adapter.getCount();
        params.height = 70 * costSize;
        llCost.setLayoutParams(params);
    }
}
