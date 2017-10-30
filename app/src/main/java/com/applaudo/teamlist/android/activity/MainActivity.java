package com.applaudo.teamlist.android.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.applaudo.teamlist.android.adapter.TeamAdapter;
import com.applaudo.teamlist.android.fragment.TeamListFragment;
import com.applaudo.teamlist.android.model.Team;
import com.applaudo.teamlist.android.network.restApi;

import com.applaudo.teamlist.android.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TeamListFragment list = new TeamListFragment();

        list.setArguments(getIntent().getExtras());

        getFragmentManager().beginTransaction().add(R.id.list, list).commit();

    }

}
