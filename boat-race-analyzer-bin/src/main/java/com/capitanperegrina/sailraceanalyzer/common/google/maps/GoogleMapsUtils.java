package com.capitanperegrina.sailraceanalyzer.common.google.maps;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capitanperegrina.common.GeneradorClaves;
import com.capitanperegrina.sailraceanalyzer.common.Globals;
import com.capitanperegrina.sailraceanalyzer.common.geo.ElementoEnMapa;
import com.capitanperegrina.sailraceanalyzer.common.geo.Line;
import com.capitanperegrina.sailraceanalyzer.common.geo.Point;
import com.capitanperegrina.sailraceanalyzer.common.util.Nautical;
import com.capitanperegrina.sailraceanalyzer.sailing.model.Bordo;

public class GoogleMapsUtils {

    public static final double GLOBE_WIDTH = 256;

    public static final Map<Integer,String> colores = new HashMap<Integer,String>(36);

    static {
		colores.put(  0, "#FFFF00" );
		colores.put( 35, "#0000FF" );
		colores.put(  1, "#CCFF33" );
		colores.put( 34, "#3300CC" );
		colores.put(  2, "#99FF66" );
		colores.put( 33, "#660099" );
		colores.put(  3, "#66FF99" );
		colores.put( 32, "#990066" );
		colores.put(  4, "#33FFCC" );
		colores.put( 31, "#CC0033" );
		colores.put(  5, "#00FFFF" );
		colores.put( 30, "#FF0000" );
		colores.put(  6, "#FFCC00" );
		colores.put( 29, "#0033FF" );
		colores.put(  7, "#CCCC33" );
		colores.put( 28, "#3333CC" );
		colores.put(  8, "#99CC66" );
		colores.put( 27, "#663399" );
		colores.put(  9, "#66CC99" );
		colores.put( 26, "#993366" );
		colores.put( 10, "#33CCCC" );
		colores.put( 25, "#CC3333" );
		colores.put( 11, "#00CCFF" );
		colores.put( 24, "#FF3300" );
		colores.put( 12, "#FF9900" );
		colores.put( 23, "#0066FF" );
		colores.put( 13, "#CC9933" );
		colores.put( 22, "#3366CC" );
		colores.put( 14, "#999966" );
		colores.put( 21, "#666699" );
		colores.put( 15, "#669999" );
		colores.put( 20, "#996666" );
		colores.put( 16, "#3399CC" );
		colores.put( 19, "#CC6633" );
		colores.put( 17, "#0099FF" );
		colores.put( 18, "#FF6600" );
    }

