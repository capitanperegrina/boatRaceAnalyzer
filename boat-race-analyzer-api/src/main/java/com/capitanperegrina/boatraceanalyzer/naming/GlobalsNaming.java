package com.capitanperegrina.boatraceanalyzer.naming;

import java.text.SimpleDateFormat;

public class GlobalsNaming {

	private GlobalsNaming() {
		super();
	}
	
	public static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("HH:mm:ss");
	
    public final static String SEMILLA = "1234567890";
    
    public final static String CSV_SEPARATOR = ";";
    public final static String CSV_CR = "\n";
}