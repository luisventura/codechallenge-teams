package com.applaudo.teamlist.android.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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

import java.util.Map;

public class TeamDetailFragment extends Fragment{

    private Team mTeam;

    public TeamDetailFragment() {
        // Required empty public constructor
    }

    public static TeamDetailFragment newInstance(Team team) {
        TeamDetailFragment fragment = new TeamDetailFragment();
        fragment.setmTeam(team);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team_detail, container, false);
        MapHolder mMapHolder = (MapHolder) getFragmentManager().findFragmentById(R.id.mapHolder);

        if (mMapHolder == null) {
            mMapHolder = mMapHolder.newInstance();
            FragmentTransaction ft = getFragmentManager()
                    .beginTransaction();
            ft.replace(R.id.mapHolder, mMapHolder);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        return v;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("savedInstanceTeam", mTeam);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (getActivity().getFragmentManager().findFragmentById(R.id.fragment_b) != null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.remove(getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_b));
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadTeamDetails(mTeam);
    }

    public void loadTeamDetails(Team team){
        setmTeam(team);

        VideoView mTeamVideo = getActivity().findViewById(R.id.teamvideo);
        mTeamVideo.setVideoPath(mTeam.getVideoUrl());
        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(mTeamVideo);
        mTeamVideo.setMediaController(mediaController);
//        mTeamVideo.resume();

        //load team data
        Glide.with(getActivity())
                .load(mTeam.getImgLogo())
                .into((ImageView) getActivity().findViewById(R.id.teamlogodetail));

        ((TextView) getActivity().findViewById(R.id.teamnamedetail)).setText(mTeam.getTeamName());
        ((TextView) getActivity().findViewById(R.id.teamdescriptiondetail)).setText(mTeam.getDescription());

        setStadiumMap(Double.parseDouble(mTeam.getLatitude()),Double.parseDouble(mTeam.getLongitude()),mTeam.getStadium());
    }

    public void setStadiumMap(Double lat, Double lon, String stadium){
        MapHolder mMapHolder = (MapHolder) getActivity().getSupportFragmentManager().findFragmentById(R.id.mapHolder);
        if(mMapHolder!=null){
            mMapHolder.setLocation(lat,lon,stadium);
        }else{
            Log.i("Map","Map fragment is null");
        }
    }

    public void setmTeam(Team mTeam) {
        this.mTeam = mTeam;
    }



}
