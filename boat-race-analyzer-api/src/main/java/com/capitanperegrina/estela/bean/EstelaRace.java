package com.capitanperegrina.estela.bean;

import java.util.HashMap;
import java.util.Map;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoatPOJO;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.RacePOJO;

public class EstelaRace {

	private RacePOJO race;
	private final Map<Integer,BoatPOJO> boats;
	private final Map<Integer,EstelaRaceLeg> legs;

	public EstelaRace() {
		this.boats = new HashMap<>();
		this.legs = new HashMap<>();
	}

	public RacePOJO getRace() {
		return this.race;
	}

	public void setRace(RacePOJO race) {
		this.race = race;
	}

	public Map<Integer, BoatPOJO> getBoats() {
		return this.boats;
	}

	public Map<Integer,EstelaRaceLeg> getLegs() {
		return this.legs;
	}

	@Override
	public String toString() {
		return "EstelaRace [race=" + this.race + ", boats=" + this.boats + ", legs=" + this.legs + "]";
	}
}
