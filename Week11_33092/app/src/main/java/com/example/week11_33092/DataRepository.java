package com.example.week11_33092;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRepository {
    static String placeholderUrl = "https://jsonplaceholder.typicode.com/";
    static final PostServices create() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(placeholderUrl)
                .build();
        return retrofit.create(PostServices.class);
    }
}
