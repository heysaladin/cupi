package com.codingdemos.flowers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.codingdemos.flowers.rest.AsyncHttpResponse;
import com.codingdemos.flowers.rest.RestApis;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddNoteActivity extends AppCompatActivity
//        implements View.OnClickListener, AsyncHttpResponse.AsyncHttpResponseListener
{

//    private static final String TAG = "ContactDetailsActivity";
//    private String requestResortName = null;
//    private String requestResortEmail = null;
//    private EditText telephone_number_tv;
//    private EditText bn_email_address_tv;
//    private EditText first_name_tv;
//    private EditText last_name_tv;
//    private EditText edittext_guest_country_tv;
//    private JSONArray resortsDataCountry;
//    private AlertDialog.Builder alertDialogBuilder = null;
//    private AlertDialog alertDialog = null;
//    private SimpleDateFormat sdf;
//    private String dateNow = "";
//    private String countryName = "";
//    private String destinationSelected = "";

//    private void getIntentData() {
//        Intent intent = this.getIntent();
////        requestResortName = intent.getStringExtra("name");
////        requestResortEmail = intent.getStringExtra(AppStrings.Unique.intent_param_resort_email);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);
        //initUI();
    }

//    public void onRadioButtonClicked(View view) {
//        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//        // imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
//        // Check which radio button was clicked
//        switch (view.getId()) {
//            case R.id.destination_seap_rb:
//                if (checked)
//                    destinationSelected = "South East Asia Pacific";
//                break;
//            case R.id.destination_india_rb:
//                if (checked)
//                    destinationSelected = "India";
//                break;
//            case R.id.destination_europe_rb:
//                if (checked)
//                    destinationSelected = "Europe";
//                break;
//        }
//    }
//
//    @SuppressLint({
//            "LongLogTag",
//            "SimpleDateFormat"
//    })
//    private void initUI() {
//        getIntentData();
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
////        AppUtils.loadTopBarOptions(this, this);
//
//        Calendar c = Calendar.getInstance();
//        sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String currentDateandTime = sdf.format(c.getTime());
//        dateNow = currentDateandTime;
//
//        String appToken = "";
////        if (!KarmaGroupsApplication.userAppToken.equals("")) {
////            appToken = KarmaGroupsApplication.userAppToken;
////        } else {
////            appToken = "NO-TOKEN";
////        }
//
//        AsyncHttpResponse response = new AsyncHttpResponse(this, false);
//        RequestParams params = new RequestParams();
//        response.getAsyncHttp(RestApis.KarmaGroups.getCountries, params);
//        response.getAsyncHttp(RestApis.KarmaGroups.destinationsNonMemberOdyssey, params);
//        TextView dd_booking_form_tv = (TextView) findViewById(R.id.dd_booking_form_tv);
//        dd_booking_form_tv.setOnClickListener(this);
//        EditText booking_request_country_code_et = (EditText) findViewById(R.id.booking_request_country_code_et);
//        telephone_number_tv = (EditText) findViewById(R.id.telephone_number_tv);
//        bn_email_address_tv = (EditText) findViewById(R.id.bn_email_address_tv);
//        first_name_tv = (EditText) findViewById(R.id.first_name_tv);
//        last_name_tv = (EditText) findViewById(R.id.last_name_tv);
//        edittext_guest_country_tv = (EditText) findViewById(R.id.guest_country_tv);
//        edittext_guest_country_tv.setOnClickListener(this);
//        RadioGroup destination_rg = (RadioGroup) findViewById(R.id.destination_rg);
//        RadioButton destination_seap_rb = (RadioButton) findViewById(R.id.destination_seap_rb);
//        RadioButton destination_india_rb = (RadioButton) findViewById(R.id.destination_india_rb);
//        RadioButton destination_europe_rb = (RadioButton) findViewById(R.id.destination_europe_rb);
//
//        // create Date object
//        Date dateUtil = new Date();
//        String strDateFormat = "dd MMMM yyyy";
//        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
//
//        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//        final RequestQueue mRequestQueue;
//        // Setup instance
//        mRequestQueue = Volley.newRequestQueue(this);
//        String requestUrl = RestApis.KarmaGroups.getGeoIp;
//        JsonObjectRequest geoInfoRequest = new JsonObjectRequest(requestUrl, null, new Response.Listener <JSONObject> () {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    countryName = response.getString("geoplugin_countryName");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Can't retrieve Geo Information.", error);
//            }
//        });
//        mRequestQueue.add(geoInfoRequest);
//        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//        loadTopBarOptions();
////        SessionManager sm = new SessionManager(this);
//        ArrayList< String > countriesArrayList = new ArrayList < > ();
////        AppUtils.loadTopBarBack(this, this);
//    }
//
//    public final static boolean isValidEmail(CharSequence target) {
//        if (target == null) {
//            return false;
//        } else {
//            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
//        }
//    }
//
//    @SuppressLint("LongLogTag")
//    public static boolean useList(String[] arr, String targetValue) {
//        return Arrays.asList(arr).contains(targetValue);
//    }
//
//    @SuppressLint("LongLogTag")
//    private void bookValidations() {
//        final AsyncHttpResponse responseValidation = new AsyncHttpResponse(this, true);
//        if (first_name_tv.getText() == null || first_name_tv.length() == 0) {
////            AppUtils.alertWithOk(this, getString(R.string.no_first_name));
//        } else if (last_name_tv.getText() == null || last_name_tv.length() == 0) {
////            AppUtils.alertWithOk(this, getString(R.string.no_surname));
//        } else if (bn_email_address_tv.getText() == null || bn_email_address_tv.length() == 0) {
////            AppUtils.alertWithOk(this, getString(R.string.no_email));
//        } else if (!isValidEmail(String.valueOf(bn_email_address_tv.getText()).trim())) {
////            AppUtils.alertWithOk(this, getString(R.string.invalid_email_address_format));
//        } else if (telephone_number_tv.getText() == null || telephone_number_tv.length() == 0) {
////            AppUtils.alertWithOk(this, getString(R.string.no_phone_number));
//        } else if (edittext_guest_country_tv.getText() == null) {
////            AppUtils.alertWithOk(this, getString(R.string.invalid_country_count));
//        } else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                alertDialogBuilder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
//            } else {
//                alertDialogBuilder = new AlertDialog.Builder(this);
//            }
//            StringBuilder listAgeChildren = new StringBuilder("");
//            afterSuccess();
//            synchronized(responseValidation) {
//                alertForSuccessfulBookingEnquiry("Thank you, your submission has been sent.");
//            }
//        }
//    }
//
//    @SuppressLint("LongLogTag")
//    private void afterSuccess() {
//        List< String > statusLabel = new ArrayList < String > ();
//        statusLabel.add("OPEN");
//        statusLabel.add("PENDING");
//        statusLabel.add("CONFIRM");
//        statusLabel.add("CANCEL");
//        String statusInfo = statusLabel.get(0);
//        String statusMessage = null;
//        switch (statusInfo) {
//            case "OPEN":
//                statusMessage = "Please wait, we will confirm your request.";
//                break;
//            case "PENDING":
//                statusMessage = "Please make sure your booking data, you can make changes on your booking.";
//                break;
//            case "CONFIRM":
//                statusMessage = "Thank you, we will confirm you for your booking.";
//                break;
//            case "CANCEL":
//                statusMessage = "Your booking is terminated.";
//                break;
//            default:
//                statusMessage = "No Status";
//        }
//        postBookingRequestJSONApiRequest();
//    }
//
//    @SuppressLint("LongLogTag")
//    private void postBookingRequestJSONApiRequest() {
////        if (AppUtils.isConnectingToInternet(this)) {
//            final AsyncHttpResponse response = new AsyncHttpResponse(this, true);
//            JSONObject jobjContactDetails = null;
//            try {
//                jobjContactDetails = new JSONObject();
//                jobjContactDetails.put("submission_date", dateNow);
//                jobjContactDetails.put("name", String.valueOf(first_name_tv.getText()).trim() /* + " " + String.valueOf(last_name_tv.getText()).trim() */ );
//                jobjContactDetails.put("surname", String.valueOf(last_name_tv.getText()).trim());
//                jobjContactDetails.put("email", String.valueOf(bn_email_address_tv.getText()).trim());
//                jobjContactDetails.put("phone", telephone_number_tv.getText().toString().trim());
//                jobjContactDetails.put("country", String.valueOf(edittext_guest_country_tv.getText()).trim());
//                jobjContactDetails.put("destination", destinationSelected);
//                jobjContactDetails.put("resort", requestResortName);
//                jobjContactDetails.put("emailTo", requestResortEmail);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                alertDialogBuilder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
//            } else {
//                alertDialogBuilder = new AlertDialog.Builder(this);
//            }
//            final JSONObject finalJobjContactDetails = jobjContactDetails;
//            Log.d(TAG, "finalJobjContactDetails: " + finalJobjContactDetails);
//            response.postJson(RestApis.KarmaGroups.contactDetails, finalJobjContactDetails);
//            if (alertDialog != null && alertDialog.isShowing()) {
//                alertDialog.dismiss();
//            }
//            alertDialog = alertDialogBuilder.create();
//            alertDialog.show();
////        } else {
////            AppUtils.alertForNoInternet(this);
////        }
//    }
//
//    @SuppressLint("LongLogTag")
//    private void alertForSuccessfulBookingEnquiry(String message) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            alertDialogBuilder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
//        } else {
//            alertDialogBuilder = new AlertDialog.Builder(this);
//        }
//        alertDialogBuilder.setMessage(message).setCancelable(false).setPositiveButton(getResources().getString(0),
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
////                        AppUtils.redirectToGuestHomeActivity(ContactDetailsActivity.this);
//                        AddNoteActivity.this.finish();
//                        Toast.makeText(AddNoteActivity.this, "Thank You", Toast.LENGTH_LONG).show();
//                    }
//                });
//        if (alertDialog != null && alertDialog.isShowing()) {
//            alertDialog.dismiss();
//        }
//        alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    }
//
//    private ArrayList < JSONObject > getArrayListFromJSONArray(JSONArray jsonArray) {
//        ArrayList < JSONObject > aList = new ArrayList < JSONObject > ();
//        try {
//            if (jsonArray != null) {
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    aList.add(jsonArray.getJSONObject(i));
//                }
//            }
//        } catch (JSONException je) {
//            je.printStackTrace();
//        }
//        return aList;
//    }
//
//    private void loadTopBarOptions() {}
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.dd_booking_form_tv:
//                bookValidations();
//                break;
////            case R.id.nl_register_tv:
////                break;
//            case R.id.guest_country_tv:
//                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//                // imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
//                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
////                displayPopupWindowCountry(edittext_guest_country_tv);
//                break;
//        }
//    }
//
////    @SuppressLint("LongLogTag")
////    private void displayPopupWindowCountry(View v) {
////        PopupWindow popupwindow_obj = null;
////        try {
////            popupwindow_obj = popupDisplayCountry();
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
////        popupwindow_obj.showAsDropDown(v, -40, 5, Gravity.CENTER_HORIZONTAL); // where u want show on view click event popupwindow.showAsDropDown(view, x, y);
////    }
////
////    @SuppressLint("LongLogTag")
////    private PopupWindow popupDisplayCountry() throws JSONException {
////        final PopupWindow popupWindow = new PopupWindow(this);
////        Display display = this.getWindowManager().getDefaultDisplay();
////        Point size = new Point();
////        display.getSize(size);
////        float d = this.getResources().getDisplayMetrics().density;
////        // inflate your layout or dynamically add view
////        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////        View view = inflater.inflate(R.layout.white_layout_resorts_dropdown_odyssey, null);
////        JSONArray listCountries = resortsDataCountry;
////        JSONArray asList = new JSONArray();
////        if (listCountries != null) {
////            for (int i = 0; i < listCountries.length(); i++) {
////                JSONObject addTitle = new JSONObject("{\"name\":\"" + listCountries.get(i) + "\",\"isTitle\":\"false\"}");
////                asList.put(addTitle);
////            }
////        }
////        ArrayList < JSONObject > listItems = getArrayListFromJSONArray(asList);
////        ListView listV = (ListView) view.findViewById(R.id.listv);
////        ListAdapter adapter = new ListAdapter(this, R.layout.list_layout_country, R.id.karma_resorts_item, listItems);
////        listV.setAdapter(adapter);
////        final ListView listv = listV;
////        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            public void onItemClick(AdapterView < ? > parent, View view, int position, long id) {
////                try {
////                    JSONObject obj = new JSONObject(String.valueOf(listv.getItemAtPosition(position)));
////                    String name = obj.getString("name");
////                    edittext_guest_country_tv.setText(name);
////                    popupWindow.dismiss();
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////            }
////        });
////        popupWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
////        popupWindow.setFocusable(true);
////        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
////        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
////        popupWindow.setContentView(view);
////        return popupWindow;
////    }
//
//    @SuppressLint("LongLogTag")
//    @Override
//    public void onAsyncHttpResponseGet(String response, String url) throws JSONException {
//        Log.d(TAG, "onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
//        if (url.equals(RestApis.KarmaGroups.getCountries)) {
//            try {
//                JSONObject jsonObject = new JSONObject(response);
////                int status = jsonObject.getInt(AppStrings.ResponseData.status);
////                String message = jsonObject.getString(AppStrings.ResponseData.message);
////                JSONArray listData = jsonObject.getJSONArray(AppStrings.ResponseData.Countries);
//                JSONArray listData = new JSONArray(response);
//                        resortsDataCountry = listData;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        if (url.equals(RestApis.KarmaGroups.destinationsNonMemberOdyssey)) {
//            try {
//                JSONObject jsonObject = new JSONObject(response);
////                int status = jsonObject.getInt(AppStrings.ResponseData.status);
////                String message = jsonObject.getString(AppStrings.ResponseData.message);
////                JSONArray listData = jsonObject.getJSONArray(AppStrings.ResponseData.data);
//                JSONArray listData = new JSONArray(response);
//                JSONArray resortsData = listData;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        if (url.equals(RestApis.KarmaGroups.getRestCountries)) {
//            try {
//                JSONObject jsonObject = new JSONObject(response);
////                int status = jsonObject.getInt(AppStrings.ResponseData.status);
////                String message = jsonObject.getString(AppStrings.ResponseData.message);
////                JSONArray listData = jsonObject.getJSONArray(AppStrings.ResponseData.data);
//                JSONArray listData = new JSONArray(response);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    protected void attachBaseContext(Context newBase) {
////        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

}