<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE html> 
<html> 
	<head>
    	<title>${boatRaceAnalysisBean.title}</title>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico" />
    	<link rel="stylesheet" href="https://webapiv2.navionics.com/dist/webapi/webapi.min.css"/>
    	<link rel="stylesheet" href="css/tracks.css"/>

    	<link rel="stylesheet" href="css/leaflet.css" />
    	<script src="js/leaflet.js"></script>
<c:if test="${mapType eq 2}">    	
    	<script type="text/javascript" src="https://webapiv2.navionics.com/dist/webapi/webapi.min.no-dep.js"></script>
</c:if>
    	<script src="js/jquery.min.js"></script> 
    	<script src="js/tracks.js"></script>
<c:if test="${not empty boatRaceAnalysisBean.tracks}">
	<c:forEach items="${boatRaceAnalysisBean.tracks}" var="track" varStatus="status">
		<script src="track.js?r=${boatRaceAnalysisBean.idRace}&l=${boatRaceAnalysisBean.idLeg}&t=${track}"></script>
	</c:forEach>
</c:if>
	</head> 
	<body>
    	<div id="buttons">
        	<div class="fieldset">
	            <fieldset>

<c:if test="${not empty boatRaceAnalysisBean.boats}">
	<c:forEach items="${boatRaceAnalysisBean.boats}" var="boat" varStatus="status">
					<div class="izda"><input type="checkbox" name="check_${boat.normalizedBoatName}" id="${boat.normalizedBoatName}" checked="checked"/>${boat.boatName}</div>
	</c:forEach>
</c:if>
            	</fieldset>
        	</div>
    	</div>
    	<div id="mapid" style="width: 1024px; height: 768px; margin: 0"></div>
	    <div id="datosDerrota"> 
        	<div class="group"> 
	            <div class="title">Time</div> 
            	<div id="dataTsp">-</div> 
        	</div> 
            <div class="group"> 
                <div class="title">Lat</div> 
                <div id="dataLon">-</div> 
            </div> 
            <div class="group"> 
                <div class="title">Lon</div> 
                <div id="dataLat">-</div> 
            </div> 
            <div class="group"> 
                <div class="title">COG</div> 
                <div id="dataCog">-</div> 
            </div> 
            <div class="group"> 
                <div class="title">SOG</div> 
                <div id="dataSog">-</div> 
            </div> 
            <div class="group"> 
                <div class="title">VMG</div> 
                <div id="dataVmg">-</div> 
            </div> 
            <div class="group"> 
                <div class="title">DTD</div> 
                <div id="dataDtd">-</div> 
            </div> 
            <div class="group"> 
                <div class="title">&Delta;</div> 
                <div id="dataDelta">-</div> 
            </div> 
	        <div class="clearfix"></div> 
    	</div>
    	
    	<script> 
<c:if test="${mapType eq 1}">
           var polylines = new L.FeatureGroup();
           var mymap = L.map('mapid').setView([${boatRaceAnalysisBean.centerTrack.latitude},${boatRaceAnalysisBean.centerTrack.longitude}], 13);
           L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
               maxZoom: 18,
               attribution: 'Map data &copy; <a href=\"https://www.openstreetmap.org/\">OpenStreetMap</a> contributors, ' + 
                   '<a href=\"https://creativecommons.org/licenses/by-sa/2.0/\">CC-BY-SA</a>, ' +
                   'Imagery © <a href=\"https://www.mapbox.com/\">Mapbox</a>',
               id: 'mapbox/streets-v11' 
           }).addTo(mymap);
           L.tileLayer('https://tiles.openseamap.org/seamark/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
               maxZoom: 18,
               attribution: 'Map data: &copy; <a href=\"http://www.openseamap.org\">OpenSeaMap</a> contributors' 
           }).addTo(mymap);    	
</c:if>
<c:if test="${mapType eq 2}">
	        var polylines = new L.FeatureGroup();
	    	var mymap = L.map('mapid');
	    	var navionics_overlay = new JNC.Leaflet.NavionicsOverlay({
	            navKey: 'Navionics_webapi_03753',
	        	chartType: JNC.NAVIONICS_CHARTS.NAUTICAL,
	        	isTransparent: false,
	        	logoPayoff: false,
	        	zIndex: 1
	    	});
	    	
	    	mymap.setView([${boatRaceAnalysisBean.centerTrack.latitude},${boatRaceAnalysisBean.centerTrack.longitude}], 13);
	    	navionics_overlay.addTo(mymap);    	
</c:if>
${boatRaceAnalysisBean.icons}
${boatRaceAnalysisBean.markers}
${boatRaceAnalysisBean.script}
	    </script>    	
	</body> 
</html>