package com.capitanperegrina.boarraceanalyzer.boot.console;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.capitanperegrina.boatraceanalyzer.exceptions.ServiceErrorException;
import com.capitanperegrina.boatraceanalyzer.service.IEstelaService;
import com.capitanperegrina.boatraceanalyzer.service.MapHtmlGeneratorService;

@ComponentScan("com.capitanperegrina")
@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootConsoleApplication.class);

	@Autowired
	private IEstelaService estelaService;

	@Autowired
	@Qualifier("navionicsHtmlGeneratorService")
	private MapHtmlGeneratorService navionicsHtmlGeneratorService;
	
	@Autowired
	@Qualifier("openStreetMapHtmlGeneratorService")
	private MapHtmlGeneratorService openStreetMapHtmlGeneratorService;	

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
		try {
			// Importar datos de la Etapa 1 - una vez imporados: comentar.
//          this.estelaService.importCvsToDatabase(1, 1, 1, 1, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376mina-minina.csv");
//          this.estelaService.importCvsToDatabase(1, 1, 2, 2, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376cassandra.csv");
//          this.estelaService.importCvsToDatabase(1, 1, 3, 3, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376patela.csv");
//          this.estelaService.importCvsToDatabase(1, 1, 4, 4, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376peregrina.csv");
//          this.estelaService.importCvsToDatabase(1, 1, 5, 5, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376voodoo.csv");
//          this.estelaService.importCvsToDatabase(1, 1, 6, 6, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376atxurri.csv");
//          this.estelaService.importCvsToDatabase(1, 1, 7, 7, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5376mou.csv");

			// Importar datos de la Etapa 2 - una vez imporados: comentar.
//          this.estelaService.importCvsToDatabase(1, 2, 1, 8, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5423mina-minina.csv");
//          this.estelaService.importCvsToDatabase(1, 2, 2, 9, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5423cassandra.csv");
//          this.estelaService.importCvsToDatabase(1, 2, 3, 10, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5423patela.csv");
//          this.estelaService.importCvsToDatabase(1, 2, 4, 11, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5423peregrina.csv");
//          this.estelaService.importCvsToDatabase(1, 2, 5, 12, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5423voodoo.csv");
//          this.estelaService.importCvsToDatabase(1, 2, 6, 13, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5423atxurri.csv");
//          this.estelaService.importCvsToDatabase(1, 2, 7, 14, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5423mou.csv");

			// Importar datos de la Etapa 2 - una vez imporados: comentar.
//            this.estelaService.importCvsToDatabase(1, 3, 2, 15, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5513cassandra.csv");
//            this.estelaService.importCvsToDatabase(1, 3, 3, 16, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5513patela.csv");
//            this.estelaService.importCvsToDatabase(1, 3, 4, 17, "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\race5513peregrina.csv");
			
			// Generar análisis de la 1ª etapa
//			FileUtils.write(new File(
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_tracks.html"),
//					this.mapHtmlGeneratorService.getHtml(1, 1, null), Charset.forName("UTF-8"));

			// Generar análisis de la 2ª etapa
//			FileUtils.write(new File(
//					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_002_tracks.html"),
//					this.mapHtmlGeneratorService.getHtml(1, 2, null), Charset.forName("UTF-8"));

			// Generar análisis de la 3ª etapa
			FileUtils.write(new File(
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_003_tracks.html"),
					this.navionicsHtmlGeneratorService.getHtml(1, 3, null), Charset.forName("UTF-8"));
			
			FileUtils.write(new File(
					"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\OSM_006_003_tracks.html"),
					this.openStreetMapHtmlGeneratorService.getHtml(1, 3, null), Charset.forName("UTF-8"));			

			// Generar análisis individualizado
//          for ( int i=1; i<=7; i++ ) {
//				FileUtils.write(new File(
//						"G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\006_001_" + String.format("%03d", i) + "_tracks.html"),
//						this.mapHtmlGeneratorService.getHtml(1, 1, i), Charset.forName("UTF-8"));				
//			}

		} catch (IOException ioe) {
			throw new ServiceErrorException(ioe);
		}
		LOGGER.info("ENDING : command line runner");
	}
}
