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
import com.android.volley.toolbox.RequestFuture;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;

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

    private static JSONArray imagesDes;

    private RequestQueue mQueue;

    private ArrayList<String> imagesReadyArray;

    private List<String> imagesBuffer;

    private List<String> stringImages;

    private static int jtotal = 0;

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



//        stringImages = new ArrayList<>();
//        stringImages.clear();

//        List<String>
                imagesBuffer = new ArrayList<>();

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

    private void desRequest(String url) {
        AsyncHttpResponse response = new AsyncHttpResponse(this, false);
        RequestParams params = new RequestParams();
        response.getAsyncHttp(url, params);
    }

    private void getImageBanner(final JSONArray dataPlanParam){
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

//            JSONArray

//            Log.d("TAG", "imagesDes xxx >>>>>>>>> " + imagesDes);

            for (int j = 0; j < dataPlanParam.length(); j++) {
                JSONObject job = null;
                job = dataPlanParam.getJSONObject(j);
                currentUser = new PlanModel();
                currentUser.set_id(job.optString("_id"));
                currentUser.setTitle(job.optString("title"));
                currentUser.setContent(job.optString("content"));
                currentUser.setBody_copy(job.optString("body_copy"));
                currentUser.setTarget_date(job.optString("target_date"));
                currentUser.setTarget_time(job.optString("target_time("));
                jsonArrayUsersFamily = new JSONArray(job.optString("destinations"));
                currentUser.setDestinations(jsonArrayUsersFamily);

                final int finalJ = j;
                final JSONObject finalJob = job;

                imagesBuffer.clear();


//                if (imagesDes.length()<1){
//                    JSONObject o = new JSONObject("{" +
//                            "\"destination_id\": \"" + finalJob.getString("_id") + "\"," +
//                            "\"images\": [" +
//                            //                            "{" +
//                            //                            "\"id\": \"" + 0 + "\"," +
//                            //                            "\"image\": \"" + response.optString("image") + "\"" +
//                            //                            "}"
//                            //                            +
//                            "]" +
//                            "}");
//                    imagesDes.put(o);
//                } else {
//                    for (int l = 0; l < imagesDes.length(); l++) {
//                        JSONObject jobl = imagesDes.getJSONObject(l);
//                        list.add(jobl.getString("destination_id"));
//                         Log.d("TAG", "list >>>>>>>>> " + list);
//                        if (!list.contains(finalJob.getString("_id"))) {
////                            Log.d("TAG", "contain list >>>>>>>>> " + !list.contains(finalJob.getString("_id")));
//                            JSONObject o = new JSONObject("{" +
//                                    "\"destination_id\": \"" + finalJob.getString("_id") + "\"," +
//                                    "\"images\": [" +
//                                    //                            "{" +
//                                    //                            "\"id\": \"" + 0 + "\"," +
//                                    //                            "\"image\": \"" + response.optString("image") + "\"" +
//                                    //                            "}"
//                                    //                            +
//                                    "]" +
//                                    "}");
//                            imagesDes.put(o);
//                        }
//
//                    }
//                }


//                stringImages.clear();
                stringImages = new ArrayList<>();
//                stringImages.clear();

//                Log.d("TAG", "jsonArrayUsersFamily.length() JJJJJJ >>>>>>>>> " + jsonArrayUsersFamily.length());
//                jtotal += jsonArrayUsersFamily.length();

                for (int k = 0; k < jsonArrayUsersFamily.length(); k++) {
                    JSONObject jobk = jsonArrayUsersFamily.getJSONObject(k);
                    String imgNow = jobk.optString("image");
                    stringImages.add(imgNow);
//                    final String nowUserId = jobk.optString("destination_id");
//                    RequestQueue mQueue = null;
                    //mQueue = Volley.newRequestQueue(this.getActivity());
//                    String url = RestApis.KarmaGroups.vacapediaDestinations + "/" + nowUserId;
                    //desRequest(url);

                    final int finalK = k;





//                    final
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "", null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
//                                    // response.optString("image");
//
                                    Log.d("TAG", "finalK >>>>>>>>> " + finalK);
                                    Log.d("TAG", "response.getString(\"image\") >>>>>>>>> " + response.optString("image"));


                                    try {
                                    JSONObject o = new JSONObject("{" +
                                                "\"inc\": \"" + finalK + "\"," +
                                                "\"img\": \"" + response.optString("image") + "\"" +
                                                "}");
                                    imagesDes.put(o);


//                                    if (finalK == 0) {
//                                        imagesReadyArray.add(response.optString("image"));
//                                    }

//                                    if (finalJ==dataPlanParam.length()-1) {
//                                        for (int l = 0; l < imagesDes.length(); l++) {
//                                            Log.d("TAG", "imagesDes.length() >>>>>>>>> " + imagesDes.length());
//                                            Log.d("TAG", "imagesDes xxx >>>>>>>>> " + imagesDes);
//                                            JSONObject jobl = imagesDes.getJSONObject(l);
//                                            if (Integer.parseInt( jobl.getString("inc") )==finalJ){
//                                                stringImages.add(jobl.getString("img"));
//                                                Log.d("TAG", "stringImages sss >>>>>>>>> " + stringImages);
//                                            }
//                                        }
//                                    }



                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    if (imagesReadyArray.size() == dataPlanParam.length()) {
                                        processData(imagesReadyArray);
                                    }

//                                    try {
////
//////                                        if(stringImages.size()<(jsonArrayUsersFamily.length())) {
//                                            stringImages.add(response.getString("image"));
////                                            Log.d("TAG", "response.getString(\"image\") >>>>>>>>> " + response.getString("image"));
//////                                        } else {
//
//
//                                        Log.d("TAG", "stringImages >>>>>>>>> " + stringImages);
//
//                                        Log.d("TAG", "stringImages size >>>>>>>>> " + stringImages.size());
//                                            Log.d("TAG", "jsonArrayUsersFamily length >>>>>>>>> " + (jsonArrayUsersFamily.length()));
////
//                                        if(stringImages.size()==jsonArrayUsersFamily.length()) {
////                                        if (finalK+1 == jsonArrayUsersFamily.length()) {
////
//////                                            for (int i = 0; i < jsonArrayUsersFamily.length(); i++) {
//////                                                numbers.add(i);
//////                                            }
//////                                            Collections.shuffle(numbers);
//////
//////                                            Log.d("TAG", "numbers >>>>>>>>> " + numbers);
////
////                                            Log.d("TAG", "finalK >>>>>>>>> " + finalK);
////
////                                            Log.d("TAG", "stringImages >>>>>>>>> " + stringImages);
////
////                                            Log.d("TAG", "stringImages >>>>>>>>> " + stringImages.size());
////
////                                            Log.d("TAG", "jsonArrayUsersFamily >>>>>>>>> " + (jsonArrayUsersFamily.length()));
////
//////                                            if (stringImages.size() == (jsonArrayUsersFamily.length())) {
//////
////////                                                Log.d("TAG", "stringImages >>>>>>>>> " + stringImages);
//                                                Log.d("TAG", "stringImages.get(0) >>>>>>>>> " + stringImages.get(0));
////                                                imagesReadyArray.add(stringImages.get(0));
//////
//////
////////                                                imagesReadyArray.add(response.optString("image"));
////                                                if (imagesReadyArray.size() == dataPlanParam.length()) {
////                                                    processData(imagesReadyArray);
////                                                }
//////
//////                                            }
////
//                                            stringImages.clear();
//                                        }
//////
//
//                                        }


                                    /*
                                        if (finalK == 0) {
                                            imagesReadyArray.add(response.optString("image"));
                                        }
                                        if (imagesReadyArray.size() == dataPlanParam.length()) {
                                            processData(imagesReadyArray);
                                        }
                                        */


//                                    if (
//                                            imagesDes.length()<1
//////                                                    ||
//////                                            !imagesDes.getJSONObject(finalK).getString("destination_id").equals(nowUserId)
//                                            ) {
//                                        JSONObject o = new JSONObject("{" +
//                                                    "\"destination_id\": \"" + finalJob.getString("_id") + "\"," +
//                                                    "\"images\": [" +
//                                                        "{" +
//                                                        "\"id\": \"" + 0 + "\"," +
//                                                        "\"image\": \"" + response.optString("image") + "\"" +
//                                                        "}"
//                                                    + "]" +
//                                                    "}");
//                                        imagesDes.put(o);
//                                    }
//                                    else
////                                        if(imagesDes.length()>1)
//                                        {
//                                            Log.d("TAG", "finalK >>>>>>>>> " + finalK);


//                                                                                    if (
//                                                    imagesDes.length()<1
////                                                    ||
////                                            !jobl.getString("destination_id").equals(finalJob.getString("_id"))
//////                                            !imagesDes.getJSONObject(finalK).getString("destination_id").equals(nowUserId)
//                                                    ) {
//                                                JSONObject o = new JSONObject("{" +
//                                                        "\"destination_id\": \"" + finalJob.getString("_id") + "\"," +
//                                                        "\"images\": [" +
//                                                        "{" +
//                                                        "\"id\": \"" + 0 + "\"," +
//                                                        "\"image\": \"" + response.optString("image") + "\"" +
//                                                        "}"
//                                                        + "]" +
//                                                        "}");
//                                                imagesDes.put(o);
//                                            } else {


//
//
//                                        for (int l = 0; l < imagesDes.length(); l++) {
//
////                                            Log.d("TAG", "imagesDes.length() >>>>>>>>> " + imagesDes.length());
////                                            Log.d("TAG", "imagesDes xxx >>>>>>>>> " + imagesDes);
//
//                                            JSONObject jobl = imagesDes.getJSONObject(l);
////                                            Log.d("TAG", "jobl.getString(\"destination_id\") >>>>>>>>> " + jobl.getString("destination_id"));
////                                            Log.d("TAG", "finalJob.getString(\"_id\") >>>>>>>>> " + finalJob.getString("_id"));
////                                            if (
////                                                    imagesDes.length()<1
//////                                                    ||
//////                                            !jobl.getString("destination_id").equals(finalJob.getString("_id"))
////////                                            !imagesDes.getJSONObject(finalK).getString("destination_id").equals(nowUserId)
////                                                    ) {
////                                                JSONObject o = new JSONObject("{" +
////                                                        "\"destination_id\": \"" + finalJob.getString("_id") + "\"," +
////                                                        "\"images\": [" +
////                                                        "{" +
////                                                        "\"id\": \"" + 0 + "\"," +
////                                                        "\"image\": \"" + response.optString("image") + "\"" +
////                                                        "}"
////                                                        + "]" +
////                                                        "}");
////                                                imagesDes.put(o);
////                                            }
////                                            else
//                                                if (jobl.getString("destination_id").equals(finalJob.getString("_id"))) {
//                                                JSONArray imgs = jobl.getJSONArray("images");
//                                                JSONObject oIn = new JSONObject("{" +
//                                                    "\"id\": \"" + (imgs.length()) + "\"," +
//                                                    "\"image\": \"" + response.optString("image") + "\"" +
//                                                    "}");
//                                                imgs.put(oIn);
//                                            }
//
//                                            Log.d("TAG", "imagesDes.length() zzz >>>>>>>>> " + (imagesDes.length()-1));
////                                            Log.d("TAG", "dataPlanParam.length() OOOOOOOOOOOOOOOOOO >>>>>>>>> " + dataPlanParam.length());
//                                            Log.d("TAG", "finalJ yyy >>>>>>>>> " + (finalJ+1));
//                                            if ( imagesDes.length()-1 == finalJ+1 ) {
//                                                Log.d("TAG", "imagesDes OOOOOOOOOOOOOOOOOO >>>>>>>>> " + imagesDes);
//                                                for (int lor = 0; lor < imagesDes.length(); lor++) {
//                                                    JSONObject imagesReadyObj = imagesDes.getJSONObject(lor);
//                                                    JSONArray imagesReady = imagesReadyObj.getJSONArray("images");
////                                                    imagesBuffer.clear();
//                                                    Log.d("TAG", "lor >>>>>>>>> " + lor);
//                                                    for (int in = 0; in < imagesReady.length(); in++) {
//                                                        JSONObject jim = imagesReady.getJSONObject(in);
////                                                        imagesBuffer.add(jim.getString("image"));
//                                                        Log.d("TAG", "in >>>>>>>>> " + in);
//                                                        Log.d("TAG", "jim.getString(\"image\") CCCCCC >>>>>>>>> " + jim.getString("image"));
//                                                        if(
////                                                                lor == 0 &&
//                                                                in == 0) {
//                                                            imagesReadyArray.add(jim.getString("image"));
//                                                        }
//                                                    }
//                                                }
////                                                Log.d("TAG", "imagesBuffer CCCCCC >>>>>>>>> " + imagesBuffer);
////                                                imagesReadyArray.add(imagesBuffer.get(0));
////                                                Log.d("TAG", "imagesReadyArray KKKKKKKKKKKKKKKKKK >>>>>>>>> " + imagesReadyArray);
//
//                                            }
////                                            if (imagesReadyArray.size() == dataPlanParam.length()) {
////                                                processData(imagesReadyArray);
////                                            }
//
//
////                                            if ( finalJ+1 == dataPlanParam.length() ){
////                                                for (int lor = 0; lor < imagesDes.length(); lor++) {
////                                                    JSONObject imagesReadyObj = imagesDes.getJSONObject(lor);
////                                                    JSONArray imagesReady = imagesReadyObj.getJSONArray("images");
//////                                                    for (int i = 0; i < imagesReady.length(); i++) {
//////                                                        numbers.add(i);
//////                                                    }
////////                                                for (int x = 0; x < imagesReady.length(); x++) {
//////                                                    Collections.shuffle(numbers);
//////                                                    JSONObject jim = imagesReady.getJSONObject(numbers.get(0));
//////                                                JSONObject jim = imagesReady.getJSONObject(x);
//////                                                    imagesReadyArray.add(jim.getString("image"));
////
////                                                    List<String> imagesBuffer = new ArrayList<>();
////                                                    for (int in = 0; in < imagesReady.length(); in++) {
////                                                        JSONObject jim = imagesReady.getJSONObject(in);
////                                                        imagesBuffer.add(jim.getString("image"));
//////                                                        numbers.add(i);
////
////
////                                                        if ( lor+1 == imagesDes.length() ) {
////                                                            imagesReadyArray.add(imagesBuffer.get(0));
////                                                            if (imagesReadyArray.size()==finalJ+1){
////                                                                processData(imagesReadyArray);
////                                                            }
////                                                        }
////
////                                                    }
//////                                                    for (int i = 0; i < imagesReady.length(); i++) {
//////                                                        numbers.add(i);
//////                                                    }
//////                                                    Collections.shuffle(numbers);
//////                                                    JSONObject jim = imagesBuffer.get(0);
////
////
//////                                                }
////
////                                                }
////                                            }
//
////                                            else {
////                                                JSONObject o = new JSONObject("{" +
////                                                        "\"destination_id\": \"" + finalJob.getString("_id") + "\"," +
////                                                        "\"images\": [" +
////                                                        "{" +
////                                                        "\"id\": \"" + 0 + "\"," +
////                                                        "\"image\": \"" + response.optString("image") + "\"" +
////                                                        "}"
////                                                        + "]" +
////                                                        "}");
////                                                imagesDes.put(o);
////                                            }
////                                        }
////
//////                                        if (imagesDes.getJSONObject(finalK).getString("destination_id").equals(nowUserId)) {
//////                                            Log.d("TAG", "SAME xxx >>>>>>>>> " + imagesDes.getJSONObject(finalK).getString("destination_id"));
//////                                            JSONArray imgs = new JSONArray(imagesDes.getJSONObject(finalK).getString("images"));
//////                                            JSONObject oIn = new JSONObject("{" +
//////                                                    "\"id\": \"" + finalK + "\"," +
//////                                                    "\"image\": \"" + response.optString("image") + "\"" +
//////                                                    "}");
//////                                            imgs.put(oIn);
////
//////                                        }
////// else {
//////
//////                                            JSONObject o = new JSONObject("{" +
//////                                                    "\"destination_id\": \"" + nowUserId + "\"," +
//////                                                    "\"images\": [" +
//////                                                    "{" +
//////                                                    "\"id\": \"" + finalK + "\"," +
//////                                                    "\"image\": \"" + response.optString("image") + "\"" +
//////                                                    "}"
//////                                                    + "]" +
//////                                                    "}");
//////                                            imagesDes.put(o);
//////
//////                                        }
//                                    }
//
//                                        Log.d("TAG", "imagesDes.length() >>>>>>>>> " + imagesDes.length());
//                                        Log.d("TAG", "imagesDes xxx >>>>>>>>> " + imagesDes);
//
////                                    if (finalJ +1==jsonArrayUsersFamily.length()){
////                                        processData();
////                                    }
//
//

//
//
//
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });


//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//
//                    if (finalK == 0) {
                        //mQueue.add(request);


                    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                    final RequestQueue mRequestQueue;
                    // Setup instance
                    mRequestQueue = Volley.newRequestQueue(this.getActivity());
                    String requestUrl = "";
                    JsonObjectRequest geoInfoRequest = new JsonObjectRequest(requestUrl, null, new Response.Listener < JSONObject > () {
                        @Override
                        public void onResponse(JSONObject response) {
//                            Log.d("TAG", "finalJ >>>>>>>>> " + finalJ);
//                            Log.d("TAG", "response.getString(\"image\") >>>>>>>>> " + response.optString("image"));
                            /*
                             */
                                        if (finalK == 0) {
                                            imagesReadyArray.add(response.optString("image"));
                                        }
                                        if (imagesReadyArray.size() == dataPlanParam.length()) {
                                            processData(imagesReadyArray);
                                        }

/*
                            try {
                                JSONObject o = new JSONObject("{" +
                                        "\"inc\": " + finalJ + "," +
                                        "\"img\": \"" + response.optString("image") + "\"" +
                                        "}");
                                imagesDes.put(o);

//                                Log.d("TAG", "imagesDes xxx >>>>>>>>> " + imagesDes);

                                if(
//                                                finalJ==dataPlanParam.length()-1 &&
                                        imagesDes.length()==jtotal){
                                    Log.d("TAG", "imagesDes.length() QQQQQQQQQQQQQQQQQQQQQQQQQ >>>>>>>>> " + imagesDes.length());
                                    for (int l = 0; l < imagesDes.length(); l++) {
//                                        Log.d("TAG", "jsonArrayUsersFamily.length() JEK >>>>>>>>> " + jsonArrayUsersFamily.length());
//                                    Log.d("TAG", "imagesDes.length() >>>>>>>>> " + imagesDes.length());
//                                    Log.d("TAG", "imagesDes xxx >>>>>>>>> " + imagesDes);
                                        JSONObject jobl = imagesDes.getJSONObject(l);
//                                Log.d("TAG", "jobl >>>>>>>>> " + jobl);
                                        if (jobl.getInt("inc") == l) {
                                Log.d("TAG", "YES");
                                            stringImages.add(jobl.getString("img"));
                                            Log.d("TAG", "stringImages sss >>>>>>>>> " + stringImages);
                                            if(jsonArrayUsersFamily.length()==stringImages.size()){
                                                stringImages.clear();
                                                Log.d("TAG", "stringImages clear >>>>>>>>> " + stringImages);
                                            }
                                        } else {
                                            Log.d("TAG", "NO");
                                        }
                                    }
                                }


//                                    if (finalK == 0) {
//                                        imagesReadyArray.add(response.optString("image"));
////                                    }
//
////                                    if (finalJ==dataPlanParam.length()-1) {
//                                for (int l = 0; l < imagesDes.length(); l++) {
////                                    Log.d("TAG", "imagesDes.length() >>>>>>>>> " + imagesDes.length());
////                                    Log.d("TAG", "imagesDes xxx >>>>>>>>> " + imagesDes);
//                                    JSONObject jobl = imagesDes.getJSONObject(l);
////                                Log.d("TAG", "jobl >>>>>>>>> " + jobl);
//                                    if (jobl.getInt("inc")==l){
//                                        stringImages.add(jobl.getString("img"));
//                                        Log.d("TAG", "stringImages sss >>>>>>>>> " + stringImages);
//
////                                        if(stringImages.size()==jsonArrayUsersFamily.length()) {
////                                            JSONObject ob = new JSONObject("{" +
////                                                    "\"id\": \"" + finalJ + "\"," +
////                                                    "\"images\": [" + "]" +
////                                                    "}");
////                                            objArr.put(ob);
////                                        }
//
//
////                                        imagesReadyArray.add(stringImages.get(0));
////                                        if (imagesReadyArray.size() == dataPlanParam.length() && stringImages.size()==dataPlanParam.length()) {
////                                            processData(imagesReadyArray);
////                                        }
//                                    }
////                                Log.d("TAG", "stringImages.size().length() KKKKK >>>>>>>>> " + stringImages.size());
////                                Log.d("TAG", "jsonArrayUsersFamily.length() JJJJJJ >>>>>>>>> " + jsonArrayUsersFamily.length());
////                                if(stringImages.size()==jsonArrayUsersFamily.length()) {
////                                    stringImages.clear();
////                                }
//                                }
//                                    }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                                */


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(TAG, "Can't retrieve Geo Information.", error);
                        }
                    });
                    // mRequestQueue.add(geoInfoRequest);
                    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>




//                    RequestFuture<JSONObject> requestFuture=RequestFuture.newFuture();
//                    final String mURL = url;
//                    JsonObjectRequest requestNew = new JsonObjectRequest(Request.Method.GET,
//                            mURL,new JSONObject(),requestFuture,requestFuture);
//                    mQueue.add(requestNew);


//                    }
//                    if (imagesReadyArray.size() == dataPlanParam.length()) {
//                        processData(imagesReadyArray);
//                    }
//                            }
//                        }, 1000);



                }


//                Log.d("TAG", "stringImages.size() >>>>>>>>> " + stringImages.size());
//                Log.d("TAG", "jsonArrayUsersFamily.length() JJJJJJ >>>>>>>>> " + jsonArrayUsersFamily.length());
//
//                if(stringImages.size()==jsonArrayUsersFamily.length()) {
////                    imagesReadyArray.add(stringImages.get(0));
////                    if (imagesReadyArray.size() == dataPlanParam.length() && stringImages.size()==dataPlanParam.length()) {
////                        processData(imagesReadyArray);
////                    }
//
//                    Log.d("TAG", "stringImages >>>>>>>>> " + stringImages);
//                    stringImages.clear();
//                }



                                    for (int i = 0; i < stringImages.size(); i++) {
                                        numbers.add(i);
                                    }
                                    Collections.shuffle(numbers);


                currentUser.setImage(stringImages.get(numbers.get(0)));
//                currentUser.setImage(imageSelected);


                currentUser.setCost("0");

//                if (imageSelected != null) {
                destinationsArrayList.add(currentUser);
//                }


            }
