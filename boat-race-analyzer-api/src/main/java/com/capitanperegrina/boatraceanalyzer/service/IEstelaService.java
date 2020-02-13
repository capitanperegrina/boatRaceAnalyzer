package com.capitanperegrina.boatraceanalyzer.service;

import com.capitanperegrina.estela.bean.EstelaRace;
import com.capitanperegrina.geo.elements.Point;

public interface IEstelaService {

	EstelaRace readEstelaRace( Integer idRace );
	
    void importCvsToDatabase(Integer idRace, Integer idLeg, Integer idBoat, Integer idTrack, String fileName);
    
    Point findLegCenter(Integer idLeg);
}