    public static String generaTablaInformacion( Date fecha, double lat, double lng, double cog, double cmg, int des, double dtd, double sog, double vmg, double sogVmg )
    {
		Calendar cal = Calendar.getInstance();
		cal.setTime( fecha );
	    	StringBuilder html = new StringBuilder();
	    	html.append("<div class=\"ancho250\">");
	    	html.append("<div>");
	    	html.append("<div class=\"izda ancho132 etiqueta\">FECHA</div>");
	    	html.append("<div class=\"izda ancho66 etiqueta\">DTD</div>");
	    	html.append("<div class=\"clear\"></div>");
	    	html.append("</div>");
	    	html.append("<div>");
	    	html.append("<div class=\"izda ancho132 valor\">" + Globals.DATE_TIME_FORMATTER.format( cal.getTime() ) + "</div>");
	    	html.append("<div class=\"izda ancho66 valor\">" + Nautical.formateaDistancia( dtd ) + " nm</div>");
	    	html.append("<div class=\"clear\"></div>");
	    	html.append("</div>");
	    	html.append("");
	    	html.append("<div>");
	    	html.append("<div class=\"izda ancho198 etiqueta\">POSICION</div>");
	    	html.append("<div class=\"clear\"></div>");
	    	html.append("</div>");
	    	html.append("<div>");
	    	html.append("<div class=\"izda ancho198 valor\">" + Nautical.formateaCoordenada( lat ) + ", " + Nautical.formateaCoordenada( lng ) + "</div>");
	    	html.append("<div class=\"clear\"></div>");
	    	html.append("</div>");
	    	html.append("");
	    	html.append("<div>");
	    	html.append("<div class=\"izda ancho66 etiqueta\">COG</div>");
	    	html.append("<div class=\"izda ancho66 etiqueta\">CMG</div>");
	    	html.append("<div class=\"izda ancho66 etiqueta\">&Delta;C</div>");
	    	html.append("<div class=\"clear\"></div>");
	    	html.append("</div>");
	    	html.append("<div>");
	    	html.append("<div class=\"izda ancho66 valor\">" + Nautical.formateaRumbo( cog ) + "&deg;</div>");
	    	html.append("<div class=\"izda ancho66 valor\">" + Nautical.formateaRumbo( cmg ) + "&deg;</div>");
	    	html.append("<div class=\"izda ancho66 valor\">" + Nautical.formateaRumbo( des ) + "&deg;</div>");
	    	html.append("<div class=\"clear\"></div>");
	    	html.append("</div>");
	    	html.append("");
	    	html.append("<div>");
	    	html.append("<div class=\"izda ancho66 etiqueta\">SOG</div>");
	    	html.append("<div class=\"izda ancho66 etiqueta\">VMG</div>");
	    	html.append("<div class=\"izda ancho66 etiqueta\">&Delta;S</div>");
	    	html.append("<div class=\"clear\"></div>");
	    	html.append("</div>");
	    	html.append("<div>");
	    	html.append("<div class=\"izda ancho66 valor\">" + Nautical.formateaDistancia( sog ) + " kn</div>");
	    	html.append("<div class=\"izda ancho66 valor\">" + Nautical.formateaDistancia( vmg ) + " kn</div>");
	    	html.append("<div class=\"izda ancho66 valor\">" + Nautical.formateaDistancia( sogVmg ) + " kn</div>");
	    	html.append("<div class=\"clear\"></div>");
	    	html.append("</div>");
	    	html.append("</div>");
	    	return html.toString();
    }
    
    public static String generaElementoEnMapa(ElementoEnMapa e) 
    {
    	if (e instanceof Point) {
    		return generaBaliza((Point) e);
    	} else if (e instanceof Line) {
    		return generaLinea((Line) e);
    	} else {
    		return "";
    	}
    }
    
    public static String generaBaliza( Point p )
    {
		String key = GeneradorClaves.generaCodigoURL(10);
		StringBuilder html = new StringBuilder();
		html.append("\n");
		html.append("        var punto_" + key + " = new google.maps.Marker({\n");
		html.append("          position: {lat: " + p.getLatitude() + ", lng: " + p.getLongitude() + "},\n");
		html.append("          map: map,\n");
		html.append("          title: '" + p.getNombre() + "',\n");
		html.append("          id: 'recorrido_punto_" + key + "',\n");
		html.append("          icon: \"" + Nautical.getBalizaImageBase64( p.getTipo() ) + "\"\n");
		html.append("        });\n");	
		return html.toString();
    }
    
    public static String generaLinea( Line l )
    {
		String key = GeneradorClaves.generaCodigoURL(10);
		StringBuilder html = new StringBuilder();
		html.append("\n");
		html.append("        var coordenadas_linea_salida_" + key + " = [\n");
		html.append("          {");
		html.append("lat: " + l.getPoint1().getLatitude() + ", ");
		html.append("lng: " + l.getPoint1().getLongitude() + "},\n");
		html.append("          {");
		html.append("lat: " + l.getPoint2().getLatitude() + ", ");
		html.append("lng: " + l.getPoint2().getLongitude() + "}\n");
		html.append("        ];\n");
		html.append("\n");
		html.append("        var dibujo_linea_salida_" + key + " = new google.maps.Polyline({\n");
		html.append("          path: coordenadas_linea_salida_" + key + ",\n");
		html.append("          geodesic: true,\n");
		html.append("          strokeColor: '#FFFFFF',\n");
		html.append("          strokeOpacity: 1.0,\n");
		html.append("          trokeWeight: 1\n");
		html.append("        });\n");
		html.append("        dibujo_linea_salida_" + key + ".setMap(map);\n");
		html.append( generaElementoEnMapa( l.getPoint1() ) );
		html.append( generaElementoEnMapa( l.getPoint2() ) );
		return html.toString();
    }
    
