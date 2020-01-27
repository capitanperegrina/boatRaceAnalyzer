package com.capitanperegrina.geo.elements;

public interface Line extends MapElement {

	Point getPoint1();

	void setPoint1(Point point1);

	Point getPoint2();

	void setPoint2(Point point2);
	
	// Other methods.
	
	public double slope();	
	
	double getCog();
	
	double getAngle();

	boolean doIntersect(Line otraLinea);

	Point closestPoint(Point point);
}