//
//            destinationsArrayListBuffer = destinationsArrayList;
            PlanAdapter myAdapter = new PlanAdapter(this.getActivity(), destinationsArrayList);
            mRecyclerView.setAdapter(myAdapter);

//            Log.d("TAG", "imagesDes xxx >>>>>>>>> " + imagesDes);

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
        if (url.contains(RestApis.KarmaGroups.vacapediaDestinations+ "/" /* + ID_FAMILY*/)) {
            Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");

//            try {



//                    String[] alphabet = new String[]{};
            List<String> list = new ArrayList<>();
//                            Arrays.asList(alphabet);

            JSONObject jres = new JSONObject(response);



//                                        Log.d("TAG", "jres.length() >>>>>>>>> " + jres.length());
                                        Log.d("TAG", "jres xxx >>>>>>>>> " + jres);



            if (imagesDes.length()<1){
                JSONObject o = new JSONObject("{" +
                        "\"destination_id\": \"" + jres.getString("_id") + "\"," +
                        "\"images\": [" +
                                                    "{" +
                                                    "\"id\": \"" + 0 + "\"," +
                                                    "\"image\": \"" + jres.getString("image") + "\"" +
                                                    "}"
                                                    +
                        "]" +
                        "}");
                imagesDes.put(o);
            } else {
                for (int l = 0; l < imagesDes.length(); l++) {
                    JSONObject jobl = imagesDes.getJSONObject(l);
                    list.add(jobl.getString("destination_id"));
                    Log.d("TAG", "list >>>>>>>>> " + list);
                    if (!list.contains(jres.getString("_id"))) {
//                            Log.d("TAG", "contain list >>>>>>>>> " + !list.contains(finalJob.getString("_id")));
                        JSONObject o = new JSONObject("{" +
                                "\"destination_id\": \"" + jres.getString("_id") + "\"," +
                                "\"images\": [" +
                                                            "{" +
                                                            "\"id\": \"" + 0 + "\"," +
                                                            "\"image\": \"" + jres.getString("image") + "\"" +
                                                            "}"
                                                            +
                                "]" +
                                "}");
                        imagesDes.put(o);
                    }
                    else {
                        JSONArray imgs = jobl.getJSONArray("images");
                        JSONObject oIn = new JSONObject("{" +
                                "\"id\": \"" + (imgs.length()) + "\"," +
                                "\"image\": \"" + jres.getString("image") + "\"" +
                                "}");
                        imgs.put(oIn);
                    }

                }
            }





//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

//            currentUser.setImage(stringImages.get(j));
//////
//////
//            currentUser.setCost("0");
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
//////
//                currentUser.setImage(stringImages.get(j));
//////
//////
//                currentUser.setCost("0");
////
//////                if (imageSelected != null) {
////                destinationsArrayList.add(currentUser);
//////                }
////
////
////            }
////
////            destinationsArrayListBuffer = destinationsArrayList;
//            PlanAdapter myAdapter = new PlanAdapter(this.getActivity(), destinationsArrayList);
//            mRecyclerView.setAdapter(myAdapter);
//            guestDestinationsAdapter.notifyDataSetChanged();

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
//                currentUser.setImage(imageSelected);


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