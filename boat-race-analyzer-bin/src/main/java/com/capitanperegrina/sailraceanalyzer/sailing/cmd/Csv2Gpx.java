package com.capitanperegrina.sailraceanalyzer.sailing.cmd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.capitanperegrina.common.Ficheros;

public class Csv2Gpx {

    public static void csv2gpx(final String in) {

	BufferedReader br = null;
	String line = "";
	final String cvsSplitBy = ",";

	try {
	    final StringBuilder lineas = new StringBuilder();
	    lineas.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
		    + "<gpx version=\"1.1\" creator=\"capitanperegrina.com\" xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd http://www.garmin.com/xmlschemas/GpxExtensions/v3 http://www.garmin.com/xmlschemas/GpxExtensionsv3.xsd http://www.garmin.com/xmlschemas/TrackPointExtension/v1 http://www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd\" xmlns=\"http://www.topografix.com/GPX/1/1\" xmlns:gpxtpx=\"http://www.garmin.com/xmlschemas/TrackPointExtension/v1\" xmlns:gpxx=\"http://www.garmin.com/xmlschemas/GpxExtensions/v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
		    + "  <metadata>\n" + "    <author>\n" + "      <name>Carlos Núñez García</name>\n"
		    + "      <email id=\"yo\" domain=\"capitanperegrina.com\"/>\n" + "    </author>\n"
		    + "    <link href=\"http://www.capitanperegrina.com\">\n"
		    + "      <text>capitanperegrina.com</text>\n" + "    </link>\n"
		    + "    <time>2013-08-21T14:07:27Z</time>\n" + "  </metadata>\n" + "  <trk>\n"
		    + "    <src>http://www.capitanperegrina.com/</src>\n"
		    + "    <link href=\"http://www.capitanperegrina.com/workouts/84996034/2843970\">\n"
		    + "      <text>capitanperegrina.com</text>\n" + "    </link>\n" + "    <type>SAILING</type>\n"
		    + "    <trkseg>");

	    br = new BufferedReader(new FileReader(in));
	    br.readLine();
	    while ((line = br.readLine()) != null) {
		final String[] campos = line.split(cvsSplitBy);
		lineas.append("      <trkpt lat=\"");
		lineas.append(campos[1]);
		lineas.append("\" lon=\"");
		lineas.append(campos[2]);
		lineas.append("\"><time>");
		lineas.append(campos[0]);
		lineas.append("Z</time></trkpt>");
		lineas.append("\n");
	    }

	    lineas.append("    </trkseg>\r\n" + "  </trk>\r\n" + "</gpx>");

	    // System.out.println(lineas.toString());
	    Ficheros.string2File(in.replace(".csv", ".gpx"), lineas.toString());

	} catch (final FileNotFoundException e) {
	    e.printStackTrace();
	} catch (final IOException e) {
	    e.printStackTrace();
	} finally {
	    if (br != null) {
		try {
		    br.close();
		} catch (final IOException e) {
		    e.printStackTrace();
		}
	    }
	}

    }

    public static void main(final String[] args) {
	final String folder = "G:\\Mi unidad\\Barco\\Regatas\\201911_REGATA_DE_NAVIDAD_DE_AGUETE\\ANALISIS\\001";
	csv2gpx(folder + "\\race5246peregrina.csv");
	csv2gpx(folder + "\\race5246cassandra.csv");

    }
}
