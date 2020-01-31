package com.capitanperegrina.boatraceanalyzer.model.entity.table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * POJO identificado con la tabla <code>trackpoint</code>
 */
public class TrackpointPOJO implements Serializable
{
    private static final long serialVersionUID = -90998954042L;

    protected Integer idTrackPoint ;
    protected Integer idTrack ;
    protected Date tsp ;
    protected BigDecimal lat ;
    protected BigDecimal lon ;
    protected BigDecimal sog ;
    protected BigDecimal cog ;

    /**
     * Constructor por defecto.
     */
    public TrackpointPOJO()
    {
        super();
    }

    public Integer getIdTrackPoint()
    {
        return this.idTrackPoint;
    }

    public void setIdTrackPoint( Integer idTrackPoint )
    {
        this.idTrackPoint = idTrackPoint;
    }

    public Integer getIdTrack()
    {
        return this.idTrack;
    }

    public void setIdTrack( Integer idTrack )
    {
        this.idTrack = idTrack;
    }

    public Date getTsp()
    {
        return this.tsp;
    }

    public void setTsp( Date tsp )
    {
        this.tsp = tsp;
    }

    public BigDecimal getLat()
    {
        return this.lat;
    }

    public void setLat( BigDecimal lat )
    {
        this.lat = lat;
    }

    public BigDecimal getLon()
    {
        return this.lon;
    }

    public void setLon( BigDecimal lon )
    {
        this.lon = lon;
    }

    public BigDecimal getSog()
    {
        return this.sog;
    }

    public void setSog( BigDecimal sog )
    {
        this.sog = sog;
    }

    public BigDecimal getCog()
    {
        return this.cog;
    }

    public void setCog( BigDecimal cog )
    {
        this.cog = cog;
    }

    @Override
    public String toString() {
        return "TrackpointPOJO [idTrackPoint=" + idTrackPoint + ", idTrack=" + idTrack + ", tsp=" + tsp + ", lat=" + lat
                + ", lon=" + lon + ", sog=" + sog + ", cog=" + cog + "]";
    }
}