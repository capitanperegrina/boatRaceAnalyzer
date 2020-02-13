package com.capitanperegrina.estela.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackPOJO;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackpointPOJO;

/**
 * The Class EstelaTrack.
 */
public class EstelaTrack {

	/** The track. */
	private TrackPOJO track;

	/** The boards. */
	private final Map<Integer, EstelaBoard> boards;

	/** The points. */
	private final List<TrackpointPOJO> points;

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
	public TrackPOJO getTrack() {
		return this.track;
	}

	/**
	 * Sets the track.
	 *
	 * @param track the new track
	 */
	public void setTrack(TrackPOJO track) {
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
	public List<TrackpointPOJO> getPoints() {
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
