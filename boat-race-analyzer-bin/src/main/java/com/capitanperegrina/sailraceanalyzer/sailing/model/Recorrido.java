package com.capitanperegrina.sailraceanalyzer.sailing.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.capitanperegrina.common.Cadenas;
import com.capitanperegrina.sailraceanalyzer.common.geo.ElementoEnMapa;
import com.capitanperegrina.sailraceanalyzer.common.geo.Point;
import com.capitanperegrina.sailraceanalyzer.common.google.maps.GoogleMapsUtils;

public class Recorrido implements Serializable {
    private static final long serialVersionUID = -2510359987348178308L;
    protected String descripcion;
    protected ElementoEnMapa salida;
    protected List<ElementoEnMapa> balizas = new ArrayList<ElementoEnMapa>();
    protected ElementoEnMapa llegada;
    protected List<Bordo> bordos = new ArrayList<Bordo>();

    public Recorrido() {
    }
    
    public Recorrido(List<Bordo> bordos) {
	super();
	this.bordos = bordos;
    }

    public List<Bordo> getBordos() {
	return bordos;
    }

    public void setBordos(List<Bordo> bordos) {
	this.bordos = bordos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ElementoEnMapa getSalida() {
        return salida;
    }

    public void setSalida(ElementoEnMapa salida) {
        this.salida = salida;
    }

    public List<ElementoEnMapa> getBalizas() {
        return balizas;
    }

    public void setBalizas(List<ElementoEnMapa> balizas) {
        this.balizas = balizas;
    }

    public ElementoEnMapa getLlegada() {
        return llegada;
    }

    public void setLlegada(ElementoEnMapa llegada) {
        this.llegada = llegada;
    }

    public Point getEsquinaNE() {
	double maxLat = 0;
	double maxLon = 0;
	for (int j = 0; j < this.bordos.size(); j++) {
	    for (int i = 0; i < this.bordos.get(j).getDerrota().size(); i++) {
		if (this.bordos.get(j).getDerrota().get(i).getPoint1()
			.getLatitude() > maxLat
			|| maxLat == 0)
		    maxLat = this.bordos.get(j).getDerrota().get(i).getPoint1()
			    .getLatitude();
		if (this.bordos.get(j).getDerrota().get(i).getPoint1()
			.getLongitude() > maxLon
			|| maxLon == 0)
		    maxLon = this.bordos.get(j).getDerrota().get(i).getPoint1()
			    .getLongitude();
	    }
	}
	return new Point(maxLat, maxLon);
    }

    public Point getEsquinaNW() {
	double maxLat = 0;
	double minLon = 0;
	for (int j = 0; j < this.bordos.size(); j++) {
	    for (int i = 0; i < this.bordos.get(j).getDerrota().size(); i++) {
		if (this.bordos.get(j).getDerrota().get(i).getPoint1()
			.getLatitude() > maxLat
			|| maxLat == 0)
		    maxLat = this.bordos.get(j).getDerrota().get(i).getPoint1()
			    .getLatitude();
		if (this.bordos.get(j).getDerrota().get(i).getPoint1()
			.getLongitude() < minLon
			|| minLon == 0)
		    minLon = this.bordos.get(j).getDerrota().get(i).getPoint1()
			    .getLongitude();
	    }
	}
	return new Point(maxLat, minLon);
    }

    public Point getEsquinaSE() {
	double minLat = 0;
	double maxLon = 0;
	for (int j = 0; j < this.bordos.size(); j++) {
	    for (int i = 0; i < this.bordos.get(j).getDerrota().size(); i++) {
		if (this.bordos.get(j).getDerrota().get(i).getPoint1()
			.getLatitude() < minLat
			|| minLat == 0)
		    minLat = this.bordos.get(j).getDerrota().get(i).getPoint1()
			    .getLatitude();
		if (this.bordos.get(j).getDerrota().get(i).getPoint1()
			.getLongitude() > maxLon
			|| maxLon == 0)
		    maxLon = this.bordos.get(j).getDerrota().get(i).getPoint1()
			    .getLongitude();
	    }
	}
	return new Point(minLat, maxLon);
    }

    public Point getEsquinaSW() {
	double minLat = 0;
	double minLon = 0;
	for (int j = 0; j < this.bordos.size(); j++) {
	    for (int i = 0; i < this.bordos.get(j).getDerrota().size(); i++) {
		if (this.bordos.get(j).getDerrota().get(i).getPoint1()
			.getLatitude() < minLat
			|| minLat == 0)
		    minLat = this.bordos.get(j).getDerrota().get(i).getPoint1()
			    .getLatitude();
		if (this.bordos.get(j).getDerrota().get(i).getPoint1()
			.getLongitude() < minLon
			|| minLon == 0)
		    minLon = this.bordos.get(j).getDerrota().get(i).getPoint1()
			    .getLongitude();
	    }
	}
	return new Point(minLat, minLon);
    }

    public Point getCentro() {
	double lat = 0;
	double lon = 0;
	for (int i = 0; i < this.bordos.size(); i++) {
	    lat = lat + this.bordos.get(i).getCentroBordo().getLatitude();
	    lon = lon + this.bordos.get(i).getCentroBordo().getLongitude();
	}
	return new Point(lat / this.bordos.size(), lon / this.bordos.size());
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((bordos == null) ? 0 : bordos.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Recorrido other = (Recorrido) obj;
	if (bordos == null) {
	    if (other.bordos != null)
		return false;
	} else if (!bordos.equals(other.bordos))
	    return false;
	return true;
    }

	public String generaHtml()
	{
		StringBuilder html = new StringBuilder();
		html.append( GoogleMapsUtils.generaHtml( getEsquinaNW(), getEsquinaSE(), this.salida, this.llegada, this.balizas, this.bordos ) );
		return html.toString();
	}

    /**
     * Construye un <code>String</code> con todos los atributos seg�n el formato
     * "nombre = valor".
     * 
     * @return Una representaci�n en forma de <code>String</code> de este
     *         objeto.
     */
    @Override
	public String toString() {
	StringBuffer retValue = new StringBuffer();
	retValue.append("Recorrido : ").append(super.toString());
	retValue.append("\n").append("bordos = ")
		.append(Cadenas.toStringGenerico(this.bordos));
	return retValue.toString();
    }
}
