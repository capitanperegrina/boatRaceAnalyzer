package com.capitanperegrina.boatraceanalyzer.elements;

import java.util.Date;

import com.capitanperegrina.geo.elements.Line;
import com.capitanperegrina.geo.elements.MapElement;

public interface TrackLineSegment extends Line {

	public Date getTime();
	
	public Date getTimeIni();
	
	public Date getTimeEnd();

	public double getVmg( MapElement destino );

	public double getCmg( MapElement destino );

	public double getSog();
}
