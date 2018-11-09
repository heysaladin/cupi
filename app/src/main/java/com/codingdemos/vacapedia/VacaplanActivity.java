package com.codingdemos.vacapedia;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.data.DestinationsModel;
import com.codingdemos.vacapedia.handlers.DestinationsLineAdapter;
import com.codingdemos.vacapedia.handlers.DestinationsTimeLineAdapter;
import com.codingdemos.vacapedia.handlers.SliderAdapter;
import com.codingdemos.vacapedia.network.ApiServices;
import com.codingdemos.vacapedia.network.InitLibrary;
import com.codingdemos.vacapedia.response.ResponseRoute;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.codingdemos.vacapedia.rest.RestApis;
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

//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.GoogleMapOptions;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;

import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VacaplanActivity extends AppCompatActivity implements
        AsyncHttpResponse.AsyncHttpResponseListener,
        OnMapReadyCallback {

    Toolbar mToolbar;

//    private String lat = "0.0";
//    private String lng = "0.0";

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

    private GoogleMap googleMap;

    private Button openAutoPlace, openDirectionMap, openOjek;

    private static String nowDestinationsSelected = "";

    private GoogleMap mMap;


//    private String API_KEY = "AIzaSyCiaIGnvo1Bc6WXbiiqy3E2ukbWjWj1VpQ";
    private String API_KEY = "AIzaSyCw96GO7U6nd8CnCVjIISXvgG3T36BKUUY";
//    private String API_KEY = "AIzaSyAT6cY6dg_0KTQtDJ2WCnxLXLxfqKnK6m0";

    private LatLng pickUpLatLng = new LatLng(-6.175110, 106.865039); // Jakarta
    private LatLng locationLatLng = new LatLng(-6.197301,106.795951); // Cirebon

    private TextView tvStartAddress, tvEndAddress, tvDuration, tvDistance;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

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


//    @Override
//    public void onMapReady(GoogleMap map) {
//        googleMap = map;
//        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//    }
//
//
//    private void addmarker(String snippet) {
//        try {
//            googleMap.clear();
//            LatLng latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))));
//            // Zoom in the Google Map
//            googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
//            googleMap.addMarker(new MarkerOptions()
//                    .position(latLng)
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_black_24dp))
//                    .title(String.valueOf(Html.fromHtml(snippet)))
//            );
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vacaplan);





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


//
//        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.dd_contact_us_map_fragment);
////        supportMapFragment.getMapAsync(VacaplanActivity.this);
//        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(GoogleMap googleMap) {
//                googleMap = googleMap;
//
//                //do load your map and other map stuffs here...
//            }
//        });
//        GoogleMapOptions op = new GoogleMapOptions();
//        op.zOrderOnTop(true);
//        SupportMapFragment.newInstance(op);
//
//
//        lat = "-6.175110";
//        lng = "106.865039";
//
//
//
//
//
//        openAutoPlace = (Button) findViewById(R.id.openAutoPlace);
//        openAutoPlace.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openAutoPlace(view);
//            }
//        });
//        openDirectionMap = (Button) findViewById(R.id.openDirectionMap);
//        openDirectionMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openDirectionMap(view);
//            }
//        });
//        openOjek = (Button) findViewById(R.id.openOjek);
//        openOjek.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openOjek(view);
//            }
//        });
//
//
//        // Here, thisActivity is the current activity
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Permission is not granted
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.ACCESS_FINE_LOCATION)) {
//                Toast.makeText(this, "Membutuhkan Izin Lokasi", Toast.LENGTH_SHORT).show();
//            } else {
//
//                // No explanation needed; request the permission
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
//                        1);
//            }
//        } else {
//            // Permission has already been granted
//            Toast.makeText(this, "Izin Lokasi diberikan", Toast.LENGTH_SHORT).show();
//        }





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
        Call<ResponseRoute> routeRequest = api.request_route(lokasiAwal, lokasiAkhir, API_KEY);

        Log.d("LOC", "routeRequest >>>>>>>>>>>>> " + routeRequest);

        Log.d("LOC", "lokasiAwal >>>>>>>>>>>>> " + lokasiAwal);
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


                    mMap.addMarker(new MarkerOptions().position(pickUpLatLng));
                    mMap.addMarker(new MarkerOptions().position(locationLatLng));

                    LatLngBounds.Builder latLongBuilder = new LatLngBounds.Builder();
                    latLongBuilder.include(pickUpLatLng);
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




//
//    public void openAutoPlace(
//            View view
//    ) {
////        startActivity(new Intent(this, PlaceAutoCompleteActivity.class));
//        Intent intent = new Intent(this, PlaceAutoCompleteActivity.class);
//        this.startActivity(intent);
//    }
//
//    public void openDirectionMap(
//            View view
//    ) {
////        startActivity(new Intent(this, DirectionActivity.class));
//        Intent intent = new Intent(this, DirectionActivity.class);
//        this.startActivity(intent);
//    }
//
//    public void openOjek(
//            View view
//    ) {
////        startActivity(new Intent(this, OjekActivity.class));
//        Intent intent = new Intent(this, OjekActivity.class);
//        this.startActivity(intent);
//    }



    private void loadBackdrop() {
        final ImageView imageView = findViewById(R.id.backdrop);
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.drawable.default_image);
        Glide.with(this).load(image).apply(options).into(imageView);
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

//            addmarker("Lokasi");

        }
    }



}
