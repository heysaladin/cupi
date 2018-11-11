package com.codingdemos.vacapedia;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
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
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
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
import java.util.List;

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


    private TextView body_copy_tv, content_tv, note_tv, costs_tv, title_bold_tv, address_tv, target_date_tv;

    private String id = null;
    private String titleString = null;
    //    private String imageString = null;
//    private String categoryString = null;
    private String body_copyString = null;
    private String contentString = null;
    private String target_dateString = null;
    private String target_timeString = null;
    private static String destinationsString = null;
    private String pointsString = null;

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

    private FloatingActionButton appbar;

    private String cost = "0";

    private JSONArray desPointsObj;

    private static float MAP_ZOOM_MAX = 3;
    private static float MAP_ZOOM_MIN = 21;



    //    private String API_KEY = "AIzaSyCiaIGnvo1Bc6WXbiiqy3E2ukbWjWj1VpQ";
    private String API_KEY = "AIzaSyCw96GO7U6nd8CnCVjIISXvgG3T36BKUUY";
//    private String API_KEY = "AIzaSyAT6cY6dg_0KTQtDJ2WCnxLXLxfqKnK6m0";

    private LatLng pickUpLatLng = new LatLng(-6.175110, 106.865039); // Jakarta
    private LatLng locationLatLng = new LatLng(-6.197301,106.795951); // Cirebon

    private List<LatLng> desPoints = new ArrayList<LatLng>();

    private TextView tvStartAddress, tvEndAddress, tvDuration, tvDistance;

    private GoogleMap map;
    LatLngBounds.Builder builder;
    CameraUpdate cu;


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


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MAP_ZOOM_MAX = mMap.getMaxZoomLevel();
        MAP_ZOOM_MIN = mMap.getMinZoomLevel();

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

        pointsString = intent.getStringExtra("destinations");

        cost = intent.getStringExtra("cost");

        JSONArray jarrPoints = new JSONArray();
        desPointsObj = new JSONArray();



        try {
            jarrPoints = new JSONArray(pointsString);
//                JSONObject jobk = jarrPoints.getJSONObject(i);
            for (int i = 0; i < jarrPoints.length(); i++) {
                JSONObject jobk = jarrPoints.getJSONObject(i);
//                String latItem = jobk.optString("latitude");
//                String longItem = jobk.optString("longitude");
//                LatLng desItemPoin = new LatLng(parseDoubleFromString(latItem), parseDoubleFromString(longItem));
//                desPoints.add(desItemPoin);
//            }


                String latItem = jobk.optString("latitude");
                String longItem = jobk.optString("longitude");
                Log.d("LOG", "latItem >>>>>>>>> " + latItem);
                Log.d("LOG", "longItem >>>>>>>>> " + longItem);

//                    if (parseDoubleFromString(latItem)!=Double.parseDouble("0")) {
//                        latItem = "-8.677335";
//                    }
//                    if (parseDoubleFromString(longItem)!=Double.parseDouble("0")) {
//                        longItem = "115.2070699";
//                    }

//                    LatLng desItemPoin = new LatLng(parseDoubleFromString(latItem), parseDoubleFromString(longItem));

                JSONObject desItemPoin = new JSONObject("{" +
                        "\"latitude\":\"" + latItem + "\", " +
                        "\"longitude\":\"" + longItem + "\"" +
                        "}");

                desPointsObj.put(desItemPoin);
                Log.d("LOG", "desPointsObj >>>>>>>>> DDDDDDDDDD " + desPointsObj);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

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

        Log.d("LOG", "desPointsObj >>>>>>>>> OBOBOBOBOBOB " + desPointsObj);
        try {
        for (int i = 0; i < desPointsObj.length(); i++) {
            JSONObject jobk = null;
                jobk = desPointsObj.getJSONObject(i);
            String latItem = jobk.optString("latitude");
                String longItem = jobk.optString("longitude");
                LatLng desItemPoin = new LatLng(parseDoubleFromString(latItem), parseDoubleFromString(longItem));
                desPoints.add(desItemPoin);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

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

//        /**get the reference of map from layout*/
//
//        SupportMapFragment map = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        map.getMapAsync(this);
//
////        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map));//.getMap();
//        /**call the map set up method*/
//        mSetUpMap();



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






        appbar = findViewById(R.id.float_btn);
        appbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(VacaplanActivity.this, EditPlanActivity.class);
                mIntent.putExtra("_id", id);
                mIntent.putExtra("body_copy", body_copyString);
                mIntent.putExtra("content", contentString);
                mIntent.putExtra("target_date", target_dateString);
                mIntent.putExtra("title", titleString);
                mIntent.putExtra("target_time", target_timeString);
                mIntent.putExtra("destinations", destinationsString);
                //mIntent.putExtra("destinations", String.valueOf(mFlowerList.get(holder.getAdapterPosition()).getDestinations()).substring(1, String.valueOf(mFlowerList.get(holder.getAdapterPosition()).getDestinations()).length()-1));
                VacaplanActivity.this.startActivity(mIntent);
            }
        });

        Intent intent = getIntent();
        final String cheeseName = intent.getStringExtra(EXTRA_NAME);

        body_copy_tv = findViewById(R.id.body_copy);
        content_tv = findViewById(R.id.content);
        // note_tv = findViewById(R.id.note);
        costs_tv = findViewById(R.id.costs);

        body_copy_tv.setText(body_copyString);
        content_tv.setText(contentString);
        //note_tv.setText("some note");
        costs_tv.setText(cost);

        title_bold_tv = findViewById(R.id.title_bold);
//        address_tv = findViewById(R.id.address);
        target_date_tv = findViewById(R.id.target_date);

        title_bold_tv.setText(titleString);
//        address_tv.setText(addressString);
        target_date_tv.setText(target_dateString);

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


    /**create method to set map view*/
    public void mSetUpMap() {
        /**clear the map before redraw to them*/
//        map.clear();
        /**Create dummy Markers List*/
        List<Marker> markersList = new ArrayList<Marker>();
        Marker Delhi = map.addMarker(new MarkerOptions().position(new LatLng(
                28.61, 77.2099)).title("Delhi"));
        Marker Chaandigarh = map.addMarker(new MarkerOptions().position(new LatLng(
                30.75, 76.78)).title("Chandigarh"));
        Marker SriLanka = map.addMarker(new MarkerOptions().position(new LatLng(
                7.000, 81.0000)).title("Sri Lanka"));
        Marker America = map.addMarker(new MarkerOptions().position(new LatLng(
                38.8833, 77.0167)).title("America"));
        Marker Arab = map.addMarker(new MarkerOptions().position(new LatLng(
                24.000, 45.000)).title("Arab"));

        /**Put all the markers into arraylist*/
        markersList.add(Delhi);
        markersList.add(SriLanka);
        markersList.add(America);
        markersList.add(Arab);
        markersList.add(Chaandigarh);

        /**create for loop for get the latLngbuilder from the marker list*/
        builder = new LatLngBounds.Builder();
        for (Marker m : markersList) {
            builder.include(m.getPosition());
        }
        /**initialize the padding for map boundary*/
        int padding = 50;
        /**create the bounds from latlngBuilder to set into map camera*/
        LatLngBounds bounds = builder.build();
        /**create the camera with bounds and padding to set into map*/
        cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        /**call the map call back to know map is loaded or not*/
        map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                /**set animated zoom camera into map*/
                map.animateCamera(cu);

            }
        });
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


