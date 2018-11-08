package com.codingdemos.vacapedia.network;

import com.codingdemos.vacapedia.response.ResponseRoute;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface ApiServices {
    //https://maps.googleapis.com/maps/api/directions/
    // json?origin=Cirebon,ID&destination=Jakarta,ID&api_key=AIzaSyCw96GO7U6nd8CnCVjIISXvgG3T36BKUUY
    @GET("json")
    Call<ResponseRoute> request_route(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("key") String key
    );
}
