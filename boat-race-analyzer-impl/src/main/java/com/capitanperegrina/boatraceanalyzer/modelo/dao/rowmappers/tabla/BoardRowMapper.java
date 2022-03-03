package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoardEntity;
import com.capitanperegrina.utils.sql.ResultSetUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardRowMapper implements RowMapper<BoardEntity> {

    @Override
    public BoardEntity mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final BoardEntity obj = new BoardEntity();
        obj.setIdBoard(ResultSetUtils.getInteger("id_board", rs));
        obj.setIdTrack(ResultSetUtils.getInteger("id_track", rs));
        obj.setIni(ResultSetUtils.getInteger("ini", rs));
        obj.setDateIni(ResultSetUtils.getTimestamp("date_ini", rs));
        obj.setFin(ResultSetUtils.getInteger("fin", rs));
        obj.setDateFin(ResultSetUtils.getTimestamp("date_fin", rs));
        return obj;
    }
}
