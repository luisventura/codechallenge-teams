package com.applaudo.teamlist.android.activity;

import android.app.Activity;
import android.os.Bundle;

import com.applaudo.teamlist.android.R;

public class TeamListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);
    }
}