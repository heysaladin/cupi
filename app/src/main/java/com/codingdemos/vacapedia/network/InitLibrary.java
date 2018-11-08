package com.codingdemos.vacapedia.network;

import com.codingdemos.vacapedia.network.ApiServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class InitLibrary {
    //https://maps.googleapis.com/maps/api/directions/json?origin=Cirebon,ID&destination=Jakarta,ID&api_key=AIzaSyCw96GO7U6nd8CnCVjIISXvgG3T36BKUUY
    public static String BASE_URL = "https://maps.googleapis.com/maps/api/directions/";
    public static Retrofit setInit(){
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ApiServices getInstance(){
        return setInit().create(ApiServices.class);
    }
}
