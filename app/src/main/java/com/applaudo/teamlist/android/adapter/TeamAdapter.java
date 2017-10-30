package com.applaudo.teamlist.android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.applaudo.teamlist.android.R;
import com.applaudo.teamlist.android.model.Team;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by azimech49 on 10/28/17.
 */

public class TeamAdapter extends ArrayAdapter<Team> {

    private ArrayList<Team> dataset;
    private Context mContext;

    public TeamAdapter(@NonNull Context context, @NonNull ArrayList<Team> objects) {
        super(context, R.layout.item_team_list, objects);

        this.mContext = context;
        this.dataset = objects;
    }

    private static class ViewHolder {
        ImageView mTeamLogo;
        TextView mTeamName;
        TextView mTeamAddress;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Team mCurrentTeam = getItem(position);
        ViewHolder mViewHolder;

        if (convertView == null) {
            mViewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            convertView = mInflater.inflate(R.layout.item_team_list, parent, false);

            mViewHolder.mTeamName = (TextView) convertView.findViewById(R.id.teamname);
            mViewHolder.mTeamAddress = (TextView) convertView.findViewById(R.id.teamaddress);
            mViewHolder.mTeamLogo = (ImageView) convertView.findViewById(R.id.teamlogo);

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.mTeamAddress.setText(mCurrentTeam.getAddress());
        mViewHolder.mTeamName.setText(mCurrentTeam.getTeamName());
        Glide.with(getContext())
                .load(mCurrentTeam.getImgLogo())
                .into(mViewHolder.mTeamLogo);


        return convertView;
    }
}
