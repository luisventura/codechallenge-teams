package com.applaudo.teamlist.android.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.applaudo.teamlist.android.R;
import com.applaudo.teamlist.android.fragment.MapHolder;
import com.applaudo.teamlist.android.fragment.TeamListFragment;

public class MainActivity extends AppCompatActivity implements TeamListFragment.OnFragmentInteractionListener,MapHolder.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

