package com.capitanperegrina.boatraceanalyzer.model.entity.table;

import java.io.Serializable;

/**
 * POJO identificado con la tabla <code>race</code>
 */
public class RacePOJO implements Serializable
{
    private static final long serialVersionUID = 53603964489L;

    protected Integer idRace ;
    protected String name ;

    /**
     * Constructor por defecto.
     */
    public RacePOJO()
    {
        super();
    }

    public Integer getIdRace()
    {
        return this.idRace;
    }

    public void setIdRace( Integer idRace )
    {
        this.idRace = idRace;
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
        return "RacePOJO [idRace=" + idRace + ", name=" + name + "]";
    }
}