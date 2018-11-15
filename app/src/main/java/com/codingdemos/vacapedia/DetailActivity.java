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

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private String name = null;
    private String image = null;
    private String description = null;
    private String _id = null;
    private String category = null;
    private String location = null;
    private String latitude = "0";
    private String longitude = "0";
    private String address = null;
    private String distance = null;
    private String note = null;
    private String costs = null;
    private String total_cost = null;
    private GoogleMap mMap;
    private FloatingActionButton appbar;
    private String API_KEY = "AIzaSyCw96GO7U6nd8CnCVjIISXvgG3T36BKUUY";
    private LatLng locationLatLng = new LatLng(-8.677393, 115.207279);
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
        if (parseDoubleFromString(latitude) != Double.parseDouble("0") || parseDoubleFromString(longitude) != Double.parseDouble("0")) {
            locationLatLng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)); // Cirebon
        }
        TextView description_tv = (TextView) findViewById(R.id.description);
        description_tv.setText(description);
    }

    private Double parseDoubleFromString(String s) {
        // Log.d("LOC", "s >>>>>>>>>>>>> " + s);
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

    public static final String EXTRA_NAME = "cheese_name";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cheese);
        getIntentData();

        // Maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        widgetInit();
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
    }

    private void widgetInit() {
        tvStartAddress = findViewById(R.id.tvStartAddress);
        tvEndAddress = findViewById(R.id.tvEndAddress);
        tvDuration = findViewById(R.id.tvDuration);
        tvDistance = findViewById(R.id.tvDistance);
    }

    private void actionRoute() {
        final String lokasiAkhir = locationLatLng.latitude + "," + locationLatLng.longitude;
        // Panggil Retrofit
        ApiServices api = InitLibrary.getInstance();
        // Siapkan request
        Call < ResponseRoute > routeRequest = api.request_route(lokasiAkhir, lokasiAkhir, API_KEY);
        // Log.d("LOC", "lokasiAkhir >>>>>>>>>>>>> " + lokasiAkhir);
        // Log.d("LOC", "API_KEY >>>>>>>>>>>>> " + API_KEY);
        // kirim request
        routeRequest.enqueue(new Callback < ResponseRoute > () {
            @Override
            public void onResponse(Call < ResponseRoute > call, Response < ResponseRoute > response) {
                if (response.isSuccessful()) {
                    // tampung response ke variable
                    ResponseRoute dataDirection = response.body();
                    // Log.d("LOC", "response.raw().request().url() >>>>>>>>>>>>> " + response.raw().request().url());
                    // Log.d("LOC", "response.body() >>>>>>>>>>>>> " + response.body());
                    // Log.d("LOC", "dataDirection >>>>>>>>>>>>> " + dataDirection);
                    mMap.addMarker(new MarkerOptions().position(locationLatLng));
                    LatLngBounds.Builder latLongBuilder = new LatLngBounds.Builder();
                    latLongBuilder.include(locationLatLng);
                    // Bounds Coordinata
                    LatLngBounds bounds = latLongBuilder.build();
                    int width = getResources().getDisplayMetrics().widthPixels;
                    int height = 250;
                    int paddingMap = 50;
                    CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, paddingMap);
                    mMap.animateCamera(cu);
                }
            }

            @Override
            public void onFailure(Call < ResponseRoute > call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadBackdrop() {
        final ImageView imageView = findViewById(R.id.backdrop);
        Glide.with(this).load(image).into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}