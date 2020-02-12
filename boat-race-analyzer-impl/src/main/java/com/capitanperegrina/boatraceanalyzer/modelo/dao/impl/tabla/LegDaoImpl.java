package com.capitanperegrina.boatraceanalyzer.modelo.dao.impl.tabla;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.LegDao;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.LegPOJO;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.GenericDao;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla.LegRowMapper;
import com.capitanperegrina.boatraceanalyzer.util.sql.QueryUtils;
/**
 * Objeto de acceso a datos para la tabla <code>leg<code>
 */
@Repository
@Transactional(propagation=Propagation.SUPPORTS)
public class LegDaoImpl extends GenericDao implements LegDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(LegDaoImpl.class);

    public static final String WHERE_BY_KEY = " id_leg = ?  ";

    public static final String ENTITY = LegPOJO.class.getName();
    public static final String TABLE = "leg";
    public static final String FIELDS_ALL = "id_leg, id_race, date, name ";
    public static final String FIELDS_INSERT = " ?, ?, ?, ? ";
    public static final String FIELDS_UPDATE = " id_race = ?, date = ?, name = ? ";
    public static final String FIELDS_PRIMARY_KEY = " id_leg = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Object[] toParamsAll( LegPOJO obj ) {
        return ArrayUtils.addAll( toParamsKey( obj ) , toParamsRest( obj ) );
    }

    private static Object[] toParamsUpdate( LegPOJO obj ) {
        return ArrayUtils.addAll( toParamsRest( obj ), toParamsKey( obj ) );
    }

    private static Object[] toParamsKey( LegPOJO obj ) {
        return new Object[] {
            obj.getIdLeg()
         };
    }

    private static Object[] toParamsRest( LegPOJO obj ) {
        return new Object[] {
            obj.getIdRace(),
            obj.getDate(),
            obj.getName()
        };
    }

    @Override
	public void crea( LegPOJO obj ) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsAll(obj);
        if ( obj.getIdLeg() == null ) {
        	q.append(StringUtils.replaceOnce(StringUtils.replaceOnce(generateInsertQuery(), "?, ", ""), "id_leg, ",""));	
        } else {
        	q.append(generateInsertQuery());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
	public boolean existe ( LegPOJO obj ) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsKey(obj);
        q.append(generateExistsQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
    }

    @Override
	public LegPOJO busca( LegPOJO obj ) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsKey(obj);
        q.append(generateSelectQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.queryForObject(q.toString(), p, new LegRowMapper());
    }

    @Override
	public void actualiza( LegPOJO obj ) {
		StringBuilder q = new StringBuilder();
		Object[] p = toParamsUpdate(obj);
		q.append(generateUpdateQuery());
		if (LOGGER.isTraceEnabled()) {
		    LOGGER.trace(QueryUtils.pintaQuery(q, p));
		}
		this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
	public void borra( LegPOJO obj ) {
		StringBuilder q = new StringBuilder();
		Object[] p = toParamsKey(obj);
		q.append(generateDeleteQuery());
		if (LOGGER.isTraceEnabled()) {
		    LOGGER.trace(QueryUtils.pintaQuery(q, p));
		}
		this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
	public List<LegPOJO> buscaTodos() {
        StringBuilder q = new StringBuilder();
        Object[] p = {};
        q.append(generateSelectQuery("1=1"));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), new LegRowMapper());
    }

    @Override
	public List<LegPOJO> buscaVarios( LegPOJO obj ) {
        if ( obj == null ) {
            return buscaTodos();
        }

        StringBuilder cond = new StringBuilder(" 1=1 ");
        List<Object> parametros = new ArrayList<>();

        if ( obj.getIdLeg() != null ) {
            cond.append( "   AND id_leg = ? " );
            parametros.add( obj.getIdLeg() );
        }
        if ( obj.getIdRace() != null ) {
            cond.append( "   AND id_race = ? " );
            parametros.add( obj.getIdRace() );
        }
        if ( obj.getDate() != null ) {
            cond.append( "   AND date = ? " );
            parametros.add( obj.getDate() );
        }
        if ( obj.getName() != null ) {
            cond.append( "   AND name = ? " );
            parametros.add( obj.getName() );
        }

        Object[] p = parametros.toArray();
        StringBuilder q = new StringBuilder();
        q.append(generateSelectQuery( cond.toString() ));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), p, new LegRowMapper());
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