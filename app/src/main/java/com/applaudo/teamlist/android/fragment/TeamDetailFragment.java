package com.applaudo.teamlist.android.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
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

public class TeamDetailFragment extends Fragment {

    private Team mTeam;

    private OnFragmentInteractionListener mListener;

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
        return inflater.inflate(R.layout.fragment_team_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MapHolder mMapHolder = (MapHolder) getFragmentManager().findFragmentById(R.id.mapHolder);

        if (mMapHolder == null) {
            mMapHolder = mMapHolder.newInstance();
            FragmentTransaction ft = getFragmentManager()
                    .beginTransaction();
            ft.replace(R.id.mapHolder, mMapHolder);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
            fragmentTransaction.remove(getActivity().getFragmentManager().findFragmentById(R.id.fragment_b));
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        MapHolder mMapHolder = (MapHolder) getActivity().getFragmentManager().findFragmentById(R.id.mapHolder);
        if(mMapHolder!=null){
            mMapHolder.setLocation(lat,lon,stadium);
            Log.i("map","set location yay");
        }else{
            Log.i("map","map holder is null");
        }
    }

    public void setmTeam(Team mTeam) {
        this.mTeam = mTeam;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
