package com.codingdemos.flowers.rest;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.karmagroups.utils.AppIntegers;
//import com.karmagroups.utils.AppStrings;
//import com.karmagroups.widgets.BusyDialog;
import com.codingdemos.flowers.rest.RestBase;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class AsyncHttpResponse {

    private static final String TAG = "AsyncHttpResponse";
    private Context context;
//    private BusyDialog progressDialog;
    private boolean isProgressVisible;
    private AsyncHttpResponseListener mListener;

    public AsyncHttpResponse(Context context, boolean isProgressVisible) {
        this.context = context;
        this.isProgressVisible = isProgressVisible;
        this.mListener = (AsyncHttpResponseListener) context;
    }

    public AsyncHttpResponse(Fragment fragment, boolean isProgressVisible) {
        this.context = fragment.getActivity();
        this.isProgressVisible = isProgressVisible;
        this.mListener = (AsyncHttpResponseListener) fragment;
    }

    private void showProgressDialog() {
        if (isProgressVisible) {
//            progressDialog = new BusyDialog(context, false, "");
//            progressDialog.show();
        }
    }

    private void dismissProgressDialog() {
        try {
            if (isProgressVisible) {
//                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAsyncHttp(final String URL, final RequestParams postParams) {
        Log.d(TAG, "getAsyncHttp() called with: URL = [" + URL + "], postParams = [" + postParams + "]");
        showProgressDialog();
        RestBase.get(context, URL, postParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                dismissProgressDialog();
                try {
                    mListener.onAsyncHttpResponseGet(response.toString(), URL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                dismissProgressDialog();
                try {
                    mListener.onAsyncHttpResponseGet(response.toString(), URL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                dismissProgressDialog();
                try {
                    mListener.onAsyncHttpResponseGet(responseString, URL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                dismissProgressDialog();
                try {
//                    if (statusCode == AppIntegers.Unauthorized) {}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode,
                                  Header[] headers,
                                  String responseString,
                                  Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                dismissProgressDialog();
                try {
//                    if (statusCode == AppIntegers.Unauthorized) {}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                dismissProgressDialog();
                try {
//                    if (statusCode == AppIntegers.Unauthorized) {}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return null;
    }

    public String postAsyncHttp(final String URL, final RequestParams postParams) {
        Log.d(TAG, "postAsyncHttp() called with: URL = [" + URL + "], postParams = [" + postParams + "]");
        showProgressDialog();
        RestBase.post(context, URL, postParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                dismissProgressDialog();
                try {
                    mListener.onAsyncHttpResponseGet(response.toString(), URL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                dismissProgressDialog();
                try {
                    mListener.onAsyncHttpResponseGet(response.toString(), URL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                dismissProgressDialog();
                try {
                    mListener.onAsyncHttpResponseGet(responseString, URL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                dismissProgressDialog();
                try {
//                    if (statusCode == AppIntegers.Unauthorized) {}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode,
                                  Header[] headers,
                                  String responseString,
                                  Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                dismissProgressDialog();
                try {
//                    if (statusCode == AppIntegers.Unauthorized) {}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                dismissProgressDialog();
                try {
//                    if (statusCode == AppIntegers.Unauthorized) {}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return null;
    }

    public String getAsyncHttps(final String URL) {
        showProgressDialog();
//        if (URL.contains(AppStrings.Unique.https)) {
//            handleSSLHandshake();
//        }
        com.karmagroups.rest.RestBaseHttps.getAsync(URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                dismissProgressDialog();
                try {
                    mListener.onAsyncHttpResponseGet(response.toString(), URL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                dismissProgressDialog();
                try {
                    mListener.onAsyncHttpResponseGet(response.toString(), URL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                dismissProgressDialog();
                try {
                    mListener.onAsyncHttpResponseGet(responseString.toString(), URL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                dismissProgressDialog();
                try {
//                    if (statusCode == AppIntegers.Unauthorized) {}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode,
                                  Header[] headers,
                                  String responseString,
                                  Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                dismissProgressDialog();
                try {
//                    if (statusCode == AppIntegers.Unauthorized) {}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                dismissProgressDialog();
                try {
//                    if (statusCode == AppIntegers.Unauthorized) {}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return null;
    }

    public void postJson(final String URL, final JSONObject jsonObject) {
        Log.d(TAG, "postJson() called with: url = [" + URL + "], jsonObject = [" + jsonObject + "]");
        showProgressDialog();
        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        if (URL.equals(com.karmagroups.rest.RestApis.KarmaGroups.karmawarehouse)) {
            RequestQueue queue = Volley.newRequestQueue(this.context);
            StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener < String > () {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Log.d("Error.Response", String.valueOf(error));
                        }
                    }
            ) {
                @Override
                protected Map < String, String > getParams() {
                    Map < String, String > params = new HashMap < String, String > ();
                    try {
                        params.put("first_name", jsonObject.getString("first_name"));
                        params.put("last_name", jsonObject.getString("last_name"));
                        params.put("phone", jsonObject.getString("phone"));
                        params.put("email", jsonObject.getString("email"));
                        params.put("collection_entry_point", jsonObject.getString("collection_entry_point"));
                        params.put("destination", jsonObject.getString("destination"));
                        params.put("data_source", jsonObject.getString("data_source"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    };
                    return params;
                }
            };
            queue.add(postRequest);
        } else {
            RestBase.postJson(context, URL, entity, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    Log.d(TAG, "onSuccess : URL = [" + URL + "]" + response);
                    dismissProgressDialog();
                    try {
                        mListener.onAsyncHttpResponseGet(response.toString(), URL);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    super.onSuccess(statusCode, headers, response);
                    Log.d(TAG, "onSuccess : URL = [" + URL + "]" + response);
                    dismissProgressDialog();
                    try {
                        mListener.onAsyncHttpResponseGet(response.toString(), URL);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    super.onSuccess(statusCode, headers, responseString);
                    Log.d(TAG, "onSuccess : URL = [" + URL + "]" + responseString);
                    try {
                        mListener.onAsyncHttpResponseGet(responseString.toString(), URL);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    dismissProgressDialog();
                    try {
//                        if (statusCode == AppIntegers.Unauthorized) {}
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(int statusCode,
                                      Header[] headers,
                                      String responseString,
                                      Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    dismissProgressDialog();
                    try {
//                        if (statusCode == AppIntegers.Unauthorized) {}
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    dismissProgressDialog();
                    try {
//                        if (statusCode == AppIntegers.Unauthorized) {}
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void putJson(final String URL, JSONObject jsonObject) {
        Log.d(TAG, "putJson() called with: url = [" + URL + "], jsonObject = [" + jsonObject + "]");
        showProgressDialog();
        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        RestBase.putJson(context, URL, entity, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d(TAG, "onSuccess : URL = [" + URL + "]" + response);
                dismissProgressDialog();
                try {
                    mListener.onAsyncHttpResponseGet(response.toString(), URL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d(TAG, "onSuccess : URL = [" + URL + "]" + response);
                dismissProgressDialog();
                try {
                    mListener.onAsyncHttpResponseGet(response.toString(), URL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                Log.d(TAG, "onSuccess : URL = [" + URL + "]" + responseString);
                dismissProgressDialog();
                try {
                    mListener.onAsyncHttpResponseGet(responseString.toString(), URL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                dismissProgressDialog();
                try {
//                    if (statusCode == AppIntegers.Unauthorized) {}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode,
                                  Header[] headers,
                                  String responseString,
                                  Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                dismissProgressDialog();
                try {
//                    if (statusCode == AppIntegers.Unauthorized) {}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                dismissProgressDialog();
                try {
                    // Log.e(TAG, "onFailure: URL = [" + URL + "] throwable--->" + throwable);
                    //Log.e(TAG, "onFailure: URL = [" + URL + "] statusCode--->" + statusCode);
                    // Log.e(TAG, "onFailure: URL = [" + URL + "] errorResponse--->" + errorResponse);
//                    if (statusCode == AppIntegers.Unauthorized) {}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public interface AsyncHttpResponseListener {
        void onAsyncHttpResponseGet(String response, String url) throws JSONException;
    }

    private static SSLSocketFactory trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[] {};
                    }
                    public void checkClientTrusted(X509Certificate[] chain,
                                                   String authType) throws CertificateException {}
                    public void checkServerTrusted(X509Certificate[] chain,
                                                   String authType) throws CertificateException {}
                }
        };
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            return sc.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
