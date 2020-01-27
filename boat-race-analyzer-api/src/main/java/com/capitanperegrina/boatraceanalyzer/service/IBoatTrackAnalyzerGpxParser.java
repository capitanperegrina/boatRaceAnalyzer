package com.capitanperegrina.boatraceanalyzer.service;

import com.capitanperegrina.boatraceanalyzer.beans.TrackLineBean;
import com.capitanperegrina.boatraceanalyzer.elements.SortedRoute;

public interface IBoatTrackAnalyzerGpxParser {

	SortedRoute sortedRouteParse(String filename);
	
	TrackLineBean trackLineListParse( String filename );	
}
