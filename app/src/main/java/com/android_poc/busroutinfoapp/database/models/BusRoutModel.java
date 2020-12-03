package com.android_poc.busroutinfoapp.database.models;

import java.util.List;

public class BusRoutModel{
	private RouteTimings routeTimings;
	private List<RouteInfoItem> routeInfo;

	public RouteTimings getRouteTimings(){
		return routeTimings;
	}

	public List<RouteInfoItem> getRouteInfo(){
		return routeInfo;
	}

	@Override
 	public String toString(){
		return 
			"BusRoutModel{" + 
			"routeTimings = '" + routeTimings + '\'' + 
			",routeInfo = '" + routeInfo + '\'' + 
			"}";
		}
}