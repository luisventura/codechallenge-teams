package com.applaudo.teamlist.android.fragment;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static ArrayList<Team> teams;
    private TeamAdapter mAdapter;
    private boolean mDualFragment;
    private int mSelectedTeam;

    private OnFragmentInteractionListener mListener;

    public TeamListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TeamListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamListFragment newInstance() {
        TeamListFragment fragment = new TeamListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        teams = new ArrayList<Team>();

        if(savedInstanceState != null) {
            teams = savedInstanceState.getParcelableArrayList("laststate");
        }
        mAdapter = new TeamAdapter(getActivity(), teams);
        ListView teamlist = getActivity().findViewById(R.id.teamlistview);
        teamlist.setAdapter(mAdapter);
        teamlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showTeamDetails(i);
//                Toast.makeText(getActivity(), "team: " + i + " team in array: " + teams.get(i).getTeamName() , Toast.LENGTH_SHORT).show();
            }
        });

        View detailsFragment = getActivity().findViewById(R.id.fragment_b);
        mDualFragment = detailsFragment != null && detailsFragment.getVisibility() == View.VISIBLE;

        if(teams.isEmpty()) {
            requestTeams();
        }
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("laststate", teams);
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
        return inflater.inflate(R.layout.fragment_team_list, container, false);
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    private void requestTeams() {

        Call<List<Team>> mApicall = RestApi.getInstance().APICall().getTeamList();
        mApicall.enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                Toast.makeText(getActivity(), "New Teams have been received!", Toast.LENGTH_SHORT).show();
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
    }

    private void showTeamDetails(int id){
        if(mDualFragment){
            TeamDetailFragment mTeamDetails = (TeamDetailFragment) getFragmentManager().findFragmentById(R.id.fragment_b);
            if (mTeamDetails == null){
                mTeamDetails = TeamDetailFragment.newInstance(1);
            }

            FragmentTransaction ft = getFragmentManager()
                    .beginTransaction();
            ft.replace(R.id.fragment_b, mTeamDetails);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }else{
            Intent intent = new Intent();

            intent.setClass(getActivity(), DetailActivity.class);

            intent.putExtra("index", id);

            startActivity(intent);
        }
    }


}
