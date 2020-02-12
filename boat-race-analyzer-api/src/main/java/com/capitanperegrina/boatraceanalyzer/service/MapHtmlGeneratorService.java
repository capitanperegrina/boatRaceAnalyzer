package com.capitanperegrina.boatraceanalyzer.service;

import java.io.IOException;

public interface MapHtmlGeneratorService {

	String getHtml(Integer raceId, Integer legId, Integer boatId) throws IOException;

}