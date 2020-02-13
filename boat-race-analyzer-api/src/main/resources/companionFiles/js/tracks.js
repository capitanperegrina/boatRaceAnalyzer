function resize() {
    $('#mapid').height($(window).height() - $('#buttons').height());
    $('#mapid').width($(window).width());
    mymap.invalidateSize();
}

function toggle(elemento) {
    if (mymap.hasLayer(elemento)) {
        mymap.removeLayer(mymap._layers[elemento._leaflet_id]);
    } else {
        mymap.addLayer(elemento);
    }
}

function getColor(speed) {
    return speed > 8.00 ? '#cc0000' :
        speed > 7.75 ? '#aa0000' :
        speed > 7.50 ? '#aa2200' :
        speed > 7.25 ? '#aa4400' :
        speed > 7.00 ? '#aa6600' :
        speed > 6.75 ? '#aa8800' :
        speed > 6.50 ? '#aaaa00' :
        speed > 6.25 ? '#aacc00' :
        speed > 6.00 ? '#aaaa00' :
        speed > 5.75 ? '#ccaa00' :
        speed > 5.50 ? '#aaaa00' :
        speed > 5.25 ? '#88aa00' :
        speed > 5.00 ? '#66aa00' :
        speed > 4.75 ? '#44aa00' :
        speed > 4.50 ? '#22aa00' :
        speed > 4.25 ? '#00aa00' :
        speed > 4.00 ? '#00aa22' :
        speed > 3.75 ? '#00aa44' :
        speed > 3.50 ? '#00aa66' :
        speed > 3.25 ? '#00aa88' :
        speed > 3.00 ? '#00aaaa' :
        speed > 2.75 ? '#00aacc' :
        speed > 2.50 ? '#00aaaa' :
        speed > 2.25 ? '#00ccaa' :
        speed > 2.00 ? '#00aaaa' :
        speed > 1.75 ? '#0088aa' :
        speed > 1.50 ? '#0066aa' :
        speed > 1.25 ? '#0044aa' :
        speed > 1.00 ? '#0022aa' :
        speed > 0.75 ? '#0000aa' :
        speed > 0.50 ? '#2200aa' :
        speed > 0.25 ? '#4400aa' :
        '#6600aa';
}

function style(feature) {
    return {
        weight: 5,
        opacity: 1,
        color: getColor(feature.properties.sog)
    };
}