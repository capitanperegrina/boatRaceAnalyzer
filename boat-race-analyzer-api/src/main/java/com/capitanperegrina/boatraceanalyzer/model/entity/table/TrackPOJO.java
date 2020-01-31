package com.capitanperegrina.boatraceanalyzer.model.entity.table;

import java.io.Serializable;

/**
 * POJO identificado con la tabla <code>track</code>
 */
public class TrackPOJO implements Serializable
{
    private static final long serialVersionUID = -15560441768L;

    protected Integer idTrack ;
    protected Integer idLeg ;
    protected Integer idBoat ;

    /**
     * Constructor por defecto.
     */
    public TrackPOJO()
    {
        super();
    }

    public Integer getIdTrack()
    {
        return this.idTrack;
    }

    public void setIdTrack( Integer idTrack )
    {
        this.idTrack = idTrack;
    }

    public Integer getIdLeg()
    {
        return this.idLeg;
    }

    public void setIdLeg( Integer idLeg )
    {
        this.idLeg = idLeg;
    }

    public Integer getIdBoat()
    {
        return this.idBoat;
    }

    public void setIdBoat( Integer idBoat )
    {
        this.idBoat = idBoat;
    }

    @Override
    public String toString() {
        return "TrackPOJO [idTrack=" + idTrack + ", idLeg=" + idLeg + ", idBoat=" + idBoat + "]";
    }
}