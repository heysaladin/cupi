package com.codingdemos.flowers.rest;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.NetworkResponse;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.karmagroups.R;
//import com.karmagroups.utils.AppStrings;
//import com.karmagroups.utils.AppUtils;
//import com.karmagroups.utils.SessionManager;
//import com.karmagroups.widgets.BusyDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codingdemos.flowers.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

//import static com.karmagroups.utils.AppStrings.RequestTypes.get;

public class VolleyResponse {

    public static final int VOLLEY_REQUEST_TIMEOUT = 10000;
    public static final int VOLLEY_MAXIMUM_RETRIES = 0;
    public static final int VOLLEY_BACK_OFF_MULTIPLIER = 0;
    private static final String TAG = "VolleyResponse";
//    SessionManager sm;
    private Context context;
//    private BusyDialog progressDialog;
    private boolean isProgressVisible;
    private VolleyResponseListener mListener;

    public VolleyResponse(Context context, boolean isProgressVisible) {
        Log.e(TAG, "VolleyResponse() called with: context = [" + context.getClass().getSimpleName() + "], isProgressVisible = [" + isProgressVisible + "]");
        this.context = context;
        this.isProgressVisible = isProgressVisible;
        this.mListener = (VolleyResponseListener) context;
//        sm = new SessionManager(context);
        handleSSLHandshake();
    }

    public VolleyResponse(Fragment fragment, boolean isProgressVisible) {
        Log.d(TAG, "VolleyResponse() called with: fragment = [" + fragment.getClass().getSimpleName() + "], isProgressVisible = [" + isProgressVisible + "]");
        this.context = fragment.getActivity();
        this.isProgressVisible = isProgressVisible;
        this.mListener = (VolleyResponseListener) fragment;
//        sm = new SessionManager(fragment.getActivity());
        handleSSLHandshake();
    }

    private void showProgressDialog() {
        Log.e(TAG, "showProgressDialog() called " + isProgressVisible);
        if (isProgressVisible) {
//            progressDialog = new BusyDialog(context, false, "");
//            progressDialog.show();
        }
    }

    private void dismissProgressDialog() {
        Log.e(TAG, "dismissProgressDialog() called : " + isProgressVisible);
        if (isProgressVisible) {
//            progressDialog.dismiss();
        }
    }

