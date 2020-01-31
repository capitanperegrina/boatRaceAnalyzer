package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackpointPOJO;
import com.capitanperegrina.boatraceanalyzer.util.sql.ResultSetUtils;

/**
 * Rowmapper de la tabla <code>trackpoint</code> a la clase TrackpointPOJO
 */
public class TrackpointRowMapper implements RowMapper<TrackpointPOJO> {
    /**
     * Método que mapea un registro de la tabla trackpoint con un la clase TrackpointPOJO.
     *
     * @param rs
     *            Resultset del que extraer la información
     * @param rowNum
     *            Número de fila del resultSet del que obtener la información.
     * @return POJO con la información mapeada
     */
    @Override
    public TrackpointPOJO mapRow(ResultSet rs, int rowNum) throws SQLException {
        TrackpointPOJO obj = new TrackpointPOJO();
        obj.setIdTrackPoint(ResultSetUtils.getInteger("id_track_point", rs));
        obj.setIdTrack(ResultSetUtils.getInteger("id_track", rs));
        obj.setTsp(ResultSetUtils.getDate("tsp", rs));
        obj.setLat(ResultSetUtils.getBigDecimal("lat", rs));
        obj.setLon(ResultSetUtils.getBigDecimal("lon", rs));
        obj.setSog(ResultSetUtils.getBigDecimal("sog", rs));
        obj.setCog(ResultSetUtils.getBigDecimal("cog", rs));
        return obj;
    }
}
