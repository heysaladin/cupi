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

import com.bumptech.glide.Glide;
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

public class VacaplanFragment extends Fragment
        implements
        AsyncHttpResponse.AsyncHttpResponseListener {

    private View view;


    private JSONObject dataUser = null;
    private JSONArray dataUserFamily = null;
    private JSONArray dataDestinations = null;
    private ArrayList <PlanModel> destinationsArrayListBuffer;
    private ArrayList <PlanModel> destinationsArrayList;
    private NotificationAdapter guestDestinationsAdapter;
    private String imageUrl = null;
    RecyclerView mRecyclerView;
    private PlanModel currentUser;
    private JSONArray jsonArrayUsersFamily;

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
//        CardView card_plan = (CardView) view.findViewById(R.id.card_plan);
//        card_plan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(view.getContext(), VacaplanActivity.class);
////                intent.putExtra(intent_param_post_id, model.getPostID());
////                intent.putExtra(intent_param_post_title, model.getName());
////                intent.putExtra(intent_param_menu_id, model.getMenuID());
////
////                intent.putExtra("name", model.getName());
////                intent.putExtra("location", model.getLocation());
////                intent.putExtra("image", model.getImage());
//
//                view.getContext().startActivity(intent);
//            }
//        });


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

    @Override
    public void onAsyncHttpResponseGet(String response, String url) throws JSONException {
        Log.d("TAG", "onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
        if (url.equals(RestApis.KarmaGroups.vacapediaPlans/* + "/" + ID_FAMILY*/)) {
            Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
//            // dataDestinations = new JSONArray(response);
//            JSONObject job = new JSONObject("{" +
//                    "\"_id\"" +
//                    "}");
//            dataDestinations.put(job);

            dataUserFamily = new JSONArray(response);

            processData();
        }
    }


    private void processData() {


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
                destinationsArrayList.add(currentUser);
            }
//            destinationsArrayListBuffer = destinationsArrayList;
            PlanAdapter myAdapter = new PlanAdapter(this.getActivity(), destinationsArrayList);
            mRecyclerView.setAdapter(myAdapter);
//            guestDestinationsAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}