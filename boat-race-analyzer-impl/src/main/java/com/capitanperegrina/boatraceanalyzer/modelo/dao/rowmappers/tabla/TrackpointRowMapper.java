package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackpointPOJO;
import com.capitanperegrina.boatraceanalyzer.util.sql.ResultSetUtils;

public class TrackpointRowMapper implements RowMapper<TrackpointPOJO> {

    @Override
    public TrackpointPOJO mapRow( ResultSet rs, int rowNum ) throws SQLException {
        TrackpointPOJO obj = new TrackpointPOJO();
        obj.setIdTrackPoint( ResultSetUtils.getInteger("id_track_point", rs) );
        obj.setIdTrack( ResultSetUtils.getInteger("id_track", rs) );
        obj.setTsp( ResultSetUtils.getTimestamp("tsp", rs) );
        obj.setLat( ResultSetUtils.getBigDecimal("lat", rs ) );
        obj.setLon( ResultSetUtils.getBigDecimal("lon", rs ) );
        obj.setSog( ResultSetUtils.getBigDecimal("sog", rs ) );
        obj.setCog( ResultSetUtils.getBigDecimal("cog", rs ) );
    	return obj;
    }
}