	public static String generaRecorrido(ElementoEnMapa salida, ElementoEnMapa llegada, List<ElementoEnMapa> balizas) 
	{
		StringBuilder html = new StringBuilder();
		html.append(generaElementoEnMapa(salida));
		html.append(generaElementoEnMapa(llegada));
		for (int i = 0; i < balizas.size(); i++) {
			html.append(generaElementoEnMapa(balizas.get(i)));
		}
		return html.toString();
	}
    
    public static String generaLeyenda()
    {
		StringBuilder html = new StringBuilder();
		html.append("    <div id=\"legend\">\n");
		html.append("      <div id=\"colores\">\n");
		html.append("        <img src=\"" + Nautical.getBalizaImageBase64( Nautical.TIPO_PUNTO_NEGRO) + "\"> VMG &lt; 0 kn &nbsp;&nbsp;|&nbsp;&nbsp;\n");
		html.append("        <img src=\"" + Nautical.getBalizaImageBase64( Nautical.TIPO_PUNTO_ROJO) + "\"> 0 kn &lt; VMG &lt; 2 Kn &nbsp;&nbsp;|&nbsp;&nbsp;\n");
		html.append("        <img src=\"" + Nautical.getBalizaImageBase64( Nautical.TIPO_PUNTO_NARANJA) + "\"> 2 kn &lt; VMG &lt; 3 Kn &nbsp;&nbsp;|&nbsp;&nbsp;\n");
		html.append("        <img src=\"" + Nautical.getBalizaImageBase64( Nautical.TIPO_PUNTO_AMARILLO) + "\"> 3 kn &lt; VMG &lt; 4 Kn &nbsp;&nbsp;|&nbsp;&nbsp;\n");
		html.append("        <img src=\"" + Nautical.getBalizaImageBase64( Nautical.TIPO_PUNTO_VERDE) + "\"> 4 kn &lt; VMG\n");
		html.append("      </div>\n");
		html.append("    </div>\n");
		return html.toString();
    }

