package com.codingdemos.vacapedia.rest;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class RestBaseHttps {

    private static final String BASE_URL = "";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static AsyncHttpClient client_https = new AsyncHttpClient(false, 80, 443);

    public static void getAsync(String url, AsyncHttpResponseHandler responseHandler) {
        client_https.addHeader("User-Agent", USER_AGENT);
        client_https.get(getAbsoluteUrl(url), responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
