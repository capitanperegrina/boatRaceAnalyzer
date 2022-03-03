package com.capitanperegrina.boatraceanalyzer.service;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoatEntity;
import com.capitanperegrina.estela.bean.EstelaRace;
import com.capitanperegrina.geo.elements.Point;

import java.util.List;

public interface IEstelaService {

    EstelaRace readEstelaRace(Integer idRace);

    List<BoatEntity> readBoatsByRaceAndLeg(Integer idRace);

    void importCvsToDatabase(Integer idRace, Integer idLeg, Integer idBoat, Integer idTrack, String fileName);

    Point findLegCenter(Integer idLeg);
}
