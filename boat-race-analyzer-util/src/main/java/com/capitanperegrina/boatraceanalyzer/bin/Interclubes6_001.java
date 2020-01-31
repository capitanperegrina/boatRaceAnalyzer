package com.capitanperegrina.boatraceanalyzer.bin;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capitanperegrina.boatraceanalyzer.beans.SailRaceBean;
import com.capitanperegrina.boatraceanalyzer.beans.SailRaceLegBean;
import com.capitanperegrina.boatraceanalyzer.beans.TrackLineBean;
import com.capitanperegrina.boatraceanalyzer.elements.TrackLineSegment;
import com.capitanperegrina.boatraceanalyzer.naming.GlobalsNaming;
import com.capitanperegrina.boatraceanalyzer.service.SailRaceParser;
import com.capitanperegrina.boatraceanalyzer.writers.SailRaceBeanCsvWriter;

@Component
public class Interclubes6_001 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Interclubes6_001.class);

	@Autowired
	private SailRaceParser sailRaceParser;

	public void run() {
		String routeFile = "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_route.gpx";

		Map<String, Map<Integer, SailRaceLegBean>> legs = new HashMap<>();
		Map<String, String> trackFiles = new HashMap<>();
		trackFiles.put("Atxurri", "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_atxurri.gpx");
		trackFiles.put("Cassandra", "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_cassandra.gpx");
		trackFiles.put("Miña Miniña", "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_mina-minina.gpx");
		trackFiles.put("Mou", "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_mou.gpx");
		trackFiles.put("Patela", "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_patela.gpx");
		trackFiles.put("Peregrina", "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_peregrina.gpx");
		trackFiles.put("Voodoo", "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_voodoo.gpx");

		SailRaceBean sr = this.sailRaceParser.parse(routeFile, trackFiles);

		try {
			int i = 1;
			String peregrinaName = "Peregrina";
			legs.put(peregrinaName, new HashMap<>());
			SailRaceLegBean peregrina1 = new SailRaceLegBean(peregrinaName, i++, sr.getRoute().getElements().get(0), sr.getRoute().getElements().get(1), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 13:45:00"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 15:15:01"));
			SailRaceLegBean peregrina2 = new SailRaceLegBean(peregrinaName, i++, sr.getRoute().getElements().get(1), sr.getRoute().getElements().get(2), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 14:15:01"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:01:00"));
			SailRaceLegBean peregrina3 = new SailRaceLegBean(peregrinaName, i++, sr.getRoute().getElements().get(2), sr.getRoute().getElements().get(3), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:01:00"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:35:35"));
			SailRaceLegBean peregrina4 = new SailRaceLegBean(peregrinaName, i++, sr.getRoute().getElements().get(3), sr.getRoute().getElements().get(4), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:35:35"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:53:20"));
			
			legs.get(peregrina1.getBoat()).put(peregrina1.getLegNumber(), peregrina1);
			legs.get(peregrina2.getBoat()).put(peregrina2.getLegNumber(), peregrina2);
			legs.get(peregrina3.getBoat()).put(peregrina3.getLegNumber(), peregrina3);
			legs.get(peregrina4.getBoat()).put(peregrina4.getLegNumber(), peregrina4);

			String cassandraName = "Cassandra";
			legs.put(cassandraName, new HashMap<>());
			SailRaceLegBean cassandra1 = new SailRaceLegBean(cassandraName, i++, sr.getRoute().getElements().get(0), sr.getRoute().getElements().get(1), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 13:45:00"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 15:14:55"));
			SailRaceLegBean cassandra2 = new SailRaceLegBean(cassandraName, i++, sr.getRoute().getElements().get(1), sr.getRoute().getElements().get(2), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 14:14:55"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:05:00"));
			SailRaceLegBean cassandra3 = new SailRaceLegBean(cassandraName, i++, sr.getRoute().getElements().get(2), sr.getRoute().getElements().get(3), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:05:00"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:35:37"));
			SailRaceLegBean cassandra4 = new SailRaceLegBean(cassandraName, i++, sr.getRoute().getElements().get(3), sr.getRoute().getElements().get(4), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:35:37"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:53:57"));
			
			legs.get(cassandra1.getBoat()).put(cassandra1.getLegNumber(), cassandra1);
			legs.get(cassandra2.getBoat()).put(cassandra2.getLegNumber(), cassandra2);
			legs.get(cassandra3.getBoat()).put(cassandra3.getLegNumber(), cassandra3);
			legs.get(cassandra4.getBoat()).put(cassandra4.getLegNumber(), cassandra4);

			String atuxurriName = "Atxurri";
			legs.put(atuxurriName, new HashMap<>());
			SailRaceLegBean atxurri1 = new SailRaceLegBean(atuxurriName, i++, sr.getRoute().getElements().get(0), sr.getRoute().getElements().get(1), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 13:45:00"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 15:20:41"));
			SailRaceLegBean atxurri2 = new SailRaceLegBean(atuxurriName, i++, sr.getRoute().getElements().get(1), sr.getRoute().getElements().get(2), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 14:20:41"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:21:26"));
			SailRaceLegBean atxurri3 = new SailRaceLegBean(atuxurriName, i++, sr.getRoute().getElements().get(2), sr.getRoute().getElements().get(3), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:21:26"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:58:32"));
			SailRaceLegBean atxurri4 = new SailRaceLegBean(atuxurriName, i++, sr.getRoute().getElements().get(3), sr.getRoute().getElements().get(4), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:58:32"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 17:15:48"));
			
			legs.get(atxurri1.getBoat()).put(atxurri1.getLegNumber(), atxurri1);
			legs.get(atxurri2.getBoat()).put(atxurri2.getLegNumber(), atxurri2);
			legs.get(atxurri3.getBoat()).put(atxurri3.getLegNumber(), atxurri3);
			legs.get(atxurri4.getBoat()).put(atxurri4.getLegNumber(), atxurri4);
			
			String patelaName = "Patela";
			legs.put(patelaName, new HashMap<>());
			SailRaceLegBean patela1 = new SailRaceLegBean(patelaName, i++, sr.getRoute().getElements().get(0), sr.getRoute().getElements().get(1), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 13:45:00"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 15:09:29"));
			SailRaceLegBean patela2 = new SailRaceLegBean(patelaName, i++, sr.getRoute().getElements().get(1), sr.getRoute().getElements().get(2), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 14:09:29"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 15:51:33"));
			SailRaceLegBean patela3 = new SailRaceLegBean(patelaName, i++, sr.getRoute().getElements().get(2), sr.getRoute().getElements().get(3), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 14:51:33"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:23:07"));
			SailRaceLegBean patela4 = new SailRaceLegBean(patelaName, i++, sr.getRoute().getElements().get(3), sr.getRoute().getElements().get(4), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:23:07"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:41:07"));
			
			legs.get(patela1.getBoat()).put(patela1.getLegNumber(), patela1);
			legs.get(patela2.getBoat()).put(patela2.getLegNumber(), patela2);
			legs.get(patela3.getBoat()).put(patela3.getLegNumber(), patela3);
			legs.get(patela4.getBoat()).put(patela4.getLegNumber(), patela4);
			
			String mouName = "Mou";
			legs.put(mouName, new HashMap<>());
			SailRaceLegBean mou1 = new SailRaceLegBean(mouName, i++, sr.getRoute().getElements().get(0), sr.getRoute().getElements().get(1), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 13:45:00"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 15:20:59"));
			SailRaceLegBean mou2 = new SailRaceLegBean(mouName, i++, sr.getRoute().getElements().get(1), sr.getRoute().getElements().get(2), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 14:20:59"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:23:26"));
			SailRaceLegBean mou3 = new SailRaceLegBean(mouName, i++, sr.getRoute().getElements().get(2), sr.getRoute().getElements().get(3), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:23:26"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:57:08"));
			SailRaceLegBean mou4 = new SailRaceLegBean(mouName, i++, sr.getRoute().getElements().get(3), sr.getRoute().getElements().get(4), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:57:08"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 17:13:55"));
			
			legs.get(mou1.getBoat()).put(mou1.getLegNumber(), mou1);
			legs.get(mou2.getBoat()).put(mou2.getLegNumber(), mou2);
			legs.get(mou3.getBoat()).put(mou3.getLegNumber(), mou3);
			legs.get(mou4.getBoat()).put(mou4.getLegNumber(), mou4);
			
			String voodooName = "Voodoo";
			legs.put(voodooName, new HashMap<>());
			SailRaceLegBean voodoo1 = new SailRaceLegBean(voodooName, i++, sr.getRoute().getElements().get(0), sr.getRoute().getElements().get(1), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 13:45:00"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 15:11:48"));
			SailRaceLegBean voodoo2 = new SailRaceLegBean(voodooName, i++, sr.getRoute().getElements().get(1), sr.getRoute().getElements().get(2), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 14:11:48"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 15:57:53"));
			SailRaceLegBean voodoo3 = new SailRaceLegBean(voodooName, i++, sr.getRoute().getElements().get(2), sr.getRoute().getElements().get(3), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 14:57:53"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:32:23"));
			SailRaceLegBean voodoo4 = new SailRaceLegBean(voodooName, i++, sr.getRoute().getElements().get(3), sr.getRoute().getElements().get(4), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:32:23"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:51:31"));
			
			legs.get(voodoo1.getBoat()).put(voodoo1.getLegNumber(), voodoo1);
			legs.get(voodoo2.getBoat()).put(voodoo2.getLegNumber(), voodoo2);
			legs.get(voodoo3.getBoat()).put(voodoo3.getLegNumber(), voodoo3);
			legs.get(voodoo4.getBoat()).put(voodoo4.getLegNumber(), voodoo4);			

			String minaMininaName = "Miña Miniña";
			legs.put(minaMininaName, new HashMap<>());
			SailRaceLegBean minaMinina1 = new SailRaceLegBean(minaMininaName, i++, sr.getRoute().getElements().get(0), sr.getRoute().getElements().get(1), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 13:45:00"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 15:07:49"));
			SailRaceLegBean minaMinina2 = new SailRaceLegBean(minaMininaName, i++, sr.getRoute().getElements().get(1), sr.getRoute().getElements().get(2), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 14:07:49"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 15:49:13"));
			SailRaceLegBean minaMinina3 = new SailRaceLegBean(minaMininaName, i++, sr.getRoute().getElements().get(2), sr.getRoute().getElements().get(3), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 14:49:13"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:19:20"));
			SailRaceLegBean minaMinina4 = new SailRaceLegBean(minaMininaName, i++, sr.getRoute().getElements().get(3), sr.getRoute().getElements().get(4), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:19:20"), GlobalsNaming.DATE_TIME_FORMATTER.parse("25/11/2020 16:35:18"));
			
			legs.get(minaMinina1.getBoat()).put(minaMinina1.getLegNumber(), minaMinina1);
			legs.get(minaMinina2.getBoat()).put(minaMinina2.getLegNumber(), minaMinina2);
			legs.get(minaMinina3.getBoat()).put(minaMinina3.getLegNumber(), minaMinina3);
			legs.get(minaMinina4.getBoat()).put(minaMinina4.getLegNumber(), minaMinina4);

		} catch (ParseException e) {
			LOGGER.error("Error while parsing date");
			throw new RuntimeException(e);
		}
		sr.getLegs().putAll(legs);
		
		Map<String,Double> distanciaTotal = new HashMap<>();
		
		for ( Entry<String, TrackLineBean> entry : sr.getTracks().entrySet() ) {
			double d=0d;
			for ( TrackLineSegment tls : entry.getValue().getTrackLineSegments() ) {
				d = d + tls.getPoint1().distanceInMeters( tls.getPoint2() );
			}
			distanciaTotal.put( entry.getKey(), d );
		}
		LOGGER.info(distanciaTotal.toString());

		SailRaceBeanCsvWriter.write("G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\2020_INTERCLUBES_001.csv", sr);
	}

}
