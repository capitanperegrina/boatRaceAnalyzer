package com.capitanperegrina.boatraceanalyzer.model.entity.table;

import java.io.Serializable;
import java.util.Calendar;

/**
 * POJO identificado con la tabla <code>leg</code>
 */
public class LegPOJO implements Serializable
{
    private static final long serialVersionUID = 43277085946L;

    protected Integer idLeg ;
    protected Integer idRace ;
    protected Calendar date ;
    protected String name ;

    /**
     * Constructor por defecto.
     */
    public LegPOJO()
    {
        super();
    }

    public Integer getIdLeg()
    {
        return this.idLeg;
    }

    public void setIdLeg( Integer idLeg )
    {
        this.idLeg = idLeg;
    }

    public Integer getIdRace()
    {
        return this.idRace;
    }

    public void setIdRace( Integer idRace )
    {
        this.idRace = idRace;
    }

    public Calendar getDate()
    {
        return this.date;
    }

    public void setDate( Calendar date )
    {
        this.date = date;
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
        return "LegPOJO [idLeg=" + idLeg + ", idRace=" + idRace + ", date=" + date + ", name=" + name + "]";
    }
}