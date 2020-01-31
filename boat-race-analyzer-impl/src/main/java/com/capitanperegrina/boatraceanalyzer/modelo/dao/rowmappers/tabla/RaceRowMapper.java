package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.RacePOJO;
import com.capitanperegrina.boatraceanalyzer.util.sql.ResultSetUtils;

/**
 * Rowmapper de la tabla <code>race</code> a la clase RacePOJO
 */
public class RaceRowMapper implements RowMapper<RacePOJO> {
    /**
     * Método que mapea un registro de la tabla race con un la clase RacePOJO.
     *
     * @param rs
     *            Resultset del que extraer la información
     * @param rowNum
     *            Número de fila del resultSet del que obtener la información.
     * @return POJO con la información mapeada
     */
    @Override
    public RacePOJO mapRow(ResultSet rs, int rowNum) throws SQLException {
        RacePOJO obj = new RacePOJO();
        obj.setIdRace(ResultSetUtils.getInteger("id_race", rs));
        obj.setName(StringUtils.trimToEmpty(ResultSetUtils.getString("name", rs)));
        return obj;
    }
}
