package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoatEntity;
import com.capitanperegrina.utils.sql.ResultSetUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoatRowMapper implements RowMapper<BoatEntity> {

    @Override
    public BoatEntity mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final BoatEntity obj = new BoatEntity();
        obj.setIdBoat(ResultSetUtils.getInteger("id_boat", rs));
        obj.setName(ResultSetUtils.getString("name", rs));
        return obj;
    }
}
