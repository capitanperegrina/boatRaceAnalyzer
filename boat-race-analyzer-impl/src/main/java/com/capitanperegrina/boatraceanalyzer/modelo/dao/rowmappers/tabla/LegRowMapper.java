package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.LegPOJO;
import com.capitanperegrina.boatraceanalyzer.util.sql.ResultSetUtils;

public class LegRowMapper implements RowMapper<LegPOJO> {

    @Override
    public LegPOJO mapRow( ResultSet rs, int rowNum ) throws SQLException {
        LegPOJO obj = new LegPOJO();
        obj.setIdLeg( ResultSetUtils.getInteger("id_leg", rs) );
        obj.setIdRace( ResultSetUtils.getInteger("id_race", rs) );
        obj.setDate( ResultSetUtils.getTimestamp("date", rs) );
        obj.setName( ResultSetUtils.getString("name", rs) );
    	return obj;
    }
}
