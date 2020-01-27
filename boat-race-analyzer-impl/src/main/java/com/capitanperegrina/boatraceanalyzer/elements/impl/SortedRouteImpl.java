package com.capitanperegrina.boatraceanalyzer.elements.impl;

import java.util.ArrayList;
import java.util.List;

import com.capitanperegrina.boatraceanalyzer.elements.SortedRoute;
import com.capitanperegrina.boatraceanalyzer.enums.MapElementType;
import com.capitanperegrina.geo.elements.MapElement;
import com.capitanperegrina.geo.elements.Point;
import com.capitanperegrina.geo.elements.impl.MapElementImpl;
import com.capitanperegrina.geo.elements.impl.PointImpl;

public class SortedRouteImpl extends MapElementImpl implements SortedRoute {

	private static final long serialVersionUID = 929412262039283336L;

    private final List<MapElement> elements = new ArrayList<MapElement>();

    public SortedRouteImpl() {
    	super();
    	this.type = MapElementType.SORTED_ROUTE;
    }

	@Override
	public List<MapElement> getElements() {
		return elements;
	}

	@Override
	public Point cornerNE() {
		double tmpLat = 0;
		double tmpLong = 0;
		for ( MapElement elemento : this.elements ) {
			Point me = elemento.cornerNE();
			if ( tmpLat < me.getLatitude() ) {
				tmpLat = me.getLatitude();
			}
			if ( tmpLong < me.getLongitude() ) {
				tmpLong = me.getLongitude();
			}
		}
		return new PointImpl(tmpLong, tmpLat);
    }

	@Override
	public Point cornerNW() {
		double tmpLat = 0;
		double tmpLong = 0;
		for ( MapElement elemento : this.elements ) {
			Point me = elemento.cornerNE();
			if ( tmpLat < me.getLatitude() ) {
				tmpLat = me.getLatitude();
			}
			if ( tmpLong > me.getLongitude() ) {
				tmpLong = me.getLongitude();
			}
		}
		return new PointImpl(tmpLong, tmpLat);
    }

	@Override
	public Point cornerSE() {
		double tmpLat = 0;
		double tmpLong = 0;
		for ( MapElement elemento : this.elements ) {
			Point me = elemento.cornerNE();
			if ( tmpLat > me.getLatitude() ) {
				tmpLat = me.getLatitude();
			}
			if ( tmpLong < me.getLongitude() ) {
				tmpLong = me.getLongitude();
			}
		}
		return new PointImpl(tmpLong, tmpLat);
    }

	@Override
	public Point cornerSW() {
		double tmpLat = 0;
		double tmpLong = 0;
		for ( MapElement elemento : this.elements ) {
			Point me = elemento.cornerNE();
			if ( tmpLat > me.getLatitude() ) {
				tmpLat = me.getLatitude();
			}
			if ( tmpLong > me.getLongitude() ) {
				tmpLong = me.getLongitude();
			}
		}
		return new PointImpl(tmpLong, tmpLat);
    }
	
    @Override
	public Point centralPosition() {
    	Point nw = cornerNW();
    	Point se = cornerSE();
		return new PointImpl((nw.getLongitude() + se.getLongitude()) / 2, (nw.getLatitude() + se.getLatitude()) / 2);
    }

	@Override
	public double distanceInMeters(MapElement otherElement) {
		throw new RuntimeException("Not Implemented");
	}

	@Override
	public String buildString() {
		throw new RuntimeException("Not Implemented");
	}

	@Override
	public MapElement rotate(double angulo, Point anchor) {
		
		SortedRoute ret = new SortedRouteImpl();
		ret.setName(this.name);
		ret.setType(this.type);
		for ( MapElement me : this.getElements() ) {
			ret.getElements().add( me.rotate(angulo, anchor));
		}
			
		
		throw new RuntimeException("Not Implemented");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SortedRouteImpl [elements=").append(elements).append(", name=").append(name).append(", type=")
				.append(type).append("]");
		return builder.toString();
	}
}
