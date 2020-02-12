package com.capitanperegrina.geo.elements;

public interface Line extends MapElement {

	Point getPoint1();

	void setPoint1(Point point1);

	Point getPoint2();

	void setPoint2(Point point2);
	
	// Other methods.
	
	double slope();	
	
	double getCog();
	
	double getAngle();
	
	double getAngle(MapElement el);

	boolean doIntersect(Line otraLinea);

	Point closestPoint(Point point);
}
