package com.capitanperegrina.boatraceanalyzer.util;

import com.capitanperegrina.boatraceanalyzer.exceptions.ServiceErrorException;
import com.capitanperegrina.boatraceanalyzer.naming.GlobalsNaming;
import com.capitanperegrina.estela.bean.EstelaCSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class EstelaUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstelaUtils.class);

    private static final SimpleDateFormat SDF_IN = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat SDF_OUT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private EstelaUtils() {
        // empty and private as it's an utils class.
    }

    public static List<EstelaCSV> readEstelaCsvFile(final String fileName) {

        final List<EstelaCSV> ret = new ArrayList<>();
        final Path pathToFile = Paths.get(fileName);
        try (final BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            while (line != null) {
                final String[] columns = line.split(GlobalsNaming.CSV_SEPARATOR);
                if (!columns[EstelaCSV.TIME].equals("time")) {

                    final Calendar c = Calendar.getInstance();
                    c.setTime(SDF_IN.parse(columns[EstelaCSV.TIME].replace("+00:00", "").replace("T", " ")));
                    c.add(Calendar.HOUR, 1);
                    ret.add(new EstelaCSV(c.getTime(),
                            new BigDecimal(columns[EstelaCSV.LATITUDE]), new BigDecimal(columns[EstelaCSV.LONGITUDE]),
                            new BigDecimal(columns[EstelaCSV.SPEED_OVER_GROUND]),
                            new BigDecimal(columns[EstelaCSV.COURSE_OVER_GROUND])));
                }
                line = br.readLine();
            }
            return ret;
        } catch (final IOException ioe) {
            LOGGER.error(ioe.getMessage(), ioe);
            throw new ServiceErrorException(fileName, ioe);
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceErrorException(fileName, e);
        }
    }

    public static XMLGregorianCalendar getXmlGregorianCalendarFromDate(final Date date)
            throws DatatypeConfigurationException {
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
    }

    public static void unificaCsv(final List<String> files, final String fileOut) {
        try {
            final SortedMap<Date, List<EstelaCSV>> map = new TreeMap<>();
            for (final String fileName : files) {
                final List<String[]> rows = readFromCsv(fileName);
                for (final String[] row : rows) {
                    if (!row[0].equals("time")) {
                        final EstelaCSV estela = new EstelaCSV(SDF_OUT.parse(row[0]), new BigDecimal(row[1]),
                                new BigDecimal(row[2]), new BigDecimal(row[3]), new BigDecimal(row[4]));
                        if (map.get(estela.getTime()) == null) {
                            map.put(estela.getTime(), new ArrayList<>());
                        }
                        map.get(estela.getTime()).add(estela);
                    }
                }
            }

            final SortedMap<Date, List<EstelaCSV>> ret = new TreeMap<>();
            for (final Date time : map.keySet()) {
                if (map.get(time).size() == 7) {
                    ret.put(time, map.get(time));
                }
            }

            final File fout = new File(fileOut);
            final FileOutputStream fos = new FileOutputStream(fout);
            final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (final Entry<Date, List<EstelaCSV>> entry : ret.entrySet()) {
                final StringBuilder line = new StringBuilder();
                line.append(SDF_OUT.format(entry.getKey())).append(GlobalsNaming.CSV_SEPARATOR);
                for (final EstelaCSV estela : entry.getValue()) {
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
        } catch (final Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static List<String[]> readFromCsv(final String fileName) {
        final List<String[]> rows = new ArrayList<>();
        final Path pathToFile = Paths.get(fileName);
        try (final BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            while (line != null) {
                final String[] columns = line.split(GlobalsNaming.CSV_SEPARATOR);
                rows.add(columns);
                line = br.readLine();
            }

            for (final String[] columns : rows) {
                if (!columns[0].equals("time")) {
                    columns[0] = columns[0].replace("+00:00", "").replace("T", " ");
                    final Date date = SDF_IN.parse(columns[0]);
                    columns[0] = SDF_OUT.format(date);
                }
            }
            return rows;
        } catch (final IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException(ioe);
        } catch (final Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
