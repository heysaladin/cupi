package com.codingdemos.vacapedia.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.codingdemos.vacapedia.DestinationsModel;
import com.codingdemos.vacapedia.DetailActivity;
import com.codingdemos.vacapedia.FamilyModel;
import com.codingdemos.vacapedia.MainActivity;
import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.NotificationAdapter;
import com.codingdemos.vacapedia.PlanAdapter;
import com.codingdemos.vacapedia.PlanModel;
import com.codingdemos.vacapedia.UserAdapter;
import com.codingdemos.vacapedia.UserModel;
import com.codingdemos.vacapedia.VacaplanActivity;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.codingdemos.vacapedia.rest.RestApis;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VacaplanFragment extends Fragment
        implements
        AsyncHttpResponse.AsyncHttpResponseListener {

    private View view;

    private List< Integer > numbers;

    private JSONObject dataUser = null;
    private JSONArray dataUserFamily = null;
    private ArrayList < String > listTarget;
    private JSONArray dataUserFamilyx = null;
    private JSONArray dataDestinations = null;
    private ArrayList <PlanModel> destinationsArrayListBuffer;
    private ArrayList <PlanModel> destinationsArrayList;
    private NotificationAdapter guestDestinationsAdapter;
    private String imageUrl = null;
    RecyclerView mRecyclerView;
    private PlanModel currentUser;
    private JSONArray jsonArrayUsersFamily;
    private static String imageSelected = "http://blog.vokamo.com/wp-content/uploads/2015/04/IMG_2836-1024x683.jpg";

    private JSONArray objArr = null;

    private JSONArray imagesDes;

    private RequestQueue mQueue;

    private ArrayList<String> imagesReadyArray;

    public static VacaplanFragment newInstance() {
        VacaplanFragment fragment = new VacaplanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_four, container, false);
        dataUserFamilyx = new JSONArray();


//        ArrayList<String>
                imagesReadyArray = new ArrayList<>();

        jsonArrayUsersFamily = new JSONArray();

        imagesDes = new JSONArray();

//        ArrayList < String >
        listTarget = new ArrayList<>();
        numbers = new ArrayList < > ();
        getKarmaGroupsApiRequest();
        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle clicks
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_four, menu);
    }

    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Vacaplan");
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            ((MainActivity) getActivity()).setActionBarTitle("Vacaplan");
            Log.d("LOG", "one");
        }
    }


    private void getKarmaGroupsApiRequest() {
        AsyncHttpResponse response = new AsyncHttpResponse(this, false);
        RequestParams params = new RequestParams();
        response.getAsyncHttp(RestApis.KarmaGroups.vacapediaPlans /* + "/" + ID_FAMILY */, params);
    }

    private void getImageBanner(JSONArray dataPlanParam){
        try {

//            JSONArray

            Log.d("TAG", "imagesDes xxx >>>>>>>>> " + imagesDes);

            for (int j = 0; j < dataPlanParam.length(); j++) {
                JSONObject job = null;
                job = dataPlanParam.getJSONObject(j);
                currentUser = new PlanModel();
                jsonArrayUsersFamily = new JSONArray(job.optString("destinations"));
                currentUser.setDestinations(jsonArrayUsersFamily);
                for (int k = 0; k < jsonArrayUsersFamily.length(); k++) {
                    JSONObject jobk = jsonArrayUsersFamily.getJSONObject(k);
                    final String nowUserId = jobk.optString("destination_id");
//                    RequestQueue mQueue = null;
                    mQueue = Volley.newRequestQueue(getContext());
                    String url = RestApis.KarmaGroups.vacapediaDestinations + "/" + nowUserId;
                    final int finalK = k;
                    final int finalJ = j;
                    final JSONObject finalJob = job;
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // response.optString("image");
                                    try {
                                    if (
                                            imagesDes.length()==0
//                                                    ||
//                                            !imagesDes.getJSONObject(finalK).getString("destination_id").equals(nowUserId)
                                            ) {
                                        JSONObject o = new JSONObject("{" +
                                                    "\"destination_id\": \"" + finalJob.getString("_id") + "\"," +
                                                    "\"images\": [" +
                                                        "{" +
                                                        "\"id\": \"" + 0 + "\"," +
                                                        "\"image\": \"" + response.optString("image") + "\"" +
                                                        "}"
                                                    + "]" +
                                                    "}");
                                        imagesDes.put(o);
                                    } else
                                        if(imagesDes.length()>1)
                                        {

//                                        for (int l = 0; l < imagesDes.length(); l++) {
                                            JSONObject jobl = imagesDes.getJSONObject(finalK);
                                            if (jobl.getString("destination_id").equals(finalJob.getString("_id"))) {
                                                JSONArray imgs = jobl.getJSONArray("images");
                                                JSONObject oIn = new JSONObject("{" +
                                                    "\"id\": \"" + finalK + "\"," +
                                                    "\"image\": \"" + response.optString("image") + "\"" +
                                                    "}");
                                                imgs.put(oIn);
                                            } else {
                                                JSONObject o = new JSONObject("{" +
                                                        "\"destination_id\": \"" + finalJob.getString("_id") + "\"," +
                                                        "\"images\": [" +
                                                        "{" +
                                                        "\"id\": \"" + 0 + "\"," +
                                                        "\"image\": \"" + response.optString("image") + "\"" +
                                                        "}"
                                                        + "]" +
                                                        "}");
                                                imagesDes.put(o);
                                            }
//                                        }

//                                        if (imagesDes.getJSONObject(finalK).getString("destination_id").equals(nowUserId)) {
//                                            Log.d("TAG", "SAME xxx >>>>>>>>> " + imagesDes.getJSONObject(finalK).getString("destination_id"));
//                                            JSONArray imgs = new JSONArray(imagesDes.getJSONObject(finalK).getString("images"));
//                                            JSONObject oIn = new JSONObject("{" +
//                                                    "\"id\": \"" + finalK + "\"," +
//                                                    "\"image\": \"" + response.optString("image") + "\"" +
//                                                    "}");
//                                            imgs.put(oIn);

//                                        }
// else {
//
//                                            JSONObject o = new JSONObject("{" +
//                                                    "\"destination_id\": \"" + nowUserId + "\"," +
//                                                    "\"images\": [" +
//                                                    "{" +
//                                                    "\"id\": \"" + finalK + "\"," +
//                                                    "\"image\": \"" + response.optString("image") + "\"" +
//                                                    "}"
//                                                    + "]" +
//                                                    "}");
//                                            imagesDes.put(o);
//
//                                        }
                                    }

                                        Log.d("TAG", "imagesDes xxx >>>>>>>>> " + imagesDes);

//                                    if (finalJ +1==jsonArrayUsersFamily.length()){
//                                        processData();
//                                    }


                                        if (jsonArrayUsersFamily.length()==finalK+1){
                                            for (int l = 0; l < imagesDes.length(); l++) {
                                                JSONObject imagesReadyObj = imagesDes.getJSONObject(l);
                                                JSONArray imagesReady = imagesReadyObj.getJSONArray("images");
                                                for (int i = 0; i < imagesReady.length(); i++) {
                                                    numbers.add(i);
                                                }
//                                                for (int x = 0; x < imagesReady.length(); x++) {
                                                    Collections.shuffle(numbers);
                                                    JSONObject jim = imagesReady.getJSONObject(numbers.get(l));
//                                                JSONObject jim = imagesReady.getJSONObject(x);
                                                    imagesReadyArray.add(jim.getString("image"));

//                                                }

                                            }
                                        }
                                        if (imagesReadyArray.size()==finalK+1 && jsonArrayUsersFamily.length()==finalK+1){
                                            processData(imagesReadyArray);
                                        }



                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

                    mQueue.add(request);


                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAsyncHttpResponseGet(String response, String url) throws JSONException {
        Log.d("TAG", "onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
        if (url.equals(RestApis.KarmaGroups.vacapediaPlans/* + "/" + ID_FAMILY*/)) {
            Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");

            dataUserFamily = new JSONArray(response);

            JSONArray dataPlan = new JSONArray(response);

            getImageBanner(dataPlan);

            //processData();
        }
//        if (url.contains(RestApis.KarmaGroups.vacapediaDestinations+ "xxx/" /* + ID_FAMILY*/)) {
//            Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
//            dataUser = new JSONObject(response);
//            Log.d("TAG", "dataUser xxx >>>>>>>>> " + dataUser);
//            dataUserFamilyx.put(dataUser);
//            //processData();
//            if (dataUserFamilyx.length()==(jsonArrayUsersFamily.length())) {
//                Log.d("LOG", "jsonArrayUsersFamily.length() >>>>>>>>> " + jsonArrayUsersFamily);
//                Log.d("LOG", "dataUserFamilyx GGG >>>>>>>>> " + dataUserFamilyx);
////                imageSelected = dataUserFamilyx.getString("image");
//
//
//                                for (int jo = 0; jo < dataUserFamilyx.length(); jo++) {
//                                    JSONObject jobDes = dataUserFamilyx.getJSONObject(jo);
//                                    DestinationsModel model = new DestinationsModel();
//                                    model.setName(jobDes.optString("name"));
//                                    model.setImage(jobDes.optString("image"));
//
//                                    model.set_id(jobDes.optString("_id"));
//                                    model.setCategory(jobDes.optString("category"));
//                                    model.setLocation(jobDes.optString("location"));
//                                    model.setDescription(jobDes.optString("description"));
//                                    model.setLatitude(jobDes.optString("latitude"));
//                                    model.setLongitude(jobDes.optString("longitude"));
//                                    model.setAddress(jobDes.optString("address"));
//                                    model.setDistance(jobDes.optString("distance"));
//                                    model.setNote(jobDes.optString("note"));
//                                    model.setCosts(jobDes.optString("costs"));
//                                    model.setTotal_cost(jobDes.optString("total_cost"));
////                                    imageSelected = jobDes.optString("image");
//                                    Log.d("LOG", "imageSelected bbbbbbbbbb >>>>>>>>> " + imageSelected);
//
//                                    JSONObject o = new JSONObject("{" +
//                                            "\"id\": \"" + jobDes.optString("_id") + "\"," +
//                                            "\"image\": \"" + jobDes.optString("image") + "\"" +
//                                            "}");
//                                    objArr.put(o);
//
//
//                                    if (objArr.length()==dataUserFamily.length()){
//                                        String img = "";
//                                        for (int ko = 0; ko < objArr.length(); ko++) {
//                                            JSONObject jko = objArr.getJSONObject(ko);
//                                            if(ko==0){
//
//                                                img = jko.getString("image");
//                                                processDataFull(img);
//                                            }
//                                        }
//                                    }
//
////                                    PlanModel currentUserx = new PlanModel();
////                                    currentUserx = currentUser;
////                                    destinationsArrayList = new ArrayList<>();
////                                    destinationsArrayList.add(currentUserx);
////
//////            destinationsArrayListBuffer = destinationsArrayList;
////                                    PlanAdapter myAdapter = new PlanAdapter(this.getActivity(), destinationsArrayList);
////                                    mRecyclerView.setAdapter(myAdapter);
//////            guestDestinationsAdapter.notifyDataSetChanged();
////
//
//                                }
//
//
//            }
//
////            try{
////            JSONArray dataJson = dataDestinations;
//////            destinationsArrayListBuffer = new ArrayList< >();
////            destinationsArrayList = new ArrayList<>();
//////            destinationsArrayList = getArrayListFromJSONArray(jsonArrayUsersFamily);
////            mRecyclerView = view.findViewById(R.id.plans);
////            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getContext());
////            mRecyclerView.setLayoutManager(mLinearLayoutManager);
////            destinationsArrayList.clear();
////            Log.d("TAG", "dataUserFamily >>>>>>>>> " + dataUserFamily);
////            for (int j = 0; j < dataUserFamily.length(); j++) {
////                JSONObject job = null;
////                job = dataUserFamily.getJSONObject(j);
////                currentUser = new PlanModel();
////                currentUser.set_id(job.optString("_id"));
////                currentUser.setTitle(job.optString("title"));
////                currentUser.setContent(job.optString("content"));
////                currentUser.setBody_copy(job.optString("body_copy"));
////                currentUser.setTarget_date(job.optString("target_date"));
////                currentUser.setTarget_time(job.optString("target_time("));
////                jsonArrayUsersFamily = new JSONArray(job.optString("destinations"));
////                currentUser.setDestinations(jsonArrayUsersFamily);
////
////                currentUser.setImage(imageSelected);
////
////
////                currentUser.setCost("0");
////
//////                if (imageSelected != null) {
////                destinationsArrayList.add(currentUser);
//////                }
////
////
////            }
////
//////            destinationsArrayListBuffer = destinationsArrayList;
////            PlanAdapter myAdapter = new PlanAdapter(this.getActivity(), destinationsArrayList);
////            mRecyclerView.setAdapter(myAdapter);
//////            guestDestinationsAdapter.notifyDataSetChanged();
////
////
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//
//
//    }
    }


    private void processData(ArrayList<String> imagesReadyArray) {
        Log.d("TAG", "imagesReadyArray VVVVVVVVVV >>>>>>>>> " + imagesReadyArray);
        try {

//                        JSONArray dataJson = dataDestinations;
//            destinationsArrayListBuffer = new ArrayList< >();
            destinationsArrayList = new ArrayList<>();
//            destinationsArrayList = getArrayListFromJSONArray(jsonArrayUsersFamily);
            mRecyclerView = view.findViewById(R.id.plans);
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getContext());
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            destinationsArrayList.clear();
            Log.d("TAG", "dataUserFamily >>>>>>>>> " + dataUserFamily);
            for (int j = 0; j < dataUserFamily.length(); j++) {
                JSONObject job = null;
                job = dataUserFamily.getJSONObject(j);
                currentUser = new PlanModel();
                currentUser.set_id(job.optString("_id"));
                currentUser.setTitle(job.optString("title"));
                currentUser.setContent(job.optString("content"));
                currentUser.setBody_copy(job.optString("body_copy"));
                currentUser.setTarget_date(job.optString("target_date"));
                currentUser.setTarget_time(job.optString("target_time("));
                jsonArrayUsersFamily = new JSONArray(job.optString("destinations"));
                currentUser.setDestinations(jsonArrayUsersFamily);

//                final String nowImage = "";
//
//
//                for (int k = 0; k < jsonArrayUsersFamily.length(); k++) {
//                    JSONObject jobk = jsonArrayUsersFamily.getJSONObject(k);
//                    String nowUserId = jobk.optString("destination_id");
//                    AsyncHttpResponse response = new AsyncHttpResponse(this, false);
//                    RequestParams params = new RequestParams();
//                    response.getAsyncHttp(RestApis.KarmaGroups.vacapediaDestinations + "/" + nowUserId, params);
//
//
//                    Log.d("LOG", "k >>>>>>>>> " + k);
////                    if (k==(jsonArrayUsersFamily.length()-1)){
////                        Log.d("LOG", "jsonArrayUsersFamily.length()-1 >>>>>>>>> " + jsonArrayUsersFamily);
////                        Log.d("LOG", "dataUserFamilyx GGG >>>>>>>>> " + dataUserFamilyx);
////                        new Handler().postDelayed(new Runnable() {
////                            @Override
////                            public void run() {
//
//
//
//
////                                for (int jo = 0; jo < dataUserFamilyx.length(); jo++) {
////                                    JSONObject jobDes = dataUserFamilyx.getJSONObject(jo);
////                                    DestinationsModel model = new DestinationsModel();
////                                    model.setName(jobDes.optString("name"));
////                                    model.setImage(jobDes.optString("image"));
////
////                                    model.set_id(jobDes.optString("_id"));
////                                    model.setCategory(jobDes.optString("category"));
////                                    model.setLocation(jobDes.optString("location"));
////                                    model.setDescription(jobDes.optString("description"));
////                                    model.setLatitude(jobDes.optString("latitude"));
////                                    model.setLongitude(jobDes.optString("longitude"));
////                                    model.setAddress(jobDes.optString("address"));
////                                    model.setDistance(jobDes.optString("distance"));
////                                    model.setNote(jobDes.optString("note"));
////                                    model.setCosts(jobDes.optString("costs"));
////                                    model.setTotal_cost(jobDes.optString("total_cost"));
////                                    imageSelected = jobDes.optString("image");
////                                    Log.d("LOG", "imageSelected bbbbbbbbbb >>>>>>>>> " + imageSelected);
////                                }
//
//
//
//
//
//
//                                //loadUsers();
//
////                                if (listTarget.size()==dataUserFamilyx.length()) {
////
////                                    Log.d("LOG", "listTarget numbers >>>>>>>>> " + listTarget.size());
////                                    for (int i = 0; i < listTarget.size(); i++) {
////                                        numbers.add(i);
////                                    }
////                                    Collections.shuffle(numbers);
////                                    Log.d("LOG", "createRandomList numbers >>>>>>>>> " + numbers);
////                                    Log.d("LOG", "listTarget X >>>>>>>>> " + listTarget);
////                                    Log.d("LOG", "listTarget.get(numbers.get(0)) X >>>>>>>>> " + listTarget.get(numbers.get(0)));
////                                    imageSelected = listTarget.get(numbers.get(0));
//////                                    nowImage = listTarget.get(numbers.get(0));
////                                    Log.d("LOG", "imageSelected X >>>>>>>>> " + imageSelected);
////
////
////                                }
//
//
////                            }
////                        }, 1000);
////                    }
////                    Log.d("LOG", "imageSelected vvv >>>>>>>>> " + imageSelected);
//
//
//                }
////                Log.d("LOG", "imageSelected hhhhhhhh >>>>>>>>> " + imageSelected);

                currentUser.setImage(imagesReadyArray.get(j));


                currentUser.setCost("0");

//                if (imageSelected != null) {
                    destinationsArrayList.add(currentUser);
//                }


            }
//
//            destinationsArrayListBuffer = destinationsArrayList;
            PlanAdapter myAdapter = new PlanAdapter(this.getActivity(), destinationsArrayList);
            mRecyclerView.setAdapter(myAdapter);
//            guestDestinationsAdapter.notifyDataSetChanged();


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

//
//    private void processDataFull(String img) {
//        try {
//
////                        JSONArray dataJson = dataDestinations;
////            destinationsArrayListBuffer = new ArrayList< >();
//            destinationsArrayList = new ArrayList<>();
////            destinationsArrayList = getArrayListFromJSONArray(jsonArrayUsersFamily);
//            mRecyclerView = view.findViewById(R.id.plans);
//            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getContext());
//            mRecyclerView.setLayoutManager(mLinearLayoutManager);
//            destinationsArrayList.clear();
//            Log.d("TAG", "dataUserFamily >>>>>>>>> " + dataUserFamily);
//            for (int j = 0; j < dataUserFamily.length(); j++) {
//                JSONObject job = null;
//                job = dataUserFamily.getJSONObject(j);
//                currentUser = new PlanModel();
//                currentUser.set_id(job.optString("_id"));
//                currentUser.setTitle(job.optString("title"));
//                currentUser.setContent(job.optString("content"));
//                currentUser.setBody_copy(job.optString("body_copy"));
//                currentUser.setTarget_date(job.optString("target_date"));
//                currentUser.setTarget_time(job.optString("target_time("));
//                jsonArrayUsersFamily = new JSONArray(job.optString("destinations"));
//                currentUser.setDestinations(jsonArrayUsersFamily);
//
////                final String nowImage = "";
//
//
//                for (int k = 0; k < jsonArrayUsersFamily.length(); k++) {
//                    JSONObject jobk = jsonArrayUsersFamily.getJSONObject(k);
//                    String nowUserId = jobk.optString("destination_id");
//                    AsyncHttpResponse response = new AsyncHttpResponse(this, false);
//                    RequestParams params = new RequestParams();
//                    response.getAsyncHttp(RestApis.KarmaGroups.vacapediaDestinations + "/" + nowUserId, params);
//
//
//                    Log.d("LOG", "k >>>>>>>>> " + k);
////                    if (k==(jsonArrayUsersFamily.length()-1)){
////                        Log.d("LOG", "jsonArrayUsersFamily.length()-1 >>>>>>>>> " + jsonArrayUsersFamily);
////                        Log.d("LOG", "dataUserFamilyx GGG >>>>>>>>> " + dataUserFamilyx);
////                        new Handler().postDelayed(new Runnable() {
////                            @Override
////                            public void run() {
//
//
//
//
////                                for (int jo = 0; jo < dataUserFamilyx.length(); jo++) {
////                                    JSONObject jobDes = dataUserFamilyx.getJSONObject(jo);
////                                    DestinationsModel model = new DestinationsModel();
////                                    model.setName(jobDes.optString("name"));
////                                    model.setImage(jobDes.optString("image"));
////
////                                    model.set_id(jobDes.optString("_id"));
////                                    model.setCategory(jobDes.optString("category"));
////                                    model.setLocation(jobDes.optString("location"));
////                                    model.setDescription(jobDes.optString("description"));
////                                    model.setLatitude(jobDes.optString("latitude"));
////                                    model.setLongitude(jobDes.optString("longitude"));
////                                    model.setAddress(jobDes.optString("address"));
////                                    model.setDistance(jobDes.optString("distance"));
////                                    model.setNote(jobDes.optString("note"));
////                                    model.setCosts(jobDes.optString("costs"));
////                                    model.setTotal_cost(jobDes.optString("total_cost"));
////                                    imageSelected = jobDes.optString("image");
////                                    Log.d("LOG", "imageSelected bbbbbbbbbb >>>>>>>>> " + imageSelected);
////                                }
//
//
//
//
//
//
//                    //loadUsers();
//
////                                if (listTarget.size()==dataUserFamilyx.length()) {
////
////                                    Log.d("LOG", "listTarget numbers >>>>>>>>> " + listTarget.size());
////                                    for (int i = 0; i < listTarget.size(); i++) {
////                                        numbers.add(i);
////                                    }
////                                    Collections.shuffle(numbers);
////                                    Log.d("LOG", "createRandomList numbers >>>>>>>>> " + numbers);
////                                    Log.d("LOG", "listTarget X >>>>>>>>> " + listTarget);
////                                    Log.d("LOG", "listTarget.get(numbers.get(0)) X >>>>>>>>> " + listTarget.get(numbers.get(0)));
////                                    imageSelected = listTarget.get(numbers.get(0));
//////                                    nowImage = listTarget.get(numbers.get(0));
////                                    Log.d("LOG", "imageSelected X >>>>>>>>> " + imageSelected);
////
////
////                                }
//
//
////                            }
////                        }, 1000);
////                    }
////                    Log.d("LOG", "imageSelected vvv >>>>>>>>> " + imageSelected);
//
//
//                }
////                Log.d("LOG", "imageSelected hhhhhhhh >>>>>>>>> " + imageSelected);
//
//
//
//                currentUser.setImage(img);
//
//
//                currentUser.setCost("0");
//
////                if (imageSelected != null) {
////                    destinationsArrayList.add(currentUser);
////                }
//
//
//            }
////
////            destinationsArrayListBuffer = destinationsArrayList;
//            PlanAdapter myAdapter = new PlanAdapter(this.getActivity(), destinationsArrayList);
//            mRecyclerView.setAdapter(myAdapter);
////            guestDestinationsAdapter.notifyDataSetChanged();
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }

//    public void loadUsers() {
//
//        try {
//
////        if(k<jsonArrayUsersFamily.length()-1 ) {
//            Log.d("TAG", "dataUserFamily lengthx >>>>>>>>> " + dataUserFamilyx.length());
////            if (dataUserFamily.length() == (jsonArrayUsersFamily.length()-1)) {
////                            try {
//////                    destinationsArrayList = new ArrayList<>();
//////                    destinationsArrayList.clear();
//            for (int jo = 0; jo < dataUserFamilyx.length(); jo++) {
//                JSONObject jobDes = dataUserFamilyx.getJSONObject(jo);
//                DestinationsModel model = new DestinationsModel();
//                model.setName(jobDes.optString("name"));
//                model.setImage(jobDes.optString("image"));
//
//                model.set_id(jobDes.optString("_id"));
//                model.setCategory(jobDes.optString("category"));
//                model.setLocation(jobDes.optString("location"));
//                model.setDescription(jobDes.optString("description"));
//                model.setLatitude(jobDes.optString("latitude"));
//                model.setLongitude(jobDes.optString("longitude"));
//                model.setAddress(jobDes.optString("address"));
//                model.setDistance(jobDes.optString("distance"));
//                model.setNote(jobDes.optString("note"));
//                model.setCosts(jobDes.optString("costs"));
//                model.setTotal_cost(jobDes.optString("total_cost"));
//
//                listTarget.add(jobDes.optString("image"));
//                Log.d("LOG", "jobDes VVVVVVVV >>>>>>>>> " + jobDes);
//                Log.d("LOG", "listTarget AAAAAAAAA >>>>>>>>> " + listTarget);
//
////                        destinationsArrayList.add(model);
//
//                Log.d("LOG", "dataUserFamily.length() OOO >>>>>>>>> " + dataUserFamily.length());
//
//
//
//
//
////                        if (j==dataUserFamily.length()-1) {
////                                    RequestOptions options = new RequestOptions();
////                                    options.centerCrop();
//////                            JSONObject jobRand = dataUserFamily.getJSONObject(0);
////                                    Log.d("LOG", "jobRand.getString(\"image\") OOOOOOO >>>>>>>>> " + job.getString("image"));
////                                    Glide.with(mContext).load(job.getString("image").replace(" ", "%20")).apply(options).into(holder.mImage);
//////            Glide.with(mContext).load(dataUser.getString("image").replace(" ", "%20")).apply(options).into(holder.mImage);
//
//
//
//
//            }
//
//
//
//    } catch (JSONException e) {
//        e.printStackTrace();
//    }
//
//
//        }
////    }


}