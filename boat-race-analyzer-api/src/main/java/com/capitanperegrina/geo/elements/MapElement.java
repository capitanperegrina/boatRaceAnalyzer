package com.capitanperegrina.geo.elements;

import com.capitanperegrina.boatraceanalyzer.enums.MapElementType;

public interface MapElement {

	String getName();

	void setName(String name);

	MapElementType getType();

	void setType(MapElementType type);
	
	Point centralPosition();

	double distanceInMeters( MapElement otherElement );

	Point cornerNW();
	
	Point cornerNE();
	
	Point cornerSW();
	
	Point cornerSE();
	
	MapElement rotate(double angulo, Point anchor);
	
	String buildString();
}
