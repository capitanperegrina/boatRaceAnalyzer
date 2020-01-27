package com.capitanperegrina.boatraceanalyzer.bin;

import java.util.ArrayList;
import java.util.List;

import com.capitanperegrina.boatraceanalyzer.util.EstelaUtils;

public class EstelaCleanCsv {

	public static void main(String[] args) {
		try {
			List<String> files = new ArrayList<>();
			files.add("C:\\Users\\Usuario\\Downloads\\race5376atxurri.csv");
			files.add("C:\\Users\\Usuario\\Downloads\\race5376mina-minina.csv");
			files.add("C:\\Users\\Usuario\\Downloads\\race5376patela.csv");
			files.add("C:\\Users\\Usuario\\Downloads\\race5376voodoo.csv");
			files.add("C:\\Users\\Usuario\\Downloads\\race5376cassandra.csv");
			files.add("C:\\Users\\Usuario\\Downloads\\race5376mou.csv");
			files.add("C:\\Users\\Usuario\\Downloads\\race5376peregrina.csv");
			EstelaUtils.unificaCsv(files, "C:\\\\Users\\\\Usuario\\\\Downloads\\\\race5376.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
