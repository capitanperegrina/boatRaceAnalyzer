package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.RouteEntity;
import com.capitanperegrina.utils.sql.ResultSetUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteRowMapper implements RowMapper<RouteEntity> {

    @Override
    public RouteEntity mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final RouteEntity obj = new RouteEntity();
        obj.setIdRoute(ResultSetUtils.getInteger("id_route", rs));
        obj.setIdLeg(ResultSetUtils.getInteger("id_leg", rs));
        obj.setSeq(ResultSetUtils.getInteger("seq", rs));
        obj.setLat1(ResultSetUtils.getBigDecimal("lat1", rs));
        obj.setLon1(ResultSetUtils.getBigDecimal("lon1", rs));
        obj.setLat2(ResultSetUtils.getBigDecimal("lat2", rs));
        obj.setLon2(ResultSetUtils.getBigDecimal("lon2", rs));
        obj.setKind(ResultSetUtils.getInteger("kind", rs));
        obj.setName(ResultSetUtils.getString("name", rs));
        obj.setPass(ResultSetUtils.getInteger("pass", rs));
        return obj;
    }
}