    public String getVolleyStringRequestResponse(final int requestType,
                                                 final String api_url,
                                                 final HashMap < String, String > params,
                                                 final HashMap < String, String > headerParams) {
        Log.d(TAG, "getVolleyStringRequestResponse() called with: requestType = [" + requestType + "], api_url = [" + api_url + "], params = [" + params + "], headerParams = [" + headerParams + "]");
        showProgressDialog();
        RequestQueue queue = Volley.newRequestQueue(context);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(requestType, api_url,
                new Response.Listener < String > () {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        dismissProgressDialog();
                        // Log.d("response", "getVolleyStringRequestResponse is : " + response);
                        try {
                            mListener.onVolleyResponseGet(response, api_url, returnRequestType(requestType));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                dismissProgressDialog();
                Log.e("volleyError", volleyError.toString());
                try {
                    mListener.onVolleyResponseGet("" + updateApiErrorResponse(volleyError, api_url), api_url, returnRequestType(requestType));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }) {
            @Override
            protected Response < String > parseNetworkResponse(NetworkResponse response) {
                Log.e(TAG, "parseNetworkResponse: statusCode is : " + response.statusCode);
                try {
                    Map < String, String > responseHeaders = response.headers;
                    for (Map.Entry < String, ? > entry : responseHeaders.entrySet()) {
                        Log.d(TAG, "parseNetworkResponse values : " + entry.getKey() + ": " + entry.getValue().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return super.parseNetworkResponse(response);
            }
        };
        // Add the request to the RequestQueue.
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_REQUEST_TIMEOUT, VOLLEY_MAXIMUM_RETRIES, VOLLEY_BACK_OFF_MULTIPLIER));
        stringRequest.setShouldCache(false);
        queue.add(stringRequest);
        return null;
    }

    public String getVSRRWithHeadersAndNoParams(final int requestType,
                                                final String api_url,
                                                final HashMap < String, String > headerParams) {
        Log.d(TAG, "getVSRRWithHeadersAndNoParams() called with: requestType = [" + requestType + "], api_url = [" + api_url + "], headerParams = [" + headerParams + "]");
        showProgressDialog();
        RequestQueue queue = Volley.newRequestQueue(context);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(requestType, api_url,
                new Response.Listener < String > () {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        dismissProgressDialog();
                        Log.d("response", "getVSRRWithHeadersAndNoParams is : " + response);
                        try {
                            mListener.onVolleyResponseGet(response, api_url, returnRequestType(requestType));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                dismissProgressDialog();
//                AppUtils.loge("url", volleyError.toString());
                try {
                    mListener.onVolleyResponseGet("" + updateApiErrorResponse(volleyError, api_url), api_url, returnRequestType(requestType));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }) {
            @Override
            public Map < String, String > getHeaders() throws AuthFailureError {
                Log.i("headerParams", "authorization: " + headerParams);
                return headerParams;
            }
            @Override
            protected Response < String > parseNetworkResponse(NetworkResponse response) {
                try {
                    Map < String, String > responseHeaders = response.headers;
                    for (Map.Entry < String, ? > entry : responseHeaders.entrySet()) {
                        Log.d(TAG, "parseNetworkResponse values : " + entry.getKey() + ": " +
                                entry.getValue().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return super.parseNetworkResponse(response);
            }
        };
        // Add the request to the RequestQueue.
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_REQUEST_TIMEOUT, VOLLEY_MAXIMUM_RETRIES, VOLLEY_BACK_OFF_MULTIPLIER));
        stringRequest.setShouldCache(false);
        queue.add(stringRequest);
        return null;
    }

    public String getVSRRWithHeadersAndParams(final int requestType,
                                              final String api_url,
                                              final HashMap < String, String > headerParams,
                                              final JSONArray jsonArray) {

        Log.d(TAG, "getVSRRWithHeadersAndNoParams() called with: requestType = [" + requestType + "], api_url = [" + api_url + "], headerParams = [" + headerParams + "]");
        showProgressDialog();
        RequestQueue queue = Volley.newRequestQueue(context);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(requestType, api_url,
                new Response.Listener < String > () {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        dismissProgressDialog();
                        Log.d("response", "getVSRRWithHeadersAndNoParams is : " + response);
                        try {
                            mListener.onVolleyResponseGet(response, api_url, returnRequestType(requestType));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                dismissProgressDialog();
//                AppUtils.loge("url", volleyError.toString());
                try {
                    mListener.onVolleyResponseGet("" + updateApiErrorResponse(volleyError, api_url), api_url, returnRequestType(requestType));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }) {
            @Override
            public Map < String, String > getHeaders() throws AuthFailureError {
                Log.i("headerParams", "authorization: " + headerParams);
                return headerParams;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                Log.i("getBody", "getBody: " + headerParams);
                return jsonArray.toString().getBytes();
            }
            @Override
            protected Response < String > parseNetworkResponse(NetworkResponse response) {

                try {
                    Map < String, String > responseHeaders = response.headers;

                    for (Map.Entry < String, ? > entry : responseHeaders.entrySet()) {
                        Log.d(TAG, "parseNetworkResponse values : " + entry.getKey() + ": " +
                                entry.getValue().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return super.parseNetworkResponse(response);

            }
        };
        // Add the request to the RequestQueue.
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_REQUEST_TIMEOUT, VOLLEY_MAXIMUM_RETRIES, VOLLEY_BACK_OFF_MULTIPLIER));
        stringRequest.setShouldCache(false);
        queue.add(stringRequest);
        return null;
    }

    public String errorJsonResponse(String message) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("status", "0");
            jsonObject.put("message", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public void getVolleyJSONObjectRequestResponse(final int requestType,
                                                   final String api_url,
                                                   final JSONObject jsonObject) {
        Log.d(TAG, "getVolleyJSONObjectRequestResponse() called with: requestType = [" + requestType + "], api_url = [" + api_url + "], jsonObject = [" + jsonObject + "]");
        showProgressDialog();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(requestType, api_url, jsonObject, new Response.Listener < JSONObject > () {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e(TAG, "onResponse: jsonObject is : " + jsonObject);
                try {
                    dismissProgressDialog();
                    mListener.onVolleyResponseGet("" + jsonObject, api_url, returnRequestType(requestType));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                try {
                    dismissProgressDialog();
                    mListener.onVolleyResponseGet("" + updateApiErrorResponse(volleyError, api_url), api_url, returnRequestType(requestType));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }) {
            /*
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<>();

                Log.e(TAG, "getHeaders: " + headers.toString());
                return headers;
            }*/
            @Override
            protected Response < JSONObject > parseNetworkResponse(NetworkResponse response) {

                try {
                    Map < String, String > responseHeaders = response.headers;

                    for (Map.Entry < String, ? > entry : responseHeaders.entrySet()) {
                        Log.d(TAG, "parseNetworkResponse values : " + entry.getKey() + ": " +
                                entry.getValue().toString());
                    }
                    Log.e(TAG, "parseNetworkResponse: statusCode : " + response.statusCode);
                } catch (Exception e) {
                    Log.e(TAG, "parseNetworkResponse: Exception");
                    e.printStackTrace();
                }
                return super.parseNetworkResponse(response);
            }
        };
        // Add the request to the RequestQueue.
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_REQUEST_TIMEOUT, VOLLEY_MAXIMUM_RETRIES, VOLLEY_BACK_OFF_MULTIPLIER));
        jsonObjectRequest.setShouldCache(false);
        requestQueue.add(jsonObjectRequest);
    }

    public JSONObject updateApiErrorResponse(VolleyError volleyError, String api_url) {
        Log.d(TAG, "updateApiErrorResponse() called with: volleyError = [" + volleyError + "], api_url = [" + api_url + "]");
        String ex = "javax.net.ssl.SSLHandshakeException";
        String ve = volleyError.getMessage();
        Log.e(TAG, "updateApiErrorResponse: ve : " + ve);
//        SessionManager sm = new SessionManager(context);
        String error = "";
        JSONObject jsonObject = null;
        NetworkResponse response = volleyError.networkResponse;
        if (response != null && response.data != null) {
            Log.e(TAG, "updateApiErrorResponse: response.statusCode : " + response.statusCode);
            switch (response.statusCode) {
                case 400:
                    error = new String(response.data);
                    error = "400 - not an perfect url or queried with bad parameters";
                    if (error != null) displayMessage(error);

                    jsonObject = generateErrorJSONObject("400", error);
                    break;
                case 401:
                    Log.d(TAG, "updateApiErrorResponse: 401");
                    error = new String(response.data);
//                    error = context.getString(R.string.error_401_unauthorised_credentials);
                    if (error != null) displayMessage(error);
                    if (ve != null) {
                        if (ve.toLowerCase().contains(ex.toLowerCase())) {
                            Log.d(TAG, "updateApiErrorResponse: 401 ssl");
//                            error = context.getString(R.string.error_4xx_unauthorised_ssl_certificate);
                            jsonObject = generateErrorJSONObject("4xx", error);
                        } else {
                            Log.d(TAG, "updateApiErrorResponse: 401 else");
                            jsonObject = generateErrorJSONObject("401", error);
                        }
                    } else {
                        Log.d(TAG, "VolleyErrorResponse: null else");
                        Log.d(TAG, "updateApiErrorResponse: 401 else");
                        jsonObject = generateErrorJSONObject("401", error);
                    }
                    break;
                case 404:
                    error = new String(response.data);
                    error = "404 - input parameters empty";
                    if (error != null) displayMessage(error);
                    jsonObject = generateErrorJSONObject("404", error);
                    break;
                case 409:
                    error = new String(response.data);
                    error = "409 - When several files are uploaded";
                    if (error != null) displayMessage(error);
                    jsonObject = generateErrorJSONObject("409", error);
                    break;
                default:
                    error = new String(response.data);
//                    error = context.getString(R.string.error_5xx);
                    if (error != null) displayMessage(error);
                    jsonObject = generateErrorJSONObject("5xx", error);
                    break;
            }
        }
        Log.e(TAG, "updateApiErrorResponse: jsonObject is : " + jsonObject);
        return jsonObject;
    }

    //Somewhere that has access to a context
    public void displayMessage(String toastString) {}

    public JSONObject generateErrorJSONObject(String error_code, String message) {
        JSONObject jsonObject = new JSONObject();
        try {
//            jsonObject.put(AppStrings.JsonParams.error, true);
//            jsonObject.put(AppStrings.JsonParams.error_code, error_code);
//            jsonObject.put(AppStrings.JsonParams.error_message, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(TAG, "generateErrorJSONObject: " + jsonObject);
        return jsonObject;
    }

    public interface VolleyResponseListener {
        void onVolleyResponseGet(String response, String url, String requestType) throws JSONException;
    }

    public String returnRequestType(int requestType) {
        String requestString = "";
        if (requestType == 0) {
//            requestString = get;
        }
        if (requestType == 1) {
//            requestString = AppStrings.RequestTypes.post;
        }
        if (requestType == 2) {
//            requestString = AppStrings.RequestTypes.put;
        }
        if (requestType == 3) {
//            requestString = AppStrings.RequestTypes.delete;
        }
        return requestString;
    }

    public void jsonArrayVolleyRequest(Context context, String url) {
        final RequestQueue queue = Volley.newRequestQueue(context);
        final JsonArrayRequest getRequest = new JsonArrayRequest(
                "http://yourlink+parameters", new Response.Listener < JSONArray > () {
            @Override
            public void onResponse(JSONArray response) {}
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
                    VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                    volleyError = error;
                    Log.i("error", "error :" + volleyError.getMessage());
                }
            }
        });
        getRequest.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_REQUEST_TIMEOUT, VOLLEY_MAXIMUM_RETRIES, VOLLEY_BACK_OFF_MULTIPLIER));
        getRequest.setShouldCache(false);
        queue.add(getRequest);
    }

    // Let's assume your server app is hosting inside a server machine
    // which has a server certificate in which "Issued to" is "localhost",for example.
    // Then, inside verify method you can verify "localhost".
    // If not, you can temporarily return true
    private HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                // return true;
                // verify always returns true, which could cause insecure network traffic due to trusting TLS/SSL server certificates for wrong hostnames
                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                /**
                 * Need to change the hostname form localhost to your sever host name
                 */
                return hv.verify("localhost", session);
            }
        };
    }

    private TrustManager[] getWrappedTrustManagers(TrustManager[] trustManagers) {
        final X509TrustManager originalTrustManager = (X509TrustManager) trustManagers[0];
        return new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return originalTrustManager.getAcceptedIssuers();
                    }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        try {
                            if (certs != null && certs.length > 0) {
                                certs[0].checkValidity();
                            } else {
                                originalTrustManager.checkClientTrusted(certs, authType);
                            }
                        } catch (CertificateException e) {
                            Log.w("checkClientTrusted", e.toString());
                        }
                    }
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        try {
                            if (certs != null && certs.length > 0) {
                                certs[0].checkValidity();
                            } else {
                                originalTrustManager.checkServerTrusted(certs, authType);
                            }
                        } catch (CertificateException e) {
                            Log.w("checkServerTrusted", e.toString());
                        }
                    }
                }
        };
    }

    /**
     * Enables https connections
     */
    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        @Override
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {}
    }

}
