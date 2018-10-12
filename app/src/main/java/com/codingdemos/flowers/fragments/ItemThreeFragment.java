/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

package com.codingdemos.flowers.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.codingdemos.flowers.DestinationsModel;
import com.codingdemos.flowers.GuestDestinationsAdapter;
import com.codingdemos.flowers.GuestDestinationsLongAdapter;
import com.codingdemos.flowers.MainActivity;
import com.codingdemos.flowers.R;
import com.codingdemos.flowers.SliderAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.codingdemos.flowers.rest.AsyncHttpResponse;
import com.codingdemos.flowers.rest.RestApis;
import com.loopj.android.http.RequestParams;

public class ItemThreeFragment extends Fragment
        implements
        AsyncHttpResponse.AsyncHttpResponseListener
//        ,
//        VolleyResponse.VolleyResponseListener
{


    private String data = "[{\"name\":\"KarmaBavaria-Germany\",\"postID\":\"731\",\"image\":\"1520823610K_Bavaria_White.png\",\"bgimage\":\"Karma-Bavariaimg.png\",\"favouriteStatus\":0,\"id\":\"1\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaBorgoDiColleoli-Italy\",\"postID\":\"69\",\"image\":\"1533624842new_logo.png\",\"bgimage\":\"152997960203096a4c-karma-tuscany-2-min.jpg\",\"favouriteStatus\":0,\"id\":\"73\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaRottnest-Australia\",\"postID\":\"802\",\"image\":\"1520823724K_Rottnest_White.png\",\"bgimage\":\"1537947603karma_rottnest_display.jpg\",\"favouriteStatus\":0,\"id\":\"4\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaKandara-Bali\",\"postID\":\"193\",\"image\":\"1520823697K_Kandara_White.png\",\"bgimage\":\"1537947614karma_kandara_display.jpg\",\"favouriteStatus\":0,\"id\":\"3\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaJimbaran-Bali\",\"postID\":\"837\",\"image\":\"1520823633K_Jimbaran_White.png\",\"bgimage\":\"1537957239karma_jimbaran2__display.jpg\",\"favouriteStatus\":0,\"id\":\"2\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"menuID\":\"2\",\"menuName\":\"KarmaRetreats\",\"image\":\"K_retreats.png\",\"bgimage\":\"K_retreatsimg.png\",\"subGroupNames\":[{\"name\":\"KarmaCâyTre-Vietnam\",\"postID\":\"963\",\"image\":\"1520823761K_Cay_Tre_White.png\",\"bgimage\":\"K_caytreimg.png\",\"favouriteStatus\":0,\"id\":\"5\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaChakra-India\",\"postID\":\"940\",\"image\":\"1520823800K_Chakra_White.png\",\"bgimage\":\"K_chakraimg.png\",\"favouriteStatus\":0,\"id\":\"6\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaHaveli-India\",\"postID\":\"782\",\"image\":\"1520823823K_Haveli_White.png\",\"bgimage\":\"K_haveliimg.png\",\"favouriteStatus\":0,\"id\":\"7\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaMayura-Bali\",\"postID\":\"889\",\"image\":\"1520823849K_Mayura_White.png\",\"bgimage\":\"karma_mayuraimg.png\",\"favouriteStatus\":0,\"id\":\"8\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaSt.Martin's-IslesofScilly\",\"postID\":\"914\",\"image\":\"1520823892K_St.Martin's_White.png\",\"bgimage\":\"karma_stmartinsimg.png\",\"favouriteStatus\":0,\"id\":\"10\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaReef-Indonesia\",\"postID\":\"866\",\"image\":\"1520823870K_Reef_White.png\",\"bgimage\":\"karma_reefimg.png\",\"favouriteStatus\":0,\"id\":\"9\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaResidenceNormande-France\",\"postID\":\"72\",\"image\":\"153362451520ef2aac-white-300x224.png\",\"bgimage\":\"1530690865back-1.jpg\",\"favouriteStatus\":0,\"id\":\"76\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaExotica-Dharamshala\",\"postID\":\"71\",\"image\":\"1530676667c2576fa3-karma-dharmasala-logo.png\",\"bgimage\":\"1530676667amen-1.jpg\",\"favouriteStatus\":0,\"id\":\"75\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaManoirdesDeuxAmants-France\",\"postID\":\"70\",\"image\":\"1533624740logo.png\",\"bgimage\":\"1529979700c22d7ea8-karma-manoir-12.jpg\",\"favouriteStatus\":0,\"id\":\"74\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaMinoan-Greece\",\"postID\":\"10\",\"image\":\"1528350256KMinoan-Logo.png\",\"bgimage\":\"1528350256minoan-background.jpg\",\"favouriteStatus\":0,\"id\":\"72\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null}]},{\"menuID\":\"4\",\"menuName\":\"KarmaEstate\",\"image\":\"1520824596K_Estates_White.png\",\"bgimage\":\"K_estatesimg.png\",\"subGroupNames\":[{\"name\":\"Pelikanos-Mykonos\",\"postID\":\"1331\",\"image\":\"Pelikanos.png\",\"bgimage\":\"pelikanosimg.png\",\"favouriteStatus\":0,\"id\":\"21\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"LePreverger-France\",\"postID\":\"1285\",\"image\":\"lepreverger.png\",\"bgimage\":\"leprevergerimg.png\",\"favouriteStatus\":0,\"id\":\"22\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null}]}]";

    /*
    name
    postID
    image
    bgimage
    "favouriteStatus":0,
    "id":"1",
    "isParent":"0",
    "child":"0",
    "email":null
     */

    private View view;

    String image="image";
    String homeDestinations="homeDestinations";
    String homeDestinationsOdyssey="homeDestinationsOdyssey";
    String menuName="menuName";
    String menuID="menuID";
    String subMenuNames="subMenuNames";
    String subMenuName="subMenuName";
    String name="name";
    String imageBaseURL="imageBaseURL";
    String bgimage="bgimage";
    String subGroupNames="subGroupNames";
    String favouriteStatus="favouriteStatus";
    String beachDetails="beachDetails";
    String spaDetails="spaDetails";
    String title="title";
    String subTitle="subTitle";
    String postID ="postID";
    String buttonName ="buttonName";
    String link ="link";
    String botiqueDetails ="botiqueDetails";
    String cost ="cost";
    String spaProductImages ="spaProductImages";
    String spaProducts ="spaProducts";
    String destinationDetails ="destinationDetails";
    String images ="images";
    String phone ="phone";
    String email ="email";
    String latitude ="latitude";
    String longitude ="longitude";

    private JSONArray dataDestinations = null;
    private JSONArray dataNews = null;

    private ViewPager viewPager;
    private TabLayout indicator;

    private List<Integer> color;
    private List<String> colorName;
    private List<String> colorImage;

    private ArrayList <DestinationsModel> destinationsArrayListBuffer, destinationsArrayListArchBuffer, destinationsArrayListCulinaryBuffer, destinationsArrayListArtBuffer;

    private RecyclerView guest_destinations_rv, guest_destinations_rv_architecture,guest_destinations_rv_culinary,guest_destinations_rv_art;
    private LinearLayoutManager linearLayoutManager, linearLayoutManagerArch, linearLayoutManagerCulinary, linearLayoutManagerArt;
    private ArrayList <DestinationsModel> destinationsArrayList, destinationsArrayListArch, destinationsArrayListCulinary, destinationsArrayListArt;
    private GuestDestinationsAdapter guestDestinationsAdapter, guestDestinationsAdapterArch, guestDestinationsAdapterCulinary, guestDestinationsAdapterArt;

    private RecyclerView guest_destinations_rv_long;
    private LinearLayoutManager linearLayoutManagerLong;
    private ArrayList <DestinationsModel> destinationsArrayListLong;
    private GuestDestinationsLongAdapter guestDestinationsAdapterLong;

    public static ItemThreeFragment newInstance() {
        ItemThreeFragment fragment = new ItemThreeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_three, container, false);





//        try {
            getKarmaGroupsApiRequest();
        getKarmaGroupsApiRequestNews();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        indicator=(TabLayout)view.findViewById(R.id.indicator);
        color = new ArrayList<>();
        color.add(Color.BLACK);
        color.add(Color.BLACK);
        color.add(Color.BLACK);
        color.add(Color.BLACK);
        color.add(Color.BLACK);

        colorName = new ArrayList<>();
        colorName.add("Fun on Beach");
        colorName.add("Amazing Tample");
        colorName.add("Exotic Culinaries");
        colorName.add("Wonderful Cultures");
        colorName.add("Vacation Planning");

        colorImage = new ArrayList<>();
        colorImage.add("http://www.bravotv.com/sites/nbcubravotv/files/field_blog_image/2017/05/most-wanted-beach-must-haves-promote.jpg");
        colorImage.add("https://image.freepik.com/free-photo/pura-ulun-danu-bratan-temple-bali-indonesia_30824-288.jpg");
        colorImage.add("https://s20642.pcdn.co/wp-content/uploads/2016/07/Bumbu-Bali-Nusa.jpg");
        colorImage.add("https://statik.tempo.co/data/2015/12/30/id_468670/468670_620.jpg");
        colorImage.add("https://s.yimg.com/ny/api/res/1.2/NmvEqUC_SWc12zxYi6GwsQ--~A/YXBwaWQ9aGlnaGxhbmRlcjtzbT0xO3c9ODAwO2lsPXBsYW5l/http://41.media.tumblr.com/535416e4be3cca14974d7d5a6d9d65df/tumblr_inline_nz6mhnXO6r1tcrvl6_1280.jpg");

        viewPager.setAdapter(new SliderAdapter(this.getContext(), color, colorName, colorImage));
        indicator.setupWithViewPager(viewPager, true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);




        return view;
    }


    CoordinatorLayout.Behavior behavior;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(behavior != null)
            return;

        FrameLayout layout =(FrameLayout) getActivity().findViewById(R.id.frame_layout);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) layout.getLayoutParams();

        behavior = params.getBehavior();
        params.setBehavior(null);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(behavior == null)
            return;

        FrameLayout layout =(FrameLayout) getActivity().findViewById(R.id.frame_layout);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) layout.getLayoutParams();

        params.setBehavior(behavior);

        layout.setLayoutParams(params);

        behavior = null;
    }







    private ArrayList<DestinationsModel> createRandomList(ArrayList<DestinationsModel> listTarget) {
        Log.d("LOG", "listTarget >>>>>>>>> " + listTarget);
        ArrayList<DestinationsModel> listResult;
        List<Integer> numbers=new ArrayList<>();
        for(int i = 0; i < listTarget.size(); i++)
        {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        Log.d("LOG", "createRandomList numbers >>>>>>>>> " + numbers);

        int size = 7;
        ArrayList<DestinationsModel> buffer = new ArrayList<DestinationsModel>();

        if (listTarget.size() >= size) {
            for (int i = 0; i < size; i++) {
                Log.d("LOG", "createRandomList hashSet.get(i) >>>>>>>>> " + numbers.get(i));
                buffer.add(listTarget.get(numbers.get(i)));
            }
            Log.d("LOG", "buffer >>>>>>>>> " + buffer);
            return listResult = buffer;
        } else {
            return listResult = listTarget;
        }

        // return listResult;
    };

    private void processData() {

        try {
            // JSONArray dataJson = new JSONArray(data);
            JSONArray dataJson = new JSONArray();

            JSONObject dataObj = new JSONObject(
                    "{" +
                            "\"name\":" + "\"name\"" + "," +
                            "\"postID\":" + "\"name\"" + "," +
                            "\"image\":" + "\"https://www.dakwatuna.com/wp-content/uploads/2015/07/masjidil-haram.jpg\"" + "," +
                            "\"bgimage\":" + "\"https://www.dakwatuna.com/wp-content/uploads/2015/07/masjidil-haram.jpg\"" + "," +
                            "\"favouriteStatus\":" + 0 + "," +
                            "\"id\":" + "1"+ "," +
                            "\"isParent\":" + "0"+ "," +
                            "\"child\":" + "0"+ "," +
                            "\"email\":" + null +
                            "}"
            );

//            for(int i=1; i<5; i++) {
//                dataJson.put(dataObj);
//            }

            dataJson = dataDestinations;


            destinationsArrayListBuffer = new ArrayList < > ();
            destinationsArrayListArchBuffer = new ArrayList < > ();
            destinationsArrayListCulinaryBuffer = new ArrayList < > ();
            destinationsArrayListArtBuffer = new ArrayList < > ();

            guest_destinations_rv = (RecyclerView) view.findViewById(R.id.guest_destinations_rv);
            destinationsArrayList = new ArrayList < > ();
            linearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
            guest_destinations_rv.setLayoutManager(linearLayoutManager);


            guest_destinations_rv_architecture = (RecyclerView) view.findViewById(R.id.guest_destinations_rv_architecture);
            destinationsArrayListArch = new ArrayList < > ();
            linearLayoutManagerArch = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
            guest_destinations_rv_architecture.setLayoutManager(linearLayoutManagerArch);


            guest_destinations_rv_culinary = (RecyclerView) view.findViewById(R.id.guest_destinations_rv_culinary);
            destinationsArrayListCulinary = new ArrayList < > ();
            linearLayoutManagerCulinary = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
            guest_destinations_rv_culinary.setLayoutManager(linearLayoutManagerCulinary);


            guest_destinations_rv_art = (RecyclerView) view.findViewById(R.id.guest_destinations_rv_art);
            destinationsArrayListArt = new ArrayList < > ();
            linearLayoutManagerArt = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
            guest_destinations_rv_art.setLayoutManager(linearLayoutManagerArt);




//            guest_destinations_rv_long = (RecyclerView) view.findViewById(R.id.guest_destinations_rv_long);
//            destinationsArrayListLong = new ArrayList < > ();
//            linearLayoutManagerLong = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
//            guest_destinations_rv_long.setLayoutManager(linearLayoutManagerLong);
//            guestDestinationsAdapterLong = new GuestDestinationsLongAdapter(this.getActivity(), destinationsArrayListLong);
//            guest_destinations_rv_long.setAdapter(guestDestinationsAdapterLong);




//        JSONObject jsonObject = new JSONObject(response);
//        int status = jsonObject.getInt(AppStrings.ResponseData.status);
//        String message = jsonObject.optString(AppStrings.ResponseData.message);
//        if (status == 1) {
            destinationsArrayList.clear();
            destinationsArrayListArch.clear();
            destinationsArrayListCulinary.clear();
            destinationsArrayListArt.clear();

//            destinationsArrayListLong.clear();

//            sm.setStringData(AppStrings.Session.api_homeDestinationsDev, response);
//            JSONArray jsonArray = dataJson;
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jobj = jsonArray.getJSONObject(i);
//                HomeDestinationsModel hModel = new HomeDestinationsModel();
//                hModel.setMenuName(jobj.optString(menuName));
            JSONArray jarry = dataJson;
            ArrayList < DestinationsModel > dma = new ArrayList < > ();
            dma.clear();
            for (int j = 0; j < jarry.length(); j++) {
                JSONObject job = jarry.getJSONObject(j);
                DestinationsModel model = new DestinationsModel();
                model.setMenuID(String.valueOf(j));
                model.setMenuName("nama"+j);
                model.setName(job.optString(name));
                model.setPostID(job.optString("id"));
                String img = "https://www.dakwatuna.com/wp-content/uploads/2015/07/masjidil-haram.jpg";
                Log.d("LOG", "img >>>>>>>>> " + img);
                model.setImage(job.optString(image));
//                    model.setImage(img);
//                if (job.optString("category").equals("1")) {
//                destinationsArrayList.add(model);
//                destinationsArrayListArch.add(model);
//                destinationsArrayListCulinary.add(model);
//                destinationsArrayListArt.add(model);




//                destinationsArrayListLong.add(model);



                switch (Integer.parseInt(job.optString("category"))) {
                    case 1:
                        dma.add(model);
                        destinationsArrayList.add(model);
//                        if (destinationsArrayList.size() == 7) {
//                            destinationsArrayList = createRandomList(destinationsArrayList);
//                        }
                        break;
                    case 2:
                        dma.add(model);
                        destinationsArrayListArch.add(model);
                        break;
                    case 3:
                        dma.add(model);
                        destinationsArrayListCulinary.add(model);
                        break;
                    case 4:
                        dma.add(model);
                        destinationsArrayListArt.add(model);
                        break;
                    default:
                        dma.add(model);
                        break;
                }



//                if (j == jarry.length()-1){
//
//                    destinationsArrayList = createRandomList(destinationsArrayListBuffer);
//                    destinationsArrayListArch = createRandomList(destinationsArrayListArchBuffer);
//                    destinationsArrayListCulinary = createRandomList(destinationsArrayListCulinaryBuffer);
//                    destinationsArrayListArt = createRandomList(destinationsArrayListArtBuffer);
//
//                }


            }
//                hModel.setDestinationsModelArrayList(dma);
//            }



//            Log.d("LOG", "destinationsArrayList before >>>>>>>>> " + destinationsArrayList);
//            destinationsArrayListBuffer = createRandomList(destinationsArrayList);
//            Log.d("LOG", "destinationsArrayList after >>>>>>>>> " + destinationsArrayList);


            destinationsArrayListBuffer = createRandomList(destinationsArrayList);
            destinationsArrayListArchBuffer = createRandomList(destinationsArrayListArch);
            destinationsArrayListCulinaryBuffer = createRandomList(destinationsArrayListCulinary);
            destinationsArrayListArtBuffer = createRandomList(destinationsArrayListArt);


            guestDestinationsAdapter = new GuestDestinationsAdapter(this.getActivity(), destinationsArrayListBuffer);
            guest_destinations_rv.setAdapter(guestDestinationsAdapter);


            guestDestinationsAdapterArch = new GuestDestinationsAdapter(this.getActivity(), destinationsArrayListArchBuffer);
            guest_destinations_rv_architecture.setAdapter(guestDestinationsAdapterArch);


            guestDestinationsAdapterCulinary = new GuestDestinationsAdapter(this.getActivity(), destinationsArrayListCulinaryBuffer);
            guest_destinations_rv_culinary.setAdapter(guestDestinationsAdapterCulinary);


            guestDestinationsAdapterArt = new GuestDestinationsAdapter(this.getActivity(), destinationsArrayListArtBuffer);
            guest_destinations_rv_art.setAdapter(guestDestinationsAdapterArt);


            guestDestinationsAdapter.notifyDataSetChanged();
            guestDestinationsAdapterArch.notifyDataSetChanged();
            guestDestinationsAdapterCulinary.notifyDataSetChanged();
            guestDestinationsAdapterArt.notifyDataSetChanged();

//            guestDestinationsAdapterLong.notifyDataSetChanged();

//        } else {
//            AppUtils.alertWithOk(this, message);
//        }




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void processDataNews() {

        try {
            // JSONArray dataJson = new JSONArray(data);
            JSONArray dataJson = new JSONArray();

            JSONObject dataObj = new JSONObject(
                    "{" +
                            "\"name\":" + "\"name\"" + "," +
                            "\"postID\":" + "\"name\"" + "," +
                            "\"image\":" + "\"https://www.dakwatuna.com/wp-content/uploads/2015/07/masjidil-haram.jpg\"" + "," +
                            "\"bgimage\":" + "\"https://www.dakwatuna.com/wp-content/uploads/2015/07/masjidil-haram.jpg\"" + "," +
                            "\"favouriteStatus\":" + 0 + "," +
                            "\"id\":" + "1"+ "," +
                            "\"isParent\":" + "0"+ "," +
                            "\"child\":" + "0"+ "," +
                            "\"email\":" + null +
                            "}"
            );

//            for(int i=1; i<5; i++) {
//                dataJson.put(dataObj);
//            }

            // dataJson = dataNews;

//            HashSet<Integer> hashSet=new HashSet<>();
            List<Integer> numbers=new ArrayList<>();
//            Random random = new Random();
//now add random number to this set
//            while(true)
//            {
//                hashSet.add(random.nextInt(dataNews.length()));
//                if(hashSet.size()==dataNews.length())
//                    break;
//            }

            //define ArrayList to hold Integer objects
//            ArrayList numbers = new ArrayList();

            for(int i = 0; i < dataNews.length(); i++)
            {
                numbers.add(i);
            }
            Collections.shuffle(numbers);
            Log.d("LOG", "numbers >>>>>>>>> " + numbers);

            int size = 5;
            JSONArray buffer = new JSONArray();

            if (dataNews.length() == size) {
                for (int i = 0; i < size; i++) {
                    Log.d("LOG", "hashSet.get(i) >>>>>>>>> " + numbers.get(i));
                    buffer.put(dataNews.get(numbers.get(i)));
                }
            }

            if (dataNews.length() == size && buffer.length() == size) {
                dataJson = buffer;
            } else {
                dataJson = dataNews;
            }

            guest_destinations_rv_long = (RecyclerView) view.findViewById(R.id.guest_destinations_rv_long);
            destinationsArrayListLong = new ArrayList < > ();
            linearLayoutManagerLong = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
            guest_destinations_rv_long.setLayoutManager(linearLayoutManagerLong);
            guestDestinationsAdapterLong = new GuestDestinationsLongAdapter(this.getActivity(), destinationsArrayListLong);
            guest_destinations_rv_long.setAdapter(guestDestinationsAdapterLong);




//        JSONObject jsonObject = new JSONObject(response);
//        int status = jsonObject.getInt(AppStrings.ResponseData.status);
//        String message = jsonObject.optString(AppStrings.ResponseData.message);
//        if (status == 1) {

            destinationsArrayListLong.clear();

//            sm.setStringData(AppStrings.Session.api_homeDestinationsDev, response);
//            JSONArray jsonArray = dataJson;
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jobj = jsonArray.getJSONObject(i);
//                HomeDestinationsModel hModel = new HomeDestinationsModel();
//                hModel.setMenuName(jobj.optString(menuName));
            JSONArray jarry = dataJson;
            ArrayList < DestinationsModel > dma = new ArrayList < > ();
            dma.clear();
            for (int j = 0; j < jarry.length(); j++) {
                JSONObject job = jarry.getJSONObject(j);
                DestinationsModel model = new DestinationsModel();
                model.setMenuID(String.valueOf(j));
                model.setMenuName("nama"+j);
                model.setName(job.optString(name));
                model.setPostID(job.optString("id"));
                String img = "https://www.dakwatuna.com/wp-content/uploads/2015/07/masjidil-haram.jpg";
                Log.d("LOG", "img >>>>>>>>> " + img);
                model.setImage(job.optString(image));
//                    model.setImage(img);
                dma.add(model);

                destinationsArrayListLong.add(model);
            }
//                hModel.setDestinationsModelArrayList(dma);
//            }

            guestDestinationsAdapterLong.notifyDataSetChanged();

//        } else {
//            AppUtils.alertWithOk(this, message);
//        }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

// throws JSONException
    private void getKarmaGroupsApiRequest() {
//        if (AppUtils.isConnectingToInternet(this)) {
            AsyncHttpResponse response = new AsyncHttpResponse(this, false);
            RequestParams params = new RequestParams();
//            if (sm.getBooleanData(AppStrings.Session.isKarmaMember)) {
//                params.put(AppStrings.ResponseData.memberID, sm.getStringData(AppStrings.Session.memberID));
//                params.put(AppStrings.ResponseData.surName, sm.getStringData(AppStrings.Session.member_surName));
//            }
            response.getAsyncHttp(RestApis.KarmaGroups.vacapediaDestinations, params);
//        } else {
//            AppUtils.alertForNoInternet(this);
//        }
    }


    // throws JSONException
    private void getKarmaGroupsApiRequestNews() {
//        if (AppUtils.isConnectingToInternet(this)) {
        AsyncHttpResponse response = new AsyncHttpResponse(this, false);
        RequestParams params = new RequestParams();
//            if (sm.getBooleanData(AppStrings.Session.isKarmaMember)) {
//                params.put(AppStrings.ResponseData.memberID, sm.getStringData(AppStrings.Session.memberID));
//                params.put(AppStrings.ResponseData.surName, sm.getStringData(AppStrings.Session.member_surName));
//            }
        response.getAsyncHttp(RestApis.KarmaGroups.vacapediaNews, params);
//        } else {
//            AppUtils.alertForNoInternet(this);
//        }
    }

    @Override
    public void onAsyncHttpResponseGet(String response, String url) throws JSONException {
        Log.d("TAG", "onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
        if (url.equals(RestApis.KarmaGroups.vacapediaDestinations)) {
            // sm.setStringData(AppStrings.Session.api_getKarmaGroupsDev, response);
            Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");

            dataDestinations = new JSONArray(response);

            processData();

        }

        if (url.equals(RestApis.KarmaGroups.vacapediaNews)) {
            // sm.setStringData(AppStrings.Session.api_getKarmaGroupsDev, response);
            Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");

            dataNews = new JSONArray(response);

            processDataNews();

        }

    }

//    @Override
//    public void onVolleyResponseGet(String response, String url, String requestType) throws JSONException {
//
//    }


    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            if (getActivity()!=null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getCurrentItem() < color.size() - 1) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }
    }

    private int fragment = 0;
    public int getFragment() {
        return fragment;
    }
    public void setFragment(int fragment) {
        this.fragment = fragment;
    }

    public void onResume(){
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Vacapedia");
        ((MainActivity) getActivity()).setFragment(1);
        this.setFragment(1);
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            if(this.getFragment() != 0) {
                ((MainActivity) getActivity()).setActionBarTitle("Vacapedia");
                ((MainActivity) getActivity()).setFragment(1);
            }
            Log.d("LOG", "Vacapedia");
        }
    }



}
