package com.capitanperegrina.boatraceanalyzer.modelo.dao.impl.tabla;

import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.LegRepository;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.LegEntity;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla.LegRowMapper;
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
 * Objeto de acceso a datos para la tabla <code>leg<code>
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class LegRepositoryImpl extends MySQLRepository implements LegRepository {

    public static final String WHERE_BY_KEY = " id_leg = ?  ";

    public static final String ENTITY = LegEntity.class.getName();

    public static final String TABLE = "leg";

    public static final String FIELDS_ALL = "id_leg, id_race, date, name ";

    public static final String FIELDS_INSERT = " ?, ?, ?, ? ";

    public static final String FIELDS_UPDATE = " id_race = ?, date = ?, name = ? ";

    public static final String FIELDS_PRIMARY_KEY = " id_leg = ? ";

    private static final Logger LOGGER = LoggerFactory.getLogger(LegRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Object[] toParamsAll(final LegEntity obj) {
        return ArrayUtils.addAll(toParamsKey(obj), toParamsRest(obj));
    }

    private static Object[] toParamsUpdate(final LegEntity obj) {
        return ArrayUtils.addAll(toParamsRest(obj), toParamsKey(obj));
    }

    private static Object[] toParamsKey(final LegEntity obj) {
        return new Object[]{
                obj.getIdLeg()
        };
    }

    private static Object[] toParamsRest(final LegEntity obj) {
        return new Object[]{
                obj.getIdRace(),
                obj.getDate(),
                obj.getName()
        };
    }

    @Override
    public void crea(final LegEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsAll(obj);
        if (obj.getIdLeg() == null) {
            q.append(StringUtils.replaceOnce(StringUtils.replaceOnce(this.generateInsertQuery(), "?, ", ""), "id_leg, ", ""));
        } else {
            q.append(this.generateInsertQuery());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public boolean existe(final LegEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateExistsQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
    }

    @Override
    public LegEntity busca(final LegEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateSelectQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.queryForObject(q.toString(), p, new LegRowMapper());
    }

    @Override
    public void actualiza(final LegEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsUpdate(obj);
        q.append(this.generateUpdateQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public void borra(final LegEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateDeleteQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public List<LegEntity> buscaTodos() {
        final StringBuilder q = new StringBuilder();
        final Object[] p = {};
        q.append(this.generateSelectQuery("1=1"));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.query(q.toString(), new LegRowMapper());
    }

    @Override
    public List<LegEntity> buscaVarios(final LegEntity obj) {
        if (obj == null) {
            return this.buscaTodos();
        }

        final StringBuilder cond = new StringBuilder(" 1=1 ");
        final List<Object> parametros = new ArrayList<>();

        if (obj.getIdLeg() != null) {
            cond.append("   AND id_leg = ? ");
            parametros.add(obj.getIdLeg());
        }
        if (obj.getIdRace() != null) {
            cond.append("   AND id_race = ? ");
            parametros.add(obj.getIdRace());
        }
        if (obj.getDate() != null) {
            cond.append("   AND date = ? ");
            parametros.add(obj.getDate());
        }
        if (obj.getName() != null) {
            cond.append("   AND name = ? ");
            parametros.add(obj.getName());
        }

        final Object[] p = parametros.toArray();
        final StringBuilder q = new StringBuilder();
        q.append(this.generateSelectQuery(cond.toString()));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
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