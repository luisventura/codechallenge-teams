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
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamDetailFragment#x} factory method to
 * create an instance of this fragment.
 */
public class TeamDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TeamDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TeamDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamDetailFragment newInstance(String videoURL, String name, String description, String logoURL, Double lat, Double lon) {
        TeamDetailFragment fragment = new TeamDetailFragment();
        Bundle args = new Bundle();
        args.putString("videoURL", videoURL);
        args.putString("name", name);
        args.putString("description", description);
        args.putString("logoURL", logoURL);
        args.putDouble("latitud", lat);
        args.putDouble("longitud", lon);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //setup map
    }

    // TODO: Rename method, update argument and hook method into UI event
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
    public void onPause() {
        super.onPause();

        if (getActivity().getFragmentManager().findFragmentById(R.id.fragment_b) != null) {
            FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
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

        //setup video
        VideoView mTeamVideo = getActivity().findViewById(R.id.teamvideo);
        mTeamVideo.setVideoPath(getArguments().getString("videoURL"));

        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(mTeamVideo);
        mTeamVideo.setMediaController(mediaController);

        mTeamVideo.resume();

        //load team data
        Glide.with(getActivity())
                .load(getArguments().getString("logoURL"))
                .into((ImageView) getActivity().findViewById(R.id.teamlogodetail));

        ((TextView) getActivity().findViewById(R.id.teamnamedetail)).setText(getArguments().getString("name"));
        ((TextView) getActivity().findViewById(R.id.teamdescriptiondetail)).setText(getArguments().getString("description"));

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
