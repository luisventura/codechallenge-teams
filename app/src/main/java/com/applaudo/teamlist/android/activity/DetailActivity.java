package com.applaudo.teamlist.android.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.applaudo.teamlist.android.fragment.TeamDetailFragment;
import com.applaudo.teamlist.android.model.Team;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        if (savedInstanceState == null) {
            Team mTeam = new Team();

            mTeam.setTeamName(getIntent().getExtras().getString("name"));
            mTeam.setDescription(getIntent().getExtras().getString("description"));
            mTeam.setImgLogo(getIntent().getExtras().getString("logoURL"));
            mTeam.setVideoUrl(getIntent().getExtras().getString("videoURL"));
            mTeam.setLatitude(getIntent().getExtras().getString("latitud"));
            mTeam.setLongitude(getIntent().getExtras().getString("longitud"));
            mTeam.setStadium(getIntent().getExtras().getString("stadium"));

            TeamDetailFragment details = TeamDetailFragment.newInstance(mTeam);

            details.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, details).commit();

            setTitle("Detail");
        }
    }
}
