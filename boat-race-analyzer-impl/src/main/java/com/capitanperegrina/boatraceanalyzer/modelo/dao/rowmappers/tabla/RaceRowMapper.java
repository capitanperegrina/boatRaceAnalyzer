package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.RaceEntity;
import com.capitanperegrina.utils.sql.ResultSetUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RaceRowMapper implements RowMapper<RaceEntity> {

    @Override
    public RaceEntity mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final RaceEntity obj = new RaceEntity();
        obj.setIdRace(ResultSetUtils.getInteger("id_race", rs));
        obj.setName(ResultSetUtils.getString("name", rs));
        return obj;
    }
}
