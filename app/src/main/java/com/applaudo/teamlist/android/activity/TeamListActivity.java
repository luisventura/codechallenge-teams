package com.applaudo.teamlist.android.activity;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.applaudo.teamlist.android.model.Team;
import com.applaudo.teamlist.android.network.restApi;

import com.applaudo.teamlist.android.R;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);
    }

    @Override
    protected void onResume() {
        super.onResume();

        requestTeams();

    }

    private void requestTeams(){

        Call<List<Team>> teams = restApi.getInstance().APICall().getTeamList();
        teams.enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                Toast.makeText(TeamListActivity.this, "New Teams have been received!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                Toast.makeText(TeamListActivity.this, "Failed to refresh! Is your Internet on?", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
