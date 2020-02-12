package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoatPOJO;
import com.capitanperegrina.boatraceanalyzer.util.sql.ResultSetUtils;

public class BoatRowMapper implements RowMapper<BoatPOJO> {

    @Override
    public BoatPOJO mapRow( ResultSet rs, int rowNum ) throws SQLException {
        BoatPOJO obj = new BoatPOJO();
        obj.setIdBoat( ResultSetUtils.getInteger("id_boat", rs) );
        obj.setName( ResultSetUtils.getString("name", rs) );
    	return obj;
    }
}
