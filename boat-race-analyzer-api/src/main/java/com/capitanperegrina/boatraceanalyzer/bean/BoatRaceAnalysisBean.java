package com.capitanperegrina.boatraceanalyzer.bean;

import com.capitanperegrina.geo.elements.Point;

import java.util.ArrayList;
import java.util.List;

public class BoatRaceAnalysisBean {

    private final List<Integer> tracks = new ArrayList<>();

    private final List<BoatNameBean> boats = new ArrayList<>();

    private Integer idRace;

    private Integer idLeg;

    private String title;

    private Point centerTrack;

    private String icons;

    private String markers;

    private String script;

    public BoatRaceAnalysisBean(final Integer idRace, final Integer idLeg) {
        super();
        this.idRace = idRace;
        this.idLeg = idLeg;
    }

    public Integer getIdRace() {
        return this.idRace;
    }

    public void setIdRace(final Integer idRace) {
        this.idRace = idRace;
    }

    public Integer getIdLeg() {
        return this.idLeg;
    }

    public void setIdLeg(final Integer idLeg) {
        this.idLeg = idLeg;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public List<Integer> getTracks() {
        return this.tracks;
    }

    public List<BoatNameBean> getBoats() {
        return this.boats;
    }

    public Point getCenterTrack() {
        return this.centerTrack;
    }

    public void setCenterTrack(final Point centerTrack) {
        this.centerTrack = centerTrack;
    }

    public String getIcons() {
        return this.icons;
    }

    public void setIcons(final String icons) {
        this.icons = icons;
    }

    public String getMarkers() {
        return this.markers;
    }

    public void setMarkers(final String markers) {
        this.markers = markers;
    }

    public String getScript() {
        return this.script;
    }

    public void setScript(final String script) {
        this.script = script;
    }

    @Override
    public String toString() {
        return "BoatRaceAnalysisBean [idRace=" + this.idRace + ", idLeg=" + this.idLeg + ", title=" + this.title + ", tracks=" + this.tracks
                + ", boats=" + this.boats + ", centerTrack=" + this.centerTrack + ", icons=" + this.icons + ", markers=" + this.markers
                + ", script=" + this.script + "]";
    }
}
