package com.cravott1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HikeReservation {
    @JsonProperty("hike")
    private String hike;
    @JsonProperty("date")
    private String date;
    @JsonProperty("duration")
    private int duration;
    @JsonProperty("party")
    private int party;

    public HikeReservation() {
        hike = null;
        date = null;
        duration = 0;
        party = 0;
    }

    public boolean isValid() {
        return (this.hike != null
                && this.date != null
                && this.duration > 0
                && this.party > 0
                && this.party <= 10);
    }

    public String getHike() {
        return hike;
    }

    public void setHike(String hike) {
        this.hike = hike;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    @JsonIgnore
    public void setDuration(String duration) {
        this.duration = Integer.parseInt(duration);
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getParty() {
        return party;
    }

    @JsonIgnore
    public void setParty(String party) {
        this.party = Integer.parseInt(party);
    }

    public void setParty(int partyNumber) {
        this.party = partyNumber;
    }

    @Override
    public String toString() {
        return "HikeReservation{" +
                "hike='" + hike + '\'' +
                ", date='" + date + '\'' +
                ", party='" + party + '\'' +
                ", duration=" + duration +
                '}';
    }
}
