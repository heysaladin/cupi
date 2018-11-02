package com.codingdemos.vacapedia.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.MainActivity;
import com.codingdemos.vacapedia.PlanAdapter;
import com.codingdemos.vacapedia.PlanModel;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.codingdemos.vacapedia.rest.RestApis;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VacaplanFragment
        extends Fragment
        implements
        AsyncHttpResponse.AsyncHttpResponseListener {

    private View view;
    private List < Integer > numbers;
    private ArrayList < PlanModel > destinationsArrayList;
    private RecyclerView mRecyclerView;
    private PlanModel currentUser;
    private JSONArray jsonArrayUsersFamily;
    private List < String > stringImages;
    private List < String > intCost;
    private int cost = 0;

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
        jsonArrayUsersFamily = new JSONArray();
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
        response.getAsyncHttp(RestApis.KarmaGroups.vacapediaPlans /* + "/" + ID_FAMILY */ , params);
    }

    private void getImageBanner(final JSONArray dataPlanParam) {

        try {

            destinationsArrayList = new ArrayList < > ();
            mRecyclerView = view.findViewById(R.id.plans);
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getContext());
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            destinationsArrayList.clear();

            for (int j = 0; j < dataPlanParam.length(); j++) {
                JSONObject job = dataPlanParam.getJSONObject(j);
                currentUser = new PlanModel();
                currentUser.set_id(job.optString("_id"));
                currentUser.setTitle(job.optString("title"));
                currentUser.setContent(job.optString("content"));
                currentUser.setBody_copy(job.optString("body_copy"));
                currentUser.setTarget_date(job.optString("target_date"));
                currentUser.setTarget_time(job.optString("target_time("));
                jsonArrayUsersFamily = new JSONArray(job.optString("destinations"));
                currentUser.setDestinations(jsonArrayUsersFamily);

                stringImages = new ArrayList < > ();
                intCost = new ArrayList < > ();
                for (int k = 0; k < jsonArrayUsersFamily.length(); k++) {
                    JSONObject jobk = jsonArrayUsersFamily.getJSONObject(k);
                    String imgNow = jobk.optString("image");
                    stringImages.add(imgNow);
                    String costNow = jobk.optString("total_cost");
                }

                for (int i = 0; i < stringImages.size(); i++) {
                    numbers.add(i);
                }
                Collections.shuffle(numbers);

                for (int c = 0; c < intCost.size(); c++) {
                    cost += parseInteger(intCost.get(c));
                }

                currentUser.setImage(stringImages.get(numbers.get(0)));
                currentUser.setCost(String.valueOf(cost));

                destinationsArrayList.add(currentUser);

            }

            PlanAdapter myAdapter = new PlanAdapter(this.getActivity(), destinationsArrayList);
            mRecyclerView.setAdapter(myAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private int parseInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public void onAsyncHttpResponseGet(String response, String url) throws JSONException {
        Log.d("TAG", "onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
        if (url.equals(RestApis.KarmaGroups.vacapediaPlans /* + "/" + ID_FAMILY*/ )) {
            Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
            JSONArray dataPlan = new JSONArray(response);
            getImageBanner(dataPlan);
        }
    }

}