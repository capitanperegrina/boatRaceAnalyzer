package com.capitanperegrina.boatraceanalyzer.modelo.dao.impl.tabla;

import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.BoatRepository;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoatEntity;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla.BoatRowMapper;
import com.capitanperegrina.utils.sql.QueryUtils;
import com.capitanperegrina.utils.sql.enginerepositories.MySQLRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
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
 * Objeto de acceso a datos para la tabla <code>boat<code>
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class BoatRepositoryImpl extends MySQLRepository implements BoatRepository {

    public static final String WHERE_BY_KEY = " id_boat = ?  ";

    public static final String ENTITY = BoatEntity.class.getName();

    public static final String TABLE = "boat";

    public static final String FIELDS_ALL = "id_boat, name ";

    public static final String FIELDS_INSERT = " ?, ? ";

    public static final String FIELDS_UPDATE = " name = ? ";

    public static final String FIELDS_PRIMARY_KEY = " id_boat = ? ";

    private static final Logger LOGGER = LoggerFactory.getLogger(BoatRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Object[] toParamsAll(final BoatEntity obj) {
        return ArrayUtils.addAll(toParamsKey(obj), toParamsRest(obj));
    }

    private static Object[] toParamsUpdate(final BoatEntity obj) {
        return ArrayUtils.addAll(toParamsRest(obj), toParamsKey(obj));
    }

    private static Object[] toParamsKey(final BoatEntity obj) {
        return new Object[]{
                obj.getIdBoat()
        };
    }

    private static Object[] toParamsRest(final BoatEntity obj) {
        return new Object[]{
                obj.getName()
        };
    }

    @Override
    public void crea(final BoatEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsAll(obj);
        if (obj.getIdBoat() == null) {
            q.append(StringUtils.replaceOnce(StringUtils.replaceOnce(this.generateInsertQuery(), "?, ", ""), "id_boat, ", ""));
        } else {
            q.append(this.generateInsertQuery());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public boolean existe(final BoatEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateExistsQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
    }

    @Override
    public BoatEntity busca(final BoatEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateSelectQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.queryForObject(q.toString(), p, new BoatRowMapper());
    }

    @Override
    public void actualiza(final BoatEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsUpdate(obj);
        q.append(this.generateUpdateQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public void borra(final BoatEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateDeleteQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public List<BoatEntity> buscaTodos() {
        final StringBuilder q = new StringBuilder();
        final Object[] p = {};
        q.append(this.generateSelectQuery("1=1"));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.query(q.toString(), new BoatRowMapper());
    }

    @Override
    public List<BoatEntity> buscaVarios(final BoatEntity obj) {
        if (obj == null) {
            return this.buscaTodos();
        }

        final StringBuilder cond = new StringBuilder(" 1=1 ");
        final List<Object> parametros = new ArrayList<>();

        if (obj.getIdBoat() != null) {
            cond.append("   AND id_boat = ? ");
            parametros.add(obj.getIdBoat());
        }
        if (obj.getName() != null) {
            cond.append("   AND name = ? ");
            parametros.add(obj.getName());
        }

        final Object[] p = parametros.toArray();
        final StringBuilder q = new StringBuilder();
        q.append(this.generateSelectQuery(cond.toString()));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, cond, p));
        }
        return this.jdbcTemplate.query(cond.toString(), p, new BoatRowMapper());
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
    public List<BoatEntity> findBoatsInRace(final Integer idRace) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = {idRace};
        final StringBuilder condition = new StringBuilder();
        condition.append("id_boat IN ( ");
        condition.append("SELECT id_boat ");
        condition.append("FROM track t, leg l ");
        condition.append("WHERE t.id_leg = l.id_leg ");
        condition.append("AND l.id_race = ? )");
        q.append(this.generateSelectQuery(condition.toString()));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.query(q.toString(), p, new BoatRowMapper());
    }
}