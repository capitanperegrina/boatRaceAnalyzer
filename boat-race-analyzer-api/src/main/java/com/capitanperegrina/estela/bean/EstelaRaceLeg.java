package com.capitanperegrina.estela.bean;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.LegEntity;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.RouteEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class EstelaRaceLeg.
 */
public class EstelaRaceLeg {

    /**
     * The route.
     */
    private final List<RouteEntity> routeElements;

    /**
     * The decoration elements.
     */
    private final List<RouteEntity> decorationElements;

    /**
     * The tracks.
     */
    private final Map<Integer, EstelaTrack> tracks;

    /**
     * The leg.
     */
    private LegEntity leg;

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
    public LegEntity getLeg() {
        return this.leg;
    }

    /**
     * Sets the leg.
     *
     * @param leg the new leg
     */
    public void setLeg(final LegEntity leg) {
        this.leg = leg;
    }

    /**
     * Gets the route elements.
     *
     * @return the route elements
     */
    public List<RouteEntity> getRouteElements() {
        return this.routeElements;
    }

    /**
     * Gets the decoration elements.
     *
     * @return the decoration elements
     */
    public List<RouteEntity> getDecorationElements() {
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
