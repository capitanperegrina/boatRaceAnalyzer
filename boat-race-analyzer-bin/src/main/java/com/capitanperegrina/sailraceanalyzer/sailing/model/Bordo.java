package com.capitanperegrina.sailraceanalyzer.sailing.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capitanperegrina.common.Cadenas;
import com.capitanperegrina.sailraceanalyzer.common.Globals;
import com.capitanperegrina.sailraceanalyzer.common.geo.ElementoEnMapa;
import com.capitanperegrina.sailraceanalyzer.common.geo.Point;
import com.capitanperegrina.sailraceanalyzer.common.geo.TrackLine;
import com.capitanperegrina.sailraceanalyzer.common.util.Nautical;

public class Bordo implements Serializable {
    private static final long serialVersionUID = -4095318744241960300L;

    private String descripcion;

    private ElementoEnMapa eIni;
    private Date tIni;

    private ElementoEnMapa eFin;
    private Date tFin;

    private List<TrackLine> derrota = new ArrayList<TrackLine>();

    public Bordo() {
    }

    public double getDistancia() {
	return Nautical.fromMeters(eIni.distanceInMeters(eFin));
    }

    public void cargaDerrota(List<TrackLine> track) {
	for (int i = 0; i < track.size(); i++) {
	    if (track.get(i).getTimeIni().after(this.tIni)
		    && track.get(i).getTimeFin().before(this.tFin)) {
		this.derrota.add(track.get(i));
	    }
	}
    }

    public Point getEsquinaNE() {
	double maxLat = 0;
	double maxLon = 0;
	for (int i = 0; i < this.derrota.size(); i++) {
	    if (this.derrota.get(i).getPoint1().getLatitude() > maxLat
		    || maxLat == 0)
		maxLat = this.derrota.get(i).getPoint1().getLatitude();
	    if (this.derrota.get(i).getPoint1().getLongitude() > maxLon
		    || maxLon == 0)
		maxLon = this.derrota.get(i).getPoint1().getLongitude();
	}
	return new Point(maxLat, maxLon);
    }

    public Point getEsquinaNW() {
	double maxLat = 0;
	double minLon = 0;
	for (int i = 0; i < this.derrota.size(); i++) {
	    if (this.derrota.get(i).getPoint1().getLatitude() > maxLat
		    || maxLat == 0)
		maxLat = this.derrota.get(i).getPoint1().getLatitude();
	    if (this.derrota.get(i).getPoint1().getLongitude() < minLon
		    || minLon == 0)
		minLon = this.derrota.get(i).getPoint1().getLongitude();
	}
	return new Point(maxLat, minLon);
    }

    public Point getEsquinaSE() {
	double minLat = 0;
	double maxLon = 0;
	for (int i = 0; i < this.derrota.size(); i++) {
	    if (this.derrota.get(i).getPoint1().getLatitude() < minLat
		    || minLat == 0)
		minLat = this.derrota.get(i).getPoint1().getLatitude();
	    if (this.derrota.get(i).getPoint1().getLongitude() > maxLon
		    || maxLon == 0)
		maxLon = this.derrota.get(i).getPoint1().getLongitude();
	}
	return new Point(minLat, maxLon);
    }

    public Point getEsquinaSW() {
	double minLat = 0;
	double minLon = 0;
	for (int i = 0; i < this.derrota.size(); i++) {
	    if (this.derrota.get(i).getPoint1().getLatitude() < minLat
		    || minLat == 0)
		minLat = this.derrota.get(i).getPoint1().getLatitude();
	    if (this.derrota.get(i).getPoint1().getLongitude() < minLon
		    || minLon == 0)
		minLon = this.derrota.get(i).getPoint1().getLongitude();
	}
	return new Point(minLat, minLon);
    }

