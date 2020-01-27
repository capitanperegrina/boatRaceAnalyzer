package com.capitanperegrina.boatraceanalyzer.elements;

import java.util.List;

import com.capitanperegrina.geo.elements.MapElement;

public interface SortedRoute extends MapElement {

	public List<MapElement> getElements();
	
/*
    
	public Point getEsquinaNE() {
		double tmpLat = 0;
		double tmpLong = 0;
		for ( MapElement elemento : this.elementos ) {
			Point me = elemento.cornerNE();
			if ( tmpLat < me.getLatitude() ) {
				tmpLat = me.getLatitude();
			}
			if ( tmpLong < me.getLongitude() ) {
				tmpLong = me.getLongitude();
			}
		}
		return new Point(tmpLong, tmpLat);
    }

	public Point getEsquinaNW() {
		double tmpLat = 0;
		double tmpLong = 0;
		for ( MapElement elemento : this.elementos ) {
			Point me = elemento.getEsquinaNE();
			if ( tmpLat < me.getLatitude() ) {
				tmpLat = me.getLatitude();
			}
			if ( tmpLong > me.getLongitude() ) {
				tmpLong = me.getLongitude();
			}
		}
		return new Point(tmpLong, tmpLat);
    }

	public Point getEsquinaSE() {
		double tmpLat = 0;
		double tmpLong = 0;
		for ( MapElement elemento : this.elementos ) {
			Point me = elemento.getEsquinaNE();
			if ( tmpLat > me.getLatitude() ) {
				tmpLat = me.getLatitude();
			}
			if ( tmpLong < me.getLongitude() ) {
				tmpLong = me.getLongitude();
			}
		}
		return new Point(tmpLong, tmpLat);
    }

	public Point getEsquinaSW() {
		double tmpLat = 0;
		double tmpLong = 0;
		for ( MapElement elemento : this.elementos ) {
			Point me = elemento.getEsquinaNE();
			if ( tmpLat > me.getLatitude() ) {
				tmpLat = me.getLatitude();
			}
			if ( tmpLong > me.getLongitude() ) {
				tmpLong = me.getLongitude();
			}
		}
		return new Point(tmpLong, tmpLat);
    }
	
    public Point getCentro() {
    	Point nw = getEsquinaNW();
    	Point se = getEsquinaSE();
		return new Point((nw.getLongitude() + se.getLongitude()) / 2, (nw.getLatitude() + se.getLatitude()) / 2);
    }

	public String generaHtml()
	{
		StringBuilder html = new StringBuilder();
		// FIXME
		// html.append( GoogleMapsUtils.generaHtml( getEsquinaNW(), getEsquinaSE(), this.elementos ) );
		return html.toString();
	}

    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SortedRoute [descripcion=").append(descripcion).append(", elementos=").append(elementos)
				.append("]");
		return builder.toString();
	}
*/
}
