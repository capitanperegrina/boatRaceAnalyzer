package com.capitanperegrina.sailraceanalyzer.common.weather;

import java.io.Serializable;

import com.capitanperegrina.sailraceanalyzer.common.util.Nautical;

public class Wind implements Serializable
{
    protected double rumbo;
    protected double intensidad;
    
    public Wind() {
    }

    public double getRumbo() {
        return rumbo;
    }

    public void setRumbo(double rumbo) {
        this.rumbo = rumbo;
    }

    public double getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(double intensidad) {
        this.intensidad = intensidad;
    }

    public String generaString()
    {
    	StringBuffer retValue = new StringBuffer();
    	retValue.append( Nautical.formateaDistancia( this.intensidad ) + " kn del " + Nautical.formateaRumbo( this.rumbo ) );
    	return retValue.toString();
    }    
    
    /**
     * Construye un <code>String</code> con todos los atributos
     * según el formato "nombre = valor".
     *
     * @return Una representación en forma de <code>String</code>
     * de este objeto.
     */
    @Override
	public String toString()
    {
    	StringBuffer retValue = new StringBuffer();
    	retValue.append( Nautical.formateaDistancia( this.intensidad ) + " kn del " + Nautical.formateaRumbo( this.rumbo ) + "\n" );
    	return retValue.toString();
    }
}
