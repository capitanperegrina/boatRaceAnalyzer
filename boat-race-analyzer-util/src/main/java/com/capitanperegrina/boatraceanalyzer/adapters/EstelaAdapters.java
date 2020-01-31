package com.capitanperegrina.boatraceanalyzer.adapters;

import java.util.ArrayList;
import java.util.List;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackpointPOJO;
import com.capitanperegrina.estela.EstelaCSV;

public class EstelaAdapters {

    private EstelaAdapters() {
        
    }
    
    public static TrackpointPOJO toTrackpointPOJO( Integer idTrack, EstelaCSV in ) {
        if ( in == null ) {
            return null;
        }
        TrackpointPOJO out = new TrackpointPOJO();
        out.setIdTrackPoint(null);
        out.setIdTrack(idTrack);
        out.setTsp(in.getTime());
        out.setLat(in.getLat());
        out.setLon(in.getLon());
        out.setSog(in.getSog());
        out.setCog(in.getCog());
        
        return out;
    }
    
    public static List<TrackpointPOJO> toTrackpointPOJOList( Integer idTrack, List<EstelaCSV> in ) {
        List<TrackpointPOJO> out = new ArrayList<>();
        if ( in == null ) {
            return out;
        }
        for ( EstelaCSV obj : in ) {
            out.add(toTrackpointPOJO(idTrack, obj));
        }
        return out;
    }
}
