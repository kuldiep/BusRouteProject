package com.android_poc.busroutinfoapp.database.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BusTimeEntity")
public class BusTimeEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String keyId;
    private String tripStartTime;
    private int totalSeats;
    private int avaiable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

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
        sb.append("keyId='").append(keyId).append('\'');
        sb.append(", tripStartTime='").append(tripStartTime).append('\'');
        sb.append(", totalSeats=").append(totalSeats);
        sb.append(", avaiable=").append(avaiable);
        sb.append('}');
        return sb.toString();
    }
}
