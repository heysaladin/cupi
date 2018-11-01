package com.codingdemos.vacapedia;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.preference.PreferenceActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.codingdemos.vacapedia.rest.RestApis;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class PlanAdapter extends RecyclerView.Adapter < PlanViewHolder >
        implements
        AsyncHttpResponse.AsyncHttpResponseListener {

    private Context mContext;
    private JSONArray dataUserFamily = null;
    private List< PlanModel > mFlowerList;
    private ArrayList < DestinationsModel > destinationsArrayList;

    private JSONObject dataUser = null;

    private JSONArray jsonArrayUsersFamily;
    private RequestQueue mQueue;

    private List < Integer > numbers;

    private ArrayList < String > listTarget;

    public PlanAdapter(Context mContext, List < PlanModel > mFlowerList) {
        this.mContext = mContext;
        this.mFlowerList = mFlowerList;
    }

    @Override
    public PlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plan, parent, false);

        dataUserFamily = new JSONArray();

        return new PlanViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final PlanViewHolder holder, final int position) {

//        List < Integer >
                numbers = new ArrayList < > ();
        dataUserFamily = new JSONArray();
         Log.d("TAG", "mFlowerList.get(position) >>>>>>>>> " + mFlowerList.get(position));
         if(mFlowerList.get(position).getImage()!="null" || mFlowerList.get(position).getImage()!=null || mFlowerList.get(position).getImage() != "") {
             RequestOptions options = new RequestOptions();
             options.centerCrop();
             Glide.with(mContext)
                     .load(mFlowerList.get(position).getImage().replace(" ", "%20"))
                     .apply(options)
                     .into(holder.mImage);
         }
        holder.mTitle.setText(mFlowerList.get(position).getTitle());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), VacaplanActivity.class);
                view.getContext().startActivity(intent);
    /*
    Intent mIntent = new Intent(mContext, DetailActivity.class);
    mIntent.putExtra("Title", mFlowerList.get(holder.getAdapterPosition()).getTitle());
    mIntent.putExtra("Description", "desc");
    mIntent.putExtra("Image", mFlowerList.get(holder.getAdapterPosition()).getImage());
    mContext.startActivity(mIntent);
    */
            }
        });


//        AsyncHttpResponse response = new AsyncHttpResponse(mContext, false);

//try {
////    ArrayList < String >
//            listTarget = new ArrayList<>();
////    dataUserFamily = new JSONArray();
////    Log.d("TAG", "dataUserFamily XXX >>>>>>>>> " + dataUserFamily);
//////    JSONArray
////    Log.d("TAG", "mFlowerList.get(position).getDestinations() >>>>>>>>> " + mFlowerList.get(position).getDestinations());
//            jsonArrayUsersFamily = mFlowerList.get(position).getDestinations();
////    Log.d("TAG", "jsonArrayUsersFamily >>>>>>>>> " + jsonArrayUsersFamily);
////    Log.d("TAG", "jsonArrayUsersFamily.length() >>>>>>>>> " + jsonArrayUsersFamily.length());
//        for (int k = 0; k < jsonArrayUsersFamily.length(); k++) {
//            JSONObject jobk = jsonArrayUsersFamily.getJSONObject(k);
//            String nowUserId = jobk.optString("destination_id");
//
//
//            String url = RestApis.KarmaGroups.vacapediaDestinations + "/" + nowUserId;
//
//            mQueue = Volley.newRequestQueue(mContext);
//            final int finalK = k;
//            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                                dataUser = response;
//                            Log.d("TAG", "dataUser >>>>>>>>> " + dataUser);
//
//                            try {
//
//                            listTarget.add(String.valueOf( dataUser.getString("image") ));
//
//                            for (int i = 0; i < listTarget.size(); i++) {
//                numbers.add(i);
//            }
//            Collections.shuffle(numbers);
//            Log.d("LOG", "createRandomList numbers >>>>>>>>> " + numbers);
//
//                                    RequestOptions options = new RequestOptions();
//                                    options.centerCrop();
////                                Glide.with(mContext).load(listTarget.get(numbers.get(0)).replace(" ", "%20")).apply(options).into(holder.mImage);
//                                 Glide.with(mContext).load(dataUser.getString("image").replace(" ", "%20")).apply(options).into(holder.mImage);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//
//                            //processData();
//
////                            dataUserFamily.put(dataUser);
////
////
////
//////            if(k<jsonArrayUsersFamily.length()-1 ){
////                            Log.d("TAG", "dataUserFamily lengthx >>>>>>>>> " + dataUserFamily.length());
//////            if (dataUserFamily.length() == (jsonArrayUsersFamily.length()-1)) {
////                            try {
//////                    destinationsArrayList = new ArrayList<>();
//////                    destinationsArrayList.clear();
////                                for (int j = 0; j < dataUserFamily.length(); j++) {
////                                    JSONObject job = dataUserFamily.getJSONObject(j);
////                                    DestinationsModel model = new DestinationsModel();
////                                    model.setName(job.optString("name"));
////                                    model.setImage(job.optString("image"));
////
////                                    model.set_id(job.optString("_id"));
////                                    model.setCategory(job.optString("category"));
////                                    model.setLocation(job.optString("location"));
////                                    model.setDescription(job.optString("description"));
////                                    model.setLatitude(job.optString("latitude"));
////                                    model.setLongitude(job.optString("longitude"));
////                                    model.setAddress(job.optString("address"));
////                                    model.setDistance(job.optString("distance"));
////                                    model.setNote(job.optString("note"));
////                                    model.setCosts(job.optString("costs"));
////                                    model.setTotal_cost(job.optString("total_cost"));
////
//////                        destinationsArrayList.add(model);
////
//////                        Log.d("LOG", "dataUserFamily.length() >>>>>>>>> " + dataUserFamily.length());
//////                        if (j==dataUserFamily.length()-1) {
////                                    RequestOptions options = new RequestOptions();
////                                    options.centerCrop();
//////                            JSONObject jobRand = dataUserFamily.getJSONObject(0);
////                                    Log.d("LOG", "jobRand.getString(\"image\") OOOOOOO >>>>>>>>> " + job.getString("image"));
////                                    Glide.with(mContext).load(job.getString("image").replace(" ", "%20")).apply(options).into(holder.mImage);
//////            Glide.with(mContext).load(dataUser.getString("image").replace(" ", "%20")).apply(options).into(holder.mImage);
//////                        }
////
////                                }
//////                                dataUserFamily = new JSONArray();
////                            } catch (JSONException e) {
////                                e.printStackTrace();
////                            }
//
//
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    error.printStackTrace();
//                }
//            });
//
////            if (k<jsonArrayUsersFamily.length()) {
//            if (k==0) {
//                mQueue.add(request);
//            }
//            } else {
//                dataUserFamily=new JSONArray();
//            }

