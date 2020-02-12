package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.RacePOJO;
import com.capitanperegrina.boatraceanalyzer.util.sql.ResultSetUtils;

public class RaceRowMapper implements RowMapper<RacePOJO> {

    @Override
    public RacePOJO mapRow( ResultSet rs, int rowNum ) throws SQLException {
        RacePOJO obj = new RacePOJO();
        obj.setIdRace( ResultSetUtils.getInteger("id_race", rs) );
        obj.setName( ResultSetUtils.getString("name", rs) );
    	return obj;
    }
}
