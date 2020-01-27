package com.capitanperegrina.boatraceanalyzer.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.capitanperegrina.boatraceanalyzer.elements.TrackLineSegment;

public class TrackLineBean implements Serializable {

	private static final long serialVersionUID = 983506431722648725L;

	private final List<TrackLineSegment> trackLineSegments;

	public TrackLineBean() {
		super();
		this.trackLineSegments = new ArrayList<>();		
	}
	
	public List<TrackLineSegment> getTrackLineSegments() {
		return trackLineSegments;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TrackLine [trackLineSegments=").append(trackLineSegments).append("]");
		return builder.toString();
	}	
}
