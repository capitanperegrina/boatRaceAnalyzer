package com.capitanperegrina.boatraceanalyzer.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoatPOJO;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.RoutePOJO;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackpointPOJO;
import com.capitanperegrina.boatraceanalyzer.service.MapHtmlGeneratorService;
import com.capitanperegrina.boatraceanalyzer.util.ExtendedFileUtils;
import com.capitanperegrina.boatraceanalyzer.util.IconsNamingService;
import com.capitanperegrina.boatraceanalyzer.util.Nautical;
import com.capitanperegrina.estela.bean.EstelaBoard;
import com.capitanperegrina.estela.bean.EstelaRace;
import com.capitanperegrina.estela.bean.EstelaRaceLeg;
import com.capitanperegrina.estela.bean.EstelaTrack;
import com.capitanperegrina.geo.elements.Line;
import com.capitanperegrina.geo.elements.MapElement;
import com.capitanperegrina.geo.elements.Point;

@Service("mapHtmlGeneratorService")
public class MapHtmlGeneratorServiceImpl implements MapHtmlGeneratorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MapHtmlGeneratorServiceImpl.class);

	protected static final String CR = "\n";
	protected static final SimpleDateFormat TSP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	protected IconsNamingService iconsNamingService;

	protected static String getTitulo(EstelaRace race, Integer idLeg) {
		EstelaRaceLeg leg = race.getLegs().get(idLeg);
		StringBuilder titulo = new StringBuilder();
		if (race.getRace() != null && StringUtils.isNotEmpty(race.getRace().getName())) {
			titulo.append(race.getRace().getName());
			if (leg != null && StringUtils.isNotEmpty(leg.getLeg().getName())) {
				titulo.append(" - ");
				titulo.append(leg.getLeg().getName());
			}
		}
		return titulo.toString();
	}
	
	protected String generateIcons(EstelaRaceLeg leg) {
		StringBuilder icons = new StringBuilder();
		for (RoutePOJO decoracion : leg.getDecorationElements()) {
			String elementPrefix = "decoration_" + decoracion.getIdRoute().toString();
			icons.append("        var " + elementPrefix).append("_icon = 'data:image/png;base64,")
					.append(this.iconsNamingService.getIcon(decoracion.getKind())).append("';").append(CR);
		}

		for (RoutePOJO el : leg.getRouteElements()) {
			String elementPrefix = "decoration_" + el.getIdRoute().toString();
			if (el.getLat2() == null) {
				icons.append("        var " + elementPrefix).append("_icon = 'data:image/png;base64,")
						.append(this.iconsNamingService.getIcon(el.getKind())).append("';").append(CR);
			}
		}
		
		return icons.toString();
	}
	
	protected static String generateMarkers(EstelaRaceLeg leg) {
		// decoracion
		StringBuilder markers = new StringBuilder();
		for (RoutePOJO decoracion : leg.getDecorationElements()) {
			String elementPrefix = "decoration_" + decoracion.getIdRoute().toString();
			markers.append("        var " + elementPrefix).append("_marker = L.marker([").append(decoracion.getLat1())
					.append(", ").append(decoracion.getLon1()).append("], { icon: new L.icon({ html: '")
					.append(decoracion.getName()).append("', iconUrl: ").append(elementPrefix)
					.append("_icon, iconSize: [24, 24], iconAnchor: [12, 12], popupAnchor: [12, 0] }) }).addTo(mymap);")
					.append(CR);
		}
		
		for (RoutePOJO el : leg.getRouteElements()) {
			String elementPrefix = "decoration_" + el.getIdRoute().toString();
			if (el.getLat2() == null) {
				markers.append("        var " + elementPrefix).append("_marker = L.marker([").append(el.getLat1())
						.append(", ").append(el.getLon1()).append("], { icon: new L.icon({ html: '")
						.append(el.getName()).append("', iconUrl: ").append(elementPrefix)
						.append("_icon, iconSize: [24, 24], iconAnchor: [12, 12], popupAnchor: [12, 0] }) }).addTo(mymap);")
						.append(CR);
			} else {
				markers.append("        var " + elementPrefix + "_line = L.polyline([" + CR);
				markers.append("            [").append(el.getLat1()).append(", ").append(el.getLon1()).append("],")
						.append(CR);
				markers.append("            [").append(el.getLat2()).append(", ").append(el.getLon2()).append("],")
						.append(CR);
				markers.append("        ]).addTo(mymap);").append(CR);
				markers.append("        " + elementPrefix + "_line.setStyle({ color: '#000000' });").append(CR);
			}
		}		
		
		return markers.toString();
	}
	
	protected static String generateJsImports(Integer raceId, Integer legId, Integer boatId, EstelaRaceLeg leg, EstelaRace race) {
		StringBuilder jsImports = new StringBuilder();
		for (EstelaTrack track : leg.getTracks().values()) {
			if ( boatId == null || boatId == track.getTrack().getIdBoat() ) {
				String boatName = race.getBoats().get(track.getTrack().getIdBoat()).getName();
				String jsonFilename = "tracks"+raceId+"-"+legId+"-"+track.getTrack().getIdBoat()+".js";
				jsImports.append("    <script src=\"tracks/" + jsonFilename + "\"></script>" + CR);
				generaJson(jsonFilename,boatName,track);
			}
		}
		return jsImports.toString();
	}
	
	protected static String generateScript(Integer raceId, Integer legId, Integer boatId, EstelaRaceLeg leg, EstelaRace race) {
		StringBuilder script = new StringBuilder();
		for (EstelaTrack track : leg.getTracks().values()) {
			if ( boatId == null || boatId == track.getTrack().getIdBoat() ) {
				String boatName = race.getBoats().get(track.getTrack().getIdBoat()).getName();
				script.append("        var polyline_" + normalizeName(boatName) + " = L.geoJson(json_" + normalizeName(boatName) + ", {").append(CR);
	        	script.append("            style: style").append(CR);
	        	script.append("        }).addTo(polylines);").append(CR);
			}
		}
        script.append("        polylines.on('mouseover', function(e) {").append(CR); 
        script.append("            $('#dataTsp').html(e.layer.feature.properties.tsp);").append(CR); 
        script.append("            $('#dataLat').html(e.layer.feature.geometry.coordinates[0][0]);").append(CR); 
        script.append("            $('#dataLon').html(e.layer.feature.geometry.coordinates[0][1]);").append(CR);
        script.append("            $('#dataCog').html(e.layer.feature.properties.cog);").append(CR);
//        script.append("            $('#dataBrg').html(e.layer.feature.properties.brg);").append(CR);
        script.append("            $('#dataSog').html(e.layer.feature.properties.sog);").append(CR);
        script.append("            $('#dataVmg').html(e.layer.feature.properties.vmg);").append(CR);
        script.append("            $('#dataDtd').html(e.layer.feature.properties.dtd);").append(CR);
        script.append("            $('#dataDelta').html(e.layer.feature.properties.delta);").append(CR);
        script.append("        });").append(CR);
        script.append("        polylines.addTo(mymap);").append(CR);
        return script.toString();
	}
	
	/**
	 * Basado en https://gis.stackexchange.com/questions/123078/add-custom-variables-to-leaflet-polyline
     */
	protected static String generaJson(final String filename, final String boatName, final EstelaTrack track) {
		String jsonVariableName = ("json_" + normalizeName(boatName));
		StringBuilder json = new StringBuilder();
		json.append("        var ").append(jsonVariableName).append(" = ").append("{").append(CR);
		json.append("            \"type\": \"FeatureCollection\",").append(CR);
		json.append("            \"features\": [").append(CR);
		TrackpointPOJO tpOld = null;
		int i=0;
		double length = 0d;
		for (TrackpointPOJO tp : track.getPoints()) {
			if (  i++ % 5 == 0  ) { 
				// Sólo 1 de cada 5 puntos para no sobrecargar.
				if (tp.getTsp().after(track.getTrack().getTspIni()) && tp.getTsp().before(track.getTrack().getTspEnd()) && tpOld != null) { 
					// Sólo los puntos entre la salida y la llegada.

					// Hay que averiguar a qué bordo pertenece el punto.
					EstelaBoard board = null;
					for ( EstelaBoard b : track.getBoards().values() ) {
						if ( b.getBoard().getDateIni().before(tp.getTsp()) && b.getBoard().getDateFin().after(tp.getTsp())) {
							board = b;
							break;
						}
					}
					
					if ( board != null ) {
						Point p1 = new Point(tp.getLat().doubleValue(), tp.getLon().doubleValue());
						Point p2 = new Point(tpOld.getLat().doubleValue(), tpOld.getLon().doubleValue());
						
						// MapElement ini = toMapElement(board.getIniRouteElement());
						MapElement fin = toMapElement(board.getFinRouteElement());
						Point location = new Point( tp.getLat().doubleValue(), tp.getLon().doubleValue());
						Point oldLocation = new Point( tpOld.getLat().doubleValue(), tpOld.getLon().doubleValue());

//						Line line = new Line(p2, p1);
//						double brgd = line.getAngle(fin);
//						BigDecimal brg = Double.isNaN(brgd) ? null : new BigDecimal(brgd);

						BigDecimal dtd = new BigDecimal( location.distanceInMeters(fin) );
						BigDecimal dtdOld = new BigDecimal( oldLocation.distanceInMeters(fin) );
						BigDecimal delta = dtdOld.subtract(dtd);
						double vmgd = (delta.doubleValue() / ((tp.getTsp().getTime() - tpOld.getTsp().getTime()) / 1000))
								* 1.94384d;
						BigDecimal vmg = new BigDecimal(vmgd);
						length = length + delta.doubleValue();
						String tsp = tp.getTsp().toString();
						tsp = tsp.substring(tsp.lastIndexOf(" ")).replace(".0", "");
						if ( p1.distanceInMeters(p2) < 25 ) {
							json.append("                {").append(CR);
							json.append("                    \"type\": \"Feature\",").append(CR);
							json.append("                    \"geometry\": {").append(CR);
							json.append("                        \"type\": \"LineString\",").append(CR);
							json.append("                        \"coordinates\": [[" + tpOld.getLon() + ", " + tpOld.getLat() + "], [" + tp.getLon() + ", " + tp.getLat() + "] ]").append(CR);
							json.append("                    },").append(CR);
							json.append("                    \"properties\": {").append(CR);
							json.append("                        \"sog\": " + tp.getSog().toString() + ", ").append(CR);
							json.append("                        \"sogFormat\": \"" + Nautical.formatDistance(tp.getSog()) + "\", ").append(CR);
							json.append("                        \"tsp\": \"" + tsp + "\", ").append(CR);
							json.append("                        \"cog\": \"" + Nautical.formatCourse(tp.getCog()) + "\", ").append(CR);
//							json.append("                        \"brg\": \"" + Nautical.formatCourse(brg) + "\", ").append(CR);
							json.append("                        \"vmg\": \"" + Nautical.formatDistance(vmg) + " n\", ").append(CR);
							json.append("                        \"dtd\": \"" + Nautical.formatDistance(dtd) + " m\", ").append(CR);
							json.append("                        \"delta\": \"" + Nautical.formatDistance(delta) + " m\", ").append(CR);
							json.append("                    }").append(CR);
							json.append("                },").append(CR);					
						}						
					}
				}
				tpOld = tp;			
			}
		}
		json.append(CR);
		json.append("            ],").append(CR);
		json.append("            \"length\": \"" + Nautical.formatDistance(length) + "\"").append(CR);
		json.append("        };").append(CR);
		LOGGER.info("{}: {} mts.", boatName, Nautical.formatDistance(length));
		ExtendedFileUtils.writeFile(json.toString(), "G:\\Mi unidad\\Barco\\Regatas\\202001_VI_REGATA_INTERCLUBES_RIA_DE_PONTEVEDRA\\tracks\\tracks\\"+filename);
		return json.toString();
	}

	protected static String normalizeName(String str) {
		return StringUtils.stripAccents(str).replace(" ", "");
	}
	
	protected static Date parse(String str) {
		try {
			return TSP.parse(str.replace(".0", ""));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	protected static String getInterfaz(Collection<BoatPOJO> boats, boolean multiple) {
		StringBuilder sb = new StringBuilder();
		if (multiple) {
			sb.append(generateButtons(boats));
		}
		sb.append(CR);
		sb.append("    <script>").append(CR);
		sb.append("        $(function() {").append(CR);
		sb.append("            $(window).on('resize', function() {").append(CR);
		sb.append("                resize();").append(CR);
		sb.append("            }).trigger('resize');").append(CR);
		if (multiple) {
			sb.append(CR);
			sb.append(generateListeners(boats));
		}
		sb.append("        });").append(CR);
		sb.append("    </script>").append(CR);
		return sb.toString();
	}

	protected static String generateButtons(Collection<BoatPOJO> boats) {
		StringBuilder buttons = new StringBuilder();
		buttons.append("    <div id=\"buttons\">").append(CR);
		buttons.append("        <div class=\"fieldset\">").append(CR);
		buttons.append("            <fieldset>").append(CR);
		for (BoatPOJO boat : boats) {
			String normalizedName = normalizeName(boat.getName());
			buttons.append("                <div class=\"izda\"><input type=\"checkbox\" name=\"check_").append(normalizedName)
					.append("\" id=\"").append(normalizedName).append("\" checked=\"checked\">").append(boat.getName())
					.append("</input></div>").append(CR);
		}
		buttons.append("            </fieldset>").append(CR);
		buttons.append("        </div>").append(CR);
//		buttons.append("        <div class=\"fieldset\">").append(CR);
//		buttons.append("            <fieldset>").append(CR);
//		buttons.append("                <input type=\"radio\" name=\"type\" value=\"track\" checked=\"checked\"> Recorrido</input>").append(CR);
//		buttons.append("                <input type=\"radio\" name=\"type\" value=\"SOG\"> Velocidad</input>").append(CR);
//		buttons.append("                <input type=\"radio\" name=\"type\" value=\"VMG\"> VMG a destino</input>").append(CR);
//		buttons.append("            </fieldset>").append(CR);
//		buttons.append("        </div>").append(CR);
		buttons.append("    </div>").append(CR);
		return buttons.toString();
	}

	protected static String generateListeners(Collection<BoatPOJO> boats) {
		StringBuilder listeners = new StringBuilder();
		for (BoatPOJO boat : boats) {
			String normalizedName = normalizeName(boat.getName());
			listeners.append("            $('#").append(normalizedName).append("').click(function() {").append(CR);
			listeners.append("                toggle(polyline_").append(normalizedName).append(");").append(CR);
			listeners.append("            });").append(CR);
		}
		return listeners.toString();
	}	

	@Override
	public String getHtml(Integer raceId, Integer legId, Integer boatId) throws IOException {
		LOGGER.error("No implementado");
		throw new RuntimeException("No implementado!");
	}
	
	private static MapElement toMapElement(RoutePOJO r) {
		if ( r.getLat2() == null ) {
			return new Point( r.getLat1().doubleValue(), r.getLon1().doubleValue() );
		} else {
			return new Line( new Point( r.getLat1().doubleValue(), r.getLon1().doubleValue() ),
					new Point( r.getLat2().doubleValue(), r.getLon2().doubleValue() ), r.getName() );
		}
	}
}
