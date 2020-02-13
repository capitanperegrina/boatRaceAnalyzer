package com.capitanperegrina.estela.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.LegPOJO;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.RoutePOJO;

/**
 * The Class EstelaRaceLeg.
 */
public class EstelaRaceLeg {

	/** The leg. */
	private LegPOJO leg;

	/** The route. */
	private final List<RoutePOJO> routeElements;

	/** The decoration elements. */
	private final List<RoutePOJO> decorationElements;

	/** The tracks. */
	private final Map<Integer, EstelaTrack> tracks;

	/**
	 * Instantiates a new estela race leg.
	 */
	public EstelaRaceLeg() {
		super();
		this.tracks = new HashMap<>();
		this.routeElements = new ArrayList<>();
		this.decorationElements = new ArrayList<>();
	}

	/**
	 * Gets the leg.
	 *
	 * @return the leg
	 */
	public LegPOJO getLeg() {
		return this.leg;
	}

	/**
	 * Sets the leg.
	 *
	 * @param leg the new leg
	 */
	public void setLeg(LegPOJO leg) {
		this.leg = leg;
	}

	/**
	 * Gets the route elements.
	 *
	 * @return the route elements
	 */
	public List<RoutePOJO> getRouteElements() {
		return this.routeElements;
	}

	/**
	 * Gets the decoration elements.
	 *
	 * @return the decoration elements
	 */
	public List<RoutePOJO> getDecorationElements() {
		return this.decorationElements;
	}

	/**
	 * Gets the tracks.
	 *
	 * @return the tracks
	 */
	public Map<Integer, EstelaTrack> getTracks() {
		return this.tracks;
	}

	@Override
	public String toString() {
		return "EstelaRaceLeg [leg=" + this.leg + ", routeElements=" + this.routeElements + ", decorationElements="
				+ this.decorationElements + ", tracks=" + this.tracks + "]";
	}
}
