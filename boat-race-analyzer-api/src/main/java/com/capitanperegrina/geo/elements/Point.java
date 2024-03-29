package com.capitanperegrina.geo.elements;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import com.capitanperegrina.boatraceanalyzer.enums.MapElementType;
import com.capitanperegrina.geo.naming.GeoNaming;

public class Point extends MapElement {

	private static final long serialVersionUID = -3187729654488159968L;

	protected double latitude;
	protected double longitude;

	public Point() {
		super();
		this.type = MapElementType.POINT;
	}

	public Point(double lat, double lon) {
		this.latitude = lat;
		this.longitude = lon;
		this.type = MapElementType.POINT;
	}

	public Point(double lat, double lon, String name) {
		this.latitude = lat;
		this.longitude = lon;
		this.name = name;
		this.type = MapElementType.POINT;
	}

	@Override
	public Point centralPosition() {
		return this;
	}

	@Override
	public double distanceInMeters(MapElement otherElement) {
		if (otherElement instanceof Point) {
			return this.distanceInMeters((Point) otherElement);
		}
		if (otherElement instanceof Line) {
			return otherElement.distanceInMeters(this);
		} else {
			return Double.NaN;
		}
	}

	private double distanceInMeters(Point otherPoint) {
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

		double distanceInMeters = GeoNaming.EARTH_RADIUS * c * 1000;

		return distanceInMeters;
	}

	public double heading(Point otherPoint) {
		Point pointA = new Point();
		pointA.setLatitude(this.latitude);
		pointA.setLongitude(otherPoint.getLongitude());

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

	@Override
	public Point rotate(double angulo, Point anchor) {
		double rad = Math.toRadians(angulo);

		Point2D.Double ini = new Point2D.Double(this.longitude, this.latitude);
		Point2D.Double ret = new Point2D.Double();

		AffineTransform rotation = new AffineTransform();
		rotation.rotate(rad, anchor.getLongitude(), anchor.getLatitude());
		rotation.transform(ini, ret);
		return new Point(ret.getX(), ret.getY());
	}

	@Override
	public Point cornerNW() {
		return this;
	}

	@Override
	public Point cornerNE() {
		return this;
	}

	@Override
	public Point cornerSW() {
		return this;
	}

	@Override
	public Point cornerSE() {
		return this;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(this.latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Point [latitude=").append(this.latitude).append(", longitude=").append(this.longitude)
				.append(", name=").append(this.name).append(", type=").append(this.type).append("]");
		return builder.toString();
	}
}
