package com.capitanperegrina.boatraceanalyzer.naming;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class GlobalsNaming {

	private GlobalsNaming() {
		super();
	}

	public final static Integer WHITE=7  ;
	public final static Integer SILVER=1 ;
	public final static Integer GRAY=14  ;
	public final static Integer BLACK=15 ;
	public final static Integer RED=2    ;
	public final static Integer MAROON=9 ;
	public final static Integer YELLOW=6 ;
	public final static Integer OLIVE=13 ;
	public final static Integer LIME=4   ;
	public final static Integer GREEN=11 ;
	public final static Integer AQUA=5   ;
	public final static Integer TEAL=12  ;
	public final static Integer BLUE=1   ;
	public final static Integer NAVY=8   ;
	public final static Integer FUCHSIA=3;
	public final static Integer PURPLE=10;

    public static final String APP_NAME = "Boat Track Analyzer";
    
    public static final Map<Integer,String> COLOR_MAP;
	
	public static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("HH:mm:ss");
	
    public final static String SEMILLA = "1234567890";
    
    public final static String CSV_SEPARATOR = ";";
    public final static String CSV_CR = "\n";
    
    static {
    	COLOR_MAP = new HashMap<>(15);
    	COLOR_MAP.put(WHITE,"#FFFFFF");
    	COLOR_MAP.put(SILVER,"#C0C0C0");
    	COLOR_MAP.put(GRAY,"#808080");
    	COLOR_MAP.put(BLACK,"#000000");
    	COLOR_MAP.put(RED,"#FF0000");
    	COLOR_MAP.put(MAROON,"#800000");
    	COLOR_MAP.put(YELLOW,"#FFFF00");
    	COLOR_MAP.put(OLIVE,"#808000");
    	COLOR_MAP.put(LIME,"#00FF00");
    	COLOR_MAP.put(GREEN,"#008000");
    	COLOR_MAP.put(AQUA,"#00FFFF");
    	COLOR_MAP.put(TEAL,"#008080");
    	COLOR_MAP.put(BLUE,"#0000FF");
    	COLOR_MAP.put(NAVY,"#000080");
    	COLOR_MAP.put(FUCHSIA,"#FF00FF");
    	COLOR_MAP.put(PURPLE,"#800080");
    }
    
    public static final String getColor(Integer index) {
    	return COLOR_MAP.get(index);
    }
}