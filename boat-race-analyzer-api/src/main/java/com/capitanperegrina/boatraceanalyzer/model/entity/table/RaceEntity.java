package com.capitanperegrina.boatraceanalyzer.model.entity.table;

import java.io.Serializable;

/**
 * POJO identificado con la tabla <code>race</code>
 */
public class RaceEntity implements Serializable {

    private static final long serialVersionUID = 53603964489L;

    protected Integer idRace;

    protected String name;

    /**
     * Constructor por defecto.
     */
    public RaceEntity() {
        super();
    }

    public Integer getIdRace() {
        return this.idRace;
    }

    public void setIdRace(final Integer idRace) {
        this.idRace = idRace;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RacePOJO [idRace=" + this.idRace + ", name=" + this.name + "]";
    }
}