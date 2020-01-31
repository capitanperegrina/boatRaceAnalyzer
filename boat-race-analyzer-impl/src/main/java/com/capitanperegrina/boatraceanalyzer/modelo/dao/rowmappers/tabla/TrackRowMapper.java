package com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackPOJO;

/**
 * Rowmapper de la tabla <code>track</code> a la clase TrackPOJO
 */
public class TrackRowMapper implements RowMapper<TrackPOJO>
{
    /**
     * Método que mapea un registro de la tabla track con un la clase TrackPOJO.
     * @param rs Resultset del que extraer la información
     * @param rowNum Número de fila del resultSet del que obtener la información.
     * @return POJO con la información mapeada
     */
    @Override
    public TrackPOJO mapRow( ResultSet rs, int rowNum ) throws SQLException
    {
        TrackPOJO obj = new TrackPOJO();

        obj.setIdTrack( new Integer( rs.getInt( "id_track" ) ) );
        if ( rs.wasNull() )
        {
            obj.setIdTrack( null );
        }
        obj.setIdLeg( new Integer( rs.getInt( "id_leg" ) ) );
        if ( rs.wasNull() )
        {
            obj.setIdLeg( null );
        }
        obj.setIdBoat( new Integer( rs.getInt( "id_boat" ) ) );
        if ( rs.wasNull() )
        {
            obj.setIdBoat( null );
        }

    	return obj;
    }
}
