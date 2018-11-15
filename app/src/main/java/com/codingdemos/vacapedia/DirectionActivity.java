package com.codingdemos.vacapedia;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.network.ApiServices;
import com.codingdemos.vacapedia.network.InitLibrary;
import com.codingdemos.vacapedia.response.ResponseRoute;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DirectionActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String API_KEY = "AIzaSyCiaIGnvo1Bc6WXbiiqy3E2ukbWjWj1VpQ";
    private LatLng pickUpLatLng = new LatLng(-6.175110, 106.865039); // Jakarta
    private LatLng locationLatLng = new LatLng(-6.197301, 106.795951); // Cirebon
    private TextView tvStartAddress, tvEndAddress, tvDuration, tvDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        // Maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        widgetInit();
        // jalankan method
        actionRoute();
    }

    private void widgetInit() {
        tvStartAddress = findViewById(R.id.tvStartAddress);
        tvEndAddress = findViewById(R.id.tvEndAddress);
        tvDuration = findViewById(R.id.tvDuration);
        tvDistance = findViewById(R.id.tvDistance);
    }

    private void actionRoute() {
        final String lokasiAwal = pickUpLatLng.latitude + "," + pickUpLatLng.longitude;
        final String lokasiAkhir = locationLatLng.latitude + "," + locationLatLng.longitude;

        // Panggil Retrofit
        ApiServices api = InitLibrary.getInstance();
        // Siapkan request
        Call < ResponseRoute > routeRequest = api.request_route(lokasiAwal, lokasiAkhir, API_KEY);

        // Log.d("LOC", "routeRequest >>>>>>>>>>>>> " + routeRequest);
        // Log.d("LOC", "lokasiAwal >>>>>>>>>>>>> " + lokasiAwal);
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
                    mMap.addMarker(new MarkerOptions().position(pickUpLatLng));
                    mMap.addMarker(new MarkerOptions().position(locationLatLng));
                    LatLngBounds.Builder latLongBuilder = new LatLngBounds.Builder();
                    latLongBuilder.include(pickUpLatLng);
                    latLongBuilder.include(locationLatLng);

                    // Bounds Coordinata
                    LatLngBounds bounds = latLongBuilder.build();

                    int width = getResources().getDisplayMetrics().widthPixels;
                    int height = getResources().getDisplayMetrics().heightPixels;
                    int paddingMap = (int)(width * 0.2); //jarak dari
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

}