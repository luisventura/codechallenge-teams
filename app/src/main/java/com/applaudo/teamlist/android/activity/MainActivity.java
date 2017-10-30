package com.applaudo.teamlist.android.activity;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.applaudo.teamlist.android.adapter.TeamAdapter;
import com.applaudo.teamlist.android.model.Team;
import com.applaudo.teamlist.android.network.restApi;

import com.applaudo.teamlist.android.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    ArrayList<Team> teams;
    TeamAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teams = new ArrayList<Team>();
        mAdapter = new TeamAdapter(getApplicationContext(),teams);
        ((ListView) findViewById(R.id.teamlistview)).setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        requestTeams();

    }

    private void requestTeams() {

        Call<List<Team>> teams = restApi.getInstance().APICall().getTeamList();
        teams.enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                Toast.makeText(MainActivity.this, "New Teams have been received!", Toast.LENGTH_SHORT).show();
                refreshTeams(response.body());
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to refresh! Is your Internet on?", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void refreshTeams(List<Team> incomingTeams) {
        teams.clear();
        teams.addAll(incomingTeams);
        Log.i("Info", "Teams Amount:" + teams.size());
    }

}
