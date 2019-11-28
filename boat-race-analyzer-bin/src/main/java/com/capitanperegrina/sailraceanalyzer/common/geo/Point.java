package com.capitanperegrina.sailraceanalyzer.common.geo;

import java.io.Serializable;

import com.capitanperegrina.sailraceanalyzer.common.Globals;
import com.capitanperegrina.sailraceanalyzer.common.math.Vector;
import com.capitanperegrina.sailraceanalyzer.common.util.Nautical;

public class Point extends ElementoEnMapa implements Serializable {
	private static final long serialVersionUID = -1223689019830999505L;

	private double latitude;
	private double longitude;

	public Point() {
	}

	public Point(double lat, double lon) {
		this.latitude = lat;
		this.longitude = lon;
	}

	public Point(double lat, double lon, String nombre, Integer tipo) {
		this.latitude = lat;
		this.longitude = lon;
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Point getMiddlePoint(Point otherPoint) {
		// double dLat = Math.toRadians( otherPoint.getLatitude() -
		// this.getLatitude() );
		double dLon = Math.toRadians(otherPoint.getLongitude() - this.getLongitude());
		double lat1 = Math.toRadians(this.getLatitude());
		double lon1 = Math.toRadians(this.getLongitude());
		double lat2 = Math.toRadians(otherPoint.getLatitude());
		// double lon2 = Math.toRadians( otherPoint.getLongitude() );

		double Bx = Math.cos(lat2) * Math.cos(dLon);
		double By = Math.cos(lat2) * Math.sin(dLon);
		double lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2),
				Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By));
		double lon3 = lon1 + Math.atan2(By, Math.cos(lat1) + Bx);

		Point middlePoint = new Point();
		middlePoint.setLatitude(lat3);
		middlePoint.setLongitude(lon3);

		return middlePoint;
		/*
		 * Point middlePoint = new Point(); middlePoint.setLatitude( ( this.latitude +
		 * otherPoint.getLatitude() )/2 ); middlePoint.setLongitude( ( this.longitude +
		 * otherPoint.getLongitude() )/2 ); return middlePoint;
		 */
	}

	public double distanceInMeters(Point otherPoint) {
		double lat1 = Math.toRadians(this.latitude);
		double lon1 = Math.toRadians(this.longitude);
		double lat2 = Math.toRadians(otherPoint.getLatitude());
		double lon2 = Math.toRadians(otherPoint.getLongitude());

		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;

		double sinlat = Math.sin(dlat / 2);
		double sinlon = Math.sin(dlon / 2);

		double a = (sinlat * sinlat) + Math.cos(lat1) * Math.cos(lat2) * (sinlon * sinlon);
		double c = 2 * Math.asin(Math.min(1.0, Math.sqrt(a)));

		double distanceInMeters = Globals.EARTH_RADIUS * c * 1000;

		return distanceInMeters;
	}

	@Override
	public double distanceInMeters(ElementoEnMapa elemento) {
		if (elemento instanceof Point) {
			return distanceInMeters((Point) elemento);
		} else if (elemento instanceof Line) {
			return distanceInMeters((Line) elemento);
		} else {
			return Double.NaN;
		}
	}

	public double distanceInMeters(Line line) {
		return line.distanceInMeters(this);
	}

	public double heading(Point otherPoint) {
		Point pointA = new Point();
		pointA.setLatitude(this.latitude);
		pointA.setLongitude(otherPoint.longitude);

		double dx = pointA.distanceInMeters(this);
		double dy = pointA.distanceInMeters(otherPoint);

		double angle = Math.atan2(dy, dx) * 180 / Math.PI;
		angle = 90 - angle;

		int cuadrante = this.longitude <= otherPoint.getLongitude() ? this.latitude <= otherPoint.getLatitude() ? 3 : 2
				: this.latitude <= otherPoint.getLatitude() ? 1 : 0;

		angle = angle + 90 * cuadrante;

		if (angle < 0)
			angle = angle + 360;

		if (angle > 360)
			angle = angle - 360;

		return angle;
	}

	public Vector toVector() {
		Vector v = new Vector();
		v.setX(this.latitude);
		v.setY(this.longitude);
		return v;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Point other = (Point) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

	@Override
	public String generaString() {
		StringBuffer retValue = new StringBuffer();
		retValue.append("( " + Nautical.formateaCoordenada(this.latitude) + " , "
				+ Nautical.formateaCoordenada(this.longitude) + " )");
		return retValue.toString();
	}

	/**
	 * Construye un <code>String</code> con todos los atributos según el formato
	 * "nombre = valor".
	 * 
	 * @return Una representación en forma de <code>String</code> de este objeto.
	 */
	@Override
	public String toString() {
		StringBuffer retValue = new StringBuffer();
		retValue.append("( " + this.latitude + " , " + this.longitude + " )");
		return retValue.toString();
	}

	/*
	 * public static void main( String[] args ) { Point camouco = new Point();
	 * camouco.setLatitude( 42.39924916812845d ); camouco.setLongitude(
	 * -8.900018695742887d );
	 * 
	 * Point picamillo = new Point(); picamillo.setLatitude( 42.39911147815722d );
	 * picamillo.setLongitude( -8.903162453208648d );
	 * 
	 * Point puerto = new Point(); puerto.setLatitude( 42.45793256674751d );
	 * puerto.setLongitude( -8.917958921677727d );
	 * 
	 * Point faroOns = new Point(); faroOns.setLatitude( 42.38273139142585d );
	 * faroOns.setLongitude( -8.936203880278999d );
	 * 
	 * Point caboUdra = new Point(); caboUdra.setLatitude( 42.3401372614937d );
	 * caboUdra.setLongitude( -8.839457126405561d );
	 * 
	 * Point x = new Point(); x.setLatitude( 42.42675856284967d ); x.setLongitude(
	 * -8.906806227633195d );
	 * 
	 * Line line1 = new Line(); line1.setPoint1( camouco ); line1.setPoint2(
	 * picamillo );
	 * 
	 * Line line2 = new Line(); line2.setPoint1( caboUdra ); line2.setPoint2(
	 * faroOns );
	 * 
	 * double distancia = line1.distanceInMeters( line2 ); System.out.println( line1
	 * ); System.out.println( line2 ); System.out.println( distancia );
	 * 
	 * distancia = line2.distanceInMeters( line1 ); System.out.println( line2 );
	 * System.out.println( line1 ); System.out.println( distancia );
	 * 
	 * distancia = puerto.distanceInMeters( camouco ); double angulo =
	 * puerto.heading( camouco ); System.out.println(
	 * "\n\nDistancia puerto-camouco: " + Nautical.fromMeters( distancia ) + " nm."
	 * ); System.out.println( angulo + " º\n\n" );
	 * 
	 * System.out.println( "puerto-AB: " ); distancia = puerto.distanceInMeters(
	 * line1 ); System.out.println( "Distancia puerto-AB: " + Nautical.fromMeters(
	 * distancia ) + " nm." );
	 * 
	 * distancia = puerto.distanceInMeters( camouco ); System.out.println(
	 * "Distancia puerto-camouco: " + Nautical.fromMeters( distancia ) + " nm." );
	 * 
	 * distancia = puerto.distanceInMeters( picamillo ); System.out.println(
	 * "Distancia puerto-picamillo: " + Nautical.fromMeters( distancia ) +
	 * " nm.\n\n" );
	 * 
	 * System.out.println( "faroOns-AB: " ); distancia = faroOns.distanceInMeters(
	 * line1 ); System.out.println( "Distancia faroOns-AB: " + Nautical.fromMeters(
	 * distancia ) + " nm." );
	 * 
	 * distancia = faroOns.distanceInMeters( camouco ); System.out.println(
	 * "Distancia faroOns-camouco: " + Nautical.fromMeters( distancia ) + " nm." );
	 * 
	 * distancia = faroOns.distanceInMeters( picamillo ); System.out.println(
	 * "Distancia faroOns-picamillo: " + Nautical.fromMeters( distancia ) +
	 * " nm.\n\n" );
	 * 
	 * System.out.println( "caboUdra-AB: " ); distancia = caboUdra.distanceInMeters(
	 * line1 ); System.out.println( "Distancia caboUdra-AB: " + Nautical.fromMeters(
	 * distancia ) + " nm." );
	 * 
	 * distancia = caboUdra.distanceInMeters( camouco ); System.out.println(
	 * "Distancia caboUdra-camouco: " + Nautical.fromMeters( distancia ) + " nm." );
	 * 
	 * distancia = caboUdra.distanceInMeters( picamillo ); System.out.println(
	 * "Distancia caboUdra-picamillo: " + Nautical.fromMeters( distancia ) +
	 * " nm.\n\n" );
	 * 
	 * System.out.println( "x-AB: " ); distancia = x.distanceInMeters( line1 );
	 * System.out.println( "Distancia x-AB: " + Nautical.fromMeters( distancia ) +
	 * " nm." );
	 * 
	 * distancia = x.distanceInMeters( camouco ); System.out.println(
	 * "Distancia x-camouco: " + Nautical.fromMeters( distancia ) + " nm." );
	 * 
	 * distancia = x.distanceInMeters( picamillo ); System.out.println(
	 * "Distancia x-picamillo: " + Nautical.fromMeters( distancia ) + " nm." );
	 * 
	 * // System.out.println( distancia2 + " m." ); // System.out.println(
	 * Nautical.fromMeters( distancia2 ) + " nm." ); // System.out.println( angulo2
	 * + " º" ); } //
	 */
}