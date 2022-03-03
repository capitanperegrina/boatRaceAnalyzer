package com.capitanperegrina.boatraceanalyzer.model.entity.table;

import java.io.Serializable;
import java.util.Date;

/**
 * POJO identificado con la tabla <code>track</code>
 */
public class TrackEntity implements Serializable {

    private static final long serialVersionUID = -15560441768L;

    protected Integer idTrack;

    protected Integer idLeg;

    protected Integer idBoat;

    protected Date tspIni;

    protected Date tspEnd;

    /**
     * Constructor por defecto.
     */
    public TrackEntity() {
        super();
    }

    public Integer getIdTrack() {
        return this.idTrack;
    }

    public void setIdTrack(final Integer idTrack) {
        this.idTrack = idTrack;
    }

    public Integer getIdLeg() {
        return this.idLeg;
    }

    public void setIdLeg(final Integer idLeg) {
        this.idLeg = idLeg;
    }

    public Integer getIdBoat() {
        return this.idBoat;
    }

    public void setIdBoat(final Integer idBoat) {
        this.idBoat = idBoat;
    }

    public Date getTspIni() {
        return this.tspIni;
    }

    public void setTspIni(final Date tspIni) {
        this.tspIni = tspIni;
    }

    public Date getTspEnd() {
        return this.tspEnd;
    }

    public void setTspEnd(final Date tspEnd) {
        this.tspEnd = tspEnd;
    }

    @Override
    public String toString() {
        return "TrackPOJO [idTrack=" + this.idTrack + ", idLeg=" + this.idLeg + ", idBoat=" + this.idBoat + ", tspIni="
                + this.tspIni + ", tspEnd=" + this.tspEnd + "]";
    }
}