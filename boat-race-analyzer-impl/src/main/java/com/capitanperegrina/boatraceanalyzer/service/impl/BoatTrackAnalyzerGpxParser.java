package com.capitanperegrina.boatraceanalyzer.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.capitanperegrina.boatraceanalyzer.beans.TrackLineBean;
import com.capitanperegrina.boatraceanalyzer.elements.SortedRoute;
import com.capitanperegrina.boatraceanalyzer.elements.TrackLineSegment;
import com.capitanperegrina.boatraceanalyzer.elements.impl.SortedRouteImpl;
import com.capitanperegrina.boatraceanalyzer.elements.impl.TrackLineSegmentImpl;
import com.capitanperegrina.boatraceanalyzer.service.IBoatTrackAnalyzerGpxParser;
import com.capitanperegrina.boatraceanalyzer.util.GpxUtils;
import com.capitanperegrina.boatraceanalyzer.util.Nautical;
import com.capitanperegrina.geo.elements.Line;
import com.capitanperegrina.geo.elements.Point;
import com.capitanperegrina.geo.elements.impl.LineImpl;
import com.capitanperegrina.geo.elements.impl.PointImpl;
import com.capitanperegrina.gpx.elements.*;

@Service
public class BoatTrackAnalyzerGpxParser implements IBoatTrackAnalyzerGpxParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(BoatTrackAnalyzerGpxParser.class);
	
	@Override
	public SortedRoute sortedRouteParse(String filename) {
		try {
			File fileRecorrido = new File(filename);
			GpxType gpxRecorrido = GpxUtils.parse(fileRecorrido.getAbsolutePath());
			
			SortedRoute ret = new SortedRouteImpl();
			
			if (gpxRecorrido.getMetadata()!=null) {
				ret.setName(StringUtils.trimToEmpty(gpxRecorrido.getMetadata().getDesc()));
			}
	
			Line salida = new LineImpl( new PointImpl(), new PointImpl() );
			Line llegada = new LineImpl( new PointImpl(), new PointImpl() );
			for ( RteType rte : gpxRecorrido.getRte() ) {
				for ( WptType wpt : rte.getRtept() ) {
					if (Nautical.NOMBRE_CR_SALIDA.equals(StringUtils.trimToEmpty(wpt.getName()))) {
						salida.getPoint1().setLatitude(wpt.getLat().doubleValue());
						salida.getPoint1().setLongitude(wpt.getLon().doubleValue());
						salida.getPoint1().setName(wpt.getName());
					} else if (Nautical.NOMBRE_PIN_SALIDA.equals(StringUtils.trimToEmpty(wpt.getName()))) {
						salida.getPoint2().setLatitude(wpt.getLat().doubleValue());
						salida.getPoint2().setLongitude(wpt.getLon().doubleValue());
						salida.getPoint2().setName(wpt.getName());
					} else if (Nautical.NOMBRE_PIN_LLEGADA.equals(StringUtils.trimToEmpty(wpt.getName()))) {
						llegada.getPoint1().setLatitude(wpt.getLat().doubleValue());
						llegada.getPoint1().setLongitude(wpt.getLon().doubleValue());
						llegada.getPoint1().setName(wpt.getName());
					} else if (Nautical.NOMBRE_CR_LLEGADA.equals(StringUtils.trimToEmpty(wpt.getName()))) {
						llegada.getPoint2().setLatitude(wpt.getLat().doubleValue());
						llegada.getPoint2().setLongitude(wpt.getLon().doubleValue());
						llegada.getPoint2().setName(wpt.getName());
					} else {
						Point elemento = new PointImpl();
						elemento.setLatitude(wpt.getLat().doubleValue());
						elemento.setLongitude(wpt.getLon().doubleValue());
						elemento.setName(wpt.getName());
						ret.getElements().add(elemento);
					}
				}
			}
			ret.getElements().add(0,salida);
			ret.getElements().add(llegada);
			
			return ret;
				
		} catch ( Exception e ) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}	
	}

	@Override
	public TrackLineBean trackLineListParse(String filename) {
		try {
			// Parseamos el fichero con la traza.
			File file = new File(filename);
			String fileIn = file.getAbsolutePath();
			GpxType gpx = GpxUtils.parse(fileIn);

			List<WptType> trackPoints = new ArrayList<>();
			List<TrkType> tracks = gpx.getTrk();
			for ( TrkType track : tracks ) {
				List<TrksegType> trackSegments = track.getTrkseg();
				for ( TrksegType trackSegment : trackSegments ) {
					trackPoints.addAll(trackSegment.getTrkpt());	
				}
			}

			int intervalo = 1;
			TrackLineBean ret = new TrackLineBean();
			if (trackPoints.size() > intervalo) {
				for (int i = 0; i < trackPoints.size() - intervalo; i = i + intervalo) {
					TrackLineSegment tls = new TrackLineSegmentImpl(trackPoints.get(i), trackPoints.get(i + intervalo));
					ret.getTrackLineSegments().add(tls);
				}
			}
			return ret;
		} catch ( Exception e ) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}	
}
