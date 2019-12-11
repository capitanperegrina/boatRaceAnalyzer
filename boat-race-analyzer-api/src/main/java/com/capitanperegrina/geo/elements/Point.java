package com.capitanperegrina.geo.elements;

public interface Point extends MapElement {

	double getLatitude();

	void setLatitude(double latitude);

	double getLongitude();

	void setLongitude(double longitude);
	
	double heading(Point otherPoint);
	
	Point rotate(double angulo, Point anchor);
	
	
/*
	public Polar polar() {
		Polar p = new Polar();
		p.setModulus(Math.hypot(this.longitude, this.latitude));
		p.setAngle(Math.atan2(this.latitude, this.longitude));
		return p;
	}
	
	public boolean puntosEnCuadranteOpuesto( Point p1, Point p2  ) {
		if ( p1.getLatitude() < this.latitude ) {
			if ( p1.getLongitude() < this.longitude ) {
				return p2.getLatitude() > 0 && p2.getLongitude() > 0;			
			} else {
				return p2.getLatitude() > 0 && p2.getLongitude() < 0;
			}
		} else {
			if ( p1.getLongitude() < this.longitude ) {
				return p2.getLatitude() < 0 && p2.getLongitude() < 0;
			} else {
				return p2.getLatitude() < 0 && p2.getLongitude() < 0;
			}
		}
	}
	*/
	
}
