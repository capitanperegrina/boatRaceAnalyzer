package com.capitanperegrina.boatraceanalyzer.modelo.dao.impl.tabla;

import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.TrackRepository;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackEntity;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla.TrackRowMapper;
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
 * Objeto de acceso a datos para la tabla <code>track<code>
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class TrackRepositoryImpl extends MySQLRepository implements TrackRepository {

    public static final String WHERE_BY_KEY = " id_track = ?  ";

    public static final String ENTITY = TrackEntity.class.getName();

    public static final String TABLE = "track";

    public static final String FIELDS_ALL = "id_track, id_leg, id_boat, tsp_ini, tsp_end ";

    public static final String FIELDS_INSERT = " ?, ?, ?, ?, ? ";

    public static final String FIELDS_UPDATE = " id_leg = ?, id_boat = ?, tsp_ini = ?, tsp_end = ? ";

    public static final String FIELDS_PRIMARY_KEY = " id_track = ? ";

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Object[] toParamsAll(final TrackEntity obj) {
        return ArrayUtils.addAll(toParamsKey(obj), toParamsRest(obj));
    }

    private static Object[] toParamsUpdate(final TrackEntity obj) {
        return ArrayUtils.addAll(toParamsRest(obj), toParamsKey(obj));
    }

    private static Object[] toParamsKey(final TrackEntity obj) {
        return new Object[]{
                obj.getIdTrack()
        };
    }

    private static Object[] toParamsRest(final TrackEntity obj) {
        return new Object[]{
                obj.getIdLeg(),
                obj.getIdBoat(),
                obj.getTspIni(),
                obj.getTspEnd()
        };
    }

    @Override
    public void crea(final TrackEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsAll(obj);
        q.append(this.generateInsertQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public boolean existe(final TrackEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateExistsQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
    }

    @Override
    public TrackEntity busca(final TrackEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateSelectQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.queryForObject(q.toString(), p, new TrackRowMapper());
    }

    @Override
    public void actualiza(final TrackEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsUpdate(obj);
        q.append(this.generateUpdateQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public void borra(final TrackEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateDeleteQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public List<TrackEntity> buscaTodos() {
        final StringBuilder q = new StringBuilder();
        final Object[] p = {};
        q.append(this.generateSelectQuery("1=1"));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.query(q.toString(), new TrackRowMapper());
    }

    @Override
    public List<TrackEntity> buscaVarios(final TrackEntity obj) {
        if (obj == null) {
            return this.buscaTodos();
        }

        final StringBuilder cond = new StringBuilder(" 1=1 ");
        final List<Object> parametros = new ArrayList<>();

        if (obj.getIdTrack() != null) {
            cond.append("   AND id_track = ? ");
            parametros.add(obj.getIdTrack());
        }
        if (obj.getIdLeg() != null) {
            cond.append("   AND id_leg = ? ");
            parametros.add(obj.getIdLeg());
        }
        if (obj.getIdBoat() != null) {
            cond.append("   AND id_boat = ? ");
            parametros.add(obj.getIdBoat());
        }
        if (obj.getTspIni() != null) {
            cond.append("   AND tsp_ini = ? ");
            parametros.add(obj.getTspIni());
        }
        if (obj.getTspEnd() != null) {
            cond.append("   AND tsp_end = ? ");
            parametros.add(obj.getTspEnd());
        }

        final Object[] p = parametros.toArray();
        final StringBuilder q = new StringBuilder();
        q.append(this.generateSelectQuery(cond.toString()));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
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