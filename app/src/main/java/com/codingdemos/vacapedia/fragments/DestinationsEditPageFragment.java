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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.NoteActivity;
import com.codingdemos.vacapedia.NotesActivity;
import com.codingdemos.vacapedia.data.DestinationsModel;
import com.codingdemos.vacapedia.EditDestinationActivity;
import com.codingdemos.vacapedia.handlers.GuestDestinationsAdapter;
import com.codingdemos.vacapedia.MainActivity;
import com.codingdemos.vacapedia.handlers.MyLineAdapter;
import com.codingdemos.vacapedia.handlers.SwipeController;
import com.codingdemos.vacapedia.handlers.SwipeControllerActions;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.codingdemos.vacapedia.rest.RestApis;
import com.codingdemos.vacapedia.widgets.BusyDialog;
import com.google.android.gms.common.api.Response;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class DestinationsEditPageFragment
        extends Fragment
        implements
        AsyncHttpResponse.AsyncHttpResponseListener {

    private RecyclerView mRecyclerView;
    private SwipeController swipeController = null;
    private String image = "image";
    private String name = "name";
    private View view;
    private JSONArray dataDestinations = null;
    private ArrayList < DestinationsModel > destinationsArrayListBuffer;
    private ArrayList < DestinationsModel > destinationsArrayList;
    private BusyDialog progressDialog;

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
        Intent mIntent = new Intent(DestinationsEditPageFragment.this.getActivity(), EditDestinationActivity.class);
        mIntent.putExtra("_id", destinationsArrayListBuffer.get(position).get_id());
        mIntent.putExtra("name", destinationsArrayListBuffer.get(position).getName());
        mIntent.putExtra("image", destinationsArrayListBuffer.get(position).getImage());
        mIntent.putExtra("category", destinationsArrayListBuffer.get(position).getCategory());
        mIntent.putExtra("location", destinationsArrayListBuffer.get(position).getLocation());
        mIntent.putExtra("description", destinationsArrayListBuffer.get(position).getDescription());
        mIntent.putExtra("latitude", destinationsArrayListBuffer.get(position).getLatitude());
        mIntent.putExtra("longitude", destinationsArrayListBuffer.get(position).getLongitude());
        mIntent.putExtra("address", destinationsArrayListBuffer.get(position).getAddress());
        mIntent.putExtra("distance", destinationsArrayListBuffer.get(position).getDistance());
        mIntent.putExtra("note", destinationsArrayListBuffer.get(position).getNote());
        mIntent.putExtra("costs", destinationsArrayListBuffer.get(position).getCosts());
        mIntent.putExtra("total_cost", destinationsArrayListBuffer.get(position).getTotal_cost());
        DestinationsEditPageFragment.this.getActivity().startActivity(mIntent);
    }

    private void callDelete(int position) {
        String idToDelete = destinationsArrayListBuffer.get(position).get_id();
        String url = RestApis.KarmaGroups.vacapediaDestinations + "/" + idToDelete;
        Log.d(TAG, " url >>>>>>>> : " + url);
        progressDialog = new BusyDialog(getActivity(), false, "");
        progressDialog.show();
        final RequestQueue mRequestQueue;
        // Setup instance
        mRequestQueue = Volley.newRequestQueue(getActivity());
        StringRequest dr = new StringRequest(Request.Method.DELETE, url,
                new com.android.volley.Response.Listener < String > () {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        // response
                        Log.d(TAG, " SUCCESS >>>>>>>> ");
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error.
                        Log.d(TAG, " FAIL >>>>>>>> ");
                    }
                }
        );
        mRequestQueue.add(dr);

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
            final MyLineAdapter myAdapter = new MyLineAdapter(view.getContext(), destinationsArrayListBuffer);
            mRecyclerView.setAdapter(myAdapter);
            swipeController = new SwipeController(new SwipeControllerActions() {
                @Override
                public void onRightClicked(int position) {
                    callDelete(position);
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
