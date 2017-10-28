package com.applaudo.teamlist.android.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by azimech49 on 10/28/17.
 */

public class restApi {
    private static final restApi ourInstance = new restApi();

    private apiWrapper mRetrofitInstance;

    public static restApi getInstance() {
        return ourInstance;
    }

    private restApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://applaudostudios.com/external/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRetrofitInstance = retrofit.create(apiWrapper.class);
    }

    public apiWrapper APICall(){
        return mRetrofitInstance;
    }
}
