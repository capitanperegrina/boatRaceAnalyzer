package com.capitanperegrina.boatraceanalyzer.model.entity.table;

import java.io.Serializable;

/**
 * POJO identificado con la tabla <code>boat</code>
 */
public class BoatPOJO implements Serializable
{
    private static final long serialVersionUID = 84634160510L;

    protected Integer idBoat ;
    protected String name ;

    /**
     * Constructor por defecto.
     */
    public BoatPOJO()
    {
        super();
    }

    public Integer getIdBoat()
    {
        return this.idBoat;
    }

    public void setIdBoat( Integer idBoat )
    {
        this.idBoat = idBoat;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BoatPOJO [idBoat=" + idBoat + ", name=" + name + "]";
    }
}