package com.codingdemos.vacapedia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.data.CostsModel;
import com.codingdemos.vacapedia.data.DestinationsModel;
import com.codingdemos.vacapedia.handlers.CostsLineAdapter;
import com.codingdemos.vacapedia.handlers.DestinationsLineAdapter;
import com.codingdemos.vacapedia.handlers.SliderAdapter;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.codingdemos.vacapedia.rest.RestApis;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class EditDestinationActivity extends AppCompatActivity
        implements View.OnClickListener,
        AsyncHttpResponse.AsyncHttpResponseListener {
    Toolbar mToolbar;
    private static final String TAG = "EditNoteActivity";
    private EditText name;
    private EditText image;
    private EditText category;
    private EditText location;
    private EditText description;
    private EditText latitude;
    private EditText longitude;
    private EditText address;
    private EditText distance;
    private EditText note;
    private EditText costs;
    private EditText total_cost;
    private AlertDialog.Builder alertDialogBuilder = null;
    private AlertDialog alertDialog = null;
    private String noteID = null;

    private String id = null;
    private String nameString = null;
    private String imageString = null;
    private String categoryString = null;
    private String locationString = null;
    private String descriptionString = null;
    private String latitudeString = null;
    private String longitudeString = null;
    private String addressString = null;
    private String distanceString = null;
    private String noteString = null;
    private String costsString = null;
    private String total_costString = null;

    private RecyclerView mRecyclerView;


    // Deklarasi variable
    private TextView tvPickUpFrom, tvDestLocation;
    private TextView tvPickUpAddr, tvPickUpLatLng, tvPickUpName;
    private TextView tvDestLocAddr, tvDestLocLatLng, tvDestLocName, tvDestLat, tvDestLong;
    public static final int PICK_UP = 0;
    public static final int DEST_LOC = 1;
    private static int REQUEST_CODE = 0;

    private LinearLayout parentLinearLayout;

    private LinearLayout parentLinearLayoutRecycler;
    private RecyclerView parentRecycler;

    private LinearLayout wrapper_dynamic;

    private JSONArray costList;


    private EditText title;
    //    private EditText image;
//    private EditText category;
    private EditText body_copy;
    private EditText content;
    private EditText target_date;
    private EditText target_time;
    private EditText destinations;
//    private android.app.AlertDialog.Builder alertDialogBuilder = null;
//    private android.app.AlertDialog alertDialog = null;
//    private String noteID = null;

    private RelativeLayout bn_find_a_restaurant_rl;
    private static TextView bn_find_a_restaurant_tv;

//    private String id = null;
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
    private ArrayList <CostsModel> destinationsArrayListBuffer;
    private ArrayList < CostsModel > destinationsArrayList;
    private SliderAdapter guestDestinationsAdapter;
    private String imageUrl = null;
//    private RecyclerView mRecyclerView;
    private String restaurantName = "";

    private static String nowDestinationsSelected = "";

    private JSONArray costJsonArray = new JSONArray();



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //Toast.makeText(this, "Sini Gaes", Toast.LENGTH_SHORT).show();
//        // Pastikan Resultnya OK
//        if (resultCode == RESULT_OK){
//            //Toast.makeText(this, "Sini Gaes2", Toast.LENGTH_SHORT).show();
//            // Tampung Data tempat ke variable
//            Place placeData = PlaceAutocomplete.getPlace(this, data);
//            if (placeData.isDataValid()){
//                // Show in Log Cat
//                Log.d("autoCompletePlace Data", placeData.toString());
//                // Dapatkan Detail
//                String placeAddress = placeData.getAddress().toString();
//                LatLng placeLatLng = placeData.getLatLng();
//                String placeName = placeData.getName().toString();
//                // Cek user milih titik jemput atau titik tujuan
//                switch (REQUEST_CODE){
////                    case PICK_UP:
////                        // Set ke widget lokasi asal
////                        tvPickUpFrom.setText(placeAddress);
////                        tvPickUpAddr.setText("Location Address : " + placeAddress);
////                        tvPickUpLatLng.setText("LatLang : " + placeLatLng.toString());
////                        tvPickUpName.setText("Place Name : " + placeName);
////                        break;
//                    case DEST_LOC:
//                        // Set ke widget lokasi tujuan
//                        tvDestLocation.setText(placeAddress);
//                        tvDestLocAddr.setText("Destination Address : " + placeAddress);
////                        tvDestLocLatLng.setText("LatLang : " + placeLatLng.toString());
////                        tvDestLocName.setText("Place Name : " + placeName);
//                        tvDestLat.setText("Destination Lat : " + placeLatLng.latitude);
//                        tvDestLong.setText("Destination Long : " + placeLatLng.longitude);
//                        break;
//                }
//            } else {
//                // Data tempat tidak valid
//                Toast.makeText(this, "Invalid Place !", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    private void getIntentData() {
        Intent intent = this.getIntent();
        id = intent.getStringExtra("_id");
        nameString = intent.getStringExtra("name");
        imageString = intent.getStringExtra("image");
        categoryString = intent.getStringExtra("category");
        locationString = intent.getStringExtra("location");
        descriptionString = intent.getStringExtra("description");
        latitudeString = intent.getStringExtra("latitude");
        longitudeString = intent.getStringExtra("longitude");
        addressString = intent.getStringExtra("address");
        distanceString = intent.getStringExtra("distance");
        noteString = intent.getStringExtra("note");
         costsString = intent.getStringExtra("costs");
        total_costString = intent.getStringExtra("total_cost");

        JSONArray constList = new JSONArray();
        try {
            constList = new JSONArray(costsString);


            destinationsArrayListBuffer = new ArrayList < > ();
            destinationsArrayList = new ArrayList < > ();
//            mRecyclerView = findViewById(R.id.recyclerview);
//            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(EditDestinationActivity.this);
//            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            destinationsArrayList.clear();

            for (int j = 0; j < constList.length(); j++) {
                JSONObject constItem = constList.getJSONObject(j);

                CostsModel model = new CostsModel();
                model.set_id(constItem.optString("_id"));
                model.setName(constItem.optString("name"));
                model.setCost(constItem.optString("cost"));

                destinationsArrayList.add(model);

            }

            costJsonArray = constList;


//            costJsonArray = destinationsArrayList;

//            destinationsArrayListBuffer = destinationsArrayList;
//            CostsLineAdapter myAdapter = new CostsLineAdapter(EditDestinationActivity.this, destinationsArrayListBuffer );
//            mRecyclerView.setAdapter(myAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public void onAddFieldFill(String name, String cost) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        EditText currentName = rowView.findViewById(R.id.text_edit_text);
        EditText currentCost = rowView.findViewById(R.id.number_edit_text);
        currentName.setText(name);
        currentCost.setText(cost);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        Log.d("LOG", "count >>>>>>>>> " + String.valueOf(Integer.parseInt(String.valueOf(parentLinearLayout.getChildCount())) - 1));
    }

    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
//        EditText currentEdit = rowView.findViewById(R.id.number_edit_text);
//        editTextList.add(currentEdit);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);

        Log.d("LOG", "count >>>>>>>>> " + String.valueOf(Integer.parseInt(String.valueOf(parentLinearLayout.getChildCount())) - 1));

    }

    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
        Log.d("LOG", "count >>>>>>>>> " + String.valueOf(Integer.parseInt(String.valueOf(parentLinearLayout.getChildCount())) - 1));
    }

