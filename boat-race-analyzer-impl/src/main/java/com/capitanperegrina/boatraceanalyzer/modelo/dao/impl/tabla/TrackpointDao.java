package com.capitanperegrina.boatraceanalyzer.modelo.dao.impl.tabla;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackpointPOJO;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.GenericDao;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla.TrackpointRowMapper;
import com.capitanperegrina.boatraceanalyzer.util.sql.QueryUtils;

/**
 * Objeto de acceso a datos para la tabla <code>trackpoint<code>
 */
@Repository("trackpointDao")
@Transactional(value="boatRaceAnalyzer",propagation=Propagation.SUPPORTS)
public class TrackpointDao extends GenericDao
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackpointDao.class);
    
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public static final String ENTIDAD = TrackpointPOJO.class.getName();
    public static final String TABLA = "trackpoint";
    public static final String CAMPOS = "id_track_point, id_track, tsp, lat, lon, sog, cog ";
    public static final String CAMPOSINSERT = " ?, ?, ?, ?, ?, ?, ? ";
    public static final String CAMPOSUPDATE = " id_track = ?, tsp = ?, lat = ?, lon = ?, sog = ?, cog = ? ";
    public static final String CAMPOSPKUPDATE = " id_track_point = ? ";

    private static Object[] toParamsTodos( TrackpointPOJO obj ) {
        return ArrayUtils.addAll( toParamsClave( obj ) , toParamsResto( obj ) );
    }

    private static Object[] toParamsUpdate( TrackpointPOJO obj ) {
        return ArrayUtils.addAll( toParamsResto( obj ), toParamsClave( obj ) );
    }

    private static Object[] toParamsClave( TrackpointPOJO obj ) {
        return new Object[] {
                obj.getIdTrackPoint()
        };
    }

    private static Object[] toParamsResto( TrackpointPOJO obj ) {
        return new Object[] {
                obj.getIdTrack(),
                obj.getTsp(),
                obj.getLat(),
                obj.getLon(),
                obj.getSog(),
                obj.getCog()
        };
    }

    /**
     * Método que inserta un registro en la tabla <code>trackpoint</code> de la base de datos.
     * @param obj Objeto POJO con la información a insertar en la tabla <code>trackpoint</code> de la base de datos.
     * @throws DuplicateKeyException si ya existe un registro en la tabla <code>trackpoint</code> de base de datos con igual clave principal.
     */
    public void crea( TrackpointPOJO obj )
    {
        try
        {
            StringBuilder q = new StringBuilder("");
            Object[] p = toParamsTodos( obj );
            q.append("INSERT INTO " + TABLA + " ( " + CAMPOS + " ) ");
            q.append("VALUES ( " + CAMPOSINSERT + " ) ");

            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace(QueryUtils.pintaQuery(q, p));
            }
            this.jdbcTemplate.update( q.toString(), p );
        }
        catch ( DuplicateKeyException e )
        {
            LOGGER.debug( "Clave duplicada al intentar insertar la información.", e );
            throw e;
        }
    }

    /**
     * Método que comprueba si existe un registro en la tabla <code>trackpoint</code> de la base de datos a partir de su clave principal.
     * @param obj Objeto del que se desea comprobar su existencia en la tabla <code>trackpoint</code> de la base de datos.
     * @return true si existe un registro en la tabla <code>trackpoint</code> de la base de datos con la misma clave principal, false si no es así.
     */
    public boolean existe ( TrackpointPOJO obj )
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsClave( obj );
        q.append(" SELECT COUNT(*) ");
        q.append("   FROM " + TABLA + " ");
        q.append("  WHERE  id_track_point = ?   ");

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return ( this.jdbcTemplate.queryForObject( q.toString(), p, Integer.class ) ) > 0;
    }

    /**
     * Método que recupera de la base de datos un registro de la tabla <code>trackpoint</code> y lo devuelve en el objeto TrackpointPOJO.
     * @param obj Objeto TrackpointPOJO con los campos de la clave principal informados.
     * @return El objeto TrackpointPOJO con la información recuperada de la base de datos.
     * @throws EmptyResultDataAccessException si no se encuentra el resitro en la tabla <code>trackpoint</code>.
     */
    public TrackpointPOJO busca( TrackpointPOJO obj )
    {
        try
        {
            StringBuilder q = new StringBuilder("");
            Object[] p = toParamsClave( obj );
            q.append(" SELECT " + CAMPOS + " ");
            q.append("   FROM " + TABLA + " ");
            q.append("  WHERE  id_track_point = ?   ");

            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace(QueryUtils.pintaQuery(q, p));
            }
            return this.jdbcTemplate.queryForObject( q.toString(), p, new TrackpointRowMapper() );
        }
        catch ( EmptyResultDataAccessException e )
        {
            LOGGER.debug( "No se ha encontrado la información buscada.", e );
            throw e;
        }
    }

    /**
     * Método que actualiza un registro de la tabla <code>trackpoint</code> de la base de datos.
     * @param obj Objeto TrackpointPOJO que se quiere actualizar en la base de datos.
     * @throws EmptyResultDataAccessException Si no se encuentra el registro que se desea actualizar en la tabla <code>trackpoint</code>.
     * @throws DuplicateKeyException si se encuentra más de un registro a actualizar en la tabla la tabla <code>trackpoint</code>.
     */
    public void actualiza( TrackpointPOJO obj )
    {
        try
        {
            StringBuilder q = new StringBuilder("");
            Object[] p = toParamsUpdate( obj );
            q.append(" UPDATE " + TABLA + " ");
            q.append("    SET " + CAMPOSUPDATE + " ");
            q.append("  WHERE " + CAMPOSPKUPDATE + " ");

            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace(QueryUtils.pintaQuery(q, p));
            }
            this.jdbcTemplate.update( q.toString(), p );
        }
        catch ( EmptyResultDataAccessException e )
        {
            LOGGER.debug( "El registro que se desea actualizar no existe.", e );
            throw e;
        }
        catch ( DuplicateKeyException e )
        {
            LOGGER.debug( "Clave duplicada al intentar actualizar la información.", e );
            throw e;
        }
    }

    /**
     * Método que elimina físicamente un registro de la tabla <code>trackpoint</code> de la base de datos a partir de los valores de su clave principal.
     * @param obj Objeto TrackpointPOJO con los campos de la clave principal informados.
     * @throws EmptyResultDataAccessException Si no se encuentra el registro que se desea eliminar en la tabla <code>trackpoint</code>.
     */
    public void borra( TrackpointPOJO obj )
    {
        try
        {
            StringBuilder q = new StringBuilder("");
            Object[] p = toParamsClave( obj );
            q.append(" DELETE FROM " + TABLA + " ");
            q.append("  WHERE  id_track_point = ?   ");

            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace(QueryUtils.pintaQuery(q, p));
            }
            this.jdbcTemplate.update( q.toString(), p );
        }
        catch ( EmptyResultDataAccessException e )
        {
            LOGGER.debug( "El registro que se desea eliminar no existe.", e );
            throw e;
        }
    }

    /**
     * Método devuelve una lista con todos los registros de la tabla <code>trackpoint</code> de la base de datos.
     * @return Una lista con todos los elementos de la tabla <code>trackpoint</code>.
     */
    public List<TrackpointPOJO> buscaTodos()
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = {};
        q.append(" SELECT " + CAMPOS + " ");
        q.append("   FROM " + TABLA + " ");

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query( q.toString(), new BeanPropertyRowMapper<TrackpointPOJO>( TrackpointPOJO.class ) );
    }

    /**
     * Método devuelve una lista con todos los registros de la tabla <code>trackpoint</code> de la base de datos a partir de los campos de de un POJO.
     * @param obj Objeto TrackpointPOJO con los campos por los que se desea buscar informados.
     * @return Una lista con todos los elementos de la tabla <code>trackpoint</code> que cumplan los criterios de búsqueda.
     */
    public List<TrackpointPOJO> buscaVarios( TrackpointPOJO obj )
    {
        if ( obj == null )
        {
            return buscaTodos();
        }

        StringBuilder q = new StringBuilder("");
        q.append(" SELECT " + CAMPOS + " ");
        q.append("   FROM " + TABLA + " ");
        q.append("  WHERE 1 = 1 ");

        List<Object> parametros = new ArrayList<Object>();

        if ( obj.getIdTrackPoint() != null )
        {
            q.append("   AND id_track_point = ? ");
            parametros.add( obj.getIdTrackPoint() );
        }
        if ( obj.getIdTrack() != null )
        {
            q.append("   AND id_track = ? ");
            parametros.add( obj.getIdTrack() );
        }
        if ( obj.getTsp() != null )
        {
            q.append("   AND tsp = ? ");
            parametros.add( obj.getTsp() );
        }
        if ( obj.getLat() != null )
        {
            q.append("   AND lat = ? ");
            parametros.add( obj.getLat() );
        }
        if ( obj.getLon() != null )
        {
            q.append("   AND lon = ? ");
            parametros.add( obj.getLon() );
        }
        if ( obj.getSog() != null )
        {
            q.append("   AND sog = ? ");
            parametros.add( obj.getSog() );
        }
        if ( obj.getCog() != null )
        {
            q.append("   AND cog = ? ");
            parametros.add( obj.getCog() );
        }
        Object[] p = parametros.toArray();

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query( q.toString(), p, new BeanPropertyRowMapper<TrackpointPOJO>( TrackpointPOJO.class ) );
    }

    @Override
    protected String getTable() {
        return TABLA;
    }

    @Override
    protected String getFields() {
        return CAMPOS;
    }

    @Override
    protected String getInsertQuestionMarks() {
        return CAMPOSINSERT;
    }
}