package com.codingdemos.flowers;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DestinationListActivity extends AppCompatActivity
//        implements AsyncHttpResponse.AsyncHttpResponseListener
{

    String status ="status";
    String success ="success";
    String message ="message";

    private static final String TAG = "DestinationListActivity";
//    private SessionManager sm;
    private Intent intent;
    private TextView dl_title_tv;
    private RecyclerView dl_rv;
    private GridLayoutManager gridLayoutManager;
    private ArrayList< SubGroupModel > arrayList;
    private DestinationListAdapter adapter;
    private String group_name = "", menuID = "", name = "", SubRestaurant = "";
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_destination_list);
        initUI();
    }

    private void initUI() {
//        sm = new SessionManager(this);
//        AppUtils.loadTopBarOptions(this, this);
//        dl_title_tv = findViewById(R.id.dl_title_tv);
//        dl_rv = findViewById(R.id.dl_rv);
//        gridLayoutManager = new GridLayoutManager(this, 2);
//        dl_rv.setLayoutManager(gridLayoutManager);
//        arrayList = new ArrayList < > ();
//        getIntentData();
    }

//    private void getIntentData() {
//        intent = getIntent();
//        group_name = intent.getStringExtra(AppStrings.Unique.intent_param_sub_group);
//        String gname = group_name;
//        gname = gname.replaceAll(".", "$0 ");
//        if (group_name.equals("SubRestaurant")) {
//            SubRestaurant = "SubRestaurant";
//            id = Integer.parseInt(intent.getStringExtra("id"));
//            name = intent.getStringExtra("name");
//            dl_title_tv.setText(name);
//        } else {
//            dl_title_tv.setText(gname);
//        }
//        adapter = new DestinationListAdapter(this, arrayList, group_name);
//        dl_rv.setAdapter(adapter);
//        String response = sm.getStringData(AppStrings.Session.api_getKarmaGroupsDev);
//        loadData(response);
//    }

//    private void getKarmaGroupsApiRequest() {
//        if (AppUtils.isConnectingToInternet(this)) {
//            AsyncHttpResponse response = new AsyncHttpResponse(this, true);
//            RequestParams params = new RequestParams();
//            if (sm.getBooleanData(AppStrings.Session.isKarmaMember)) {
//                params.put(AppStrings.ResponseData.memberID, sm.getStringData(AppStrings.Session.memberID));
//                params.put(AppStrings.ResponseData.surName, sm.getStringData(AppStrings.Session.member_surName));
//            }
//            response.getAsyncHttp(RestApis.KarmaGroups.getKarmaGroupsDev, params);
//        } else {
//            AppUtils.alertForNoInternet(this);
//        }
//    }

