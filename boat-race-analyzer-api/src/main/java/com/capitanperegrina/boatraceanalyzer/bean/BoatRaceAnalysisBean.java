package com.capitanperegrina.boatraceanalyzer.bean;

import java.util.ArrayList;
import java.util.List;

import com.capitanperegrina.geo.elements.Point;

public class BoatRaceAnalysisBean {

	private Integer idRace;
	private Integer idLeg;
	private String title;
	private final List<Integer> tracks = new ArrayList<>();
	private final List<BoatNameBean> boats = new ArrayList<>();
	private Point centerTrack;
	private String icons;
	private String markers;
	private String script;

	public BoatRaceAnalysisBean(Integer idRace, Integer idLeg) {
		super();
		this.idRace = idRace;
		this.idLeg = idLeg;
	}

	public Integer getIdRace() {
		return this.idRace;
	}

	public void setIdRace(Integer idRace) {
		this.idRace = idRace;
	}

	public Integer getIdLeg() {
		return this.idLeg;
	}

	public void setIdLeg(Integer idLeg) {
		this.idLeg = idLeg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Integer> getTracks() {
		return this.tracks;
	}

	public List<BoatNameBean> getBoats() {
		return this.boats;
	}

	public Point getCenterTrack() {
		return centerTrack;
	}

	public void setCenterTrack(Point centerTrack) {
		this.centerTrack = centerTrack;
	}

	public String getIcons() {
		return icons;
	}

	public void setIcons(String icons) {
		this.icons = icons;
	}

	public String getMarkers() {
		return markers;
	}

	public void setMarkers(String markers) {
		this.markers = markers;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	@Override
	public String toString() {
		return "BoatRaceAnalysisBean [idRace=" + idRace + ", idLeg=" + idLeg + ", title=" + title + ", tracks=" + tracks
				+ ", boats=" + boats + ", centerTrack=" + centerTrack + ", icons=" + icons + ", markers=" + markers
				+ ", script=" + script + "]";
	}
}
