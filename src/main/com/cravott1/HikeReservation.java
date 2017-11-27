package com.cravott1;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;


public class HikeReservation {
    @JsonProperty("hike")
    private String hikeName;
    @JsonProperty("date")
    private String startDate;
    @JsonProperty("duration")
    private int duration;
    @JsonProperty("party")
    private int partyNumber;

    public HikeReservation() {
        hikeName = null;
        startDate = null;
        duration = 0;
        partyNumber = 0;
    }

    public boolean isValid() {
        return (this.hikeName != null
                && this.startDate != null
                && this.duration > 0
                && this.partyNumber > 0
                && this.partyNumber <= 10);
    }

    public String getHikeName() {
        return hikeName;
    }

    public void setHikeName(String hikeName) {
        this.hikeName = hikeName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = Integer.parseInt(duration);
    }

    public int getPartyNumber() {
        return partyNumber;
    }

    public void setPartyNumber(String partyNumber) {
        this.partyNumber = Integer.parseInt(partyNumber);
    }

    @Override
    public String toString() {
        return "HikeReservation{" +
                "hikeName='" + hikeName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", partyNumber='" + partyNumber + '\'' +
                ", duration=" + duration +
                '}';
    }
}
