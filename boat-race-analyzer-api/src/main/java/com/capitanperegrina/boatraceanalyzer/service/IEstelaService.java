package com.capitanperegrina.boatraceanalyzer.service;

import java.util.List;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoatPOJO;
import com.capitanperegrina.estela.bean.EstelaRace;
import com.capitanperegrina.geo.elements.Point;

public interface IEstelaService {

	EstelaRace readEstelaRace( Integer idRace );
	
	List<BoatPOJO> readBoatsByRaceAndLeg(Integer idRace);
	
    void importCvsToDatabase(Integer idRace, Integer idLeg, Integer idBoat, Integer idTrack, String fileName);
    
    Point findLegCenter(Integer idLeg);
}
