package com.codingdemos.vacapedia.fragments;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.DestinationsModel;
import com.codingdemos.vacapedia.DetailActivity;
import com.codingdemos.vacapedia.GuestDestinationsAdapter;
import com.codingdemos.vacapedia.MainActivity;
import com.codingdemos.vacapedia.MyLineAdapter;
import com.codingdemos.vacapedia.SwipeController;
import com.codingdemos.vacapedia.SwipeControllerActions;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.codingdemos.vacapedia.rest.RestApis;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DestinationsEditPageFragment
        extends Fragment
        implements
        AsyncHttpResponse.AsyncHttpResponseListener {

    RecyclerView mRecyclerView;
    SwipeController swipeController = null;
    String image = "image";
    String name = "name";

    private View view;
    private JSONArray dataDestinations = null;
    private ArrayList < DestinationsModel > destinationsArrayListBuffer;
    private ArrayList < DestinationsModel > destinationsArrayList;
    private GuestDestinationsAdapter guestDestinationsAdapter;

    public static DestinationsEditPageFragment newInstance() {
        DestinationsEditPageFragment fragment = new DestinationsEditPageFragment();
        return fragment;
    }

    public DestinationsEditPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.d("LOG", "onCreate one");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("LOG", "onCreateView one");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_one, container, false);
        getKarmaGroupsApiRequest();
        return view;
    }

    private void callEdit(int position) {
        Intent mIntent = new Intent(DestinationsEditPageFragment.this.getActivity(), DetailActivity.class);
        mIntent.putExtra("Title", destinationsArrayListBuffer.get(position).getName());
        mIntent.putExtra("Description", "description");
        mIntent.putExtra("Image", destinationsArrayListBuffer.get(position).getImage());
        DestinationsEditPageFragment.this.getActivity().startActivity(mIntent);
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
        inflater.inflate(R.menu.menu_edit, menu);
    }

    public void onResume() {
        super.onResume();
        Log.d("LOG", "RESUME one");
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            ((MainActivity) getActivity()).setActionBarTitle("Edit Data");
            Log.d("LOG", "one");
        }
    }

    private void processData() {
        try {
            JSONArray dataJson = dataDestinations;
            destinationsArrayListBuffer = new ArrayList < > ();
            destinationsArrayList = new ArrayList < > ();
            mRecyclerView = view.findViewById(R.id.recyclerview);
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(view.getContext());
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
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
                dma.add(model);
                destinationsArrayList.add(model);
            }
            destinationsArrayListBuffer = destinationsArrayList;
            final MyLineAdapter myAdapter = new MyLineAdapter(view.getContext(), destinationsArrayListBuffer);
            mRecyclerView.setAdapter(myAdapter);
            swipeController = new SwipeController(new SwipeControllerActions() {
                @Override
                public void onRightClicked(int position) {
                    destinationsArrayListBuffer.remove(position);
                    myAdapter.notifyItemRemoved(position);
                    myAdapter.notifyItemRangeChanged(position, myAdapter.getItemCount());
                }
                @Override
                public void onCenterClicked(int position) {
                    Log.d("LOG", "position" + position);
                    callEdit(position);
                    myAdapter.notifyItemChanged(position);
                    myAdapter.notifyItemRangeChanged(position, myAdapter.getItemCount());
                }
                @Override
                public void onLeftClicked(int position) {}
            });
            ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
            itemTouchhelper.attachToRecyclerView(mRecyclerView);
            mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                    swipeController.onDraw(c);
                }
            });
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
