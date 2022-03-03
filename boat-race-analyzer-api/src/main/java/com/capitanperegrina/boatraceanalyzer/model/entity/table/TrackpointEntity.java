package com.capitanperegrina.boatraceanalyzer.model.entity.table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * POJO identificado con la tabla <code>trackpoint</code>
 */
public class TrackpointEntity implements Serializable {

    private static final long serialVersionUID = -90998954042L;

    protected Integer idTrackPoint;

    protected Integer idTrack;

    protected Date tsp;

    protected BigDecimal lat;

    protected BigDecimal lon;

    protected BigDecimal sog;

    protected BigDecimal cog;

    /**
     * Constructor por defecto.
     */
    public TrackpointEntity() {
        super();
    }

    public Integer getIdTrackPoint() {
        return this.idTrackPoint;
    }

    public void setIdTrackPoint(final Integer idTrackPoint) {
        this.idTrackPoint = idTrackPoint;
    }

    public Integer getIdTrack() {
        return this.idTrack;
    }

    public void setIdTrack(final Integer idTrack) {
        this.idTrack = idTrack;
    }

    public Date getTsp() {
        return this.tsp;
    }

    public void setTsp(final Date tsp) {
        this.tsp = tsp;
    }

    public BigDecimal getLat() {
        return this.lat;
    }

    public void setLat(final BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return this.lon;
    }

    public void setLon(final BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getSog() {
        return this.sog;
    }

    public void setSog(final BigDecimal sog) {
        this.sog = sog;
    }

    public BigDecimal getCog() {
        return this.cog;
    }

    public void setCog(final BigDecimal cog) {
        this.cog = cog;
    }

    @Override
    public String toString() {
        return "TrackpointPOJO [idTrackPoint=" + this.idTrackPoint + ", idTrack=" + this.idTrack + ", tsp=" + this.tsp + ", lat=" + this.lat
                + ", lon=" + this.lon + ", sog=" + this.sog + ", cog=" + this.cog + "]";
    }
}