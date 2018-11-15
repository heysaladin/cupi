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
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.codingdemos.vacapedia.rest.RestApis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.R.style.Theme_Material_Light_Dialog_Alert;

public class AddDestinationActivity extends AppCompatActivity
        implements View.OnClickListener,
        AsyncHttpResponse.AsyncHttpResponseListener {
    Toolbar mToolbar;
    private static final String TAG = "AddDestinationActivity";
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
    private LinearLayout parentLinearLayout;
    private JSONArray costList;
    private void getIntentData() {
        Intent intent = this.getIntent();
    }

    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        // Log.d("LOG", "count >>>>>>>>> " + String.valueOf(Integer.parseInt(String.valueOf(parentLinearLayout.getChildCount())) - 1));
    }

    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
        // Log.d("LOG", "count >>>>>>>>> " + String.valueOf(Integer.parseInt(String.valueOf(parentLinearLayout.getChildCount())) - 1));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_add);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);
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
    }

    /*
     * AlertDialog for Validation Form
     */
    private void alertWithOk(final Context context, String message) {
        Log.d(TAG, "alertWithOk() called with:  message = [" + message + "]");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alertDialogBuilder = new AlertDialog.Builder(context, Theme_Material_Light_Dialog_Alert);
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
                alertDialogBuilder = new AlertDialog.Builder(this, Theme_Material_Light_Dialog_Alert);
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
            alertDialogBuilder = new AlertDialog.Builder(this, Theme_Material_Light_Dialog_Alert);
        } else {
            alertDialogBuilder = new AlertDialog.Builder(this);
        }
        final JSONObject finalJobjContactDetails = jobjContactDetails;
        Log.d(TAG, "finalJobjContactDetails: " + finalJobjContactDetails);
        response.postJson(RestApis.KarmaGroups.vacapediaDestinations, finalJobjContactDetails);
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @SuppressLint("LongLogTag")
    private void alertForSuccessfulBookingEnquiry(String message) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alertDialogBuilder = new AlertDialog.Builder(this, Theme_Material_Light_Dialog_Alert);
        } else {
            alertDialogBuilder = new AlertDialog.Builder(this);
        }
        alertDialogBuilder.setMessage(message).setCancelable(false).setPositiveButton(getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent in = new Intent(AddDestinationActivity.this, MainActivity.class);
                        AddDestinationActivity.this.startActivity( in );
                        AddDestinationActivity.this.finish();
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
                int parentLong = Integer.parseInt(String.valueOf(parentLinearLayout.getChildCount())) - 1;
                for (int k = 0; k < parentLong; k++) {
                    try {
                        View currentView = parentLinearLayout.getChildAt(k);
                        EditText currentEditName = currentView.findViewById(R.id.text_edit_text);
                        EditText currentEditCost = currentView.findViewById(R.id.number_edit_text);
                        if (!currentEditName.getText().toString().equals("") || !currentEditCost.getText().toString().equals("")) {
                            JSONObject costObj = new JSONObject("{" +
                                    "\"name\":\"" + currentEditName.getText() + "\"," +
                                    "\"cost\":\"" + currentEditCost.getText() + "\"" +
                                    "}");
                            // Log.d(TAG, k + " k >>>>>>>> : " + costObj);
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

}