////    private List<EditText> editTextList = new ArrayList<EditText>();
//
//    private Button submitButton() {
//        Button button = new Button(this);
//        button.setHeight(WRAP_CONTENT);
//        button.setText("Submit");
//        button.setOnClickListener(submitListener);
//        return button;
//    }
//
//    // Access the value of the EditText
//
//    private View.OnClickListener submitListener = new View.OnClickListener() {
//        public void onClick(View view) {
//            StringBuilder stringBuilder = new StringBuilder();
//            for (EditText editText : editTextList) {
//                stringBuilder.append(editText.getText().toString());
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_edit);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mToolbar.setTitle("Edit Destination");

        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);
//        parentLinearLayoutRecycler = (LinearLayout) findViewById(R.id.parent_linear_layout_recycler_view);
//        parentRecycler = (RecyclerView) findViewById(R.id.recyclerview);

//        wrapper_dynamic = (LinearLayout) findViewById(R.id.wrapper_dynamic);

//        LinearLayout linearLayout = new LinearLayout(this);
////        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(FILL_PARENT, WRAP_CONTENT);
////        linearLayout.setLayoutParams(params);
////        linearLayout.setOrientation(VERTICAL);
////
////        int count = 10;
////        linearLayout.addView(tableLayout(count));
//        linearLayout.addView(submitButton());
//        setContentView(linearLayout);


        initUI();





