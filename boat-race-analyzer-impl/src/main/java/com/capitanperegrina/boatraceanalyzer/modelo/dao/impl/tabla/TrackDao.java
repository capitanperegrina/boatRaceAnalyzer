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

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackPOJO;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.GenericDao;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla.TrackRowMapper;
import com.capitanperegrina.boatraceanalyzer.util.sql.QueryUtils;

/**
 * Objeto de acceso a datos para la tabla <code>track<code>
 */
@Repository("trackDao")
@Transactional(value="boatRaceAnalyzer",propagation=Propagation.SUPPORTS)
public class TrackDao extends GenericDao
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackDao.class);
    
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
    public static final String ENTIDAD = TrackPOJO.class.getName();
    public static final String TABLA = "track";
    public static final String CAMPOS = "id_track, id_leg, id_boat ";
    public static final String CAMPOSINSERT = " ?, ?, ? ";
    public static final String CAMPOSUPDATE = " id_leg = ?, id_boat = ? ";
    public static final String CAMPOSPKUPDATE = " id_track = ? ";

    private static Object[] toParamsTodos( TrackPOJO obj ) {
        return ArrayUtils.addAll( toParamsClave( obj ) , toParamsResto( obj ) );
    }

    private static Object[] toParamsUpdate( TrackPOJO obj ) {
        return ArrayUtils.addAll( toParamsResto( obj ), toParamsClave( obj ) );
    }

    private static Object[] toParamsClave( TrackPOJO obj ) {
        return new Object[] {
                obj.getIdTrack()
        };
    }

    private static Object[] toParamsResto( TrackPOJO obj ) {
        return new Object[] {
                obj.getIdLeg(),
                obj.getIdBoat()
        };
    }

    /**
     * Método que inserta un registro en la tabla <code>track</code> de la base de datos.
     * @param obj Objeto POJO con la información a insertar en la tabla <code>track</code> de la base de datos.
     * @throws DuplicateKeyException si ya existe un registro en la tabla <code>track</code> de base de datos con igual clave principal.
     */
    public void crea( TrackPOJO obj )
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsTodos( obj );
        q.append(generateInsertQuery());

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        this.jdbcTemplate.update( q.toString(), p );
    }

    /**
     * Método que comprueba si existe un registro en la tabla <code>track</code> de la base de datos a partir de su clave principal.
     * @param obj Objeto del que se desea comprobar su existencia en la tabla <code>track</code> de la base de datos.
     * @return true si existe un registro en la tabla <code>track</code> de la base de datos con la misma clave principal, false si no es así.
     */
    public boolean existe ( TrackPOJO obj )
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsClave( obj );
        q.append("SELECT COUNT(*) FROM ").append(TABLA).append(" WHERE id_track = ? ");

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return ( this.jdbcTemplate.queryForObject( q.toString(), p, Integer.class ) ) > 0;
    }

    /**
     * Método que recupera de la base de datos un registro de la tabla <code>track</code> y lo devuelve en el objeto TrackPOJO.
     * @param obj Objeto TrackPOJO con los campos de la clave principal informados.
     * @return El objeto TrackPOJO con la información recuperada de la base de datos.
     * @throws EmptyResultDataAccessException si no se encuentra el resitro en la tabla <code>track</code>.
     */
    public TrackPOJO busca( TrackPOJO obj )
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsClave( obj );
        q.append(" SELECT ").append(CAMPOS).append(" ");
        q.append("   FROM ").append(TABLA).append(" ");
        q.append("  WHERE  id_track = ?   ");

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.queryForObject( q.toString(), p, new TrackRowMapper() );
    }

    /**
     * Método que actualiza un registro de la tabla <code>track</code> de la base de datos.
     * @param obj Objeto TrackPOJO que se quiere actualizar en la base de datos.
     * @throws EmptyResultDataAccessException Si no se encuentra el registro que se desea actualizar en la tabla <code>track</code>.
     * @throws DuplicateKeyException si se encuentra más de un registro a actualizar en la tabla la tabla <code>track</code>.
     */
    public void actualiza( TrackPOJO obj )
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsUpdate( obj );
        q.append(" UPDATE ").append(TABLA).append(" ");
        q.append("    SET ").append(CAMPOSUPDATE).append(" ");
        q.append("  WHERE ").append(CAMPOSPKUPDATE).append(" ");

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        this.jdbcTemplate.update( q.toString(), p );
    }

    /**
     * Método que elimina físicamente un registro de la tabla <code>track</code> de la base de datos a partir de los valores de su clave principal.
     * @param obj Objeto TrackPOJO con los campos de la clave principal informados.
     * @throws EmptyResultDataAccessException Si no se encuentra el registro que se desea eliminar en la tabla <code>track</code>.
     */
    public void borra( TrackPOJO obj )
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsClave( obj );
        q.append(" DELETE FROM ").append(TABLA).append(" ");
        q.append("  WHERE  id_track = ?   ");

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        this.jdbcTemplate.update( q.toString(), p );
    }

    /**
     * Método devuelve una lista con todos los registros de la tabla <code>track</code> de la base de datos.
     * @return Una lista con todos los elementos de la tabla <code>track</code>.
     */
    public List<TrackPOJO> buscaTodos()
    {
        StringBuilder q = new StringBuilder("");
        Object[] p = {};
        q.append(" SELECT ").append(CAMPOS).append(" ");
        q.append("   FROM ").append(TABLA).append(" ");

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query( q.toString(), new BeanPropertyRowMapper<TrackPOJO>( TrackPOJO.class ) );
    }

    /**
     * Método devuelve una lista con todos los registros de la tabla <code>track</code> de la base de datos a partir de los campos de de un POJO.
     * @param obj Objeto TrackPOJO con los campos por los que se desea buscar informados.
     * @return Una lista con todos los elementos de la tabla <code>track</code> que cumplan los criterios de búsqueda.
     */
    public List<TrackPOJO> buscaVarios( TrackPOJO obj )
    {
        if ( obj == null )
        {
            return buscaTodos();
        }

        StringBuilder q = new StringBuilder("");
        q.append(" SELECT ").append(CAMPOS).append(" ");
        q.append("   FROM ").append(TABLA).append(" ");
        q.append("  WHERE 1 = 1 ");

        List<Object> parametros = new ArrayList<Object>();

        if ( obj.getIdTrack() != null )
        {
            q.append("   AND id_track = ? ");
            parametros.add( obj.getIdTrack() );
        }
        if ( obj.getIdLeg() != null )
        {
            q.append("   AND id_leg = ? ");
            parametros.add( obj.getIdLeg() );
        }
        if ( obj.getIdBoat() != null )
        {
            q.append("   AND id_boat = ? ");
            parametros.add( obj.getIdBoat() );
        }
        Object[] p = parametros.toArray();

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query( q.toString(), p, new BeanPropertyRowMapper<TrackPOJO>( TrackPOJO.class ) );
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