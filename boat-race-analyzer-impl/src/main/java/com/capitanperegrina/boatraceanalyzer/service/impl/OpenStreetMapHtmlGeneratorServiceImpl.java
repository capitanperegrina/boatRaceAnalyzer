package com.capitanperegrina.boatraceanalyzer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitanperegrina.boatraceanalyzer.service.IEstelaService;
import com.capitanperegrina.boatraceanalyzer.service.MapHtmlGeneratorService;
import com.capitanperegrina.estela.bean.EstelaRace;
import com.capitanperegrina.estela.bean.EstelaRaceLeg;
import com.capitanperegrina.geo.elements.Point;

@Service("openStreetMapHtmlGeneratorService")
public class OpenStreetMapHtmlGeneratorServiceImpl extends MapHtmlGeneratorServiceImpl implements MapHtmlGeneratorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpenStreetMapHtmlGeneratorServiceImpl.class);

	private static final String ESQUELETO_WEB = "<!DOCTYPE html>" 
			+ CR + "<html>" + CR 
			+ CR + "<head>" + CR
			+ "    <title>%TITLE%</title>" + CR
			+ CR
			+ "    <meta charset=\"utf-8\" />" + CR
			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" + CR
			+ CR
			+ "    <link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"img/favicon.ico\" />" + CR
			+ "    <link rel=\"stylesheet\" href=\"css/tracks.css\"/>" + CR 
			+ CR
			+ "    <link rel=\"stylesheet\" href=\"css/leaflet.css\" />" + CR
			+ "    <script src=\"js/leaflet.js\"></script>" + CR
			+ "    <script src=\"js/Leaflet.MultiOptionsPolyline.js\"></script>" + CR
			+ "    <script src=\"js/jquery.min.js\"></script>" + CR 
			+ "    <script src=\"js/tracks.js\"></script>" + CR
			+ "%JS_IMPORTS%" + CR
			+ "</head>" + CR 
			+ CR 
			+ "<body>"
			+ CR 
			+ "%INTERFACE%" + CR
			+ "    <div id=\"mapid\" style=\"width: 1024px; height: 768px; margin: 0\"></div>" + CR 
			+ "    <script>" + CR 
			+ "        var polylines = new L.FeatureGroup();" + CR
			+ "        var mymap = L.map('mapid').setView([%LATITUDE%,%LONGITUDE%], %ZOOM%)" + CR
			+ "        L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {"
			+ CR 
			+ "            maxZoom: 18," + CR
			+ "            attribution: 'Map data &copy; <a href=\"https://www.openstreetmap.org/\">OpenStreetMap</a> contributors, ' +" + CR 
			+ "                '<a href=\"https://creativecommons.org/licenses/by-sa/2.0/\">CC-BY-SA</a>, ' +" + CR
			+ "                'Imagery © <a href=\"https://www.mapbox.com/\">Mapbox</a>'," + CR
			+ "            id: 'mapbox/streets-v11'" + CR 
			+ "        }).addTo(mymap);" + CR
			+ "        L.tileLayer('https://tiles.openseamap.org/seamark/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {"
			+ CR 
			+ "            maxZoom: 18," + CR
			+ "            attribution: 'Map data: &copy; <a href=\"http://www.openseamap.org\">OpenSeaMap</a> contributors'" + CR 
			+ "        }).addTo(mymap);" + CR 
			+ CR 
			+ "%ICONS%" + CR
			+ "%MARKERS%" + CR
			+ "%SCRIPT%" + CR 
			+ "    </script>" + CR
			+ "    <div id=\"datosDerrota\">" + CR 
			+ "        <div class=\"group\">" + CR 
			+ "            <div class=\"title\">Time</div>" + CR 
			+ "            <div id=\"dataTsp\">-</div>" + CR 
			+ "        </div>" + CR 
			+ "            <div class=\"group\">" + CR 
			+ "                <div class=\"title\">Lat</div>" + CR 
			+ "                <div id=\"dataLon\">-</div>" + CR 
			+ "            </div>" + CR 
			+ "            <div class=\"group\">" + CR 
			+ "                <div class=\"title\">Lon</div>" + CR 
			+ "                <div id=\"dataLat\">-</div>" + CR 
			+ "            </div>" + CR 
			+ "            <div class=\"group\">" + CR 
			+ "                <div class=\"title\">COG</div>" + CR 
			+ "                <div id=\"dataCog\">-</div>" + CR 
			+ "            </div>" + CR 
//			+ "            <div class=\"group\">" + CR 
//			+ "                <div class=\"title\">BRG</div>" + CR 
//			+ "                <div id=\"dataBrg\">-</div>" + CR 
//			+ "            </div>" + CR 
			+ "            <div class=\"group\">" + CR 
			+ "                <div class=\"title\">SOG</div>" + CR 
			+ "                <div id=\"dataSog\">-</div>" + CR 
			+ "            </div>" + CR 
			+ "            <div class=\"group\">" + CR 
			+ "                <div class=\"title\">VMG</div>" + CR 
			+ "                <div id=\"dataVmg\">-</div>" + CR 
			+ "            </div>" + CR 
			+ "            <div class=\"group\">" + CR 
			+ "                <div class=\"title\">DTD</div>" + CR 
			+ "                <div id=\"dataDtd\">-</div>" + CR 
			+ "            </div>" + CR 
			+ "            <div class=\"group\">" + CR 
			+ "                <div class=\"title\">Δ</div>" + CR 
			+ "                <div id=\"dataDelta\">-</div>" + CR 
			+ "            </div>" + CR 
			+ "        <div class=\"clearfix\"></div>" + CR 
			+ "    </div>" + CR
			+ "</body>" + CR 
			+ CR
			+ "</html>";

	@Autowired
	private IEstelaService estelaService;

	@Override
	public final String getHtml(Integer raceId, Integer legId, Integer boatId) {

		Point center = this.estelaService.findLegCenter(legId);

		EstelaRace race = this.estelaService.readEstelaRace(raceId);
		EstelaRaceLeg leg = race.getLegs().get(legId);
		String titulo = getTitulo(race, legId);
		String icons = generateIcons(leg);
		String markers = generateMarkers(leg);
		String jsImports = generateJsImports(raceId, legId, boatId, leg, race);
		String script = generateScript(raceId, legId, boatId, leg, race);

		String ret = ESQUELETO_WEB.replace("%TITLE%", titulo).replace("%LATITUDE%", String.valueOf(center.getLatitude()))
				.replace("%LONGITUDE%", String.valueOf(center.getLongitude())).replace("%ZOOM%", "13")
				.replace("%SCRIPT%", script)
				.replace("%ICONS%", icons)
				.replace("%MARKERS%", markers)
				.replace("%JS_IMPORTS%", jsImports)
				.replace("%INTERFACE%", getInterfaz(race.getBoats().values(), boatId == null));
		return ret;
	}
}
