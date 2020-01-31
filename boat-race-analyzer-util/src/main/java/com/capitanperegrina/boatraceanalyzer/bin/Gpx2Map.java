package com.capitanperegrina.boatraceanalyzer.bin;

import com.capitanperegrina.boatraceanalyzer.util.GpxUtils;
import com.capitanperegrina.boatraceanalyzer.util.OpenStreetMapHtmlGenerator;

import io.jenetics.jpx.GPX;

public class Gpx2Map {

	public static void main(String[] args) {
		try {
		    GPX route = GpxUtils.read("G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_route.gpx");
		    
			// GPX track = GpxUtils.read("G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_peregrina.gpx");
			
			System.out.println( OpenStreetMapHtmlGenerator.getHtml(route, null) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

