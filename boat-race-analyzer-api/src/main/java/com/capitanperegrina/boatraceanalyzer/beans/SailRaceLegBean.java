package com.capitanperegrina.boatraceanalyzer.beans;

import java.io.Serializable;
import java.util.Date;

import com.capitanperegrina.geo.elements.MapElement;

public class SailRaceLegBean implements Serializable {

	private static final long serialVersionUID = -8374573283221425757L;

	private String boat;
	private Integer legNumber;
	private MapElement fromPosition;
	private MapElement toPosition;
	private Date fromTime;
	private Date toTime;
	
	public SailRaceLegBean() {
		super();
	}

	public String getBoat() {
		return boat;
	}

	public void setBoat(String boat) {
		this.boat = boat;
	}

	public Integer getLegNumber() {
		return legNumber;
	}

	public void setLegNumber(Integer legNumber) {
		this.legNumber = legNumber;
	}

	public MapElement getFromPosition() {
		return fromPosition;
	}

	public void setFromPosition(MapElement fromPosition) {
		this.fromPosition = fromPosition;
	}

	public MapElement getToPosition() {
		return toPosition;
	}

	public void setToPosition(MapElement toPosition) {
		this.toPosition = toPosition;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public Date getToTime() {
		return toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SailRaceLeg [boat=").append(boat).append(", legNumber=").append(legNumber)
				.append(", fromPosition=").append(fromPosition).append(", toPosition=").append(toPosition)
				.append(", fromTime=").append(fromTime).append(", toTime=").append(toTime).append("]");
		return builder.toString();
	}
}
