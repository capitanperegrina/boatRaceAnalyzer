package com.capitanperegrina.boatraceanalyzer.service;

import java.util.Map;

import com.capitanperegrina.boatraceanalyzer.beans.SailRaceBean;

public interface SailRaceParser {

	SailRaceBean parse(String routeFilenane, Map<String, String> boatsFilenames);

}