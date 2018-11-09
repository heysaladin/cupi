package com.codingdemos.vacapedia;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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
import com.codingdemos.vacapedia.fragments.DestinationsEditPageFragment;
import com.codingdemos.vacapedia.network.ApiServices;
import com.codingdemos.vacapedia.network.InitLibrary;
import com.codingdemos.vacapedia.response.ResponseRoute;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements
        OnMapReadyCallback {

    Toolbar mToolbar;
    ImageView mFlower;
    TextView mDescription;

    private String name = null;
    private String image = null;
    private String description = null;


    private String _id = null;
    private String category = null;
    private String location = null;
    private String latitude = null;
    private String longitude = null;
    private String address = null;
    private String distance = null;
    private String note = null;
    private String costs = null;
    private String total_cost = null;


    private GoogleMap mMap;
    private FloatingActionButton appbar;


    //    private String API_KEY = "AIzaSyCiaIGnvo1Bc6WXbiiqy3E2ukbWjWj1VpQ";
    private String API_KEY = "AIzaSyCw96GO7U6nd8CnCVjIISXvgG3T36BKUUY";
//    private String API_KEY = "AIzaSyAT6cY6dg_0KTQtDJ2WCnxLXLxfqKnK6m0";

//    private LatLng pickUpLatLng = new LatLng(-6.175110, 106.865039); // Jakarta
    private LatLng locationLatLng = new LatLng(-6.197301,106.795951); // Cirebon

    private TextView tvStartAddress, tvEndAddress, tvDuration, tvDistance;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }



    private void getIntentData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        image = intent.getStringExtra("image");
        description = intent.getStringExtra("description");

        _id = intent.getStringExtra("_id");
        category = intent.getStringExtra("category");
        location = intent.getStringExtra("location");
        latitude = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");
        address = intent.getStringExtra("address");
        distance = intent.getStringExtra("distance");
        note = intent.getStringExtra("note");
        costs = intent.getStringExtra("costs");
        total_cost = intent.getStringExtra("total_cost");

        TextView description_tv = (TextView) findViewById(R.id.description);
        description_tv.setText(description);

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//
//        getIntentData();
//
//        mToolbar = findViewById(R.id.toolbar);
//        mFlower = findViewById(R.id.ivImage);
//        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
//        mDescription = findViewById(R.id.tvDescription);
//
//        Bundle mBundle = getIntent().getExtras();
//        if (mBundle != null) {
//            mToolbar.setTitle(mBundle.getString("Title"));
//            if (imageUrl != null) {
//                Glide.with(this)
//                        .load(imageUrl)
//                        .into(mFlower);
//            } else {
//                mFlower.setImageResource(mBundle.getInt("Image"));
//            }
//            mDescription.setText(mBundle.getString("Description"));
//
//            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_account_box_black_24dp);
//            mToolbar.setOverflowIcon(drawable);
//
//        }
//    }



    public static final String EXTRA_NAME = "cheese_name";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cheese);
        // Back button
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true)

        getIntentData();





        // Maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // Set Title bar
