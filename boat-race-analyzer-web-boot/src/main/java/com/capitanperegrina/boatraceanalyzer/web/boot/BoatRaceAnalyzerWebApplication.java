package com.capitanperegrina.boatraceanalyzer.web.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.capitanperegrina")
@SpringBootApplication
public class BoatRaceAnalyzerWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(BoatRaceAnalyzerWebApplication.class, args);
	}
}
