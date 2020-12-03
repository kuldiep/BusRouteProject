package com.android_poc.busroutinfoapp.database.models;

public class BusTimingPojo {
    private String tripStartTime;
    private int totalSeats;
    private int avaiable;

    public String getTripStartTime() {
        return tripStartTime;
    }

    public void setTripStartTime(String tripStartTime) {
        this.tripStartTime = tripStartTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvaiable() {
        return avaiable;
    }

    public void setAvaiable(int avaiable) {
        this.avaiable = avaiable;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusTimingPojo{");
        sb.append("tripStartTime='").append(tripStartTime).append('\'');
        sb.append(", totalSeats=").append(totalSeats);
        sb.append(", avaiable=").append(avaiable);
        sb.append('}');
        return sb.toString();
    }
}
