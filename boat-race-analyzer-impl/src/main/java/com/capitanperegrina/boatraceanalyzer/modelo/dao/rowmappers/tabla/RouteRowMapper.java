package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.RoutePOJO;
import com.capitanperegrina.boatraceanalyzer.util.sql.ResultSetUtils;

public class RouteRowMapper implements RowMapper<RoutePOJO> {

    @Override
    public RoutePOJO mapRow( ResultSet rs, int rowNum ) throws SQLException {
        RoutePOJO obj = new RoutePOJO();
        obj.setIdRoute( ResultSetUtils.getInteger("id_route", rs) );
        obj.setIdLeg( ResultSetUtils.getInteger("id_leg", rs) );
        obj.setSeq( ResultSetUtils.getInteger("seq", rs) );
        obj.setLat1( ResultSetUtils.getBigDecimal("lat1", rs ) );
        obj.setLon1( ResultSetUtils.getBigDecimal("lon1", rs ) );
        obj.setLat2( ResultSetUtils.getBigDecimal("lat2", rs ) );
        obj.setLon2( ResultSetUtils.getBigDecimal("lon2", rs ) );
        obj.setKind( ResultSetUtils.getInteger("kind", rs) );
        obj.setName( ResultSetUtils.getString("name", rs) );
        obj.setPass( ResultSetUtils.getInteger("pass", rs) );
    	return obj;
    }
}