//                    mMap.addMarker(new MarkerOptions().position(pickUpLatLng));
//                    mMap.addMarker(new MarkerOptions().position(locationLatLng));

                    LatLngBounds.Builder latLongBuilder = new LatLngBounds.Builder();

                    for(int z=0; z < desPoints.size(); z++) {
//                        JSONObject jdesob = jdes.getJSONObject(z);
                        LatLng itemPoin = desPoints.get(z);
                        mMap.addMarker(new MarkerOptions().position(itemPoin));
                        latLongBuilder.include(itemPoin);
                        Log.d("LOC", "itemPoin >>>>>>>>>>>>> OOOOOOOOOOOOOO " + itemPoin);
                    }

//                    latLongBuilder.include(pickUpLatLng);
//                    latLongBuilder.include(locationLatLng);

                    // Bounds Coordinata
                    LatLngBounds bounds = latLongBuilder.build();

//                    LatLngBounds bounds = mMap.getProjection().getVisibleRegion().latLngBounds;
//
//                    for (Marker k : mListMarkers) {
//                        if (bounds.contains(k.getPosition())) {
//                            currentFoundPoi++;
//                        }
//                    }

                    int width = getResources().getDisplayMetrics().widthPixels;
//                    int height = getResources().getDisplayMetrics().heightPixels - 500;

//                    int width = 1;
                    int height = 250 * 2;

//                    int paddingMap = (int) (width * 0.2); //jarak dari
//                    int paddingMap = (int) (width * 0.1); //jarak dari
                    int paddingMap = 50; //jarak dari
                    final CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, paddingMap);
                    mMap.animateCamera(cu);

                    mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                        @Override
                        public void onMapLoaded() {
                            /**set animated zoom camera into map*/
                            mMap.animateCamera(cu);

                        }
                    });

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

//                model.setMenuName("nama" + j);
//                model.setName(job.optString(name));
//                model.setPostID(job.optString("id"));
//                model.setImage(job.optString(image));

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