//        tvDestLocAddr = findViewById(R.id.tvDestLocAddr);
//        tvDestLat = findViewById(R.id.tvDestLat);
//        tvDestLong = findViewById(R.id.tvDestLong);


        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
                String placeAddress = place.getAddress().toString();
                LatLng placeLatLng = place.getLatLng();
                String placeName = place.getName().toString();
//                tvDestLocAddr.setText("Destination Address : " + placeAddress);
////                        tvDestLocLatLng.setText("LatLang : " + placeLatLng.toString());
////                        tvDestLocName.setText("Place Name : " + placeName);
//                tvDestLat.setText("Destination Lat : " + placeLatLng.latitude);
//                tvDestLong.setText("Destination Long : " + placeLatLng.longitude);

                address.setText(placeAddress);
                latitude.setText(String.valueOf(placeLatLng.latitude));
                longitude.setText(String.valueOf(placeLatLng.longitude));


            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });






    }

    @SuppressLint({
            "LongLogTag",
            "SimpleDateFormat"
    })
    private void initUI() {
        getIntentData();

        try {
            for (int j = 0; j < costJsonArray.length(); j++) {
                JSONObject costItem = costJsonArray.getJSONObject(j);
                onAddFieldFill(costItem.getString("name"), costItem.getString("cost"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        TextView dd_booking_form_tv = (TextView) findViewById(R.id.dd_booking_form_tv);
        dd_booking_form_tv.setOnClickListener(this);
        name = (EditText) findViewById(R.id.name);
        image = (EditText) findViewById(R.id.image);
        category = (EditText) findViewById(R.id.category);
        location = (EditText) findViewById(R.id.location);
        description = (EditText) findViewById(R.id.description);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        address = (EditText) findViewById(R.id.address);
        distance = (EditText) findViewById(R.id.distance);
        note = (EditText) findViewById(R.id.note);
        costs = (EditText) findViewById(R.id.costs);
        total_cost = (EditText) findViewById(R.id.total_cost);

        name.setText(nameString);
        image.setText(imageString);
        category.setText(categoryString);
        location.setText(locationString);
        description.setText(descriptionString);
        latitude.setText(latitudeString);
        longitude.setText(longitudeString);
        address.setText(addressString);
        distance.setText(distanceString);
        note.setText(noteString);
        // costs.setText(costsString);
        total_cost.setText(total_costString);

        Toast.makeText(EditDestinationActivity.this, noteID, Toast.LENGTH_LONG).show();

    }

    /*
     * AlertDialog for Validation Form
     */
    private void alertWithOk(final Context context, String message) {
        Log.d(TAG, "alertWithOk() called with:  message = [" + message + "]");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alertDialogBuilder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            alertDialogBuilder = new AlertDialog.Builder(context);
        }
        alertDialogBuilder.setMessage(message).setCancelable(false).setPositiveButton(context.getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @SuppressLint("LongLogTag")
    private void bookValidations() {
        final AsyncHttpResponse responseValidation = new AsyncHttpResponse(this, true);
        if (name.getText() == null || name.length() == 0) {
            alertWithOk(this, "please provide name!");
        } else if (image.getText() == null || image.length() == 0) {
            alertWithOk(this, "please provide note image!");
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                alertDialogBuilder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                alertDialogBuilder = new AlertDialog.Builder(this);
            }
            afterSuccess();
            synchronized(responseValidation) {
                alertForSuccessfulBookingEnquiry("Thank you, your submission has been sent.");
            }
        }
    }

    @SuppressLint("LongLogTag")
    private void afterSuccess() {
        postBookingRequestJSONApiRequest();
    }

    @SuppressLint("LongLogTag")
    private void postBookingRequestJSONApiRequest() {
        final AsyncHttpResponse response = new AsyncHttpResponse(this, true);
        JSONObject jobjContactDetails = null;
        JSONArray jarr = new JSONArray();
        try {
            jobjContactDetails = new JSONObject();
            jobjContactDetails.put("name", String.valueOf(name.getText()).trim());
            jobjContactDetails.put("image", String.valueOf(image.getText()).trim());
            jobjContactDetails.put("category", String.valueOf(category.getText()).trim());
            jobjContactDetails.put("location", String.valueOf(location.getText()).trim());
            jobjContactDetails.put("description", String.valueOf(description.getText()).trim());
            jobjContactDetails.put("latitude", String.valueOf(latitude.getText()).trim());
            jobjContactDetails.put("longitude", String.valueOf(longitude.getText()).trim());
            jobjContactDetails.put("address", String.valueOf(address.getText()).trim());
            jobjContactDetails.put("distance", String.valueOf(distance.getText()).trim());
            jobjContactDetails.put("note", String.valueOf(note.getText()).trim());
            jobjContactDetails.put("costs", costList);
            jobjContactDetails.put("total_cost", String.valueOf(total_cost.getText()).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alertDialogBuilder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            alertDialogBuilder = new AlertDialog.Builder(this);
        }
        final JSONObject finalJobjContactDetails = jobjContactDetails;
        Log.d(TAG, "finalJobjContactDetails: " + finalJobjContactDetails);
        response.putJson(RestApis.KarmaGroups.vacapediaDestinations + "/" + id, finalJobjContactDetails);
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @SuppressLint("LongLogTag")
    private void alertForSuccessfulBookingEnquiry(String message) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alertDialogBuilder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            alertDialogBuilder = new AlertDialog.Builder(this);
        }
        alertDialogBuilder.setMessage(message).setCancelable(false).setPositiveButton(getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent in = new Intent(EditDestinationActivity.this, MainActivity.class);
                        EditDestinationActivity.this.startActivity( in );
                        EditDestinationActivity.this.finish();
                        Toast.makeText(EditDestinationActivity.this, "Thank You", Toast.LENGTH_LONG).show();
                    }
                });
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dd_booking_form_tv:

                costList = new JSONArray();


//                int parentLongRecycler = Integer.parseInt(String.valueOf(parentRecycler.getChildCount())) - 1;
//
//                for (int j = 0; j < parentLongRecycler; j++) {
//                    try {
//                        View currentView = parentRecycler.getChildAt(j);
//                        EditText currentEditName = currentView.findViewById(R.id.text_edit_text);
//                        EditText currentEditCost = currentView.findViewById(R.id.number_edit_text);
//                        //Log.d(TAG, j + ">>>>>>>> : " + currentEdit.getText() );
//                        if(!currentEditName.getText().toString().equals("")||!currentEditCost.getText().toString().equals("")) {
//                            JSONObject costObj = new JSONObject("{" +
//                                    "\"name\":\"" + currentEditName.getText() + "\"," +
//                                    "\"cost\":\"" + currentEditCost.getText() + "\"" +
//                                    "}");
//                            Log.d(TAG, j + " j >>>>>>>> : " + costObj );
//                            costList.put(costObj);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }


                int parentLong = Integer.parseInt(String.valueOf(parentLinearLayout.getChildCount())) - 1;

                for (int k = 0; k < parentLong; k++) {
                    try {
                        View currentView = parentLinearLayout.getChildAt(k);
                        EditText currentEditName = currentView.findViewById(R.id.text_edit_text);
                        EditText currentEditCost = currentView.findViewById(R.id.number_edit_text);
                        //Log.d(TAG, k + ">>>>>>>> : " + currentEdit.getText() );
                        if(!currentEditName.getText().toString().equals("")||!currentEditCost.getText().toString().equals("")) {
                            JSONObject costObj = new JSONObject("{" +
                                    "\"name\":\"" + currentEditName.getText() + "\"," +
                                    "\"cost\":\"" + currentEditCost.getText() + "\"" +
                                    "}");
                        Log.d(TAG, k + " k >>>>>>>> : " + costObj );
                        costList.put(costObj);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                bookValidations();

                break;
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onAsyncHttpResponseGet(String response, String url) throws JSONException {
        Log.d(TAG, "onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

//
//    private void processData() {
//        try {
//            JSONArray dataJson = desPlan;
//            destinationsArrayListBuffer = new ArrayList < > ();
//            destinationsArrayList = new ArrayList < > ();
//            mRecyclerView = findViewById(R.id.recyclerview);
//            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(EditDestinationActivity.this);
//            mRecyclerView.setLayoutManager(mLinearLayoutManager);
//            destinationsArrayList.clear();
//            JSONArray jarry = dataJson;
//            ArrayList <DestinationsModel> dma = new ArrayList < > ();
//            dma.clear();
//            for (int j = 0; j < jarry.length(); j++) {
//                JSONObject job = jarry.getJSONObject(j);
//                /*
//                PlanModel model = new PlanModel();
//                model.set_id(job.optString("_id"));
//                model.setTitle(job.optString("title"));
//                model.setBody_copy(job.optString("body_copy"));
//                model.setContent(job.optString("content"));
//                model.setTarget_date(job.optString("target_date"));
//                model.setTarget_time(job.optString("target_time"));
//                //JSONArray dest = new JSONArray();
//                JSONArray jdes = new JSONArray(job.optString("destinations"));
//                / *
//                Log.d("XXX", "jdes: " + jdes);
//                //ArrayList < String > jsarr = new ArrayList < > ();
//                StringBuilder jPlain = new StringBuilder();
//                for(int z=0; z < jdes.length(); z++){
//                    JSONObject jdesob = jdes.getJSONObject(z);
//                    //jsarr.add(jdesob.getString("_id"));
//                    jPlain.append(jdesob.getString("_id"));
//                }
//                Log.d("XXX", "jPlain: " + jPlain);
//                //String jarrClean = String.valueOf(String.valueOf(jsarr).trim().replaceAll("\"","").split("\\s*,\\s*"));
//                //dest.put(String.valueOf(destinations.getText()).trim());
////                String[] animalsArray = String.valueOf(jPlain).trim().replaceAll("\"","").split("\\s*,\\s*");
//                String[] animalsArray = String.valueOf(jPlain).trim().replaceAll("\"","").split("\\s*,\\s*");
//                Log.d("XXX", "animalsArray: " + animalsArray);
//                for(int i=0; i < animalsArray.length; i++){
//                    dest.put((animalsArray[i]).replaceAll("\"",""));
//                }
//                Log.d("XXX", "dest: " + dest);
//                * /
//
//                model.setDestinations(jdes);
////                model.setImage(job.optString("image"));
//                // model.setCategory(job.optString("category"));
////                jobjContactDetails.put("body_copy", String.valueOf(body_copy.getText()).trim());
////                jobjContactDetails.put("content", String.valueOf(content.getText()).trim());
////                jobjContactDetails.put("target_date", String.valueOf(target_date.getText()).trim());
////                jobjContactDetails.put("target_time", String.valueOf(target_time.getText()).trim());
////                jobjContactDetails.put("destinations", dest);
//                */
//
//
//                DestinationsModel model = new DestinationsModel();
//                model.setMenuID(String.valueOf(j));
//                model.setMenuName("nama" + j);
//                model.setName(job.optString("name"));
//                model.setPostID(job.optString("id"));
//                model.setImage(job.optString("image"));
//
////                model.setMenuName("nama" + j);
////                model.setName(job.optString(name));
////                model.setPostID(job.optString("id"));
////                model.setImage(job.optString(image));
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
//
//                dma.add(model);
//                destinationsArrayList.add(model);
//            }
//            destinationsArrayListBuffer = destinationsArrayList;
//            DestinationsLineAdapter myAdapter = new DestinationsLineAdapter(EditDestinationActivity.this, destinationsArrayListBuffer );
//            mRecyclerView.setAdapter(myAdapter);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void getKarmaGroupsApiRequest() {
//        AsyncHttpResponse response = new AsyncHttpResponse(this, false);
//        RequestParams params = new RequestParams();
//        response.getAsyncHttp(RestApis.KarmaGroups.vacapediaDestinations, params);
//    }
//
//    @Override
//    public void onAsyncHttpResponseGet(String response, String url) throws JSONException {
//        Log.d("TAG", "onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
//        if (url.equals(RestApis.KarmaGroups.vacapediaDestinations)) {
//            Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
//            dataDestinations = new JSONArray(response);
//            processData();
//        }
//    }



}
