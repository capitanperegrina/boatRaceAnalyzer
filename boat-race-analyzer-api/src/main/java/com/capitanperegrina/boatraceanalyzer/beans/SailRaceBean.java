package com.capitanperegrina.boatraceanalyzer.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.capitanperegrina.boatraceanalyzer.elements.SortedRoute;

public class SailRaceBean implements Serializable {

	private SortedRoute route;
	private final Map<String,TrackLineBean> tracks = new HashMap<>();;
	private final Map<String,Map<Integer,SailRaceLegBean>> legs = new HashMap<>();
	
	public SortedRoute getRoute() {
		return route;
	}
	
	public void setRoute(SortedRoute route) {
		this.route = route;
	}
	
	public Map<String, TrackLineBean> getTracks() {
		return tracks;
	}

	public Map<String, Map<Integer, SailRaceLegBean>> getLegs() {
		return legs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SailRaceBean [route=").append(route).append(", tracks=").append(tracks).append(", legs=")
				.append(legs).append("]");
		return builder.toString();
	}
}
