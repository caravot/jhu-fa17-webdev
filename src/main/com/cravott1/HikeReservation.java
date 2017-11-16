package com.cravott1;

public class HikeReservation {
    private String hikeName;
    private String startDate;
    private int duration;

    public HikeReservation() {
        hikeName = null;
        startDate = null;
        duration = 0;
    }

    public boolean isValid() {
        return (this.hikeName != null && this.startDate != null && this.duration > 0);
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

    @Override
    public String toString() {
        return "HikeReservation{" +
                "hikeName='" + hikeName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", duration=" + duration +
                '}';
    }
}
