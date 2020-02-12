package com.capitanperegrina.boatraceanalyzer.modelo.dao.impl.tabla;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.TrackDao;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackPOJO;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.GenericDao;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla.TrackRowMapper;
import com.capitanperegrina.boatraceanalyzer.util.sql.QueryUtils;
/**
 * Objeto de acceso a datos para la tabla <code>track<code>
 */
@Repository
@Transactional(propagation=Propagation.SUPPORTS)
public class TrackDaoImpl extends GenericDao implements TrackDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackDaoImpl.class);

    public static final String WHERE_BY_KEY = " id_track = ?  ";

    public static final String ENTITY = TrackPOJO.class.getName();
    public static final String TABLE = "track";
    public static final String FIELDS_ALL = "id_track, id_leg, id_boat, tsp_ini, tsp_end ";
    public static final String FIELDS_INSERT = " ?, ?, ?, ?, ? ";
    public static final String FIELDS_UPDATE = " id_leg = ?, id_boat = ?, tsp_ini = ?, tsp_end = ? ";
    public static final String FIELDS_PRIMARY_KEY = " id_track = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Object[] toParamsAll( TrackPOJO obj ) {
        return ArrayUtils.addAll( toParamsKey( obj ) , toParamsRest( obj ) );
    }

    private static Object[] toParamsUpdate( TrackPOJO obj ) {
        return ArrayUtils.addAll( toParamsRest( obj ), toParamsKey( obj ) );
    }

    private static Object[] toParamsKey( TrackPOJO obj ) {
        return new Object[] {
            obj.getIdTrack()
         };
    }

    private static Object[] toParamsRest( TrackPOJO obj ) {
        return new Object[] {
            obj.getIdLeg(),
            obj.getIdBoat(),
            obj.getTspIni(),
            obj.getTspEnd()
        };
    }

    @Override
	public void crea( TrackPOJO obj ) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsAll(obj);
        q.append(generateInsertQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
	public boolean existe ( TrackPOJO obj ) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsKey(obj);
        q.append(generateExistsQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
    }

    @Override
	public TrackPOJO busca( TrackPOJO obj ) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsKey(obj);
        q.append(generateSelectQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.queryForObject(q.toString(), p, new TrackRowMapper());
    }

    @Override
	public void actualiza( TrackPOJO obj ) {
		StringBuilder q = new StringBuilder();
		Object[] p = toParamsUpdate(obj);
		q.append(generateUpdateQuery());
		if (LOGGER.isTraceEnabled()) {
		    LOGGER.trace(QueryUtils.pintaQuery(q, p));
		}
		this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
	public void borra( TrackPOJO obj ) {
		StringBuilder q = new StringBuilder();
		Object[] p = toParamsKey(obj);
		q.append(generateDeleteQuery());
		if (LOGGER.isTraceEnabled()) {
		    LOGGER.trace(QueryUtils.pintaQuery(q, p));
		}
		this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
	public List<TrackPOJO> buscaTodos() {
        StringBuilder q = new StringBuilder();
        Object[] p = {};
        q.append(generateSelectQuery("1=1"));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), new TrackRowMapper());
    }

    @Override
	public List<TrackPOJO> buscaVarios( TrackPOJO obj ) {
        if ( obj == null ) {
            return buscaTodos();
        }

        StringBuilder cond = new StringBuilder(" 1=1 ");
        List<Object> parametros = new ArrayList<>();

        if ( obj.getIdTrack() != null ) {
            cond.append( "   AND id_track = ? " );
            parametros.add( obj.getIdTrack() );
        }
        if ( obj.getIdLeg() != null ) {
            cond.append( "   AND id_leg = ? " );
            parametros.add( obj.getIdLeg() );
        }
        if ( obj.getIdBoat() != null ) {
            cond.append( "   AND id_boat = ? " );
            parametros.add( obj.getIdBoat() );
        }
        if ( obj.getTspIni() != null ) {
            cond.append( "   AND tsp_ini = ? " );
            parametros.add( obj.getTspIni() );
        }
        if ( obj.getTspEnd() != null ) {
            cond.append( "   AND tsp_end = ? " );
            parametros.add( obj.getTspEnd() );
        }

        Object[] p = parametros.toArray();
        StringBuilder q = new StringBuilder();
        q.append(generateSelectQuery( cond.toString() ));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), p, new TrackRowMapper());
    }

    @Override
    protected String getTable() {
        return TABLE;
    }

    @Override
    protected String getFields() {
        return FIELDS_ALL;
    }

    @Override
    protected String getInsertQuestionMarks() {
        return FIELDS_INSERT;
    }

    @Override
    protected String getWhereByKey() {
        return FIELDS_PRIMARY_KEY;
    }

    @Override
    protected String getFieldsUpdate() {
       return FIELDS_UPDATE;
    }
}