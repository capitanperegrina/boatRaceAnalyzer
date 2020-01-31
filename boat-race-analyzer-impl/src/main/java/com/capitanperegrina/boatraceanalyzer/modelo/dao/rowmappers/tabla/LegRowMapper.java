package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.LegPOJO;
import com.capitanperegrina.boatraceanalyzer.util.sql.ResultSetUtils;

/**
 * Rowmapper de la tabla <code>leg</code> a la clase LegPOJO
 */
public class LegRowMapper implements RowMapper<LegPOJO> {
    /**
     * Método que mapea un registro de la tabla leg con un la clase LegPOJO.
     *
     * @param rs
     *            Resultset del que extraer la información
     * @param rowNum
     *            Número de fila del resultSet del que obtener la información.
     * @return POJO con la información mapeada
     */
    @Override
    public LegPOJO mapRow(ResultSet rs, int rowNum) throws SQLException {
        LegPOJO obj = new LegPOJO();
        obj.setIdLeg(ResultSetUtils.getInteger("id_leg", rs));
        obj.setIdRace(ResultSetUtils.getInteger("id_race", rs));
        obj.setDate(ResultSetUtils.getDate("date", rs));
        obj.setName(StringUtils.trimToEmpty(ResultSetUtils.getString("name", rs)));
        return obj;
    }
}
