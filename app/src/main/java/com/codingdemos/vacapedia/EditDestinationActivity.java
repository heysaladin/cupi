package com.codingdemos.vacapedia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.codingdemos.vacapedia.rest.RestApis;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


    // Deklarasi variable
    private TextView tvPickUpFrom, tvDestLocation;
    private TextView tvPickUpAddr, tvPickUpLatLng, tvPickUpName;
    private TextView tvDestLocAddr, tvDestLocLatLng, tvDestLocName, tvDestLat, tvDestLong;
    public static final int PICK_UP = 0;
    public static final int DEST_LOC = 1;
    private static int REQUEST_CODE = 0;


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
        // costs = intent.getStringExtra("costs");
        total_costString = intent.getStringExtra("total_cost");
    }

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
        mToolbar.setTitle("Note");
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
            jobjContactDetails.put("costs", jarr);
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

}
