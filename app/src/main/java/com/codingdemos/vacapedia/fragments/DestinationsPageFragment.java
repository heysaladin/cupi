package com.codingdemos.vacapedia.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.data.DestinationsModel;
import com.codingdemos.vacapedia.handlers.GuestDestinationsAdapter;
import com.codingdemos.vacapedia.MainActivity;
import com.codingdemos.vacapedia.handlers.MyAdapter;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.codingdemos.vacapedia.rest.RestApis;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DestinationsPageFragment extends Fragment
        implements
        AsyncHttpResponse.AsyncHttpResponseListener {

    String image = "image";
    String name = "name";
    RecyclerView mRecyclerView;

    private int fragment = 0;
    private View view;
    private JSONArray dataDestinations = null;
    private ArrayList < DestinationsModel > destinationsArrayListBuffer;
    private ArrayList < DestinationsModel > destinationsArrayList;
    private GuestDestinationsAdapter guestDestinationsAdapter;

    public static DestinationsPageFragment newInstance() {
        DestinationsPageFragment fragment = new DestinationsPageFragment();
        return fragment;
    }

    public int getFragment() {
        return fragment;
    }

    public void setFragment(int fragment) {
        this.fragment = fragment;
    }

    public DestinationsPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.d("LOG", "onCreate two");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("LOG", "onCreateView two");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_two, container, false);
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
        inflater.inflate(R.menu.menu_one, menu);
    }

    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Destinations");
        ((MainActivity) getActivity()).setFragment(1);
        this.setFragment(1);
        Log.d("LOG", "RESUME two");
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            if (this.getFragment() != 0) {
                ((MainActivity) getActivity()).setActionBarTitle("Destinations");
                ((MainActivity) getActivity()).setFragment(1);
            }
            Log.d("LOG", "two");
        }
    }

    private void processData() {
        try {
            JSONArray dataJson = dataDestinations;
            destinationsArrayListBuffer = new ArrayList < > ();
            destinationsArrayList = new ArrayList < > ();
            mRecyclerView = view.findViewById(R.id.recyclerview);
            GridLayoutManager mGridLayoutManager = new GridLayoutManager(view.getContext(), 2);
            mRecyclerView.setLayoutManager(mGridLayoutManager);
            destinationsArrayList.clear();
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
                destinationsArrayList.add(model);
            }
            destinationsArrayListBuffer = destinationsArrayList;
            MyAdapter myAdapter = new MyAdapter(view.getContext(), destinationsArrayListBuffer);
            mRecyclerView.setAdapter(myAdapter);
            guestDestinationsAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getKarmaGroupsApiRequest() {
        AsyncHttpResponse response = new AsyncHttpResponse(this, false);
        RequestParams params = new RequestParams();
        response.getAsyncHttp(RestApis.KarmaGroups.vacapediaDestinations, params);
    }


    @Override
    public void onAsyncHttpResponseGet(String response, String url) throws JSONException {
        Log.d("TAG", "onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
        if (url.equals(RestApis.KarmaGroups.vacapediaDestinations)) {
            Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
            dataDestinations = new JSONArray(response);
            processData();
        }
    }

}
