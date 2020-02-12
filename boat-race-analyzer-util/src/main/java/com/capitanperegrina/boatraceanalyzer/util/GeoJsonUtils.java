package com.capitanperegrina.boatraceanalyzer.util;

import java.io.IOException;
import java.io.StringReader;

import org.geotools.geojson.geom.GeometryJSON;

import com.vividsolutions.jts.geom.Point;

public class GeoJsonUtils {

	public static Point getGeoJsonPoint(double longitude, double latitude) throws IOException {
	    Point point = null;
	    GeometryJSON gtjson = new GeometryJSON();
	    point = gtjson.readPoint(new StringReader("{\"type\":\"Point\", \"coordinates\":[" + longitude + "," + latitude + "]}"));
	    return point;
	}
}
