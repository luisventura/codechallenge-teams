package com.applaudo.teamlist.android.model;

/**
 * Created by azimech49 on 10/27/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.applaudo.teamlist.android.model.ScheduleGames;


public class Team implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("team_name")
    @Expose
    private String teamName;
    @SerializedName("since")
    @Expose
    private String since;
    @SerializedName("coach")
    @Expose
    private String coach;
    @SerializedName("team_nickname")
    @Expose
    private String teamNickname;
    @SerializedName("stadium")
    @Expose
    private String stadium;
    @SerializedName("img_logo")
    @Expose
    private String imgLogo;
    @SerializedName("img_stadium")
    @Expose
    private String imgStadium;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("tickets_url")
    @Expose
    private String ticketsUrl;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("video_url")
    @Expose
    private String videoUrl;

    public Team() {
    }

    protected Team(android.os.Parcel in) {
        id = in.readLong();
        teamName = in.readString();
        since = in.readString();
        coach = in.readString();
        teamNickname = in.readString();
        stadium = in.readString();
        imgLogo = in.readString();
        imgStadium = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        website = in.readString();
        ticketsUrl = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
        description = in.readString();
        videoUrl = in.readString();
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel parcel) {
            return new Team(parcel);
        }

        @Override
        public Team[] newArray(int i) {
            return new Team[0];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(teamName);
        parcel.writeString(since);
        parcel.writeString(coach);
        parcel.writeString(teamNickname);
        parcel.writeString(stadium);
        parcel.writeString(imgLogo);
        parcel.writeString(imgStadium);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeString(website);
        parcel.writeString(ticketsUrl);
        parcel.writeString(address);
        parcel.writeString(phoneNumber);
        parcel.writeString(description);
        parcel.writeString(videoUrl);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getTeamNickname() {
        return teamNickname;
    }

    public void setTeamNickname(String teamNickname) {
        this.teamNickname = teamNickname;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(String imgLogo) {
        this.imgLogo = imgLogo;
    }

    public String getImgStadium() {
        return imgStadium;
    }

    public void setImgStadium(String imgStadium) {
        this.imgStadium = imgStadium;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTicketsUrl() {
        return ticketsUrl;
    }

    public void setTicketsUrl(String ticketsUrl) {
        this.ticketsUrl = ticketsUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

}