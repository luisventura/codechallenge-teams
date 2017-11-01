package com.applaudo.teamlist.android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.applaudo.teamlist.android.R;
import com.applaudo.teamlist.android.activity.DetailActivity;
import com.applaudo.teamlist.android.adapter.TeamAdapter;
import com.applaudo.teamlist.android.model.Team;
import com.applaudo.teamlist.android.network.RestApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamListFragment extends Fragment {

    private static ArrayList<Team> teams;
    private TeamAdapter mAdapter;
    private boolean mDualFragment;

    public TeamListFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        teams = new ArrayList<Team>();

        if (savedInstanceState != null) {
            teams = savedInstanceState.getParcelableArrayList("laststate");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        mAdapter = new TeamAdapter(getActivity(), teams);

        ListView teamlist = this.getActivity().findViewById(R.id.teamlistview);
        teamlist.setAdapter(mAdapter);
        teamlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showTeamDetails(teams.get(i));
            }
        });

        final SwipeRefreshLayout mSwipeLayout = getActivity().findViewById(R.id.teamlist_srl);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestTeams();
                mSwipeLayout.setRefreshing(false);
            }
        });

        View detailsFragment = getActivity().findViewById(R.id.fragment_b);
        mDualFragment = detailsFragment != null && detailsFragment.getVisibility() == View.VISIBLE;
        if (teams.isEmpty()) {
            requestTeams();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("laststate", teams);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_team_list, container, false);
    }

    private void requestTeams() {
        Call<List<Team>> mApicall = RestApi.getInstance().APICall().getTeamList();
        mApicall.enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                Toast.makeText(getActivity(), "Teams have been received!", Toast.LENGTH_SHORT).show();
                saveTeams(response.body());
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to refresh! Is your Internet on?", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveTeams(List<Team> incomingTeams) {
        teams.clear();
        teams.addAll(incomingTeams);
        mAdapter.notifyDataSetChanged();
    }

    private void showTeamDetails(Team team) {
        if (mDualFragment) {
            TeamDetailFragment mTeamDetails = (TeamDetailFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_b);
            if (mTeamDetails == null) {
                mTeamDetails = TeamDetailFragment.newInstance(team);
                FragmentTransaction ft = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                ft.replace(R.id.fragment_b, mTeamDetails);
                ft.commit();
            } else {
                mTeamDetails.loadTeamDetails(team);
            }
        } else {
            Intent intent = new Intent();

            intent.setClass(getActivity(), DetailActivity.class);

            intent.putExtra("name", team.getTeamName());
            intent.putExtra("description", team.getDescription());
            intent.putExtra("logoURL", team.getImgLogo());
            intent.putExtra("videoURL", team.getVideoUrl());
            intent.putExtra("latitud", team.getLatitude());
            intent.putExtra("longitud", team.getLongitude());
            intent.putExtra("stadium", team.getStadium());

            startActivity(intent);
        }
    }
}
