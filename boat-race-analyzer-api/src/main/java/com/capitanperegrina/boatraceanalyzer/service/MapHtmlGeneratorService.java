package com.capitanperegrina.boatraceanalyzer.service;

import com.capitanperegrina.boatraceanalyzer.bean.BoatRaceAnalysisBean;

import java.io.IOException;

public interface MapHtmlGeneratorService {

    BoatRaceAnalysisBean generateBoatRaceAnalysisBean(Integer raceId, Integer legId);

    String getHtml(Integer raceId, Integer legId, Integer boatId) throws IOException;

    String generateRaceAnalysis(Integer raceId, Integer legId, Integer boatId);

    String generateJavascriptVariableTrack(Integer raceId, Integer legId, Integer trackId);

}