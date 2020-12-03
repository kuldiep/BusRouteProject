package com.android_poc.busroutinfoapp.database.models;

import java.util.HashMap;
import java.util.List;


public class RouteTimings{

	private HashMap<String,List<BusTimingPojo>> map;

	public HashMap<String, List<BusTimingPojo>> getMap() {
		return map;
	}

	public void setMap(HashMap<String, List<BusTimingPojo>> map) {
		this.map = map;
	}

}