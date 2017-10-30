package com.applaudo.teamlist.android.network;

import com.applaudo.teamlist.android.model.Team;

import java.util.List;

import retrofit2.*;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by azimech49 on 10/27/17.
 */

public interface ApiWrapper {

    @GET("applaudo_homework.json")
    Call<List<Team>> getTeamList();

}
