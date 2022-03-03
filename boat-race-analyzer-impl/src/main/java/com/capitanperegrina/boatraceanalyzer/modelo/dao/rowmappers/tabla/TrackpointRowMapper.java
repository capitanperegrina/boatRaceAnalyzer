package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackpointEntity;
import com.capitanperegrina.utils.sql.ResultSetUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackpointRowMapper implements RowMapper<TrackpointEntity> {

    @Override
    public TrackpointEntity mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final TrackpointEntity obj = new TrackpointEntity();
        obj.setIdTrackPoint(ResultSetUtils.getInteger("id_track_point", rs));
        obj.setIdTrack(ResultSetUtils.getInteger("id_track", rs));
        obj.setTsp(ResultSetUtils.getTimestamp("tsp", rs));
        obj.setLat(ResultSetUtils.getBigDecimal("lat", rs));
        obj.setLon(ResultSetUtils.getBigDecimal("lon", rs));
        obj.setSog(ResultSetUtils.getBigDecimal("sog", rs));
        obj.setCog(ResultSetUtils.getBigDecimal("cog", rs));
        return obj;
    }
}
