package com.capitanperegrina.boatraceanalyzer.service;

import com.capitanperegrina.boatraceanalyzer.beans.TrackLineBean;
import com.capitanperegrina.boatraceanalyzer.elements.SortedRoute;
import com.capitanperegrina.gpx.service.IGpxParser;

public interface IBoatTrackAnalyzerGpxParser extends IGpxParser {

	SortedRoute sortedRouteParse(String filename);
	
	TrackLineBean trackLineListParse( String filename );	
}
