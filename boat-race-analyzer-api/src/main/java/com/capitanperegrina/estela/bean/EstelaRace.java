package com.capitanperegrina.estela.bean;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoatEntity;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.RaceEntity;

import java.util.HashMap;
import java.util.Map;

public class EstelaRace {

    private final Map<Integer, BoatEntity> boats;

    private final Map<Integer, EstelaRaceLeg> legs;

    private RaceEntity race;

    public EstelaRace() {
        this.boats = new HashMap<>();
        this.legs = new HashMap<>();
    }

    public RaceEntity getRace() {
        return this.race;
    }

    public void setRace(final RaceEntity race) {
        this.race = race;
    }

    public Map<Integer, BoatEntity> getBoats() {
        return this.boats;
    }

    public Map<Integer, EstelaRaceLeg> getLegs() {
        return this.legs;
    }

    @Override
    public String toString() {
        return "EstelaRace [race=" + this.race + ", boats=" + this.boats + ", legs=" + this.legs + "]";
    }
}
