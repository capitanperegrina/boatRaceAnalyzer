package com.capitanperegrina.boatraceanalyzer.writers;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capitanperegrina.boatraceanalyzer.beans.BoatPositionBean;
import com.capitanperegrina.boatraceanalyzer.beans.SailRaceBean;
import com.capitanperegrina.boatraceanalyzer.beans.TrackLineBean;
import com.capitanperegrina.boatraceanalyzer.elements.TrackLineSegment;
import com.capitanperegrina.boatraceanalyzer.naming.GlobalsNaming;
import com.capitanperegrina.boatraceanalyzer.util.Nautical;

public class SailRaceBeanCsvWriter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SailRaceBeanCsvWriter.class);
	
	private static final String quote(String in) {
		return "\"" + in + "\"";
	}
	
	public static final void write(String filename, SailRaceBean srb) {
		List<String> boats = new ArrayList<>(srb.getTracks().entrySet().size());
		Map<Date,BoatPositionBean[]> boatPositions = new HashMap<>(srb.getTracks().entrySet().size());
		int i=0;
		for ( Entry<String, TrackLineBean> entry : srb.getTracks().entrySet() ) {
			boats.add(entry.getKey());
			for ( TrackLineSegment ls : entry.getValue().getTrackLineSegments() ) {
				if ( boatPositions.get(ls.getTimeIni()) == null ) {
					boatPositions.put(ls.getTimeIni(), new BoatPositionBean[srb.getTracks().entrySet().size()]);
				}
				BoatPositionBean bp = new BoatPositionBean();
				bp.setCog(new BigDecimal(ls.getCog()));
				bp.setDtd(new BigDecimal(Nautical.calculateDtd(srb.getLegs().get(entry.getKey()), ls.getPoint1(), ls.getTimeIni())));
				bp.setPosition(ls.getPoint1());
				bp.setSog(new BigDecimal(ls.getSog()));
				bp.setTiempo(ls.getTime());
				bp.setVmg(new BigDecimal(Nautical.calculateVmg(srb.getLegs().get(entry.getKey()), ls)));
				boatPositions.get(ls.getTimeIni())[i] = bp;
			}
			i++;
		}
		
		StringBuilder sb = new StringBuilder();
		for ( String boat : boats ) {
			sb.append(GlobalsNaming.CSV_SEPARATOR);
			sb.append(boat);
			sb.append(GlobalsNaming.CSV_SEPARATOR);
			sb.append(GlobalsNaming.CSV_SEPARATOR);
			sb.append(GlobalsNaming.CSV_SEPARATOR);
			sb.append(GlobalsNaming.CSV_SEPARATOR);
			sb.append(GlobalsNaming.CSV_SEPARATOR);
		}
		sb.append(GlobalsNaming.CSV_CR);
		sb.append("TIME");
		for ( String boat : boats ) {
			sb.append(GlobalsNaming.CSV_SEPARATOR);
			sb.append("LAT");
			sb.append(GlobalsNaming.CSV_SEPARATOR);
			sb.append("LON");
			sb.append(GlobalsNaming.CSV_SEPARATOR);
			sb.append("COG");
			sb.append(GlobalsNaming.CSV_SEPARATOR);
			sb.append("DTD");
			sb.append(GlobalsNaming.CSV_SEPARATOR);
			sb.append("SOG");
			sb.append(GlobalsNaming.CSV_SEPARATOR);
			sb.append("VMG");
		}
		sb.append(GlobalsNaming.CSV_CR);
		
		for (Entry<Date,BoatPositionBean[]> entry : boatPositions.entrySet() ) {
			sb.append(quote(GlobalsNaming.DATE_TIME_FORMATTER.format(entry.getKey())));
			for ( int j = 0 ; j < i ; j++ ) {
				if ( entry.getValue()[j] != null ) {
					sb.append(GlobalsNaming.CSV_SEPARATOR);
					sb.append(quote(StringUtils.trimToEmpty(Nautical.formatCordinate(entry.getValue()[j].getPosition().getLatitude()))));
					sb.append(GlobalsNaming.CSV_SEPARATOR);
					sb.append(quote(StringUtils.trimToEmpty(Nautical.formatCordinate(entry.getValue()[j].getPosition().getLongitude()))));
					sb.append(GlobalsNaming.CSV_SEPARATOR);
					sb.append(quote(StringUtils.trimToEmpty(Nautical.formatDistance(entry.getValue()[j].getCog()))));
					sb.append(GlobalsNaming.CSV_SEPARATOR);
					sb.append(quote(StringUtils.trimToEmpty(Nautical.formatDistance(entry.getValue()[j].getDtd()))));
					sb.append(GlobalsNaming.CSV_SEPARATOR);
					sb.append(quote(StringUtils.trimToEmpty(Nautical.formatDistance(entry.getValue()[j].getSog()))));
					sb.append(GlobalsNaming.CSV_SEPARATOR);
					sb.append(quote(StringUtils.trimToEmpty(Nautical.formatDistance(entry.getValue()[j].getVmg()))));					
				}
			}
			sb.append(GlobalsNaming.CSV_CR);				
		}

		try {
			File file = new File(filename);
			FileUtils.writeStringToFile(file, sb.toString(),Charset.defaultCharset());			
		} catch ( IOException e ) {
			LOGGER.error("Error while writing file {} ({})", filename, e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
}
