package com.capitanperegrina.boatraceanalyzer.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import com.capitanperegrina.boatraceanalyzer.naming.GlobalsNaming;
import com.capitanperegrina.estela.EstelaCSV;

public class EstelaUtils {

	private static final SimpleDateFormat SDF_IN = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat SDF_OUT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public static void unificaCsv(List<String> files, String fileOut) {
		try {

			int i = 0;
			SortedMap<Date, List<EstelaCSV>> map = new TreeMap<>();
			for (String fileName : files) {
				List<String[]> rows = readFromCsv(fileName);
				for (String[] row : rows) {
					if (!row[0].equals("time")) {
						EstelaCSV estela = new EstelaCSV(SDF_OUT.parse(row[0]), new BigDecimal(row[1]),
								new BigDecimal(row[2]), new BigDecimal(row[3]), new BigDecimal(row[4]));
						if (map.get(estela.getTime()) == null) {
							map.put(estela.getTime(), new ArrayList<>());
						}
						map.get(estela.getTime()).add(estela);
					}
				}
			}

			SortedMap<Date, List<EstelaCSV>> ret = new TreeMap<>();
			for (Date time : map.keySet()) {
				if (map.get(time).size() == 7) {
					ret.put(time, map.get(time));
				}
			}

			File fout = new File(fileOut);
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			for (Entry<Date, List<EstelaCSV>> entry : ret.entrySet()) {
				StringBuilder line = new StringBuilder();
				line.append(SDF_OUT.format(entry.getKey())).append(GlobalsNaming.CSV_SEPARATOR);
				for (EstelaCSV estela : entry.getValue()) {
					line.append(estela.getLat()).append(GlobalsNaming.CSV_SEPARATOR);
					line.append(estela.getLon()).append(GlobalsNaming.CSV_SEPARATOR);
					line.append(estela.getSog()).append(GlobalsNaming.CSV_SEPARATOR);
					line.append(estela.getCog()).append(GlobalsNaming.CSV_SEPARATOR);
				}
				bw.write(line.toString());
				bw.newLine();
			}
			bw.close();

			System.out.println(ret.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private static List<String[]> readFromCsv(String fileName) {
		List<String[]> rows = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
			String line = br.readLine();
			while (line != null) {
				String[] columns = line.split(GlobalsNaming.CSV_SEPARATOR);
				rows.add(columns);
				line = br.readLine();
			}

			for (String[] columns : rows) {
				if (!columns[0].equals("time")) {
					columns[0] = columns[0].replace("+00:00", "").replace("T", " ");
					Date date = SDF_IN.parse(columns[0]);
					columns[0] = SDF_OUT.format(date);
				}
			}
			return rows;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new RuntimeException(ioe);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