    public static String generaHtml( Point esquina_nw, Point esquina_se, ElementoEnMapa salida, ElementoEnMapa llegada, List<ElementoEnMapa> balizas, List<Bordo> bordos  ) {
		StringBuilder html = new StringBuilder();
		html.append("<!DOCTYPE html>\n");
		html.append("<html lang=\"es\">\n");
		html.append("  <head>\n");
		html.append("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\n");
		html.append("    <style>\n");
		html.append("      body {\n");
		html.append("        font-family: Arial;\n");
		html.append("        font-size: 12px;\n");
		html.append("      }\n");
		html.append("\n");
		html.append("      html, body, #map {\n");
		html.append("        margin: 0;\n");
		html.append("        padding: 0;\n");
		html.append("        height: 100%;\n");
		html.append("      }\n");
		html.append("\n");
		html.append("      .izda {\n");
		html.append("        float: left;\n");
		html.append("      }\n");
		html.append("\n");
		html.append("      .dcha {\n");
		html.append("        float: right;\n");
		html.append("      }\n");
		html.append("\n");
		html.append("      .clear {\n");
		html.append("        clear: both;\n");
		html.append("      }\n");
		html.append("\n");
		html.append("      .ancho66 {\n");
		html.append("      	width: 66px;\n");
		html.append("      }\n");
		html.append("\n");
		html.append("      .ancho132 {\n");
		html.append("      	width: 132px;\n");
		html.append("      }\n");
		html.append("\n");
		html.append("      .ancho198 {\n");
		html.append("      	width: 198px;\n");
		html.append("      }\n");
		html.append("\n");
		html.append("      .ancho250 {\n");
		html.append("      	width: 250px;\n");
		html.append("      }\n");	
		html.append("\n");
		html.append("      .etiqueta {\n");
		html.append("        font-weight: bold;\n");
		html.append("        text-align:center;\n");
		html.append("        border-bottom: 1px dotted black;\n");
		html.append("      }\n");
		html.append("\n");
		html.append("      .valor {\n");
		html.append("        text-align: center;\n");
		html.append("        margin-bottom: 12px;\n");
		html.append("      }\n");
		html.append("\n");
		html.append("      #legend {\n");
		html.append("        position: absolute;\n");
		html.append("        top: 0px;\n");
		html.append("        left: 110px;\n");
		html.append("        z-index: 99;\n");
		html.append("        width: 590px;\n");
		html.append("        background: #fff;\n");
		html.append("        padding: 10px;\n");
		html.append("        margin: 7px;\n");
		html.append("        border: 1px solid #000;\n");
		html.append("      }\n");
		html.append("\n");
		html.append("      #datosDerrota {\n");
		html.append("        position: absolute;\n");
		html.append("        top: 0px;\n");
		html.append("        right: 10px;\n");
		html.append("        z-index: 99;\n");
		html.append("        width: 200px;\n");
		html.append("        background: #fff;\n");
		html.append("        padding: 10px;\n");
		html.append("        margin: 7px;\n");
		html.append("        border: 1px solid #bbbbbb;\n");
		html.append("      }\n");
		html.append("\n");	
		html.append("      #legend img {\n");
		html.append("        vertical-align: middle;\n");
		html.append("    </style>\n");
		html.append("    <script src=\"https://maps.googleapis.com/maps/api/js\"></script>\n");
		html.append("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n");
		html.append("    <script>\n");
		
		html.append("\n");
		html.append("	function creaMarker( mapa, latitud, longitud )\n");
		html.append("	{\n");
		html.append("		return new google.maps.Marker({\n");
		html.append("			position: {lat: latitud, lng: longitud },\n");
		html.append("			map: mapa,\n");
		html.append("			draggable: false,\n");
		html.append("			visible: false,\n");
		html.append("			title: ''\n");
		html.append("		});\n");
		html.append("	}\n");	
		html.append("	\n");
		html.append("	function creaDetalle( tsp, dtd, lat, lng, cog, cmg, ac, sog, vmg, as )\n");
		html.append("	{\n");
		html.append("		return '<div class=\"ancho250\"><div><div class=\"izda ancho132 etiqueta\">FECHA</div><div class=\"izda ancho66 etiqueta\">DTD</div><div class=\"clear\"></div></div><div><div class=\"izda ancho132 valor\">' + tsp + '</div><div class=\"izda ancho66 valor\">' + dtd + ' nm</div><div class=\"clear\"></div></div><div><div class=\"izda ancho198 etiqueta\">POSICION</div><div class=\"clear\"></div></div><div><div class=\"izda ancho198 valor\">' + lat + ', ' + lng + '</div><div class=\"clear\"></div></div><div><div class=\"izda ancho66 etiqueta\">COG</div><div class=\"izda ancho66 etiqueta\">CMG</div><div class=\"izda ancho66 etiqueta\">&Delta;C</div><div class=\"clear\"></div></div><div><div class=\"izda ancho66 valor\">' + cog + '&deg;</div><div class=\"izda ancho66 valor\">' + cmg + '&deg;</div><div class=\"izda ancho66 valor\">' + ac + '&deg;</div><div class=\"clear\"></div></div><div><div class=\"izda ancho66 etiqueta\">SOG</div><div class=\"izda ancho66 etiqueta\">VMG</div><div class=\"izda ancho66 etiqueta\">&Delta;S</div><div class=\"clear\"></div></div><div><div class=\"izda ancho66 valor\">' + sog + ' kn</div><div class=\"izda ancho66 valor\">' + vmg  + ' kn</div><div class=\"izda ancho66 valor\">' + as + ' kn</div><div class=\"clear\"></div></div></div>';\n");
		html.append("	}\n");
		html.append("\n");	
		html.append("      function initialize() {\n");
	
		double aLat = esquina_nw.getLatitude() - esquina_se.getLatitude();
		if ( aLat < 0 ) aLat += 360;
		double aLon = Math.abs( esquina_nw.getLongitude() - esquina_se.getLongitude() );
		Point centro = new Point( ( esquina_nw.getLatitude() + esquina_se.getLatitude() ) / 2, ( esquina_nw.getLongitude() + esquina_se.getLongitude() ) / 2 );
		html.append("        var GLOBE_WIDTH = 256;\n");
		html.append("        var zoom = 0;\n");
		html.append("        var zoomV = Math.round( Math.log( window.innerWidth * 360 / " + aLat + " / GLOBE_WIDTH) / Math.LN2 );\n");
		html.append("        var zoomH = Math.round( Math.log( window.innerWidth * 360 / " + aLon + " / GLOBE_WIDTH) / Math.LN2 );\n");
		html.append("        if ( zoomV > zoomH ) {\n");
		html.append("          zoom = zoomH;\n");
		html.append("        } else {\n");
		html.append("          zoom = zoomV;\n");
		html.append("        }\n");
		html.append("\n");	
		html.append("        var map = new google.maps.Map(document.getElementById('map'), {\n");
		html.append("        	zoom: zoom,\n");
		html.append("        	center: {lat: " + centro.getLatitude() + ", lng: " + centro.getLongitude() + "},\n");
		html.append("        	mapTypeId: google.maps.MapTypeId.TERRAIN\n");
		html.append("        });\n");
		html.append("\n");
	
		html.append( generaRecorrido( salida, llegada, balizas ) );
	
		int num = 0;
		for ( int i=0; i < bordos.size(); i++ ) {
		    for (int j = 0; j < bordos.get(i).getDerrota().size(); j++) {
			Date fecha = bordos.get(i).getDerrota().get(j).getTimeIni();
			double lat = bordos.get(i).getDerrota().get(j).getPoint1().getLatitude();
			double lng = bordos.get(i).getDerrota().get(j).getPoint1().getLongitude();
			double cog = bordos.get(i).getDerrota().get(j).getCog();
			double cmg = bordos.get(i).getDerrota().get(j).getCmg(bordos.get(i).geteFin());
			int des = new Double(cmg - cog).intValue();
			double dtd = Nautical.fromMeters(bordos.get(i).getDerrota().get(j).distanceInMeters(bordos.get(i).geteFin()));
			double sog = bordos.get(i).getDerrota().get(j).getSog();
			double vmg = bordos.get(i).getDerrota().get(j).getVmg(bordos.get(i).geteFin());
			double sogVmg = sog - vmg;
			String color = getColorVelocidad( vmg, -2, 6 );
			Calendar cal = Calendar.getInstance();
			cal.setTime( fecha );
			
			html.append("\n");
			html.append("        var derrota_bordo_" + num + " = new google.maps.Polyline({\n");
			html.append("        	path: [ {lat: " + bordos.get(i).getDerrota().get(j).getPoint1().getLatitude() + ", lng: " + bordos.get(i).getDerrota().get(j).getPoint1().getLongitude() + "}, " +
					                       "{lat: " + bordos.get(i).getDerrota().get(j).getPoint2().getLatitude() + ", lng: " + bordos.get(i).getDerrota().get(j).getPoint2().getLongitude() + " } ],\n");
			html.append("        	geodesic: true,\n");
			html.append("        	strokeColor: '" + color + "',\n");
			html.append("        	strokeOpacity: 1.0,\n");
			html.append("        	strokeWeight: 9\n");
			html.append("        });\n");
			html.append("        derrota_bordo_" + num + ".addListener('mouseover', function() {\n");
			html.append("        	var marker = creaMarker( map, " + bordos.get(i).getDerrota().get(j).getMiddlePoint().getLatitude() + ", " + bordos.get(i).getDerrota().get(j).getMiddlePoint().getLongitude() + " );\n");
			html.append("        	var detalle = creaDetalle( '" + Globals.DATE_TIME_FORMATTER.format( cal.getTime() ) + 
					    "', '" + Nautical.formateaDistancia( dtd ) + 
					    "', '" + Nautical.formateaCoordenada( lat ) + 
					    "', '" + Nautical.formateaCoordenada( lng ) + 
					    "', '" + Nautical.formateaRumbo( cog ) + 
					    "', '" + Nautical.formateaRumbo( cmg ) + 
					    "', '" + Nautical.formateaRumbo( des ) + 
					    "', '" + Nautical.formateaDistancia( sog ) + 
					    "', '" + Nautical.formateaDistancia( vmg ) + 
					    "', '" + Nautical.formateaDistancia( sogVmg ) + "' );\n");
			// html.append("        	var infowindow = new google.maps.InfoWindow({\n");
			// html.append("        		content: detalle,\n");
			// html.append("        		maxWidth: 500\n");
			// html.append("        	});\n");
			html.append("        	$('#datosDerrota').html( detalle ).show();\n");
			// html.append("        	infowindow.open( map, marker );\n");
			html.append("        	derrota_bordo_" + num + ".addListener('mouseout', function() {\n");
			// html.append("        		infowindow.close( map, marker );\n");
			html.append("        		$('#datosDerrota').html( '' ).hide();\n");
			html.append("        	});\n");
			html.append("        });\n");
			html.append("        derrota_bordo_" + num + ".setMap(map);\n");		
			html.append("\n");
	
			
			/*
			
			html.append("        var contentString_" + num + " = '" + generaTablaInformacion( fecha, lat, lng, cog, cmg, des, dtd, sog, vmg,  sogVmg )  + "';");
			html.append("\n");
			html.append("        var infowindow_" + num + " = new google.maps.InfoWindow({\n");
			html.append("          content: contentString_" + num + ",\n");
			html.append("          maxWidth: 500\n");
			html.append("        });\n");
			html.append("\n");
			
			html.append("\n");
			html.append("        var marker_" + num + " = new google.maps.Marker({\n");
			html.append("          position: { lat: " + bordos.get(i).getDerrota().get(j).getPoint1().getLatitude() + ", lng: " + bordos.get(i).getDerrota().get(j).getPoint1().getLongitude() + " },\n");
			html.append("          map: map,\n");
			html.append("          draggable: false,\n");
			html.append("          icon: {\n");
			html.append("            url: \"" + Nautical.getBalizaImageBase64( Nautical.getTipoPunto( vmg ) ) + "\",\n");
			html.append("            rotation: " + cog +"\n");
			html.append("          },\n");
			html.append("          title: ''\n");
			html.append("        });\n");
			html.append("\n");
			html.append("        marker_" + num + ".addListener('click', function() {\n");
			html.append("          infowindow_" + num + ".open( map, marker_" + num + " );\n");
			html.append("        });\n");
			html.append("\n");
			html.append("        var coordenadas_bordo_" + num + " = [\n");
			html.append("          {");
			html.append("lat: " + bordos.get(i).getDerrota().get(j).getPoint1().getLatitude() + ", ");
			html.append("lng: " + bordos.get(i).getDerrota().get(j).getPoint1().getLongitude() + "},\n");
			html.append("          {");
			html.append("lat: " + bordos.get(i).getDerrota().get(j).getPoint2().getLatitude() + ", ");
			html.append("lng: " + bordos.get(i).getDerrota().get(j).getPoint2().getLongitude() + "}\n");
			html.append("        ];\n");
			html.append("\n");
			html.append("        var derrota_bordo_" + num + " = new google.maps.Polyline({\n");
			html.append("          path: coordenadas_bordo_" + num + ",\n");
			html.append("          geodesic: true,\n");
			html.append("          strokeColor: '" + colores.get(i) + "',\n");
			html.append("          strokeOpacity: 1.0,\n");
			html.append("          trokeWeight: 2\n");
			html.append("        });\n");
			html.append("        derrota_bordo_" + num + ".setMap(map);\n");
			*/
			num++;
		    }	    
		}
		html.append("      }\n");
		html.append("      google.maps.event.addDomListener(window, 'load', initialize);\n");
		html.append("    </script>\n");		
		html.append("    <title>GoogleMaps APIv3</title>\n");
		html.append("  </head>\n");
		html.append("  <body>\n");
		html.append("    <div id=\"map\"></div>\n");
		html.append("    <div id=\"datosDerrota\"></div>\n");
		// html.append( generaLeyenda() );
		html.append("  </body>\n");
		html.append("</html>\n");	
		return html.toString();
    }
    
    public static String getColorVelocidad( double velocidad, double min, double max )
    {
    	int r = 0;
    	int g = 0;
    	int b = 0;
    	if ( velocidad < 0 ) {
    		r = 128 + ( Double.valueOf( ( ( velocidad - min ) / max ) * 128 ).intValue() );
    	}
    	if ( velocidad > 0 ) {
    		g = ( Double.valueOf( ( ( velocidad - min ) / max ) * 255 ).intValue() );
    	}
    	return String.format("#%02x%02x%02x", r, g, b);  
    }
}
