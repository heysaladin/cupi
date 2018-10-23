package com.codingdemos.vacapedia.rest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

//import com.karmagroups.utils.AppIntegers;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cz.msebera.android.httpclient.HttpEntity;

public class RestBase {

    private static final String TAG = "RestBase";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String NO_CACHE = "no-cache";
    private static AsyncHttpClient client = new AsyncHttpClient(false, 80, 443);

    public static void get(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.d(TAG, "get() called with: context = [" + context + "], url = [" + url + "], params = [" + params + "]");
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        client.addHeader("User-Agent", USER_AGENT);
        client.addHeader("Cache-Control", NO_CACHE);
        client.get(getAbsoluteUrl(url), params, responseHandler);
        Log.e(TAG, "get: " + getAbsoluteUrl(url));
    }

    public static void getAsyncHeader(String url, String key, String contentType, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("User-Agent", USER_AGENT);
        client.addHeader("api_key", key);
        client.addHeader("Content-Type", contentType);
        client.get(getAbsoluteUrl(url), responseHandler);
    }

    public static void getAsync(String url, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("User-Agent", USER_AGENT);
        client.get(getAbsoluteUrl(url), responseHandler);
    }

    public static void post(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        client.addHeader("Cache-Control", NO_CACHE);
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void postJson(Context context, String url, HttpEntity entity, ResponseHandlerInterface responseHandler) {
//        client.setMaxRetriesAndTimeout(AppIntegers.server_retry_no, AppIntegers.server_retry_time);
//        client.setTimeout(AppIntegers.server_timeout);
        client.addHeader("Cache-Control", NO_CACHE);
        client.post(context, url, entity, "application/json", responseHandler);
    }

    public static void putJson(Context context, String url, HttpEntity entity, ResponseHandlerInterface responseHandler) {
//        client.setMaxRetriesAndTimeout(AppIntegers.server_retry_no, AppIntegers.server_retry_time);
//        client.setTimeout(AppIntegers.server_timeout);
        client.addHeader("Cache-Control", NO_CACHE);
        client.put(context, url, entity, "application/json", responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        Log.d(TAG, "getAbsoluteUrl() called with: relativeUrl = [" + relativeUrl + "]");
        return relativeUrl;
    }

    /**
     * Enables https connections
     */
    @SuppressLint("TrulyRandom")
    public static SSLContext handleSSLHandshake() {
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
            try {
                HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String arg0, SSLSession arg1) {
                        return true;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sc;
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    private static void disableSSLCertificateChecking() {
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    @Override
                    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                        // Not implemented
                    }
                    @Override
                    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                        // Not implemented
                    }
                }
        };
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static SSLContext getSslContext() {
        TrustManager[] byPassTrustManagers = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                }
        };
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sslContext.init(null, byPassTrustManagers, new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return sslContext;
    }

}
