package com.capitanperegrina.boatraceanalyzer.modelo.dao.impl.tabla;

import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.BoardRepository;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoardEntity;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla.BoardRowMapper;
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
 * Objeto de acceso a datos para la tabla <code>board<code>
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class BoardRepositoryImpl extends MySQLRepository implements BoardRepository {

    public static final String WHERE_BY_KEY = " id_board = ?  ";

    public static final String ENTITY = BoardEntity.class.getName();

    public static final String TABLE = "board";

    public static final String FIELDS_ALL = "id_board, id_track, ini, date_ini, fin, date_fin ";

    public static final String FIELDS_INSERT = " ?, ?, ?, ?, ?, ? ";

    public static final String FIELDS_UPDATE = " id_track = ?, ini = ?, date_ini = ?, fin = ?, date_fin = ? ";

    public static final String FIELDS_PRIMARY_KEY = " id_board = ? ";

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Object[] toParamsAll(final BoardEntity obj) {
        return ArrayUtils.addAll(toParamsKey(obj), toParamsRest(obj));
    }

    private static Object[] toParamsUpdate(final BoardEntity obj) {
        return ArrayUtils.addAll(toParamsRest(obj), toParamsKey(obj));
    }

    private static Object[] toParamsKey(final BoardEntity obj) {
        return new Object[]{
                obj.getIdBoard()
        };
    }

    private static Object[] toParamsRest(final BoardEntity obj) {
        return new Object[]{
                obj.getIdTrack(),
                obj.getIni(),
                obj.getDateIni(),
                obj.getFin(),
                obj.getDateFin()
        };
    }

    @Override
    public void crea(final BoardEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsAll(obj);
        q.append(this.generateInsertQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public boolean existe(final BoardEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateExistsQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
    }

    @Override
    public BoardEntity busca(final BoardEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateSelectQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.queryForObject(q.toString(), p, new BoardRowMapper());
    }

    @Override
    public void actualiza(final BoardEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsUpdate(obj);
        q.append(this.generateUpdateQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public void borra(final BoardEntity obj) {
        final StringBuilder q = new StringBuilder();
        final Object[] p = toParamsKey(obj);
        q.append(this.generateDeleteQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
    public List<BoardEntity> buscaTodos() {
        final StringBuilder q = new StringBuilder();
        final Object[] p = {};
        q.append(this.generateSelectQuery("1=1"));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.query(q.toString(), new BoardRowMapper());
    }

    @Override
    public List<BoardEntity> buscaVarios(final BoardEntity obj) {
        if (obj == null) {
            return this.buscaTodos();
        }

        final StringBuilder cond = new StringBuilder(" 1=1 ");
        final List<Object> parametros = new ArrayList<>();

        if (obj.getIdBoard() != null) {
            cond.append("   AND id_board = ? ");
            parametros.add(obj.getIdBoard());
        }
        if (obj.getIdTrack() != null) {
            cond.append("   AND id_track = ? ");
            parametros.add(obj.getIdTrack());
        }
        if (obj.getIni() != null) {
            cond.append("   AND ini = ? ");
            parametros.add(obj.getIni());
        }
        if (obj.getDateIni() != null) {
            cond.append("   AND date_ini = ? ");
            parametros.add(obj.getDateIni());
        }
        if (obj.getFin() != null) {
            cond.append("   AND fin = ? ");
            parametros.add(obj.getFin());
        }
        if (obj.getDateFin() != null) {
            cond.append("   AND date_fin = ? ");
            parametros.add(obj.getDateFin());
        }

        final Object[] p = parametros.toArray();
        final StringBuilder q = new StringBuilder();
        q.append(this.generateSelectQuery(cond.toString()));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.queryLog(this.engine, q, p));
        }
        return this.jdbcTemplate.query(q.toString(), p, new BoardRowMapper());
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