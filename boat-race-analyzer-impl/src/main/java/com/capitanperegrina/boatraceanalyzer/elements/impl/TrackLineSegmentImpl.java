package com.capitanperegrina.boatraceanalyzer.elements.impl;

import java.util.Date;

import com.capitanperegrina.boatraceanalyzer.elements.TrackLineSegment;
import com.capitanperegrina.boatraceanalyzer.enums.MapElementType;
import com.capitanperegrina.boatraceanalyzer.util.Nautical;
import com.capitanperegrina.geo.elements.Line;
import com.capitanperegrina.geo.elements.MapElement;
import com.capitanperegrina.geo.elements.Point;
import com.capitanperegrina.geo.elements.impl.LineImpl;
import com.capitanperegrina.gpx.elements.WptType;
import com.capitanperegrina.weather.elements.Wind;

public class TrackLineSegmentImpl extends LineImpl implements TrackLineSegment {

private static final long	serialVersionUID	= -198202301694376025L;
	
	protected Date				timeIni;
	protected Date				timeEnd;
	protected Wind				wind;

	public TrackLineSegmentImpl(Point p1, Point p2, String name) {
		super(p1, p2, name);
	}

	public TrackLineSegmentImpl(double lat1, double lon1, double lat2, double lon2, Date timeIni, Date timeEnd,
			Wind wind) {
		super(lat1, lon1, lat2, lon2);
		this.timeIni = timeIni;
		this.timeEnd = timeEnd;
		this.wind = wind;
	}

	public TrackLineSegmentImpl( WptType wp1, WptType wp2 )
	{
		super( wp1.getLat().doubleValue(), wp1.getLon().doubleValue(), wp2.getLat().doubleValue(), wp2.getLon().doubleValue() );
		this.timeIni = wp1.getTime().toGregorianCalendar().getTime();
		this.timeEnd = wp2.getTime().toGregorianCalendar().getTime();
		this.wind = null;
		this.type = MapElementType.TRACK_LINE_SEGMENT;
	}
	
	public TrackLineSegmentImpl(double lat1, double lon1, double lat2, double lon2, Date timeIni, Date timeFin, String name) {
		super(lat1, lon1, lat2, lon2, name);
		this.wind = null;
		this.type = MapElementType.TRACK_LINE_SEGMENT;
	}
	
	public TrackLineSegmentImpl(double lat1, double lon1, double lat2, double lon2, Date timeIni, Date timeFin) {
		super(lat1, lon1, lat2, lon2);
		this.wind = null;
		this.type = MapElementType.TRACK_LINE_SEGMENT;
	}
	
	public TrackLineSegmentImpl(Point p1, Point p2, Date timeIni, Date timeFin, String name) {
		super(p1, p2, name);
		this.wind = null;
		this.type = MapElementType.TRACK_LINE_SEGMENT;
	}
	
	public TrackLineSegmentImpl(Point p1, Point p2, Date timeIni, Date timeFin) {
		super(p1, p2);
		this.wind = null;
		this.type = MapElementType.TRACK_LINE_SEGMENT;
	}

	@Override
	public Date getTimeIni() {
		return timeIni;
	}

	public void setTimeIni(Date timeIni) {
		this.timeIni = timeIni;
	}

	@Override
	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	@Override
	public Date getTime()
	{
		long diff = Math.abs( this.timeEnd.getTime() - this.timeIni.getTime() );
		return new Date( diff );
	}

	@Override
	public double getVmg( MapElement destination )
	{
		double initialDistance = this.getPoint1().distanceInMeters( destination );
		double finalDistance = this.getPoint2().distanceInMeters( destination );
		double traveledDistance = initialDistance - finalDistance;
		traveledDistance = Nautical.fromMeters( traveledDistance );
		double time = this.getTime().getTime();
		time = time / ( 1000 * 60 * 60 );
		if ( time > 0 )
		{
			return traveledDistance / time;
		}
		else
		{
			return Double.NaN;
		}
	}

	@Override
	public double getCmg( MapElement destination )
	{
		Line cmg;
		if ( destination instanceof Point )
		{
			cmg = new LineImpl( this.point1, (Point) destination );
		}
		else
		{
			cmg = new LineImpl( this.point1, ( (Line) destination ).closestPoint( this.point1 ) );
		}
		return cmg.getCog();
	}

	@Override
	public double getSog()
	{
		double distance = this.getPoint1().distanceInMeters( this.getPoint2() );
		distance = Nautical.fromMeters( distance );
		double time = this.getTime().getTime();
		time = time / ( 1000 * 60 * 60 );
		if ( time > 0 )
		{
			return distance / time;
		}
		else
		{
			return Double.NaN;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TrackLineSegmentImpl [timeIni=").append(timeIni).append(", timeEnd=").append(timeEnd)
				.append(", wind=").append(wind).append(", point1=").append(point1).append(", point2=").append(point2)
				.append(", name=").append(name).append(", type=").append(type).append("]");
		return builder.toString();
	}
}
