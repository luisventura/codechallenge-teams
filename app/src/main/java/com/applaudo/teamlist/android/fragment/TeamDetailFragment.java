package com.applaudo.teamlist.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.applaudo.teamlist.android.R;
import com.applaudo.teamlist.android.model.Team;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;

public class TeamDetailFragment extends Fragment {

    private Team mTeam;

    public TeamDetailFragment() {
    }

    public static TeamDetailFragment newInstance(Team team) {
        TeamDetailFragment fragment = new TeamDetailFragment();
        fragment.setmTeam(team);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_team_detail, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        MapHolderFragment mMapHolderFragment = (MapHolderFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.mapHolder);

        if (mMapHolderFragment == null) {
            Log.i("Azi","oncreateviewdetail instantiating new mapholder fragment instance");
            mMapHolderFragment = mMapHolderFragment.newInstance();
            FragmentTransaction ft = getFragmentManager()
                    .beginTransaction();
            ft.replace(R.id.mapHolder, mMapHolderFragment,"mapa");
            ft.commit();
        }
        loadTeamDetails(mTeam);
    }


    public void loadTeamDetails(Team team) {
        this.mTeam = team;
        if(mTeam==null) return;

        VideoView mTeamVideo = getActivity().findViewById(R.id.teamvideo);
        if(mTeamVideo!=null) {
            mTeamVideo.setVideoPath(mTeam.getVideoUrl());
            MediaController mediaController = new MediaController(getContext());
            mediaController.setAnchorView(mTeamVideo);
            mTeamVideo.setMediaController(mediaController);
            mTeamVideo.resume();
        }
        //load team data
        Glide.with(getActivity())
                .load(mTeam.getImgLogo())
                .into((ImageView) getActivity().findViewById(R.id.teamlogodetail));

        ((TextView) getActivity().findViewById(R.id.teamnamedetail)).setText(mTeam.getTeamName());
        ((TextView) getActivity().findViewById(R.id.teamdescriptiondetail)).setText(mTeam.getDescription());

        setStadiumMap(Double.parseDouble(mTeam.getLatitude()), Double.parseDouble(mTeam.getLongitude()), mTeam.getStadium());
    }

    public void setStadiumMap(Double lat, Double lon, String stadium) {
        MapHolderFragment mMapHolderFragment = (MapHolderFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.mapHolder);
        if (mMapHolderFragment != null) {
            Log.i("Azi", "setstadiummap fragment not null");
            mMapHolderFragment.setLocation(lat, lon, stadium);
        } else {
            Log.i("Azi", "setstadiummap fragment not null");
        }
    }

    public void setmTeam(Team mTeam) {
        this.mTeam = mTeam;
    }


}