    public Point getCentroBordo() {
	return new Point(
		(getEsquinaNE().getLatitude() + getEsquinaSW().getLatitude()) / 2,
		(getEsquinaNE().getLongitude() + getEsquinaSW().getLongitude()) / 2);
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public ElementoEnMapa geteIni() {
	return eIni;
    }

    public void seteIni(ElementoEnMapa eIni) {
	this.eIni = eIni;
    }

    public Date gettIni() {
	return tIni;
    }

    public void settIni(Date tIni) {
	this.tIni = tIni;
    }

    public ElementoEnMapa geteFin() {
	return eFin;
    }

    public void seteFin(ElementoEnMapa eFin) {
	this.eFin = eFin;
    }

    public Date gettFin() {
	return tFin;
    }

    public void settFin(Date tFin) {
	this.tFin = tFin;
    }

    public List<TrackLine> getDerrota() {
	return derrota;
    }

    public void setDerrota(List<TrackLine> derrota) {
	this.derrota = derrota;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((derrota == null) ? 0 : derrota.hashCode());
	result = prime * result
		+ ((descripcion == null) ? 0 : descripcion.hashCode());
	result = prime * result + ((eFin == null) ? 0 : eFin.hashCode());
	result = prime * result + ((eIni == null) ? 0 : eIni.hashCode());
	result = prime * result + ((tFin == null) ? 0 : tFin.hashCode());
	result = prime * result + ((tIni == null) ? 0 : tIni.hashCode());
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
	Bordo other = (Bordo) obj;
	if (derrota == null) {
	    if (other.derrota != null)
		return false;
	} else if (!derrota.equals(other.derrota))
	    return false;
	if (descripcion == null) {
	    if (other.descripcion != null)
		return false;
	} else if (!descripcion.equals(other.descripcion))
	    return false;
	if (eFin == null) {
	    if (other.eFin != null)
		return false;
	} else if (!eFin.equals(other.eFin))
	    return false;
	if (eIni == null) {
	    if (other.eIni != null)
		return false;
	} else if (!eIni.equals(other.eIni))
	    return false;
	if (tFin == null) {
	    if (other.tFin != null)
		return false;
	} else if (!tFin.equals(other.tFin))
	    return false;
	if (tIni == null) {
	    if (other.tIni != null)
		return false;
	} else if (!tIni.equals(other.tIni))
	    return false;
	return true;
    }

    public String generaString() {
	StringBuffer ret = new StringBuffer();

	ret.append("Bordo : \n");
	ret.append(" Descripcion  : " + this.descripcion + "\n");
	ret.append("      Inicio  : " + this.eIni.generaString() + "\n");
	ret.append("      Destino : " + this.eFin.generaString() + "\n");
	ret.append("    Distancia : "
		+ Nautical.formateaDistancia(this.getDistancia()) + " nm \n\n");
	ret.append("       FECHA Y HORA       COG    CMG   DESVIO   DTD      SOG      VMG    DIF.VEL\n");
	ret.append("  --------------------------------------------------------------------------------\n");
	for (int i = 0; i < this.derrota.size(); i++) {

	    double cog = this.derrota.get(i).getCog();
	    double cmg = this.derrota.get(i).getCmg(this.eFin);
	    int dev = new Double(cmg - cog).intValue();
	    double dtd = Nautical.fromMeters(this.derrota.get(i)
		    .distanceInMeters(this.eFin));

	    double sog = this.derrota.get(i).getSog();
	    double vmg = this.derrota.get(i).getVmg(this.eFin);
	    double sogVmg = sog - vmg;

	    ret.append("   ");
	    ret.append(Globals.DATE_TIME_FORMATTER.format(this.derrota.get(i).getTimeIni()));
	    ret.append("   ");
	    ret.append(Nautical.formateaRumbo(cog));
	    ret.append("   ");
	    ret.append(Nautical.formateaRumbo(cmg));
	    ret.append("   ");
	    ret.append(Nautical.formateaRumbo(dev));
	    ret.append("   ");
	    ret.append(Nautical.formateaDistancia(dtd));
	    ret.append("   ");
	    ret.append(Nautical.formateaDistancia(sog));
	    ret.append("   ");
	    ret.append(Nautical.formateaDistancia(vmg));
	    ret.append("   ");
	    ret.append(Nautical.formateaDistancia(sogVmg));

	    ret.append("\n");
	}
	ret.append("  --------------------------------------------------------------------------------\n");
	ret.append("\n");
	return ret.toString();
    }

    /**
     * Construye un <code>String</code> con todos los atributos según el formato
     * "nombre = valor".
     * 
     * @return Una representación en forma de <code>String</code> de este
     *         objeto.
     */
    @Override
	public String toString() {
	StringBuffer retValue = new StringBuffer();
	retValue.append("Bordo : ").append(super.toString());
	retValue.append("\n").append("descripcion = ")
		.append(Cadenas.toStringGenerico(this.descripcion));
	retValue.append("\n").append("eIni = ")
		.append(Cadenas.toStringGenerico(this.eIni));
	retValue.append("\n").append("tIni = ")
		.append(Cadenas.toStringGenerico(this.tIni));
	retValue.append("\n").append("eFin = ")
		.append(Cadenas.toStringGenerico(this.eFin));
	retValue.append("\n").append("tFin = ")
		.append(Cadenas.toStringGenerico(this.tFin));
	retValue.append("\n").append("derrota = ")
		.append(Cadenas.toStringGenerico(this.derrota));
	return retValue.toString();
    }

}
