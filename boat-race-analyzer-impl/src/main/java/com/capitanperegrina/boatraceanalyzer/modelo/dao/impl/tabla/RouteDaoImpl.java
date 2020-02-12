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

import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.RouteDao;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.RoutePOJO;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.GenericDao;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla.RouteRowMapper;
import com.capitanperegrina.boatraceanalyzer.util.sql.QueryUtils;
/**
 * Objeto de acceso a datos para la tabla <code>route<code>
 */
@Repository
@Transactional(propagation=Propagation.SUPPORTS)
public class RouteDaoImpl extends GenericDao implements RouteDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteDaoImpl.class);

    public static final String WHERE_BY_KEY = " id_route = ?  ";

    public static final String ENTITY = RoutePOJO.class.getName();
    public static final String TABLE = "route";
    public static final String FIELDS_ALL = "id_route, id_leg, seq, lat1, lon1, lat2, lon2, kind, name, pass ";
    public static final String FIELDS_INSERT = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";
    public static final String FIELDS_UPDATE = " id_leg = ?, seq = ?, lat1 = ?, lon1 = ?, lat2 = ?, lon2 = ?, kind = ?, name = ?, pass = ? ";
    public static final String FIELDS_PRIMARY_KEY = " id_route = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Object[] toParamsAll( RoutePOJO obj ) {
        return ArrayUtils.addAll( toParamsKey( obj ) , toParamsRest( obj ) );
    }

    private static Object[] toParamsUpdate( RoutePOJO obj ) {
        return ArrayUtils.addAll( toParamsRest( obj ), toParamsKey( obj ) );
    }

    private static Object[] toParamsKey( RoutePOJO obj ) {
        return new Object[] {
            obj.getIdRoute()
         };
    }

    private static Object[] toParamsRest( RoutePOJO obj ) {
        return new Object[] {
            obj.getIdLeg(),
            obj.getSeq(),
            obj.getLat1(),
            obj.getLon1(),
            obj.getLat2(),
            obj.getLon2(),
            obj.getKind(),
            obj.getName(),
            obj.getPass()
        };
    }

    @Override
	public void crea( RoutePOJO obj ) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsAll(obj);
        q.append(generateInsertQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
	public boolean existe ( RoutePOJO obj ) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsKey(obj);
        q.append(generateExistsQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
    }

    @Override
	public RoutePOJO busca( RoutePOJO obj ) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsKey(obj);
        q.append(generateSelectQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.queryForObject(q.toString(), p, new RouteRowMapper());
    }

    @Override
	public void actualiza( RoutePOJO obj ) {
		StringBuilder q = new StringBuilder();
		Object[] p = toParamsUpdate(obj);
		q.append(generateUpdateQuery());
		if (LOGGER.isTraceEnabled()) {
		    LOGGER.trace(QueryUtils.pintaQuery(q, p));
		}
		this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
	public void borra( RoutePOJO obj ) {
		StringBuilder q = new StringBuilder();
		Object[] p = toParamsKey(obj);
		q.append(generateDeleteQuery());
		if (LOGGER.isTraceEnabled()) {
		    LOGGER.trace(QueryUtils.pintaQuery(q, p));
		}
		this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
	public List<RoutePOJO> buscaTodos() {
        StringBuilder q = new StringBuilder();
        Object[] p = {};
        q.append(generateSelectQuery("1=1"));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), new RouteRowMapper());
    }

    @Override
	public List<RoutePOJO> buscaVarios( RoutePOJO obj ) {
        if ( obj == null ) {
            return buscaTodos();
        }

        StringBuilder cond = new StringBuilder(" 1=1 ");
        List<Object> parametros = new ArrayList<>();

        if ( obj.getIdRoute() != null ) {
            cond.append( "   AND id_route = ? " );
            parametros.add( obj.getIdRoute() );
        }
        if ( obj.getIdLeg() != null ) {
            cond.append( "   AND id_leg = ? " );
            parametros.add( obj.getIdLeg() );
        }
        if ( obj.getSeq() != null ) {
            cond.append( "   AND seq = ? " );
            parametros.add( obj.getSeq() );
        }
        if ( obj.getLat1() != null ) {
            cond.append( "   AND lat1 = ? " );
            parametros.add( obj.getLat1() );
        }
        if ( obj.getLon1() != null ) {
            cond.append( "   AND lon1 = ? " );
            parametros.add( obj.getLon1() );
        }
        if ( obj.getLat2() != null ) {
            cond.append( "   AND lat2 = ? " );
            parametros.add( obj.getLat2() );
        }
        if ( obj.getLon2() != null ) {
            cond.append( "   AND lon2 = ? " );
            parametros.add( obj.getLon2() );
        }
        if ( obj.getKind() != null ) {
            cond.append( "   AND kind = ? " );
            parametros.add( obj.getKind() );
        }
        if ( obj.getName() != null ) {
            cond.append( "   AND name = ? " );
            parametros.add( obj.getName() );
        }
        if ( obj.getPass() != null ) {
            cond.append( "   AND pass = ? " );
            parametros.add( obj.getPass() );
        }

        Object[] p = parametros.toArray();
        StringBuilder q = new StringBuilder();
        q.append(generateSelectQuery( cond.toString() ));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), p, new RouteRowMapper());
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
    
	@Override
	public List<RoutePOJO> findRouteElements(Integer idLeg) {
        Object[] p = { idLeg };
        StringBuilder cond = new StringBuilder(" id_leg = ? AND seq IS NOT NULL ");

        StringBuilder q = new StringBuilder();
        q.append(generateSelectQuery( cond.toString() ));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), p, new RouteRowMapper());
	}
	
	@Override
	public List<RoutePOJO> findDecorationElements(Integer idLeg) {
        Object[] p = { idLeg };
        StringBuilder cond = new StringBuilder(" id_leg = ? AND seq IS NULL ");

        StringBuilder q = new StringBuilder();
        q.append(generateSelectQuery( cond.toString() ));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), p, new RouteRowMapper());
	}
}