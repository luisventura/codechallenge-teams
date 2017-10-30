package com.applaudo.teamlist.android.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import com.applaudo.teamlist.android.R;
import com.applaudo.teamlist.android.fragment.TeamDetailFragment;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        if (savedInstanceState == null) {

            TeamDetailFragment details = new TeamDetailFragment();

            details.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction()
                    .add(android.R.id.content, details).commit();

        }
    }
}
