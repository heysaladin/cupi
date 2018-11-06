package com.codingdemos.vacapedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.data.DestinationsModel;
import com.codingdemos.vacapedia.handlers.DestinationsLineAdapter;
import com.codingdemos.vacapedia.handlers.DestinationsTimeLineAdapter;
import com.codingdemos.vacapedia.handlers.SliderAdapter;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.codingdemos.vacapedia.rest.RestApis;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VacaplanActivity extends AppCompatActivity implements
        AsyncHttpResponse.AsyncHttpResponseListener {

    Toolbar mToolbar;

    private String name = null;
    private String image = null;


    private TextView body_copy_tv, content_tv, note_tv, costs_tv;

    private String id = null;
    private String titleString = null;
    //    private String imageString = null;
//    private String categoryString = null;
    private String body_copyString = null;
    private String contentString = null;
    private String target_dateString = null;
    private String target_timeString = null;
    private static String destinationsString = null;

    private JSONArray desPlan = null;
    private JSONArray dataDestinations = null;
    private ArrayList<DestinationsModel> destinationsArrayListBuffer;
    private ArrayList < DestinationsModel > destinationsArrayList;
    private SliderAdapter guestDestinationsAdapter;
    private String imageUrl = null;
    private RecyclerView mRecyclerView;
    private String restaurantName = "";

    private static String nowDestinationsSelected = "";


    private void getIntentData() {
        Intent intent = this.getIntent();

        name = intent.getStringExtra("name");
        image = intent.getStringExtra("image");

        id = intent.getStringExtra("_id");
        titleString = intent.getStringExtra("title");
//        imageString = intent.getStringExtra("image");
//        categoryString = intent.getStringExtra("category");
        body_copyString = intent.getStringExtra("body_copy");
        contentString = intent.getStringExtra("content");
        target_dateString = intent.getStringExtra("target_date");
        target_timeString = intent.getStringExtra("target_time");
        //destinationsString = intent.getStringExtra("destinations");

        JSONArray jdes = null;
        try {
            jdes = new JSONArray(intent.getStringExtra("destinations"));

            desPlan = jdes;


            Log.d("XXX", "jdes: " + jdes);
            //ArrayList < String > jsarr = new ArrayList < > ();
            StringBuilder jPlain = new StringBuilder();
            for(int z=0; z < jdes.length(); z++){
                JSONObject jdesob = jdes.getJSONObject(z);
                //jsarr.add(jdesob.getString("_id"));
                jPlain.append(jdesob.getString("_id"));
                if(z!=jdes.length()-1){
                    jPlain.append(",");
                }
            }
            Log.d("XXX", "jPlain: " + jPlain);
            destinationsString = jPlain.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("TAG", "target_dateString = [" + target_dateString + "]");
        Log.d("TAG", "target_timeString = [" + target_timeString + "]");
        Log.d("TAG", "destinationsString = [" + destinationsString + "]");
    }


    public static final String EXTRA_NAME = "cheese_name";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vacaplan);

        getIntentData();

        Intent intent = getIntent();
        final String cheeseName = intent.getStringExtra(EXTRA_NAME);

        body_copy_tv = findViewById(R.id.body_copy);
        content_tv = findViewById(R.id.content);
        note_tv = findViewById(R.id.note);
        costs_tv = findViewById(R.id.costs);

        body_copy_tv.setText(body_copyString);
        content_tv.setText(contentString);
        note_tv.setText("some note");
        costs_tv.setText("0");


        final Toolbar toolbar = findViewById(R.id.toolbar_collapse);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(cheeseName);

        final LinearLayout overlay = findViewById(R.id.overlay);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                    // Collapsed
                    overlay.setVisibility(View.GONE);
                } else {
                    // Expanded
                    overlay.setVisibility(View.VISIBLE);
                }
            }
        });

        loadBackdrop();


        getKarmaGroupsApiRequest();



    }

    private void loadBackdrop() {
        final ImageView imageView = findViewById(R.id.backdrop);
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.drawable.default_image);
        Glide.with(this).load(image).apply(options).into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }




    private void processData() {
        try {
            JSONArray dataJson = desPlan;
            destinationsArrayListBuffer = new ArrayList < > ();
            destinationsArrayList = new ArrayList < > ();
            mRecyclerView = findViewById(R.id.recyclerview);
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(VacaplanActivity.this);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            destinationsArrayList.clear();
            JSONArray jarry = dataJson;
            ArrayList <DestinationsModel> dma = new ArrayList < > ();
            dma.clear();
            for (int j = 0; j < jarry.length(); j++) {
                JSONObject job = jarry.getJSONObject(j);

                DestinationsModel model = new DestinationsModel();
                model.setMenuID(String.valueOf(j));
                model.setMenuName("nama" + j);
                model.setName(job.optString("name"));
                model.setPostID(job.optString("id"));
                model.setImage(job.optString("image"));


                dma.add(model);
                destinationsArrayList.add(model);
            }
            destinationsArrayListBuffer = destinationsArrayList;
            DestinationsTimeLineAdapter myAdapter = new DestinationsTimeLineAdapter(VacaplanActivity.this, destinationsArrayListBuffer );
            mRecyclerView.setAdapter(myAdapter);

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
