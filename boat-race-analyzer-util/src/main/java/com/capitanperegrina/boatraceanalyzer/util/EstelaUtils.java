package com.capitanperegrina.boatraceanalyzer.util;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.capitanperegrina.boatraceanalyzer.naming.GlobalsNaming;
import com.capitanperegrina.estela.EstelaCSV;
import com.capitanperegrina.gpx.elements.GpxType;
import com.capitanperegrina.gpx.elements.TrkType;
import com.capitanperegrina.gpx.elements.TrksegType;
import com.capitanperegrina.gpx.elements.WptType;

public class EstelaUtils {

    private static final SimpleDateFormat SDF_IN = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat SDF_OUT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static void estelaCsv2Gpx(String boatName, String fileNameIn, String fileNameOut) {

        GpxType gpx = new GpxType();
        gpx.setCreator(GlobalsNaming.APP_NAME);
        Path pathToFile = Paths.get(fileNameIn);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            TrksegType trackSegment = new TrksegType();
            while (line != null) {
                String[] columns = line.split(GlobalsNaming.CSV_SEPARATOR);
                if (!columns[EstelaCSV.TIME].equals("time")) {
                    WptType waypoint = new WptType();
                    String tmpTime = columns[EstelaCSV.TIME].replace("+00:00", "").replace("T", " ");
                    waypoint.setTime(getXmlGregorianCalendarFromDate(SDF_IN.parse(tmpTime)));
                    waypoint.setLat(new BigDecimal(columns[EstelaCSV.LATITUDE]));
                    waypoint.setLon(new BigDecimal(columns[EstelaCSV.LONGITUDE]));
                    trackSegment.getTrkpt().add(waypoint);
                }
                line = br.readLine();
            }
            TrkType track = new TrkType();
            track.setName(boatName);
            track.getTrkseg().add(trackSegment);
            gpx.getTrk().add(track);

            GpxUtils.write(gpx, fileNameOut);

        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException(ioe);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static XMLGregorianCalendar getXmlGregorianCalendarFromDate(final Date date)
            throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
    }

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

    public static void main(String[] args) {
        try {
            estelaCsv2Gpx("Peregrina", "C:\\Users\\cng\\Downloads\\race5376peregrina.csv",
                    "C:\\Users\\cng\\Downloads\\race5376peregrina.gpx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
