package com.codingdemos.vacapedia;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.data.PlanModel;
import com.codingdemos.vacapedia.data.PromosModel;
import com.codingdemos.vacapedia.handlers.PlanAdapter;
import com.codingdemos.vacapedia.handlers.PlansLineAdapter;
import com.codingdemos.vacapedia.handlers.PromosAdapter;
import com.codingdemos.vacapedia.handlers.SliderAdapter;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.codingdemos.vacapedia.rest.RestApis;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListPlansActivity
        extends AppCompatActivity
        implements
        AsyncHttpResponse.AsyncHttpResponseListener {

    String image = "image";
    Toolbar mToolbar;
    ImageView mFlower;
    TextView mDescription;

    private JSONArray dataDestinations = null;
    private ArrayList <PlanModel> destinationsArrayListBuffer;
    private ArrayList < PlanModel > destinationsArrayList;
    private SliderAdapter guestDestinationsAdapter;
    private String imageUrl = null;
    RecyclerView mRecyclerView;

    private void getIntentData() {
        Intent intent = getIntent();
        imageUrl = intent.getStringExtra("Image");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        getIntentData();
        getKarmaGroupsApiRequest();
        mToolbar = findViewById(R.id.toolbar);
        mFlower = findViewById(R.id.ivImage);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mDescription = findViewById(R.id.tvDescription);
        mToolbar.setTitle("Notifications");

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mToolbar.setTitle(mBundle.getString("Title"));
            if (imageUrl != null) {
                Glide.with(this)
                        .load(imageUrl)
                        .into(mFlower);
            } else {
                mFlower.setImageResource(mBundle.getInt("Image"));
            }
            mDescription.setText(mBundle.getString("title"));
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_account_box_black_24dp);
            mToolbar.setOverflowIcon(drawable);
        }
    }

    private void processData() {
        try {
            JSONArray dataJson = dataDestinations;
            destinationsArrayListBuffer = new ArrayList < > ();
            destinationsArrayList = new ArrayList < > ();
            mRecyclerView = findViewById(R.id.recyclerview);
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(ListPlansActivity.this);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            destinationsArrayList.clear();
            JSONArray jarry = dataJson;
            ArrayList < PlanModel > dma = new ArrayList < > ();
            dma.clear();
            for (int j = 0; j < jarry.length(); j++) {
                JSONObject job = jarry.getJSONObject(j);
                PlanModel model = new PlanModel();
                model.set_id(job.optString("_id"));
                model.setTitle(job.optString("title"));
                model.setBody_copy(job.optString("body_copy"));
                model.setContent(job.optString("content"));
                model.setTarget_date(job.optString("target_date"));
                model.setCosts(job.optJSONArray("costs"));
                model.setTarget_time(job.optString("target_time"));
                //JSONArray dest = new JSONArray();
                JSONArray jdes = new JSONArray(job.optString("destinations"));
                /*
                Log.d("XXX", "jdes: " + jdes);
                //ArrayList < String > jsarr = new ArrayList < > ();
                StringBuilder jPlain = new StringBuilder();
                for(int z=0; z < jdes.length(); z++){
                    JSONObject jdesob = jdes.getJSONObject(z);
                    //jsarr.add(jdesob.getString("_id"));
                    jPlain.append(jdesob.getString("_id"));
                }
                Log.d("XXX", "jPlain: " + jPlain);
                //String jarrClean = String.valueOf(String.valueOf(jsarr).trim().replaceAll("\"","").split("\\s*,\\s*"));
                //dest.put(String.valueOf(destinations.getText()).trim());
//                String[] animalsArray = String.valueOf(jPlain).trim().replaceAll("\"","").split("\\s*,\\s*");
                String[] animalsArray = String.valueOf(jPlain).trim().replaceAll("\"","").split("\\s*,\\s*");
                Log.d("XXX", "animalsArray: " + animalsArray);
                for(int i=0; i < animalsArray.length; i++){
                    dest.put((animalsArray[i]).replaceAll("\"",""));
                }
                Log.d("XXX", "dest: " + dest);
                */
                model.setDestinations(jdes);
//                model.setImage(job.optString("image"));
                // model.setCategory(job.optString("category"));
//                jobjContactDetails.put("body_copy", String.valueOf(body_copy.getText()).trim());
//                jobjContactDetails.put("content", String.valueOf(content.getText()).trim());
//                jobjContactDetails.put("target_date", String.valueOf(target_date.getText()).trim());
//                jobjContactDetails.put("target_time", String.valueOf(target_time.getText()).trim());
//                jobjContactDetails.put("destinations", dest);
                dma.add(model);
                destinationsArrayList.add(model);
            }
            destinationsArrayListBuffer = destinationsArrayList;
            PlansLineAdapter myAdapter = new PlansLineAdapter(ListPlansActivity.this, destinationsArrayListBuffer );
            mRecyclerView.setAdapter(myAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getKarmaGroupsApiRequest() {
        AsyncHttpResponse response = new AsyncHttpResponse(this, false);
        RequestParams params = new RequestParams();
        response.getAsyncHttp(RestApis.KarmaGroups.vacapediaPlans, params);
    }

    @Override
    public void onAsyncHttpResponseGet(String response, String url) throws JSONException {
        Log.d("TAG", "onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
        if (url.equals(RestApis.KarmaGroups.vacapediaPlans)) {
            Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
            dataDestinations = new JSONArray(response);
            processData();
        }
    }

}
