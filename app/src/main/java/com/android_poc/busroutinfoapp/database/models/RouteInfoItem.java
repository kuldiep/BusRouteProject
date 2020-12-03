package com.android_poc.busroutinfoapp.database.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "RoutInfoItem")
public class RouteInfoItem{
	@PrimaryKey
	@NonNull
	private String name;
	private String destination;
	private String icon;
	private String id;
	private String source;
	private String tripDuration;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTripDuration() {
		return tripDuration;
	}

	public void setTripDuration(String tripDuration) {
		this.tripDuration = tripDuration;
	}

	@Override
 	public String toString(){
		return 
			"RouteInfoItem{" + 
			"name = '" + name + '\'' + 
			",destination = '" + destination + '\'' + 
			",icon = '" + icon + '\'' + 
			",id = '" + id + '\'' + 
			",source = '" + source + '\'' + 
			",tripDuration = '" + tripDuration + '\'' + 
			"}";
		}
}