//            }

//        }

//    } catch (JSONException e) {
//        e.printStackTrace();
//    }

        
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }

//    private JSONObject loadUsers(final PlanViewHolder holder, int position) {
//
//
//        String image = "";
//
//        int cost = 0;
//
//        JSONObject jobFinal = null;
//
//        try {
//
//            ArrayList < String > listTarget = new ArrayList<>();
//
////            JSONArray dataUserFamily = dataUser;
////                    mFlowerList.get(
////                    position).getDestinations();
//
//            destinationsArrayList = new ArrayList<>();
////            destinationsArrayList = getArrayListFromJSONArray(jsonArrayUsersFamily);
////        mRecyclerView = view.findViewById(R.id.recyclerview);
////        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getContext());
////        mRecyclerView.setLayoutManager(mLinearLayoutManager);
//            destinationsArrayList.clear();
//            numbers.clear();
//            listTarget.clear();
//            Log.d("TAG", "dataUserFamily length >>>>>>>>> " + dataUserFamily.length());
//            Log.d("TAG", "dataUserFamily >>>>>>>>> " + dataUserFamily);
//            for (int j = 0; j < dataUserFamily.length(); j++) {
//                JSONObject job = null;
//                job = dataUserFamily.getJSONObject(j);
//                DestinationsModel model = new DestinationsModel();
////            model.setMenuID(String.valueOf(j));
////            model.setMenuName("nama" + j);
//                model.setName(job.optString("name"));
////            model.setPostID(job.optString("id"));
//                model.setImage(job.optString("image"));
//
//                model.set_id(job.optString("_id"));
//                model.setCategory(job.optString("category"));
//                model.setLocation(job.optString("location"));
//                model.setDescription(job.optString("description"));
//                model.setLatitude(job.optString("latitude"));
//                model.setLongitude(job.optString("longitude"));
//                model.setAddress(job.optString("address"));
//                model.setDistance(job.optString("distance"));
//                model.setNote(job.optString("note"));
//                model.setCosts(job.optString("costs"));
//                model.setTotal_cost(job.optString("total_cost"));
//
//                destinationsArrayList.add(model);
//
//                Log.d("TAG", "job.optString(\"image\") >>>>>>>>> " + job.optString("image"));
//                listTarget.add(String.valueOf(job.optString("image")));
//
//            }
//
////        String[] images = new String[0];
//
////            ArrayList < String > listTarget = null;
//
////            for (int j = 0; j < destinationsArrayList.size(); j++) {
////
//////            DestinationsModel dm = new DestinationsModel();
////                listTarget.add(destinationsArrayList.get(j).getImage());
////
////            }
//
//            for (int i = 0; i < listTarget.size(); i++) {
//                numbers.add(i);
//            }
//            Collections.shuffle(numbers);
//            Log.d("LOG", "createRandomList numbers >>>>>>>>> " + numbers);
//
//
////            Glide.with(mContext)
////                    .load(listTarget.get(numbers.get(0)).replace(" ", "%20"))
////                    .into(holder.mImage);
//
////            image = listTarget.get(numbers.get(0));
//            image = listTarget.get(0);
//            cost = 0;
//
//            jobFinal = new JSONObject("{" +
//                    "\"image\":\"" + image + "\","+
//                    "\"cost\":\"" + String.valueOf(cost) + "\"" +
//                    "}");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        return jobFinal;
//
//    }

    @Override
    public void onAsyncHttpResponseGet(String response, String url) throws JSONException {
        Log.d("TAG", "onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
//        if (url.equals(RestApis.KarmaGroups.vacapediaFamilies/* + "/" + ID_FAMILY*/)) {
//            Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
////            // dataDestinations = new JSONArray(response);
////            JSONObject job = new JSONObject("{" +
////                    "\"_id\"" +
////                    "}");
////            dataDestinations.put(job);
//
//            dataDestinations = new JSONArray(response);
//
//            processData();
//        }
//        if (url.contains(RestApis.KarmaGroups.vacapediaDestinations+ "/" /* + ID_FAMILY*/)) {
//            Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
//            dataUser = new JSONObject(response);
//            Log.d("TAG", "dataUser >>>>>>>>> " + dataUser);
//            dataUserFamily.put(dataUser);
//            //processData();
//        }
    }
}

class PlanViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle;
    CardView mCardView;

    PlanViewHolder(View itemView) {
        super(itemView);

        mImage = itemView.findViewById(R.id.image);
        mTitle = itemView.findViewById(R.id.title);
        mCardView = itemView.findViewById(R.id.card_plan);
    }
}
