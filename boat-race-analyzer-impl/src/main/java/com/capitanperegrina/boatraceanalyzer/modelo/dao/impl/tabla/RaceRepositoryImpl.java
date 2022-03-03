package com.capitanperegrina.boatraceanalyzer.modelo.dao.impl.tabla;

import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.RaceRepository;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.RaceEntity;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla.RaceRowMapper;
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
 * Objeto de acceso a datos para la tabla <code>race<code>
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class RaceRepositoryImpl extends MySQLRepository implements RaceRepository {

    public static final String WHERE_BY_KEY = " id_race = ?  ";

    public static final String ENTITY = RaceEntity.class.getName();

    public static final String TABLE = "race";

    public static final String FIELDS_ALL = "id_race, name ";

    public static final String FIELDS_INSERT = " ?, ? ";

    public static final String FIELDS_UPDATE = " name = ? ";

    public static final String FIELDS_PRIMARY_KEY = " id_race = ? ";

    private static final Logger LOGGER = LoggerFactory.getLogger(RaceRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Object[] toParamsAll(final RaceEntity obj) {
        return ArrayUtils.addAll(toParamsKey(obj), toParamsRest(obj));
    }

    private static Object[] toParamsUpdate(final RaceEntity obj) {
        return ArrayUtils.addAll(toParamsRest(obj), toParamsKey(obj));
    }

    private static Object[] toParamsKey(final RaceEntity obj) {
        return new Object[]{
                obj.getIdRace()
        };
    }

    private static Object[] toParamsRest(final RaceEntity obj) {
        return new Object[]{
                obj.getName()
        };
    }

    @Override
    public void crea(final RaceEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsAll(obj);
        if (obj.getIdRace() == null) {
            q.append(StringUtils.replaceOnce(StringUtils.replaceOnce(this.generateInsertQuery(), "?, ", ""), "id_race, ", ""));
        } else {
            q.append(this.generateInsertQuery());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public boolean existe(final RaceEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateExistsQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
    }

    @Override
    public RaceEntity busca(final RaceEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateSelectQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.queryForObject(q.toString(), p, new RaceRowMapper());
    }

    @Override
    public void actualiza(final RaceEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsUpdate(obj);
        q.append(this.generateUpdateQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public void borra(final RaceEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateDeleteQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public List<RaceEntity> buscaTodos() {
        final StringBuilder q = new StringBuilder();
        final Object[] p = {};
        q.append(this.generateSelectQuery("1=1"));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.query(q.toString(), new RaceRowMapper());
    }

    @Override
    public List<RaceEntity> buscaVarios(final RaceEntity obj) {
        if (obj == null) {
            return this.buscaTodos();
        }

        final StringBuilder cond = new StringBuilder(" 1=1 ");
        final List<Object> parametros = new ArrayList<>();

        if (obj.getIdRace() != null) {
            cond.append("   AND id_race = ? ");
            parametros.add(obj.getIdRace());
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
        return this.jdbcTemplate.query(q.toString(), p, new RaceRowMapper());
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