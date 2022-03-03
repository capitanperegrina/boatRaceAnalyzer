package com.capitanperegrina.boatraceanalyzer.model.entity.table;

import java.io.Serializable;

/**
 * POJO identificado con la tabla <code>boat</code>
 */
public class BoatEntity implements Serializable {

    private static final long serialVersionUID = 84634160510L;

    protected Integer idBoat;

    protected String name;

    /**
     * Constructor por defecto.
     */
    public BoatEntity() {
        super();
    }

    public Integer getIdBoat() {
        return this.idBoat;
    }

    public void setIdBoat(final Integer idBoat) {
        this.idBoat = idBoat;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BoatPOJO [idBoat=" + this.idBoat + ", name=" + this.name + "]";
    }
}