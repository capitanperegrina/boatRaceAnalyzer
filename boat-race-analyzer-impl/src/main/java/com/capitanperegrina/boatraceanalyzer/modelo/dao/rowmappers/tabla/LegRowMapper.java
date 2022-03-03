package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.LegEntity;
import com.capitanperegrina.utils.sql.ResultSetUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LegRowMapper implements RowMapper<LegEntity> {

    @Override
    public LegEntity mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final LegEntity obj = new LegEntity();
        obj.setIdLeg(ResultSetUtils.getInteger("id_leg", rs));
        obj.setIdRace(ResultSetUtils.getInteger("id_race", rs));
        obj.setDate(ResultSetUtils.getTimestamp("date", rs));
        obj.setName(ResultSetUtils.getString("name", rs));
        return obj;
    }
}
