package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoardPOJO;
import com.capitanperegrina.boatraceanalyzer.util.sql.ResultSetUtils;

public class BoardRowMapper implements RowMapper<BoardPOJO> {

    @Override
    public BoardPOJO mapRow( ResultSet rs, int rowNum ) throws SQLException {
        BoardPOJO obj = new BoardPOJO();
        obj.setIdBoard( ResultSetUtils.getInteger("id_board", rs) );
        obj.setIdTrack( ResultSetUtils.getInteger("id_track", rs) );
        obj.setIni( ResultSetUtils.getInteger("ini", rs) );
        obj.setDateIni( ResultSetUtils.getTimestamp("date_ini", rs) );
        obj.setFin( ResultSetUtils.getInteger("fin", rs) );
        obj.setDateFin( ResultSetUtils.getTimestamp("date_fin", rs) );
    	return obj;
    }
}
