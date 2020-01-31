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

	public SailRaceLegBean(String boat, Integer legNumber, MapElement fromPosition, MapElement toPosition,
			Date fromTime, Date toTime) {
		super();
		this.boat = boat;
		this.legNumber = legNumber;
		this.fromPosition = fromPosition;
		this.toPosition = toPosition;
		this.fromTime = fromTime;
		this.toTime = toTime;
	}

	public String getBoat() {
		return this.boat;
	}

	public void setBoat(String boat) {
		this.boat = boat;
	}

	public Integer getLegNumber() {
		return this.legNumber;
	}

	public void setLegNumber(Integer legNumber) {
		this.legNumber = legNumber;
	}

	public MapElement getFromPosition() {
		return this.fromPosition;
	}

	public void setFromPosition(MapElement fromPosition) {
		this.fromPosition = fromPosition;
	}

	public MapElement getToPosition() {
		return this.toPosition;
	}

	public void setToPosition(MapElement toPosition) {
		this.toPosition = toPosition;
	}

	public Date getFromTime() {
		return this.fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public Date getToTime() {
		return this.toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SailRaceLeg [boat=").append(this.boat).append(", legNumber=").append(this.legNumber)
				.append(", fromPosition=").append(this.fromPosition).append(", toPosition=").append(this.toPosition)
				.append(", fromTime=").append(this.fromTime).append(", toTime=").append(this.toTime).append("]");
		return builder.toString();
	}
}
