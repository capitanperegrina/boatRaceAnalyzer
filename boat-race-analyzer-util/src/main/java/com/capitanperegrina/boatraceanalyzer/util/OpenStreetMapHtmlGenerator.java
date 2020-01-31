package com.capitanperegrina.boatraceanalyzer.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jenetics.jpx.GPX;

public class OpenStreetMapHtmlGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenStreetMapHtmlGenerator.class);
    
    private OpenStreetMapHtmlGenerator() {
        
    }
    
    public static final String getHtml( GPX route, List<GPX> gpxTracks ) {
    	StringBuilder s = new StringBuilder();

    	StringBuilder name = new StringBuilder();
    	if ( route.getMetadata().isPresent() && route.getMetadata().get().getDescription().isPresent() ) {
    		name.append(route.getMetadata().get().getDescription().get());
    		name.append(" - ");
    	}
    	name.append(route.getCreator());

    	s.append("<!DOCTYPE html>\r\n"); 
    	s.append("<html>\r\n"); 
    	s.append("\r\n"); 
    	s.append("<head>\r\n"); 
    	s.append("    <title>").append(name).append("</title>\r\n"); 
    	s.append("\r\n"); 
    	s.append("    <meta charset=\"utf-8\" />\r\n"); 
    	s.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"); 
    	s.append("\r\n"); 
    	s.append("    <link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"docs/images/favicon.ico\" />\r\n"); 
    	s.append("\r\n"); 
    	s.append("    <link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet@1.6.0/dist/leaflet.css\" integrity=\"sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ==\" crossorigin=\"\" />\r\n"); 
    	s.append("    <script src=\"https://unpkg.com/leaflet@1.6.0/dist/leaflet.js\" integrity=\"sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew==\" crossorigin=\"\"></script>\r\n"); 
    	s.append("</head>\r\n"); 
    	s.append("\r\n"); 
    	s.append("<body>\r\n"); 
    	s.append("    <div id=\"mapid\" style=\"width: 1024px; height: 768px; margin: 0\"></div>\r\n"); 
    	s.append("    <script>\r\n"); 
    	s.append("        var mymap = L.map('mapid').setView([42.433311, -8.859094], 13);\r\n"); 
    	s.append("        L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {\r\n"); 
    	s.append("            maxZoom: 18,\r\n"); 
    	s.append("            attribution: 'Map data &copy; <a href=\"https://www.openstreetmap.org/\">OpenStreetMap</a> contributors, ' +\r\n"); 
    	s.append("                '<a href=\"https://creativecommons.org/licenses/by-sa/2.0/\">CC-BY-SA</a>, ' +\r\n"); 
    	s.append("                'Imagery © <a href=\"https://www.mapbox.com/\">Mapbox</a>',\r\n"); 
    	s.append("            id: 'mapbox/streets-v11'\r\n"); 
    	s.append("        }).addTo(mymap);\r\n"); 
    	s.append("\r\n"); 
    	s.append("        var polyline = L.polyline([\r\n"); 
    	s.append("            [42.433311, -8.859094],\r\n"); 
    	s.append("            [51.509, -0.08],\r\n"); 
    	s.append("            [51.503, -0.06],\r\n"); 
    	s.append("            [51.51, -0.047]\r\n"); 
    	s.append("        ]).addTo(mymap);\r\n"); 
    	s.append("\r\n"); 
    	s.append("        polyline.setStyle({\r\n"); 
    	s.append("            color: 'black'\r\n"); 
    	s.append("        });\r\n"); 
    	s.append("    </script>\r\n"); 
    	s.append("</body>\r\n"); 
    	s.append("\r\n"); 
    	s.append("</html>");
    	
    	return s.toString();
    }
}
