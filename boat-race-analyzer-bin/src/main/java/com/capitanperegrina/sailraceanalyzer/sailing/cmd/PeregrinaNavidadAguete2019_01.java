package com.capitanperegrina.sailraceanalyzer.sailing.cmd;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capitanperegrina.common.Ficheros;
import com.capitanperegrina.sailraceanalyzer.common.Globals;
import com.capitanperegrina.sailraceanalyzer.common.geo.ElementoEnMapa;
import com.capitanperegrina.sailraceanalyzer.common.geo.Line;
import com.capitanperegrina.sailraceanalyzer.common.geo.Locations;
import com.capitanperegrina.sailraceanalyzer.common.geo.Point;
import com.capitanperegrina.sailraceanalyzer.common.geo.TrackLine;
import com.capitanperegrina.sailraceanalyzer.common.gpx.GpxType;
import com.capitanperegrina.sailraceanalyzer.common.gpx.RteType;
import com.capitanperegrina.sailraceanalyzer.common.gpx.TrkType;
import com.capitanperegrina.sailraceanalyzer.common.gpx.TrksegType;
import com.capitanperegrina.sailraceanalyzer.common.gpx.WptType;
import com.capitanperegrina.sailraceanalyzer.common.gpx.parser.GpxParser;
import com.capitanperegrina.sailraceanalyzer.common.util.Nautical;
import com.capitanperegrina.sailraceanalyzer.sailing.model.Bordo;
import com.capitanperegrina.sailraceanalyzer.sailing.model.Recorrido;

public class PeregrinaNavidadAguete2019_01 {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(PeregrinaNavidadAguete2019_01.class);
	
    public static void main(String[] args) {
		try {

			// Leyendo el recorrido - Se hizo limpieza primero en el gpx para eliminar duplicados.
			// TODO - Al margen de entrada y salida, todo lo demás son el recorrido de la regata,
			// waypoint por waypoint. Tal y como está se obvian los duplicados.
			File fileRecorrido = new File(PeregrinaNavidadAguete2019_01.class.getClassLoader().getResource("regatas/2019navidadAguete/01/recorrido.gpx").getFile());
			GpxType gpxRecorrido = GpxParser.parse(fileRecorrido.getAbsolutePath());
			
			Map<String,Point> recorridoMap = new HashMap<>();
			for ( RteType rte : gpxRecorrido.getRte() ) {
				for ( WptType wpt : rte.getRtept() ) {
					LOGGER.info( "{} - {} - {}", wpt.getName(), wpt.getLat().toString(), wpt.getLon().toString() );
					recorridoMap.put(
							wpt.getName(), 
							new Point(
									wpt.getLat().doubleValue(),
									wpt.getLon().doubleValue(),
									wpt.getName(),
									Nautical.getTypeByName(wpt.getName())
							));
				}
			}
			
			// Construimos el recorrido: salida, llegada + balizas por orden
			ElementoEnMapa salida = new Line(
					recorridoMap.get(Nautical.NOMBRE_PIN_SALIDA), 
					recorridoMap.get(Nautical.NOMBRE_COMITE_SALIDA));
			recorridoMap.remove(Nautical.NOMBRE_PIN_SALIDA);
			recorridoMap.remove(Nautical.NOMBRE_COMITE_SALIDA);
			
			ElementoEnMapa llegada = new Line(
					recorridoMap.get(Nautical.NOMBRE_PIN_LLEGADA), 
					recorridoMap.get(Nautical.NOMBRE_COMITE_LLEGADA));
			recorridoMap.remove(Nautical.NOMBRE_PIN_LLEGADA);
			recorridoMap.remove(Nautical.NOMBRE_COMITE_LLEGADA);
			
			List<ElementoEnMapa> balizas = new ArrayList<ElementoEnMapa>();
			balizas.addAll(recorridoMap.values());
			
			
			// Parseamos el fichero con la traza.
			File file = new File(PeregrinaNavidadAguete2019_01.class.getClassLoader().getResource("regatas/2019navidadAguete/01/race5246peregrina.gpx").getFile());
			String fileIn = file.getAbsolutePath();
			GpxType gpx = GpxParser.parse(fileIn);

			List<WptType> trackPoints = new ArrayList<>();
			List<TrkType> tracks = gpx.getTrk();
			for ( TrkType track : tracks ) {
				List<TrksegType> trackSegments = track.getTrkseg();
				for ( TrksegType trackSegment : trackSegments ) {
					trackPoints.addAll(trackSegment.getTrkpt());	
				}
			}

			int intervalo = 5;
			List<TrackLine> trackLines = new ArrayList<TrackLine>();

			if (trackPoints.size() > intervalo) {
				for (int i = 0; i < trackPoints.size() - intervalo; i = i + intervalo) {
					TrackLine tl = new TrackLine(trackPoints.get(i), trackPoints.get(i + intervalo));
					trackLines.add(tl);
				}
			}

			
			// Definimos los bordos
			// TODO - Automatizar esto.
			String fecha = "16/11/2019";

			Bordo bordo1 = new Bordo();
			bordo1.setDescripcion( "Salida -> Morrazán" );
			
			bordo1.settIni( Globals.DATE_TIME_FORMATTER.parse(fecha + " 14:45:45") );
			bordo1.settFin( Globals.DATE_TIME_FORMATTER.parse(fecha + " 15:15:37") );
			bordo1.cargaDerrota( trackLines );
			bordo1.seteIni( salida );
			bordo1.seteFin( Locations.MORRAZAN );
			System.out.println( bordo1.generaString() );

			Bordo bordo2 = new Bordo();
			bordo2.setDescripcion( "Morrazán -> Llegada" );
			bordo2.settIni( Globals.DATE_TIME_FORMATTER.parse(fecha + " 15:15:37") );
			bordo2.settFin( Globals.DATE_TIME_FORMATTER.parse(fecha + " 15:39:50") );
			bordo2.cargaDerrota( trackLines );
			bordo2.seteIni( Locations.MORRAZAN );
			bordo2.seteFin( llegada );
			System.out.println( bordo2.generaString() );
			
			Recorrido recorrido = new Recorrido();
			recorrido.setSalida(salida);
			recorrido.setBalizas(balizas);
			recorrido.setLlegada(llegada);
			recorrido.getBordos().add( bordo1 );
			recorrido.getBordos().add( bordo2 );

			// Generación del HTML de salida.
			// FIXME - Puto google maps, que ahora es de pago.
			Ficheros.string2File(fileIn.replace(".gpx", ".txt"),trackLines.toString());
			StringBuilder unl = new StringBuilder();
			for (int i = 0; i < trackLines.size(); i++) {
				unl.append(trackLines.get(i).toUnl() + "\n");
			}
			Ficheros.string2File(fileIn.replace(".gpx", ".unl"), unl.toString().replace(".", ","));
			Ficheros.string2File("C:\\churreraOut\\out.html", recorrido.generaHtml());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}