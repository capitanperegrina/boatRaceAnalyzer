package com.capitanperegrina.boatraceanalyzer.modelo.dao.impl.tabla;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.TrackpointDao;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackpointPOJO;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.GenericDao;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla.TrackpointRowMapper;
import com.capitanperegrina.boatraceanalyzer.util.sql.QueryUtils;
import com.capitanperegrina.boatraceanalyzer.util.sql.ResultSetUtils;
import com.capitanperegrina.geo.elements.Line;
import com.capitanperegrina.geo.elements.Point;
import com.capitanperegrina.geo.elements.impl.LineImpl;
import com.capitanperegrina.geo.elements.impl.PointImpl;
/**
 * Objeto de acceso a datos para la tabla <code>trackpoint<code>
 */
@Repository
@Transactional(propagation=Propagation.SUPPORTS)
public class TrackpointDaoImpl extends GenericDao implements TrackpointDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackpointDaoImpl.class);

    public static final String WHERE_BY_KEY = " id_track_point = ?  ";

    public static final String ENTITY = TrackpointPOJO.class.getName();
    public static final String TABLE = "trackpoint";
    public static final String FIELDS_ALL = "id_track_point, id_track, tsp, lat, lon, sog, cog ";
    public static final String FIELDS_INSERT = " ?, ?, ?, ?, ?, ?, ? ";
    public static final String FIELDS_UPDATE = " id_track = ?, tsp = ?, lat = ?, lon = ?, sog = ?, cog = ? ";
    public static final String FIELDS_PRIMARY_KEY = " id_track_point = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Object[] toParamsAll( TrackpointPOJO obj ) {
        return ArrayUtils.addAll( toParamsKey( obj ) , toParamsRest( obj ) );
    }

    private static Object[] toParamsUpdate( TrackpointPOJO obj ) {
        return ArrayUtils.addAll( toParamsRest( obj ), toParamsKey( obj ) );
    }

    private static Object[] toParamsKey( TrackpointPOJO obj ) {
        return new Object[] {
            obj.getIdTrackPoint()
         };
    }

    private static Object[] toParamsRest( TrackpointPOJO obj ) {
        return new Object[] {
            obj.getIdTrack(),
            obj.getTsp(),
            obj.getLat(),
            obj.getLon(),
            obj.getSog(),
            obj.getCog()
        };
    }

    @Override
	public void crea( TrackpointPOJO obj ) {
        StringBuilder q = new StringBuilder();
        Object[] p;
        if ( obj.getIdTrackPoint() == null ) {
        	p = toParamsRest(obj);
        	q.append(StringUtils.replaceOnce(StringUtils.replaceOnce(generateInsertQuery(), "?, ", ""), "id_track_point, ",""));	
        } else {
        	p = toParamsAll(obj);
        	q.append(generateInsertQuery());
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
	public boolean existe ( TrackpointPOJO obj ) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsKey(obj);
        q.append(generateExistsQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
    }

    @Override
	public TrackpointPOJO busca( TrackpointPOJO obj ) {
        StringBuilder q = new StringBuilder();
        Object[] p = toParamsKey(obj);
        q.append(generateSelectQuery());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.queryForObject(q.toString(), p, new TrackpointRowMapper());
    }

    @Override
	public void actualiza( TrackpointPOJO obj ) {
		StringBuilder q = new StringBuilder();
		Object[] p = toParamsUpdate(obj);
		q.append(generateUpdateQuery());
		if (LOGGER.isTraceEnabled()) {
		    LOGGER.trace(QueryUtils.pintaQuery(q, p));
		}
		this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
	public void borra( TrackpointPOJO obj ) {
		StringBuilder q = new StringBuilder();
		Object[] p = toParamsKey(obj);
		q.append(generateDeleteQuery());
		if (LOGGER.isTraceEnabled()) {
		    LOGGER.trace(QueryUtils.pintaQuery(q, p));
		}
		this.jdbcTemplate.update(q.toString(), p);
    }

    @Override
	public List<TrackpointPOJO> buscaTodos() {
        StringBuilder q = new StringBuilder();
        Object[] p = {};
        q.append(generateSelectQuery("1=1"));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), new TrackpointRowMapper());
    }

    @Override
	public List<TrackpointPOJO> buscaVarios( TrackpointPOJO obj ) {
        if ( obj == null ) {
            return buscaTodos();
        }

        StringBuilder cond = new StringBuilder(" 1=1 ");
        List<Object> parametros = new ArrayList<>();

        if ( obj.getIdTrackPoint() != null ) {
            cond.append( "   AND id_track_point = ? " );
            parametros.add( obj.getIdTrackPoint() );
        }
        if ( obj.getIdTrack() != null ) {
            cond.append( "   AND id_track = ? " );
            parametros.add( obj.getIdTrack() );
        }
        if ( obj.getTsp() != null ) {
            cond.append( "   AND tsp = ? " );
            parametros.add( obj.getTsp() );
        }
        if ( obj.getLat() != null ) {
            cond.append( "   AND lat = ? " );
            parametros.add( obj.getLat() );
        }
        if ( obj.getLon() != null ) {
            cond.append( "   AND lon = ? " );
            parametros.add( obj.getLon() );
        }
        if ( obj.getSog() != null ) {
            cond.append( "   AND sog = ? " );
            parametros.add( obj.getSog() );
        }
        if ( obj.getCog() != null ) {
            cond.append( "   AND cog = ? " );
            parametros.add( obj.getCog() );
        }

        Object[] p = parametros.toArray();
        StringBuilder q = new StringBuilder();
        q.append(generateSelectQuery( cond.toString() ));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query(q.toString() + " ORDER BY tsp ", p, new TrackpointRowMapper());
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
    public Line findLegBoundaries(Integer idLeg ) {
        StringBuilder q = new StringBuilder();
        Object[] p = { idLeg };
        q.append(SELECT);
        q.append("MAX(LAT) AS MAX_LAT, MAX(LON) AS MAX_LON, MIN(LAT) AS MIN_LAT, MIN(LON) AS MIN_LON ");
        q.append(FROM + TABLE + WHERE );
        q.append("ID_TRACK IN ( SELECT ID_TRACK FROM TRACK WHERE ID_LEG = ? )");
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.queryForObject(q.toString(), p, new RowMapper<Line>() {
			@Override
			public Line mapRow(ResultSet rs, int rowNum) throws SQLException {
				Point max = new PointImpl();
				max.setLatitude(ResultSetUtils.getDouble("MAX_LAT", rs));
				max.setLongitude(ResultSetUtils.getDouble("MAX_LON", rs));
				Point min = new PointImpl();
				min.setLatitude(ResultSetUtils.getDouble("MIN_LAT", rs));
				min.setLongitude(ResultSetUtils.getDouble("MIN_LON", rs));
				return new LineImpl(max,min);
			}
		});
    }
}