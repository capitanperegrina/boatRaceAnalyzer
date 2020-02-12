package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackPOJO;
import com.capitanperegrina.boatraceanalyzer.util.sql.ResultSetUtils;

public class TrackRowMapper implements RowMapper<TrackPOJO> {

    @Override
    public TrackPOJO mapRow( ResultSet rs, int rowNum ) throws SQLException {
        TrackPOJO obj = new TrackPOJO();
        obj.setIdTrack( ResultSetUtils.getInteger("id_track", rs) );
        obj.setIdLeg( ResultSetUtils.getInteger("id_leg", rs) );
        obj.setIdBoat( ResultSetUtils.getInteger("id_boat", rs) );
        obj.setTspIni( ResultSetUtils.getTimestamp("tsp_ini", rs) );
        obj.setTspEnd( ResultSetUtils.getTimestamp("tsp_end", rs) );
    	return obj;
    }
}
