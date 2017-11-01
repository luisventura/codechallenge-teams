package com.applaudo.teamlist.android.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applaudo.teamlist.android.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapHolderFragment extends Fragment {

    MapView mMapview;
    private GoogleMap mMap;


    public MapHolderFragment() {
    }

    public static MapHolderFragment newInstance() {
        MapHolderFragment fragment = new MapHolderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mMapview!=null) mMapview.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMapview != null) mMapview.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMapview != null) mMapview.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mMapview != null) mMapview.onLowMemory();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map_holder, container, false);

        mMapview = v.findViewById(R.id.map);
        mMapview.onCreate(savedInstanceState);
        mMapview.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapview.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
            }
        });

        return v;
    }

    public void setLocation(Double lat, Double lon, String location) {
        LatLng mLocation = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(mLocation).title(location)).showInfoWindow();

        Log.i("Azi", "mapholderfrag setting location");
        CameraPosition cameraPosition = new CameraPosition.Builder().target(mLocation).zoom(6).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
