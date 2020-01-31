package com.capitanperegrina.boarraceanalyzer.boot.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.capitanperegrina.boatraceanalyzer.bin.Interclubes6_001;

@ComponentScan("com.capitanperegrina")
@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootConsoleApplication.class);
    
    @Autowired
    private Interclubes6_001 etapa1;
 
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
        // this.etapa1.run();
        LOGGER.info("ENDING : command line runner");
    }
}
