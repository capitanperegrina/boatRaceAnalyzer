package com.capitanperegrina.boatraceanalyzer.modelo.dao.impl.tabla;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.RacePOJO;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.GenericDao;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla.RaceRowMapper;
import com.capitanperegrina.boatraceanalyzer.util.sql.QueryUtils;

/**
 * Objeto de acceso a datos para la tabla <code>race<code>
 */
@Repository("raceDao")
@Transactional(value = "boatRaceAnalyzer", propagation = Propagation.SUPPORTS)
public class RaceDao extends GenericDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(RaceDao.class);
    
    private static final String WHERE_ID_RACE = " WHERE id_race = ?";

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public static final String ENTIDAD = RacePOJO.class.getName();
    public static final String TABLA = "race";
    public static final String CAMPOS = "id_race, name ";
    public static final String CAMPOSINSERT = " ?, ? ";
    public static final String CAMPOSUPDATE = " name = ? ";
    public static final String CAMPOSPKUPDATE = " id_race = ? ";

    private static Object[] toParamsTodos(RacePOJO obj) {
        return ArrayUtils.addAll(toParamsClave(obj), toParamsResto(obj));
    }

    private static Object[] toParamsUpdate(RacePOJO obj) {
        return ArrayUtils.addAll(toParamsResto(obj), toParamsClave(obj));
    }

    private static Object[] toParamsClave(RacePOJO obj) {
        return new Object[] { obj.getIdRace() };
    }

    private static Object[] toParamsResto(RacePOJO obj) {
        return new Object[] { obj.getName() };
    }

    /**
     * Método que inserta un registro en la tabla <code>race</code> de la base de datos.
     * 
     * @param obj
     *            Objeto POJO con la información a insertar en la tabla <code>race</code> de la base de datos.
     * @throws DuplicateKeyException
     *             si ya existe un registro en la tabla <code>race</code> de base de datos con igual clave principal.
     */
    public void crea(RacePOJO obj) {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsTodos(obj);
        q.append(generateInsertQuery());

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    /**
     * Método que comprueba si existe un registro en la tabla <code>race</code> de la base de datos a partir de su clave
     * principal.
     * 
     * @param obj
     *            Objeto del que se desea comprobar su existencia en la tabla <code>race</code> de la base de datos.
     * @return true si existe un registro en la tabla <code>race</code> de la base de datos con la misma clave
     *         principal, false si no es así.
     */
    public boolean existe(RacePOJO obj) {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsClave(obj);
        q.append("SELECT COUNT(*) ").append(FROM).append(TABLA).append(WHERE_ID_RACE);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
    }

    /**
     * Método que recupera de la base de datos un registro de la tabla <code>race</code> y lo devuelve en el objeto
     * RacePOJO.
     * 
     * @param obj
     *            Objeto RacePOJO con los campos de la clave principal informados.
     * @return El objeto RacePOJO con la información recuperada de la base de datos.
     * @throws EmptyResultDataAccessException
     *             si no se encuentra el resitro en la tabla <code>race</code>.
     */
    public RacePOJO busca(RacePOJO obj) {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsClave(obj);
        q.append(SELECT).append(CAMPOS).append(FROM).append(TABLA).append(WHERE_ID_RACE);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.queryForObject(q.toString(), p, new RaceRowMapper());
    }

    /**
     * Método que actualiza un registro de la tabla <code>race</code> de la base de datos.
     * 
     * @param obj
     *            Objeto RacePOJO que se quiere actualizar en la base de datos.
     * @throws EmptyResultDataAccessException
     *             Si no se encuentra el registro que se desea actualizar en la tabla <code>race</code>.
     * @throws DuplicateKeyException
     *             si se encuentra más de un registro a actualizar en la tabla la tabla <code>race</code>.
     */
    public void actualiza(RacePOJO obj) {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsUpdate(obj);
        q.append("UPDATE ").append(TABLA).append(" SET ").append(CAMPOSUPDATE).append(" WHERE ").append(CAMPOSPKUPDATE).append(" ");

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    /**
     * Método que elimina físicamente un registro de la tabla <code>race</code> de la base de datos a partir de los
     * valores de su clave principal.
     * 
     * @param obj
     *            Objeto RacePOJO con los campos de la clave principal informados.
     * @throws EmptyResultDataAccessException
     *             Si no se encuentra el registro que se desea eliminar en la tabla <code>race</code>.
     */
    public void borra(RacePOJO obj) {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsClave(obj);
        q.append("DELETE FROM ").append(TABLA).append(WHERE_ID_RACE);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    /**
     * Método devuelve una lista con todos los registros de la tabla <code>race</code> de la base de datos.
     * 
     * @return Una lista con todos los elementos de la tabla <code>race</code>.
     */
    public List<RacePOJO> buscaTodos() {
        StringBuilder q = new StringBuilder("");
        Object[] p = {};
        q.append(SELECT).append(CAMPOS).append(FROM).append(TABLA);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), new RaceRowMapper());
    }

    /**
     * Método devuelve una lista con todos los registros de la tabla <code>race</code> de la base de datos a partir de
     * los campos de de un POJO.
     * 
     * @param obj
     *            Objeto RacePOJO con los campos por los que se desea buscar informados.
     * @return Una lista con todos los elementos de la tabla <code>race</code> que cumplan los criterios de búsqueda.
     */
    public List<RacePOJO> buscaVarios(RacePOJO obj) {
        if (obj == null) {
            return buscaTodos();
        }

        StringBuilder q = new StringBuilder("");
        q.append(SELECT).append(CAMPOS).append(FROM).append(TABLA).append(" WHERE 1 = 1 ");

        List<Object> parametros = new ArrayList<Object>();

        if (obj.getIdRace() != null) {
            q.append(" AND id_race = ? ");
            parametros.add(obj.getIdRace());
        }
        if (obj.getName() != null) {
            q.append(" AND name = ? ");
            parametros.add(obj.getName());
        }
        Object[] p = parametros.toArray();

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), p, new RaceRowMapper());
    }

    @Override
    protected String getTable() {
        return TABLA;
    }

    @Override
    protected String getFields() {
        return CAMPOS;
    }

    @Override
    protected String getInsertQuestionMarks() {
        return CAMPOSINSERT;
    }
}