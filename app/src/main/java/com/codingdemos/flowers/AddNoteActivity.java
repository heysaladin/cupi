package com.codingdemos.flowers;

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

import com.codingdemos.flowers.rest.AsyncHttpResponse;
import com.codingdemos.flowers.rest.RestApis;

import org.json.JSONException;
import org.json.JSONObject;

public class AddNoteActivity extends AppCompatActivity
        implements View.OnClickListener,
        AsyncHttpResponse.AsyncHttpResponseListener {
    Toolbar mToolbar;
    private static final String TAG = "AddNoteActivity";
    private EditText title_tv;
    private EditText note_tv;
    private AlertDialog.Builder alertDialogBuilder = null;
    private AlertDialog alertDialog = null;

    private void getIntentData() {
        Intent intent = this.getIntent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);
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
        title_tv = (EditText) findViewById(R.id.title_tv);
        note_tv = (EditText) findViewById(R.id.note_tv);
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
        if (title_tv.getText() == null || title_tv.length() == 0) {
            alertWithOk(this, "please provide title!");
        } else if (note_tv.getText() == null || note_tv.length() == 0) {
            alertWithOk(this, "please provide note content!");
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                alertDialogBuilder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                alertDialogBuilder = new AlertDialog.Builder(this);
            }
            StringBuilder listAgeChildren = new StringBuilder("");
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
        try {
            jobjContactDetails = new JSONObject();
            jobjContactDetails.put("title", String.valueOf(title_tv.getText()).trim());
            jobjContactDetails.put("content", String.valueOf(note_tv.getText()).trim());
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
        response.postJson(RestApis.KarmaGroups.addNote, finalJobjContactDetails);
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
        alertDialogBuilder.setMessage(message).setCancelable(false).setPositiveButton(getResources().getString(0),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent in = new Intent(AddNoteActivity.this, NotesActivity.class);
                        AddNoteActivity.this.startActivity( in );
                        AddNoteActivity.this.finish();
                        Toast.makeText(AddNoteActivity.this, "Thank You", Toast.LENGTH_LONG).show();
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
