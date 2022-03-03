package com.capitanperegrina.boatraceanalyzer.adapters;

import java.util.ArrayList;
import java.util.List;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackpointEntity;
import com.capitanperegrina.estela.bean.EstelaCSV;

public class EstelaAdapters {

    private EstelaAdapters() {
        
    }
    
    public static TrackpointEntity toTrackpointPOJO(Integer idTrack, EstelaCSV in ) {
        if ( in == null ) {
            return null;
        }
        TrackpointEntity out = new TrackpointEntity();
        out.setIdTrackPoint(null);
        out.setIdTrack(idTrack);
        out.setTsp(in.getTime());
        out.setLat(in.getLat());
        out.setLon(in.getLon());
        out.setSog(in.getSog());
        out.setCog(in.getCog());
        
        return out;
    }
    
    public static List<TrackpointEntity> toTrackpointPOJOList(Integer idTrack, List<EstelaCSV> in ) {
        List<TrackpointEntity> out = new ArrayList<>();
        if ( in == null ) {
            return out;
        }
        for ( EstelaCSV obj : in ) {
            out.add(toTrackpointPOJO(idTrack, obj));
        }
        return out;
    }
}
