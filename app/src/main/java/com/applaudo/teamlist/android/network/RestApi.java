package com.applaudo.teamlist.android.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by azimech49 on 10/28/17.
 */

public class RestApi {
    private static final RestApi ourInstance = new RestApi();

    private ApiWrapper mRetrofitInstance;

    public static RestApi getInstance() {
        return ourInstance;
    }

    private RestApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://applaudostudios.com/external/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRetrofitInstance = retrofit.create(ApiWrapper.class);
    }

    public ApiWrapper APICall() {
        return mRetrofitInstance;
    }
}
