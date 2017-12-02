package com.cravott1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseData {
    @JsonProperty("message")
    private String message;
    @JsonProperty("reservation")
    private HikeReservation hikeReservation;

    public ResponseData() {
        message = "";
        hikeReservation = new HikeReservation();
    }

    public ResponseData(String message, HikeReservation hikeReservation) {
        this.message = message;
        this.hikeReservation = hikeReservation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HikeReservation getHikeReservation() {
        return hikeReservation;
    }

    public void setHikeReservation(HikeReservation hikeReservation) {
        this.hikeReservation = hikeReservation;
    }
}
