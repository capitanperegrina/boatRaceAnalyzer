package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackEntity;
import com.capitanperegrina.utils.sql.ResultSetUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackRowMapper implements RowMapper<TrackEntity> {

    @Override
    public TrackEntity mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final TrackEntity obj = new TrackEntity();
        obj.setIdTrack(ResultSetUtils.getInteger("id_track", rs));
        obj.setIdLeg(ResultSetUtils.getInteger("id_leg", rs));
        obj.setIdBoat(ResultSetUtils.getInteger("id_boat", rs));
        obj.setTspIni(ResultSetUtils.getTimestamp("tsp_ini", rs));
        obj.setTspEnd(ResultSetUtils.getTimestamp("tsp_end", rs));
        return obj;
    }
}
