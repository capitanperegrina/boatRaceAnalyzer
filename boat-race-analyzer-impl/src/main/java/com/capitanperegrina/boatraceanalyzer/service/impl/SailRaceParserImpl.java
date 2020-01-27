package com.capitanperegrina.boatraceanalyzer.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitanperegrina.boatraceanalyzer.beans.SailRaceBean;
import com.capitanperegrina.boatraceanalyzer.beans.TrackLineBean;
import com.capitanperegrina.boatraceanalyzer.service.IBoatTrackAnalyzerGpxParser;
import com.capitanperegrina.boatraceanalyzer.service.SailRaceParser;

@Service
public class SailRaceParserImpl implements SailRaceParser {

	@Autowired(required = false)
	IBoatTrackAnalyzerGpxParser boatTrackAnalyzerGpxParser;
	
	@Override
	public SailRaceBean parse(String routeFilenane, Map<String,String> boatsFilenames) {
		SailRaceBean ret = new SailRaceBean();
		if ( StringUtils.isNoneEmpty(routeFilenane) ) {
			ret.setRoute(this.boatTrackAnalyzerGpxParser.sortedRouteParse(routeFilenane));	
		}
		if ( MapUtils.isNotEmpty(boatsFilenames)) {
			int i = 1;
			for ( Entry<String,String> boatEntry : boatsFilenames.entrySet() ) {
				String key = "#" + i++;
				if (StringUtils.isNotEmpty(boatEntry.getKey())) {
					key = boatEntry.getKey();
				}
				TrackLineBean tl = null;
				if (StringUtils.isNotEmpty(boatEntry.getValue())) {
					tl = this.boatTrackAnalyzerGpxParser.trackLineListParse(boatEntry.getValue());
					ret.getTracks().put(key, tl);					
				}
			}
		}
		return ret;
	}
}
