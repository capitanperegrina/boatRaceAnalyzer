package com.capitanperegrina.boarraceanalyzer.boot.console;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.capitanperegrina.boatraceanalyzer.beans.SailRaceBean;
import com.capitanperegrina.boatraceanalyzer.beans.SailRaceLegBean;
import com.capitanperegrina.boatraceanalyzer.naming.GlobalsNaming;
import com.capitanperegrina.boatraceanalyzer.service.SailRaceParser;
import com.capitanperegrina.boatraceanalyzer.writers.SailRaceBeanCsvWriter;

@ComponentScan("com.capitanperegrina")
@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootConsoleApplication.class);
    
    @Autowired
    private SailRaceParser sailRaceParser;
 
    public static void main(String[] args) {
        LOGGER.info("STARTING THE APPLICATION");
        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        LOGGER.info("APPLICATION FINISHED");
    }
  
    @Override
    public void run(String... args) {
        LOGGER.info("EXECUTING : command line runner");
        String routeFile = "G:\\Mi unidad\\Barco\\Regatas\\201911_REGATA_DE_NAVIDAD_DE_AGUETE\\ANALISIS\\003\\route.gpx";
        Map<String,Map<Integer,SailRaceLegBean>> legs = new HashMap<>();
        Map<String,String> trackFiles = new HashMap<>();
        trackFiles.put("Cassandra", "G:\\Mi unidad\\Barco\\Regatas\\201911_REGATA_DE_NAVIDAD_DE_AGUETE\\ANALISIS\\003\\cassandra.gpx");
        trackFiles.put("Peregrina", "G:\\Mi unidad\\Barco\\Regatas\\201911_REGATA_DE_NAVIDAD_DE_AGUETE\\ANALISIS\\003\\peregrina.gpx");

        SailRaceBean sr = this.sailRaceParser.parse(routeFile, trackFiles);
        
        try {
        	int i = 1;
        	String boatName = "Peregrina";
        	legs.put(boatName,new HashMap<>());
        	SailRaceLegBean srl = new SailRaceLegBean();
        	srl.setBoat(boatName);
        	srl.setLegNumber(i++);
        	srl.setFromPosition(sr.getRoute().getElements().get(0));
        	srl.setToPosition(sr.getRoute().getElements().get(1));
            srl.setFromTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 13:50:00"));
            srl.setToTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 14:20:15"));
            legs.get(srl.getBoat()).put(srl.getLegNumber(), srl);
            
            srl = new SailRaceLegBean();
        	srl.setBoat(boatName);
        	srl.setLegNumber(i++);
        	srl.setFromPosition(sr.getRoute().getElements().get(1));
        	srl.setToPosition(sr.getRoute().getElements().get(2));
            srl.setFromTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 14:20:15"));
            srl.setToTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 14:51:11"));
            legs.get(srl.getBoat()).put(srl.getLegNumber(), srl);
            
            srl = new SailRaceLegBean();
        	srl.setBoat(boatName);
        	srl.setLegNumber(i++);
        	srl.setFromPosition(sr.getRoute().getElements().get(2));
        	srl.setToPosition(sr.getRoute().getElements().get(3));
            srl.setFromTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 14:51:11"));
            srl.setToTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 15:35:35"));
            legs.get(srl.getBoat()).put(srl.getLegNumber(), srl);
            
            srl = new SailRaceLegBean();
        	srl.setBoat(boatName);
        	srl.setLegNumber(i++);
        	srl.setFromPosition(sr.getRoute().getElements().get(3));
        	srl.setToPosition(sr.getRoute().getElements().get(4));
            srl.setFromTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 15:35:35"));
            srl.setToTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 15:37:59"));
            legs.get(srl.getBoat()).put(srl.getLegNumber(), srl);
            
            i = 1;
            boatName = "Cassandra";
        	legs.put(boatName,new HashMap<>());
        	srl = new SailRaceLegBean();
        	srl.setBoat(boatName);
        	srl.setLegNumber(i++);
        	srl.setFromPosition(sr.getRoute().getElements().get(0));
        	srl.setToPosition(sr.getRoute().getElements().get(1));
            srl.setFromTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 13:50:00"));
            srl.setToTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 14:23:54"));
            legs.get(srl.getBoat()).put(srl.getLegNumber(), srl);
            
        	srl = new SailRaceLegBean();
        	srl.setBoat(boatName);
        	srl.setLegNumber(i++);
        	srl.setFromPosition(sr.getRoute().getElements().get(1));
        	srl.setToPosition(sr.getRoute().getElements().get(2));
            srl.setFromTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 14:23:54"));
            srl.setToTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 14:51:48"));
            legs.get(srl.getBoat()).put(srl.getLegNumber(), srl);
            
        	srl = new SailRaceLegBean();
        	srl.setBoat(boatName);
        	srl.setLegNumber(i++);
        	srl.setFromPosition(sr.getRoute().getElements().get(2));
        	srl.setToPosition(sr.getRoute().getElements().get(3));
            srl.setFromTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 14:51:48"));
            srl.setToTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 15:49:25"));
            legs.get(srl.getBoat()).put(srl.getLegNumber(), srl);
            
        	srl = new SailRaceLegBean();
        	srl.setBoat(boatName);
        	srl.setLegNumber(i++);
        	srl.setFromPosition(sr.getRoute().getElements().get(3));
        	srl.setToPosition(sr.getRoute().getElements().get(4));
            srl.setFromTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 15:49:25"));
            srl.setToTime(GlobalsNaming.DATE_TIME_FORMATTER.parse("30/11/2019 15:51:55"));
            legs.get(srl.getBoat()).put(srl.getLegNumber(), srl);

        } catch ( ParseException e ) {
        	LOGGER.error("Error while parsing date");
        	throw new RuntimeException(e);
        }
        sr.getLegs().putAll(legs);

        SailRaceBeanCsvWriter.write("C:\\churreraOut\\201911_REGATA_DE_NAVIDAD_DE_AGUETE_003.csv", sr);
        LOGGER.info("ENDING : command line runner");
    }
}
