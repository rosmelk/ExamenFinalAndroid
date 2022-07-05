package com.example.examenfinal.factories;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFactory {

    public static Retrofit build(Context context){

        return new Retrofit.Builder()
                .baseUrl("https://628f371bdc478523653bc250.mockapi.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
