package com.capitanperegrina.estela.bean;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackEntity;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackpointEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class EstelaTrack.
 */
public class EstelaTrack {

    /**
     * The boards.
     */
    private final Map<Integer, EstelaBoard> boards;

    /**
     * The points.
     */
    private final List<TrackpointEntity> points;

    /**
     * The track.
     */
    private TrackEntity track;

    /**
     * Instantiates a new estela track.
     */
    public EstelaTrack() {
        super();
        this.boards = new HashMap<>();
        this.points = new ArrayList<>();
    }

    /**
     * Gets the track.
     *
     * @return the track
     */
    public TrackEntity getTrack() {
        return this.track;
    }

    /**
     * Sets the track.
     *
     * @param track the new track
     */
    public void setTrack(final TrackEntity track) {
        this.track = track;
    }

    /**
     * Gets the boards.
     *
     * @return the boards
     */
    public Map<Integer, EstelaBoard> getBoards() {
        return this.boards;
    }

    /**
     * Gets the points.
     *
     * @return the points
     */
    public List<TrackpointEntity> getPoints() {
        return this.points;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "EstelaTrack [track=" + this.track + ", boards=" + this.boards + ", points=" + this.points + "]";
    }
}
