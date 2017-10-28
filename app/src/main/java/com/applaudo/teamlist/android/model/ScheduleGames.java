package com.applaudo.teamlist.android.model;

/**
 * Created by azimech49 on 10/27/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleGames {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("stadium")
    @Expose
    private String stadium;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

}