//    private void loadData(String response) {
//        try {
//            JSONObject jsonObject = new JSONObject(response);
//            int status = jsonObject.getInt(AppStrings.ResponseData.status);
//            String message = jsonObject.getString(AppStrings.ResponseData.message);
//            String imageBaseURL = jsonObject.getString(AppStrings.ResponseData.imageBaseURL);
//            if (status == 1) {
//                JSONArray jsonArray = jsonObject.getJSONArray(AppStrings.ResponseData.offers);
//                arrayList.clear();
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jobj = jsonArray.getJSONObject(i);
//                    if (group_name.equals(jobj.getString(AppStrings.ResponseData.menuName))) {
//                        GroupsModel model = new GroupsModel();
//                        model.setMenuID(jobj.getString(AppStrings.ResponseData.menuID));
//                        model.setMenuName(jobj.getString(AppStrings.ResponseData.menuName));
//                        model.setImage(imageBaseURL + jobj.getString(AppStrings.ResponseData.image));
//                        model.setBgimage(imageBaseURL + jobj.getString(AppStrings.ResponseData.bgimage));
//                        JSONArray jarry = new JSONArray();
//                        if (jobj.getString(AppStrings.ResponseData.menuName).equals(AppStrings.Unique.group_karma_restaurant)) {
//                            JSONArray jarryTemp = jobj.getJSONArray(AppStrings.ResponseData.subGroupNames);
//                            for (int j = 0; j < jarryTemp.length(); j++) {
//                                JSONObject jobTemp = jarryTemp.getJSONObject(j);
//                                if ( jobTemp.getString(AppStrings.ResponseData.postID).equals("0") && !jobTemp.getString("isParent").equals("0") ) {
//                                    jarry.put(jobTemp);
//                                }
//                            }
//                        } else if (SubRestaurant.equals("SubRestaurant")) {
//                            JSONArray jarryTemp = jobj.getJSONArray(AppStrings.ResponseData.subGroupNames);
//                            for (int j = 0; j < jarryTemp.length(); j++) {
//                                JSONObject jobTemp = jarryTemp.getJSONObject(j);
//                                if (!jobTemp.getString(AppStrings.ResponseData.postID).equals("0") && jobTemp.getString("isParent").equals("0")) {
//                                    jarry.put(jobTemp);
//                                }
//                            }
//                        } else {
//                            jarry = jobj.getJSONArray(AppStrings.ResponseData.subGroupNames);
//                        }
//                        ArrayList < SubGroupModel > al = new ArrayList < > ();
//                        al.clear();
//                        Log.d(TAG, "jarry : //////////////////////////////// " + jarry);
//                        for (int j = 0; j < jarry.length(); j++) {
//                            JSONObject job = jarry.getJSONObject(j);
//                            SubGroupModel subModel = new SubGroupModel();
//                            subModel.setMenuID(jobj.getString(AppStrings.ResponseData.menuID));
//                            subModel.setMenuName(jobj.getString(AppStrings.ResponseData.menuName));
//                            subModel.setName(job.getString(AppStrings.ResponseData.name));
//                            subModel.setPostID(job.getString(AppStrings.ResponseData.postID));
//                            subModel.setImage(imageBaseURL + job.getString(AppStrings.ResponseData.image));
//                            subModel.setBgimage(imageBaseURL + job.getString(AppStrings.ResponseData.bgimage));
//                            subModel.setFavouriteStatus(job.getInt(AppStrings.ResponseData.favouriteStatus));
//                            subModel.setId(job.getString("id"));
//                            subModel.setIsParent(job.getString("isParent"));
//                            subModel.setChild(job.getString("child"));
//                            arrayList.add(subModel);
//                        }
//                        model.setSubGroupModelArrayList(al);
//                    } else if (group_name.equals("SubRestaurant")) {
//                        GroupsModel model = new GroupsModel();
//                        model.setMenuID(jobj.getString(AppStrings.ResponseData.menuID));
//                        model.setMenuName(jobj.getString(AppStrings.ResponseData.menuName));
//                        model.setImage(imageBaseURL + jobj.getString(AppStrings.ResponseData.image));
//                        model.setBgimage(imageBaseURL + jobj.getString(AppStrings.ResponseData.bgimage));
//                        JSONArray jarry = new JSONArray();
//                        JSONArray jarryTemp = jobj.getJSONArray(AppStrings.ResponseData.subGroupNames);
//                        for (int j = 0; j < jarryTemp.length(); j++) {
//                            JSONObject jobTemp = jarryTemp.getJSONObject(j);
//                            if (jobj.getString(AppStrings.ResponseData.menuName).equals(AppStrings.Unique.group_karma_restaurant) &&
//                                    !jobTemp.getString(AppStrings.ResponseData.postID).equals("0") &&
//                                    jobTemp.getString("isParent").equals("0") &&
//                                    jobTemp.getString("child").equals(String.valueOf(id))
//                                    ) {
//                                jarry.put(jobTemp);
//                            }
//                        }
//                        ArrayList < SubGroupModel > al = new ArrayList < > ();
//                        al.clear();
//                        for (int j = 0; j < jarry.length(); j++) {
//                            JSONObject job = jarry.getJSONObject(j);
//                            SubGroupModel subModel = new SubGroupModel();
//                            subModel.setMenuID(jobj.getString(AppStrings.ResponseData.menuID));
//                            subModel.setMenuName(jobj.getString(AppStrings.ResponseData.menuName));
//                            subModel.setName(job.getString(AppStrings.ResponseData.name));
//                            subModel.setPostID(job.getString(AppStrings.ResponseData.postID));
//                            subModel.setImage(imageBaseURL + job.getString(AppStrings.ResponseData.image));
//                            subModel.setBgimage(imageBaseURL + job.getString(AppStrings.ResponseData.bgimage));
//                            subModel.setFavouriteStatus(job.getInt(AppStrings.ResponseData.favouriteStatus));
//                            arrayList.add(subModel);
//                        }
//                        model.setSubGroupModelArrayList(al);
//                    } else {
//                        Log.d(TAG, "loadData: Group name not matching");
//                    }
//                }
//                adapter.notifyDataSetChanged();
//            } else {
//                AppUtils.alertWithOk(this, message);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void updateFavourite(int pos, String postId, String menuID, int fav_status) {
//        Log.d(TAG, "updateFavourite() called with: postId = [" + postId + "], menuID = [" + menuID + "], fav_status = [" + fav_status + "]");
//        if (AppUtils.isConnectingToInternet(this)) {
//            AsyncHttpResponse response = new AsyncHttpResponse(this, false);
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put(AppStrings.ResponseData.memberID, sm.getStringData(AppStrings.Session.memberID));
//                jsonObject.put(AppStrings.ResponseData.surName, sm.getStringData(AppStrings.Session.member_surName));
//                jsonObject.put(AppStrings.ResponseData.menuID, menuID);
//                jsonObject.put(AppStrings.ResponseData.postID, postId);
//                jsonObject.put(AppStrings.ResponseData.status, "" + fav_status);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            response.postJson(RestApis.KarmaGroups.updateFavourite, jsonObject);
//        } else {
//            AppUtils.alertForNoInternet(this);
//        }
//    }
//
//    @Override
//    public void onAsyncHttpResponseGet(String response, String url) throws JSONException {
//        Log.d(TAG, "onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
//        if (url.equals(RestApis.KarmaGroups.getKarmaGroupsDev)) {
//            loadData(response);
//        }
//        if (url.equals(RestApis.KarmaGroups.updateFavourite)) {
//            Log.d(TAG, "onAsyncHttpResponseGet: response : " + response);
//            try {
//                JSONObject jsonObject = new JSONObject(response);
//                int status = jsonObject.getInt(AppStrings.ResponseData.status);
//                String message = jsonObject.getString(AppStrings.ResponseData.message);
//                if (status == 1) {
//                    getKarmaGroupsApiRequest();
//                } else {
//                    AppUtils.alertWithOk(this, message);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

}