//        getSupportActionBar().setTitle("Direction Maps API");
        // Inisialisasi Widget
        widgetInit();
        // jalankan method
        actionRoute();




        appbar = findViewById(R.id.float_btn);
        appbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(DetailActivity.this, EditDestinationActivity.class);
                mIntent.putExtra("_id", _id);
                mIntent.putExtra("name", name);
                mIntent.putExtra("image", image);
                mIntent.putExtra("category", category);
                mIntent.putExtra("location", location);
                mIntent.putExtra("description", description);
                mIntent.putExtra("latitude", latitude);
                mIntent.putExtra("longitude", longitude);
                mIntent.putExtra("address", address);
                mIntent.putExtra("distance", distance);
                mIntent.putExtra("note", note);
                mIntent.putExtra("costs", costs);
                mIntent.putExtra("total_cost", total_cost);
                DetailActivity.this.startActivity(mIntent);
            }
        });

        Intent intent = getIntent();
        final String cheeseName = intent.getStringExtra(EXTRA_NAME);

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

                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0)
                {
                    // Collapsed
                    overlay.setVisibility(View.GONE);
                }
                else
                {
                    // Expanded
                    overlay.setVisibility(View.VISIBLE);
                }
            }
        });


        loadBackdrop();
    }




    private void widgetInit() {
        tvStartAddress = findViewById(R.id.tvStartAddress);
        tvEndAddress = findViewById(R.id.tvEndAddress);
        tvDuration = findViewById(R.id.tvDuration);
        tvDistance = findViewById(R.id.tvDistance);
    }

    private void actionRoute() {
//        final String lokasiAwal = pickUpLatLng.latitude + "," + pickUpLatLng.longitude;
        final String lokasiAkhir = locationLatLng.latitude + "," + locationLatLng.longitude;

        // Panggil Retrofit
        ApiServices api = InitLibrary.getInstance();
        // Siapkan request
        Call<ResponseRoute> routeRequest = api.request_route(lokasiAkhir, lokasiAkhir, API_KEY);

//        Log.d("LOC", "routeRequest >>>>>>>>>>>>> " + routeRequest);

//        Log.d("LOC", "lokasiAwal >>>>>>>>>>>>> " + lokasiAwal);
        Log.d("LOC", "lokasiAkhir >>>>>>>>>>>>> " + lokasiAkhir);
        Log.d("LOC", "API_KEY >>>>>>>>>>>>> " + API_KEY);

        // kirim request
        routeRequest.enqueue(new Callback<ResponseRoute>() {
            @Override
            public void onResponse(Call<ResponseRoute> call, Response<ResponseRoute> response) {

                if (response.isSuccessful()){
                    // tampung response ke variable
                    ResponseRoute dataDirection = response.body();

                    Log.d("LOC", "response.raw().request().url() >>>>>>>>>>>>> " + response.raw().request().url());


//                    RequestQueue queue = Volley.newRequestQueue(this);
//                    final String url = response.raw().request().url();
//
//// prepare the Request
//                    JsonObjectRequest getRequest = new JsonObjectRequest(DownloadManager.Request.Method.GET, url, null,
//                            new Response.Listener<JSONObject>()
//                            {
//                                @Override
//                                public void onResponse(JSONObject response) {
//                                    // display response
//                                    Log.d("Response", response.toString());
//                                }
//                            },
//                            new Response.ErrorListener()
//                            {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    Log.d("Error.Response", response);
//                                }
//                            }
//                    );
//
//// add it to the RequestQueue
//                    queue.add(getRequest);


                    Log.d("LOC", "response.body() >>>>>>>>>>>>> " + response.body());
                    Log.d("LOC", "dataDirection >>>>>>>>>>>>> " + dataDirection);

//                    LegsItem dataLegs = dataDirection.getRoutes().get(0).getLegs().get(0);
//
//                    // Dapatkan garis polyline
//                    String polylinePoint = dataDirection.getRoutes().get(0).getOverviewPolyline().getPoints();
//                    // Decode
//                    List<LatLng> decodePath = PolyUtil.decode(polylinePoint);
//                    // Gambar garis ke maps
//                    mMap.addPolyline(new PolylineOptions().addAll(decodePath)
//                            .width(8f).color(Color.argb(255, 56, 167, 252)))
//                            .setGeodesic(true);
//
//                    // Tambah Marker
//                    mMap.addMarker(new MarkerOptions().position(pickUpLatLng).title("Lokasi Awal"));
//                    mMap.addMarker(new MarkerOptions().position(locationLatLng).title("Lokasi Akhir"));
//                    // Dapatkan jarak dan waktu
//                    Distance dataDistance = dataLegs.getDistance();
//                    Duration dataDuration = dataLegs.getDuration();
//
//                    // Set Nilai Ke Widget
//                    tvStartAddress.setText("start location : " + dataLegs.getStartAddress().toString());
//                    tvEndAddress.setText("end location : " + dataLegs.getEndAddress().toString());
//
//                    tvDistance.setText("distance : " + dataDistance.getText() + " (" + dataDistance.getValue() + ")");
//                    tvDuration.setText("duration : " + dataDuration.getText() + " (" + dataDuration.getValue() + ")");
//                    /** START
//                     * Logic untuk membuat layar berada ditengah2 dua koordinat
//                     */


//                    mMap.addMarker(new MarkerOptions().position(pickUpLatLng));
                    mMap.addMarker(new MarkerOptions().position(locationLatLng));

                    LatLngBounds.Builder latLongBuilder = new LatLngBounds.Builder();
//                    latLongBuilder.include(pickUpLatLng);
                    latLongBuilder.include(locationLatLng);

                    // Bounds Coordinata
                    LatLngBounds bounds = latLongBuilder.build();

                    int width = getResources().getDisplayMetrics().widthPixels;
                    int height = getResources().getDisplayMetrics().heightPixels;
                    int paddingMap = (int) (width * 0.2); //jarak dari
                    CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, paddingMap);
                    mMap.animateCamera(cu);

                    /** END
                     * Logic untuk membuat layar berada ditengah2 dua koordinat
                     */

                }
            }

            @Override
            public void onFailure(Call<ResponseRoute> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }





    private void loadBackdrop() {
        final ImageView imageView = findViewById(R.id.backdrop);
        Glide.with(this).load(image).into(imageView);
//        Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).apply(RequestOptions.centerCropTransform()).into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}