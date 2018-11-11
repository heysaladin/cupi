package com.codingdemos.vacapedia.fragments;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.codingdemos.vacapedia.data.DestinationsModel;
import com.codingdemos.vacapedia.handlers.GuestDestinationsAdapter;
import com.codingdemos.vacapedia.handlers.GuestDestinationsLongAdapter;
import com.codingdemos.vacapedia.MainActivity;
import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.handlers.SliderAdapter;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.codingdemos.vacapedia.rest.RestApis;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment
        implements
        AsyncHttpResponse.AsyncHttpResponseListener {

    private View view;

    String image = "image";
    String name = "name";

    private JSONArray dataDestinations = null;
    private JSONArray dataNews = null;
    private JSONArray dataSlides = null;

    private ViewPager viewPager;
    private TabLayout indicator;

    private List < Integer > color;
    private List < String > colorName;
    private List < String > colorImage;
    private List < String > colorCopy;

    private ArrayList < DestinationsModel > destinationsArrayListBuffer, destinationsArrayListArchBuffer, destinationsArrayListCulinaryBuffer, destinationsArrayListArtBuffer;

    private RecyclerView guest_destinations_rv, guest_destinations_rv_architecture, guest_destinations_rv_culinary, guest_destinations_rv_art;
    private LinearLayoutManager linearLayoutManager, linearLayoutManagerArch, linearLayoutManagerCulinary, linearLayoutManagerArt;
    private ArrayList < DestinationsModel > destinationsArrayList, destinationsArrayListArch, destinationsArrayListCulinary, destinationsArrayListArt;
    private GuestDestinationsAdapter guestDestinationsAdapter, guestDestinationsAdapterArch, guestDestinationsAdapterCulinary, guestDestinationsAdapterArt;

    private RecyclerView guest_destinations_rv_long;
    private LinearLayoutManager linearLayoutManagerLong;
    private ArrayList < DestinationsModel > destinationsArrayListLong;
    private GuestDestinationsLongAdapter guestDestinationsAdapterLong;

    private ShimmerFrameLayout mShimmerViewContainer;

    private LinearLayout content;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_item_three, container, false);

        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        content = (LinearLayout) view.findViewById(R.id.content);
        content.setVisibility(View.GONE);

        getKarmaGroupsApiRequest();
        getKarmaGroupsApiRequestNews();
        getKarmaGroupsApiRequestSlides();

        return view;
    }

    CoordinatorLayout.Behavior behavior;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (behavior != null)
            return;

        FrameLayout layout = (FrameLayout) getActivity().findViewById(R.id.frame_layout);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) layout.getLayoutParams();

        behavior = params.getBehavior();
        params.setBehavior(null);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (behavior == null)
            return;

        FrameLayout layout = (FrameLayout) getActivity().findViewById(R.id.frame_layout);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) layout.getLayoutParams();

        params.setBehavior(behavior);

        layout.setLayoutParams(params);

        behavior = null;
    }

    private ArrayList < DestinationsModel > createRandomList(ArrayList < DestinationsModel > listTarget) {
        Log.d("LOG", "listTarget >>>>>>>>> " + listTarget);
        ArrayList < DestinationsModel > listResult;
        List < Integer > numbers = new ArrayList < > ();
        for (int i = 0; i < listTarget.size(); i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        Log.d("LOG", "createRandomList numbers >>>>>>>>> " + numbers);

        int size = 7;
        ArrayList < DestinationsModel > buffer = new ArrayList < DestinationsModel > ();

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
    };

    private void processData() {
        try {
            JSONArray dataJson = dataDestinations;

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

            destinationsArrayList.clear();
            destinationsArrayListArch.clear();
            destinationsArrayListCulinary.clear();
            destinationsArrayListArt.clear();

            JSONArray jarry = dataJson;
            ArrayList < DestinationsModel > dma = new ArrayList < > ();
            dma.clear();
            for (int j = 0; j < jarry.length(); j++) {
                JSONObject job = jarry.getJSONObject(j);
                DestinationsModel model = new DestinationsModel();
                model.setMenuID(String.valueOf(j));
                model.setMenuName("nama" + j);
                model.setName(job.optString(name));
                model.setPostID(job.optString("id"));
                model.setImage(job.optString(image));

//                model.setMenuName("nama" + j);
//                model.setName(job.optString(name));
//                model.setPostID(job.optString("id"));
//                model.setImage(job.optString(image));

                model.set_id(job.optString("_id"));
                model.setCategory(job.optString("category"));
                model.setLocation(job.optString("location"));
                model.setDescription(job.optString("description"));
                model.setLatitude(job.optString("latitude"));
                model.setLongitude(job.optString("longitude"));
                model.setAddress(job.optString("address"));
                model.setDistance(job.optString("distance"));
                model.setNote(job.optString("note"));
                model.setCosts(job.optString("costs"));
                model.setTotal_cost(job.optString("total_cost"));

                switch (Integer.parseInt(job.optString("category"))) {
                    case 1:
                        dma.add(model);
                        destinationsArrayList.add(model);
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
            }

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

            // Stopping Shimmer Effect's animation after data is loaded to ListView
            mShimmerViewContainer.stopShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.GONE);
            content.setVisibility(View.VISIBLE);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void processDataNews() {
        try {
            JSONArray dataJson = new JSONArray();
            List < Integer > numbers = new ArrayList < > ();
            for (int i = 0; i < dataNews.length(); i++) {
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

            destinationsArrayListLong.clear();

            JSONArray jarry = dataJson;
            ArrayList < DestinationsModel > dma = new ArrayList < > ();
            dma.clear();
            for (int j = 0; j < jarry.length(); j++) {
                JSONObject job = jarry.getJSONObject(j);
                DestinationsModel model = new DestinationsModel();
                model.setMenuID(String.valueOf(j));
                model.setMenuName("nama" + j);
                model.setName(job.optString(name));
                model.setPostID(job.optString("id"));
                model.setImage(job.optString(image));

//                model.setMenuName("nama" + j);
//                model.setName(job.optString(name));
//                model.setPostID(job.optString("id"));
//                model.setImage(job.optString(image));

                model.set_id(job.optString("_id"));
                model.setCategory(job.optString("category"));
                model.setLocation(job.optString("location"));
                model.setDescription(job.optString("description"));
                model.setLatitude(job.optString("latitude"));
                model.setLongitude(job.optString("longitude"));
                model.setAddress(job.optString("address"));
                model.setDistance(job.optString("distance"));
                model.setNote(job.optString("note"));
                model.setCosts(job.optString("costs"));
                model.setTotal_cost(job.optString("total_cost"));

                dma.add(model);
                destinationsArrayListLong.add(model);
            }
            guestDestinationsAdapterLong.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getKarmaGroupsApiRequest() {
        AsyncHttpResponse response = new AsyncHttpResponse(this, false);
        RequestParams params = new RequestParams();
        response.getAsyncHttp(RestApis.KarmaGroups.vacapediaDestinations, params);
    }

    private void getKarmaGroupsApiRequestNews() {
        AsyncHttpResponse response = new AsyncHttpResponse(this, false);
        RequestParams params = new RequestParams();
        response.getAsyncHttp(RestApis.KarmaGroups.vacapediaNews, params);
    }

    private void getKarmaGroupsApiRequestSlides() {
        AsyncHttpResponse response = new AsyncHttpResponse(this, false);
        RequestParams params = new RequestParams();
        response.getAsyncHttp(RestApis.KarmaGroups.vacapediaSlides, params);
    }

    @Override
    public void onAsyncHttpResponseGet(String response, String url) throws JSONException {
        Log.d("TAG", "onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
        if (url.equals(RestApis.KarmaGroups.vacapediaDestinations)) {
            Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
            dataDestinations = new JSONArray(response);
            processData();
        }
        if (url.equals(RestApis.KarmaGroups.vacapediaNews)) {
            Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
            dataNews = new JSONArray(response);
            processDataNews();
        }
        if (url.equals(RestApis.KarmaGroups.vacapediaSlides)) {
            Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
            dataSlides = new JSONArray(response);

            viewPager = (ViewPager) view.findViewById(R.id.viewPager);
            indicator = (TabLayout) view.findViewById(R.id.indicator);

            color = new ArrayList < > ();
//        color.add(Color.BLACK);
//        color.add(Color.BLACK);
//        color.add(Color.BLACK);
//        color.add(Color.BLACK);
//        color.add(Color.BLACK);

            colorName = new ArrayList < > ();
//        colorName.add("Fun on Beach");
//        colorName.add("Amazing Tample");
//        colorName.add("Exotic Culinaries");
//        colorName.add("Wonderful Cultures");
//        colorName.add("Vacation Planning");


            colorCopy = new ArrayList < > ();

            colorImage = new ArrayList < > ();
//        colorImage.add("http://www.bravotv.com/sites/nbcubravotv/files/field_blog_image/2017/05/most-wanted-beach-must-haves-promote.jpg");
//        colorImage.add("https://image.freepik.com/free-photo/pura-ulun-danu-bratan-temple-bali-indonesia_30824-288.jpg");
//        colorImage.add("https://s20642.pcdn.co/wp-content/uploads/2016/07/Bumbu-Bali-Nusa.jpg");
//        colorImage.add("https://statik.tempo.co/data/2015/12/30/id_468670/468670_620.jpg");
//        colorImage.add("https://s.yimg.com/ny/api/res/1.2/NmvEqUC_SWc12zxYi6GwsQ--~A/YXBwaWQ9aGlnaGxhbmRlcjtzbT0xO3c9ODAwO2lsPXBsYW5l/http://41.media.tumblr.com/535416e4be3cca14974d7d5a6d9d65df/tumblr_inline_nz6mhnXO6r1tcrvl6_1280.jpg");

            if (dataSlides != null) {
                try {
                    JSONArray jarry = dataSlides;
                    for (int j = 0; j < jarry.length(); j++) {
                        JSONObject job = jarry.getJSONObject(j);
                        color.add(Color.BLACK);
                        colorName.add(job.optString("title"));
                        colorCopy.add(job.optString("body_copy"));
                        colorImage.add(job.optString("image"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            viewPager.setAdapter(new SliderAdapter(this.getContext(), color, colorName, colorCopy, colorImage));
            indicator.setupWithViewPager(viewPager, true);

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);

        }
    }

    private class SliderTimer extends TimerTask {
        @Override
        public void run() {
            if (getActivity() != null) {
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

    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Vacapedia");
        ((MainActivity) getActivity()).setFragment(1);
        this.setFragment(1);
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            if (this.getFragment() != 0) {
                ((MainActivity) getActivity()).setActionBarTitle("Vacapedia");
                ((MainActivity) getActivity()).setFragment(1);
            }
            Log.d("LOG", "Vacapedia");
        }
    }

}