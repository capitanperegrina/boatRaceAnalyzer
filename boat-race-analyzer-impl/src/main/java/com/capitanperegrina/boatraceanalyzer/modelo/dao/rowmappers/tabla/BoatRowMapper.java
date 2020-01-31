package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoatPOJO;
import com.capitanperegrina.boatraceanalyzer.util.sql.ResultSetUtils;

/**
 * Rowmapper de la tabla <code>boat</code> a la clase BoatPOJO
 */
public class BoatRowMapper implements RowMapper<BoatPOJO> {
    /**
     * Método que mapea un registro de la tabla boat con un la clase BoatPOJO.
     *
     * @param rs
     *            Resultset del que extraer la información
     * @param rowNum
     *            Número de fila del resultSet del que obtener la información.
     * @return POJO con la información mapeada
     */
    @Override
    public BoatPOJO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BoatPOJO obj = new BoatPOJO();
        obj.setIdBoat(ResultSetUtils.getInteger("id_boat", rs));
        obj.setName(StringUtils.trimToEmpty(ResultSetUtils.getString("name", rs)));
        return obj;
    }
}
