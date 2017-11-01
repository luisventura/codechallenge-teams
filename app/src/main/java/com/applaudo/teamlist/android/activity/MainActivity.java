package com.applaudo.teamlist.android.activity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.applaudo.teamlist.android.R;
import com.applaudo.teamlist.android.fragment.MapHolderFragment;
import com.applaudo.teamlist.android.fragment.TeamDetailFragment;
import com.applaudo.teamlist.android.fragment.TeamListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

