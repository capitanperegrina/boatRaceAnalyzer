package com.capitanperegrina.boatraceanalyzer.modelo.dao.impl.tabla;

import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.RouteRepository;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.RouteEntity;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla.RouteRowMapper;
import com.capitanperegrina.utils.sql.QueryUtils;
import com.capitanperegrina.utils.sql.enginerepositories.MySQLRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto de acceso a datos para la tabla <code>route<code>
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class RouteRepositoryImpl extends MySQLRepository implements RouteRepository {

    public static final String WHERE_BY_KEY = " id_route = ?  ";

    public static final String ENTITY = RouteEntity.class.getName();

    public static final String TABLE = "route";

    public static final String FIELDS_ALL = "id_route, id_leg, seq, lat1, lon1, lat2, lon2, kind, name, pass ";

    public static final String FIELDS_INSERT = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";

    public static final String FIELDS_UPDATE = " id_leg = ?, seq = ?, lat1 = ?, lon1 = ?, lat2 = ?, lon2 = ?, kind = ?, name = ?, pass = ? ";

    public static final String FIELDS_PRIMARY_KEY = " id_route = ? ";

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Object[] toParamsAll(final RouteEntity obj) {
        return ArrayUtils.addAll(toParamsKey(obj), toParamsRest(obj));
    }

    private static Object[] toParamsUpdate(final RouteEntity obj) {
        return ArrayUtils.addAll(toParamsRest(obj), toParamsKey(obj));
    }

    private static Object[] toParamsKey(final RouteEntity obj) {
        return new Object[]{
                obj.getIdRoute()
        };
    }

    private static Object[] toParamsRest(final RouteEntity obj) {
        return new Object[]{
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
    public void crea(final RouteEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsAll(obj);
        q.append(this.generateInsertQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public boolean existe(final RouteEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateExistsQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
    }

    @Override
    public RouteEntity busca(final RouteEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateSelectQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.queryForObject(q.toString(), p, new RouteRowMapper());
    }

    @Override
    public void actualiza(final RouteEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsUpdate(obj);
        q.append(this.generateUpdateQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public void borra(final RouteEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateDeleteQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public List<RouteEntity> buscaTodos() {
        final StringBuilder q = new StringBuilder();
        final Object[] p = {};
        q.append(this.generateSelectQuery("1=1"));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.query(q.toString(), new RouteRowMapper());
    }

    @Override
    public List<RouteEntity> buscaVarios(final RouteEntity obj) {
        if (obj == null) {
            return this.buscaTodos();
        }

        final StringBuilder cond = new StringBuilder(" 1=1 ");
        final List<Object> parametros = new ArrayList<>();

        if (obj.getIdRoute() != null) {
            cond.append("   AND id_route = ? ");
            parametros.add(obj.getIdRoute());
        }
        if (obj.getIdLeg() != null) {
            cond.append("   AND id_leg = ? ");
            parametros.add(obj.getIdLeg());
        }
        if (obj.getSeq() != null) {
            cond.append("   AND seq = ? ");
            parametros.add(obj.getSeq());
        }
        if (obj.getLat1() != null) {
            cond.append("   AND lat1 = ? ");
            parametros.add(obj.getLat1());
        }
        if (obj.getLon1() != null) {
            cond.append("   AND lon1 = ? ");
            parametros.add(obj.getLon1());
        }
        if (obj.getLat2() != null) {
            cond.append("   AND lat2 = ? ");
            parametros.add(obj.getLat2());
        }
        if (obj.getLon2() != null) {
            cond.append("   AND lon2 = ? ");
            parametros.add(obj.getLon2());
        }
        if (obj.getKind() != null) {
            cond.append("   AND kind = ? ");
            parametros.add(obj.getKind());
        }
        if (obj.getName() != null) {
            cond.append("   AND name = ? ");
            parametros.add(obj.getName());
        }
        if (obj.getPass() != null) {
            cond.append("   AND pass = ? ");
            parametros.add(obj.getPass());
        }

        final Object[] p = parametros.toArray();
        final StringBuilder q = new StringBuilder();
        q.append(this.generateSelectQuery(cond.toString()));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
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
    public List<RouteEntity> findRouteElements(final Integer idLeg) {
        final Object[] p = {idLeg};
        final StringBuilder cond = new StringBuilder(" id_leg = ? AND seq IS NOT NULL ");

        final StringBuilder q = new StringBuilder();
        q.append(this.generateSelectQuery(cond.toString()));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.query(q.toString(), p, new RouteRowMapper());
    }

    @Override
    public List<RouteEntity> findDecorationElements(final Integer idLeg) {
        final Object[] p = {idLeg};
        final StringBuilder cond = new StringBuilder(" id_leg = ? AND seq IS NULL ");

        final StringBuilder q = new StringBuilder();
        q.append(this.generateSelectQuery(cond.toString()));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.query(q.toString(), p, new RouteRowMapper());
    }
}