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
import com.codingdemos.vacapedia.data.DestinationsModel;
import com.codingdemos.vacapedia.handlers.PlanAdapter;
import com.codingdemos.vacapedia.data.PlanModel;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.codingdemos.vacapedia.rest.RestApis;
import com.google.android.gms.maps.model.LatLng;
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
    private ArrayList < PlanModel > destinationsArrayListBuff;
    private ArrayList < PlanModel > destinationsArrayList;
    private RecyclerView mRecyclerView;
    private PlanModel currentUser;
    private JSONArray jsonArrayUsersFamily;
    private List < String > stringImages;
    private List < String > stringLocations;
    private List < String > intCost;
    private int cost = 0;


    private JSONArray desPoints = new JSONArray();


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



    private Double parseDoubleFromString(String s) {

        Log.d("LOC", "s >>>>>>>>>>>>> " + s);

        String sinit = "0";

        try {
            if (s == "null" || s == null || s == "") {
                sinit = s;
                return Double.parseDouble(sinit);
            } else {
                return Double.parseDouble(s);
            }
        } catch (NumberFormatException e) {
            return Double.parseDouble("0");
        }
    }

    private void getImageBanner(final JSONArray dataPlanParam) {

        try {

            destinationsArrayListBuff = new ArrayList < > ();
            destinationsArrayListBuff.clear();
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
                stringLocations = new ArrayList < > ();
                intCost = new ArrayList < > ();
                stringImages.clear();
                stringLocations.clear();
                intCost.clear();
                desPoints = new JSONArray();
                cost = 0;
                for (int k = 0; k < jsonArrayUsersFamily.length(); k++) {
                    JSONObject jobk = jsonArrayUsersFamily.getJSONObject(k);
                    String imgNow = jobk.optString("image");
                    stringImages.add(imgNow);
                    String locationNow = jobk.optString("location");
                    stringLocations.add(locationNow);
                    String costNow = jobk.optString("total_cost");
                    intCost.add(costNow);
//
//                    String latItem = jobk.optString("latitude");
//                    String longItem = jobk.optString("longitude");
//                    Log.d("LOG", "latItem >>>>>>>>> " + latItem);
//                    Log.d("LOG", "longItem >>>>>>>>> " + longItem);
//
////                    if (parseDoubleFromString(latItem)!=Double.parseDouble("0")) {
////                        latItem = "-8.677335";
////                    }
////                    if (parseDoubleFromString(longItem)!=Double.parseDouble("0")) {
////                        longItem = "115.2070699";
////                    }
//
////                    LatLng desItemPoin = new LatLng(parseDoubleFromString(latItem), parseDoubleFromString(longItem));
//
//                    JSONObject desItemPoin = new JSONObject("{" +
//                            "\"latitude\":\"" + latItem +"\", " +
//                            "\"longitude\":\"" + longItem + "\"" +
//                            "}");
//
//                    desPoints.put(desItemPoin);
//                    Log.d("LOG", "desPoints >>>>>>>>> DDDDDDDDDD " + desPoints);

                }

                numbers.clear();

                for (int i = 0; i < stringImages.size(); i++) {
                    numbers.add(i);
                }
                Collections.shuffle(numbers);

                for (int c = 0; c < intCost.size(); c++) {
                    cost += parseInteger(intCost.get(c));
                }

                if(stringImages.size()>1) {
                    if (numbers.get(0)!=0) {
                        Log.d("LOG", "numbers >>>>>>>>> " + numbers);
                        currentUser.setImage(stringImages.get(numbers.get(0) - 1));
                        currentUser.setLocation(stringLocations.get(numbers.get(0) - 1));
                    } else {
                        currentUser.setImage(stringImages.get(numbers.get(0)));
                        currentUser.setLocation(stringLocations.get(numbers.get(0)));
                    }
                }else{
                    currentUser.setImage(stringImages.get(0));
                    currentUser.setLocation(stringLocations.get(0));
                }
                currentUser.setCost(String.valueOf(cost));

                destinationsArrayList.add(currentUser);

            }

//            for (int i = 0; i < destinationsArrayList.size(); i++) {
//                numbers.add(i);
//            }
//            Collections.shuffle(numbers);
//
//            //JSONArray destinationsArrayListBuf = new JSONArray();
//            for (int i = 0; i < numbers.size(); i++) {
//                destinationsArrayListBuff.add(destinationsArrayList.get(numbers.get(i)));
//            }

            destinationsArrayListBuff = createRandomList(destinationsArrayList);
            //destinationsArrayListBuff = destinationsArrayList;

            PlanAdapter myAdapter = new PlanAdapter(this.getActivity(), destinationsArrayListBuff
//                    , jsonArrayUsersFamily
            );
            mRecyclerView.setAdapter(myAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private ArrayList <PlanModel> createRandomList(ArrayList < PlanModel > listTarget) {
        Log.d("LOG", "listTarget >>>>>>>>> " + listTarget);
        ArrayList < PlanModel > listResult;
        List < Integer > numbers = new ArrayList < > ();
        for (int i = 0; i < listTarget.size(); i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        Log.d("LOG", "createRandomList numbers >>>>>>>>> " + numbers);

        int size = listTarget.size();
        ArrayList < PlanModel > buffer = new ArrayList < PlanModel > ();

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