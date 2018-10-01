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

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codingdemos.flowers.DestinationsModel;
import com.codingdemos.flowers.GuestDestinationsAdapter;
import com.codingdemos.flowers.HomeDestinationsModel;
import com.codingdemos.flowers.R;
import com.codingdemos.flowers.SliderAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ItemThreeFragment extends Fragment {


    private String data = "[{\"name\":\"KarmaBavaria-Germany\",\"postID\":\"731\",\"image\":\"1520823610K_Bavaria_White.png\",\"bgimage\":\"Karma-Bavariaimg.png\",\"favouriteStatus\":0,\"id\":\"1\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaBorgoDiColleoli-Italy\",\"postID\":\"69\",\"image\":\"1533624842new_logo.png\",\"bgimage\":\"152997960203096a4c-karma-tuscany-2-min.jpg\",\"favouriteStatus\":0,\"id\":\"73\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaRottnest-Australia\",\"postID\":\"802\",\"image\":\"1520823724K_Rottnest_White.png\",\"bgimage\":\"1537947603karma_rottnest_display.jpg\",\"favouriteStatus\":0,\"id\":\"4\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaKandara-Bali\",\"postID\":\"193\",\"image\":\"1520823697K_Kandara_White.png\",\"bgimage\":\"1537947614karma_kandara_display.jpg\",\"favouriteStatus\":0,\"id\":\"3\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaJimbaran-Bali\",\"postID\":\"837\",\"image\":\"1520823633K_Jimbaran_White.png\",\"bgimage\":\"1537957239karma_jimbaran2__display.jpg\",\"favouriteStatus\":0,\"id\":\"2\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"menuID\":\"2\",\"menuName\":\"KarmaRetreats\",\"image\":\"K_retreats.png\",\"bgimage\":\"K_retreatsimg.png\",\"subGroupNames\":[{\"name\":\"KarmaCâyTre-Vietnam\",\"postID\":\"963\",\"image\":\"1520823761K_Cay_Tre_White.png\",\"bgimage\":\"K_caytreimg.png\",\"favouriteStatus\":0,\"id\":\"5\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaChakra-India\",\"postID\":\"940\",\"image\":\"1520823800K_Chakra_White.png\",\"bgimage\":\"K_chakraimg.png\",\"favouriteStatus\":0,\"id\":\"6\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaHaveli-India\",\"postID\":\"782\",\"image\":\"1520823823K_Haveli_White.png\",\"bgimage\":\"K_haveliimg.png\",\"favouriteStatus\":0,\"id\":\"7\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaMayura-Bali\",\"postID\":\"889\",\"image\":\"1520823849K_Mayura_White.png\",\"bgimage\":\"karma_mayuraimg.png\",\"favouriteStatus\":0,\"id\":\"8\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaSt.Martin's-IslesofScilly\",\"postID\":\"914\",\"image\":\"1520823892K_St.Martin's_White.png\",\"bgimage\":\"karma_stmartinsimg.png\",\"favouriteStatus\":0,\"id\":\"10\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaReef-Indonesia\",\"postID\":\"866\",\"image\":\"1520823870K_Reef_White.png\",\"bgimage\":\"karma_reefimg.png\",\"favouriteStatus\":0,\"id\":\"9\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaResidenceNormande-France\",\"postID\":\"72\",\"image\":\"153362451520ef2aac-white-300x224.png\",\"bgimage\":\"1530690865back-1.jpg\",\"favouriteStatus\":0,\"id\":\"76\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaExotica-Dharamshala\",\"postID\":\"71\",\"image\":\"1530676667c2576fa3-karma-dharmasala-logo.png\",\"bgimage\":\"1530676667amen-1.jpg\",\"favouriteStatus\":0,\"id\":\"75\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaManoirdesDeuxAmants-France\",\"postID\":\"70\",\"image\":\"1533624740logo.png\",\"bgimage\":\"1529979700c22d7ea8-karma-manoir-12.jpg\",\"favouriteStatus\":0,\"id\":\"74\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"KarmaMinoan-Greece\",\"postID\":\"10\",\"image\":\"1528350256KMinoan-Logo.png\",\"bgimage\":\"1528350256minoan-background.jpg\",\"favouriteStatus\":0,\"id\":\"72\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null}]},{\"menuID\":\"4\",\"menuName\":\"KarmaEstate\",\"image\":\"1520824596K_Estates_White.png\",\"bgimage\":\"K_estatesimg.png\",\"subGroupNames\":[{\"name\":\"Pelikanos-Mykonos\",\"postID\":\"1331\",\"image\":\"Pelikanos.png\",\"bgimage\":\"pelikanosimg.png\",\"favouriteStatus\":0,\"id\":\"21\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null},{\"name\":\"LePreverger-France\",\"postID\":\"1285\",\"image\":\"lepreverger.png\",\"bgimage\":\"leprevergerimg.png\",\"favouriteStatus\":0,\"id\":\"22\",\"isParent\":\"0\",\"child\":\"0\",\"email\":null}]}]";


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

    private ViewPager viewPager;
    private TabLayout indicator;

    private List<Integer> color;
    private List<String> colorName;

    private RecyclerView guest_destinations_rv;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList <DestinationsModel> destinationsArrayList;
    private GuestDestinationsAdapter guestDestinationsAdapter;

    public static ItemThreeFragment newInstance() {
        ItemThreeFragment fragment = new ItemThreeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_three, container, false);



        viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        indicator=(TabLayout)view.findViewById(R.id.indicator);
        color = new ArrayList<>();
        color.add(Color.BLACK);
        color.add(Color.BLACK);
        color.add(Color.BLACK);

        colorName = new ArrayList<>();
        colorName.add("Hegia Sopia");
        colorName.add("Masjid");
        colorName.add("Arena");

        viewPager.setAdapter(new SliderAdapter(this.getContext(), color, colorName));
        indicator.setupWithViewPager(viewPager, true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);



        try {
            JSONArray dataJson = new JSONArray(data);

        guest_destinations_rv = (RecyclerView) view.findViewById(R.id.guest_destinations_rv);
        destinationsArrayList = new ArrayList < > ();
        linearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        guest_destinations_rv.setLayoutManager(linearLayoutManager);
        guestDestinationsAdapter = new GuestDestinationsAdapter(this.getActivity(), destinationsArrayList);
        guest_destinations_rv.setAdapter(guestDestinationsAdapter);


//        JSONObject jsonObject = new JSONObject(response);
//        int status = jsonObject.getInt(AppStrings.ResponseData.status);
//        String message = jsonObject.optString(AppStrings.ResponseData.message);
//        if (status == 1) {
            destinationsArrayList.clear();
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
                    model.setPostID(job.optString(postID));
                    String img = "https://www.dakwatuna.com/wp-content/uploads/2015/07/masjidil-haram.jpg";
                    Log.d("LOG", "img >>>>>>>>> " + img);
                    // model.setImage(job.optString(image));
                    model.setImage(img);
                    dma.add(model);
                    destinationsArrayList.add(model);
                }
//                hModel.setDestinationsModelArrayList(dma);
//            }
            guestDestinationsAdapter.notifyDataSetChanged();
//        } else {
//            AppUtils.alertWithOk(this, message);
//        }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }


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


}
