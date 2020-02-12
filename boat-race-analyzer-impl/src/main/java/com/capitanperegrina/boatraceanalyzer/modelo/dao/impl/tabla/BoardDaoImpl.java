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

import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.BoardDao;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoardPOJO;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.GenericDao;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla.BoardRowMapper;
import com.capitanperegrina.boatraceanalyzer.util.sql.QueryUtils;
/**
 * Objeto de acceso a datos para la tabla <code>board<code>
 */
@Repository
@Transactional(propagation=Propagation.SUPPORTS)
public class BoardDaoImpl extends GenericDao implements BoardDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardDaoImpl.class);

    public static final String WHERE_BY_KEY = " id_board = ?  ";

    public static final String ENTITY = BoardPOJO.class.getName();
    public static final String TABLE = "board";
    public static final String FIELDS_ALL = "id_board, id_track, ini, date_ini, fin, date_fin ";
    public static final String FIELDS_INSERT = " ?, ?, ?, ?, ?, ? ";
    public static final String FIELDS_UPDATE = " id_track = ?, ini = ?, date_ini = ?, fin = ?, date_fin = ? ";
    public static final String FIELDS_PRIMARY_KEY = " id_board = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Object[] toParamsAll( BoardPOJO obj ) {
        return ArrayUtils.addAll( toParamsKey( obj ) , toParamsRest( obj ) );
    }

    private static Object[] toParamsUpdate( BoardPOJO obj ) {
        return ArrayUtils.addAll( toParamsRest( obj ), toParamsKey( obj ) );
    }

    private static Object[] toParamsKey( BoardPOJO obj ) {
        return new Object[] {
            obj.getIdBoard()
         };
    }

    private static Object[] toParamsRest( BoardPOJO obj ) {
        return new Object[] {
            obj.getIdTrack(),
            obj.getIni(),
            obj.getDateIni(),
            obj.getFin(),
            obj.getDateFin()
        };
    }

    @Override
	public void crea( BoardPOJO obj ) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsAll(obj);
        q.append(generateInsertQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
	public boolean existe ( BoardPOJO obj ) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsKey(obj);
        q.append(generateExistsQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
    }

    @Override
	public BoardPOJO busca( BoardPOJO obj ) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsKey(obj);
        q.append(generateSelectQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.queryForObject(q.toString(), p, new BoardRowMapper());
    }

    @Override
	public void actualiza( BoardPOJO obj ) {
		StringBuilder q = new StringBuilder();
		Object[] p = toParamsUpdate(obj);
		q.append(generateUpdateQuery());
		if (LOGGER.isTraceEnabled()) {
		    LOGGER.trace(QueryUtils.pintaQuery(q, p));
		}
		this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
	public void borra( BoardPOJO obj ) {
		StringBuilder q = new StringBuilder();
		Object[] p = toParamsKey(obj);
		q.append(generateDeleteQuery());
		if (LOGGER.isTraceEnabled()) {
		    LOGGER.trace(QueryUtils.pintaQuery(q, p));
		}
		this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
	public List<BoardPOJO> buscaTodos() {
        StringBuilder q = new StringBuilder();
        Object[] p = {};
        q.append(generateSelectQuery("1=1"));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), new BoardRowMapper());
    }

    @Override
	public List<BoardPOJO> buscaVarios( BoardPOJO obj ) {
        if ( obj == null ) {
            return buscaTodos();
        }

        StringBuilder cond = new StringBuilder(" 1=1 ");
        List<Object> parametros = new ArrayList<>();

        if ( obj.getIdBoard() != null ) {
            cond.append( "   AND id_board = ? " );
            parametros.add( obj.getIdBoard() );
        }
        if ( obj.getIdTrack() != null ) {
            cond.append( "   AND id_track = ? " );
            parametros.add( obj.getIdTrack() );
        }
        if ( obj.getIni() != null ) {
            cond.append( "   AND ini = ? " );
            parametros.add( obj.getIni() );
        }
        if ( obj.getDateIni() != null ) {
            cond.append( "   AND date_ini = ? " );
            parametros.add( obj.getDateIni() );
        }
        if ( obj.getFin() != null ) {
            cond.append( "   AND fin = ? " );
            parametros.add( obj.getFin() );
        }
        if ( obj.getDateFin() != null ) {
            cond.append( "   AND date_fin = ? " );
            parametros.add( obj.getDateFin() );
        }

        Object[] p = parametros.toArray();
        StringBuilder q = new StringBuilder();
        q.append(generateSelectQuery( cond.toString() ));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
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