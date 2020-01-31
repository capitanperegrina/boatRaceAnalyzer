package com.capitanperegrina.boatraceanalyzer.bin;

import com.capitanperegrina.boatraceanalyzer.util.EstelaUtils;

import io.jenetics.jpx.GPX;

public class EstelaCleanCsv {

	public static void main(String[] args) {
		try {
			EstelaUtils.estelaCsv2Gpx("Atxurri", 
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376atxurri.csv",
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_atxurri.gpx",
					GPX.Version.V11);
			EstelaUtils.estelaCsv2Gpx("Miña Miniña",
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376mina-minina.csv",
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_mina-minina.gpx",
					GPX.Version.V11);
			EstelaUtils.estelaCsv2Gpx("Patela",
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376patela.csv",
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_patela.gpx",
					GPX.Version.V11);
			EstelaUtils.estelaCsv2Gpx("Voodoo",
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376voodoo.csv",
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_voodoo.gpx",
					GPX.Version.V11);
			EstelaUtils.estelaCsv2Gpx("Cassandra",
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376cassandra.csv",
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_cassandra.gpx",
					GPX.Version.V11);
			EstelaUtils.estelaCsv2Gpx("Mou",
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376mou.csv",
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_mou.gpx",
					GPX.Version.V11);
			EstelaUtils.estelaCsv2Gpx("Peregrina",
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376peregrina.csv",
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_peregrina.gpx",
					GPX.Version.V11);			
			
//			EstelaUtils.estelaCsv2Gpx("Atxurri", GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 13:45:00"),
//					GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 16:15:48"),
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376atxurri.csv",
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_atxurri.gpx");
//			EstelaUtils.estelaCsv2Gpx("Miña Miniña", GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 13:45:00"),
//					GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:35:18"),
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376mina-minina.csv",
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_mina-minina.gpx");
//			EstelaUtils.estelaCsv2Gpx("Patela", GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 13:45:00"),
//					GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:41:07"),
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376patela.csv",
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_patela.gpx");
//			EstelaUtils.estelaCsv2Gpx("Voodoo", GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 13:45:00"),
//					GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:51:31"),
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376voodoo.csv",
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_voodoo.gpx");
//			EstelaUtils.estelaCsv2Gpx("Cassandra", GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 13:45:00"),
//					GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:53:57"),
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376cassandra.csv",
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_cassandra.gpx");
//			EstelaUtils.estelaCsv2Gpx("Mou", GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 13:45:00"),
//					GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 16:13:55"),
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376mou.csv",
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_mou.gpx");
//			EstelaUtils.estelaCsv2Gpx("Peregrina", GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 13:45:00"),
//					GlobalsNaming.DATE_TIME_FORMATTER.parse("25/01/2020 15:53:20"),
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376peregrina.csv",
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_peregrina.gpx");

//			List<String> files = new ArrayList<>();
//			files.add("G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376atxurri.csv");
//			files.add("G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376mina-minina.csv");
//			files.add("G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376patela.csv");
//			files.add("G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376voodoo.csv");
//			files.add("G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376cassandra.csv");
//			files.add("G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376mou.csv");
//			files.add("G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376peregrina.csv");
//			EstelaUtils.unificaCsv(files, "C:\\Users\\Usuario\\Downloads\\race5376.csv");        	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
