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

import com.capitanperegrina.boatraceanalyzer.model.entity.table.LegPOJO;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.GenericDao;
import com.capitanperegrina.boatraceanalyzer.modelo.dao.rowmappers.tabla.LegRowMapper;
import com.capitanperegrina.boatraceanalyzer.util.sql.QueryUtils;

/**
 * Objeto de acceso a datos para la tabla <code>leg<code>
 */
@Repository("legDao")
@Transactional(value = "boatRaceAnalyzer", propagation = Propagation.SUPPORTS)
public class LegDao extends GenericDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(LegDao.class);
    
    private static final String WHERE_ID_LEG = " WHERE id_leg = ?";

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public static final String ENTIDAD = LegPOJO.class.getName();
    public static final String TABLA = "leg";
    public static final String CAMPOS = "id_leg, id_race, date, name ";
    public static final String CAMPOSINSERT = " ?, ?, ?, ? ";
    public static final String CAMPOSUPDATE = " id_race = ?, date = ?, name = ? ";
    public static final String CAMPOSPKUPDATE = " id_leg = ? ";

    private static Object[] toParamsTodos(LegPOJO obj) {
        return ArrayUtils.addAll(toParamsClave(obj), toParamsResto(obj));
    }

    private static Object[] toParamsUpdate(LegPOJO obj) {
        return ArrayUtils.addAll(toParamsResto(obj), toParamsClave(obj));
    }

    private static Object[] toParamsClave(LegPOJO obj) {
        return new Object[] { obj.getIdLeg() };
    }

    private static Object[] toParamsResto(LegPOJO obj) {
        return new Object[] { obj.getIdRace(), obj.getDate(), obj.getName() };
    }

    /**
     * Método que inserta un registro en la tabla <code>leg</code> de la base de datos.
     *
     * @param obj
     *            Objeto POJO con la información a insertar en la tabla <code>leg</code> de la base de datos.
     * @throws DuplicateKeyException
     *             si ya existe un registro en la tabla <code>leg</code> de base de datos con igual clave principal.
     */
    public void crea(LegPOJO obj) {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsTodos(obj);
        q.append(generateInsertQuery());

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        this.jdbcTemplate.update(q.toString(), p);
    }

    /**
     * Método que comprueba si existe un registro en la tabla <code>leg</code> de la base de datos a partir de su clave
     * principal.
     *
     * @param obj
     *            Objeto del que se desea comprobar su existencia en la tabla <code>leg</code> de la base de datos.
     * @return true si existe un registro en la tabla <code>leg</code> de la base de datos con la misma clave principal,
     *         false si no es así.
     */
    public boolean existe(LegPOJO obj) {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsClave(obj);
        q.append("SELECT COUNT(*) ").append(FROM).append(TABLA).append(WHERE_ID_LEG);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return (this.jdbcTemplate.queryForObject(q.toString(), p, Integer.class)) > 0;
    }

    /**
     * Método que recupera de la base de datos un registro de la tabla <code>leg</code> y lo devuelve en el objeto
     * LegPOJO.
     *
     * @param obj
     *            Objeto LegPOJO con los campos de la clave principal informados.
     * @return El objeto LegPOJO con la información recuperada de la base de datos.
     * @throws EmptyResultDataAccessException
     *             si no se encuentra el resitro en la tabla <code>leg</code>.
     */
    public LegPOJO busca(LegPOJO obj) {
        StringBuilder q = new StringBuilder("");
        Object[] p = toParamsClave(obj);
        q.append(SELECT).append(CAMPOS).append(FROM).append(TABLA).append(WHERE_ID_LEG);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.queryForObject(q.toString(), p, new LegRowMapper());
    }

    /**
     * Método que actualiza un registro de la tabla <code>leg</code> de la base de datos.
     *
     * @param obj
     *            Objeto LegPOJO que se quiere actualizar en la base de datos.
     * @throws EmptyResultDataAccessException
     *             Si no se encuentra el registro que se desea actualizar en la tabla <code>leg</code>.
     * @throws DuplicateKeyException
     *             si se encuentra más de un registro a actualizar en la tabla la tabla <code>leg</code>.
     */
    public void actualiza(LegPOJO obj) {
            StringBuilder q = new StringBuilder("");
            Object[] p = toParamsUpdate(obj);
            q.append("UPDATE ").append(TABLA).append(" SET ").append(CAMPOSUPDATE).append(" WHERE ").append(CAMPOSPKUPDATE).append(" ");

            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace(QueryUtils.pintaQuery(q, p));
            }
            this.jdbcTemplate.update(q.toString(), p);
    }

    /**
     * Método que elimina físicamente un registro de la tabla <code>leg</code> de la base de datos a partir de los
     * valores de su clave principal.
     *
     * @param obj
     *            Objeto LegPOJO con los campos de la clave principal informados.
     * @throws EmptyResultDataAccessException
     *             Si no se encuentra el registro que se desea eliminar en la tabla <code>leg</code>.
     */
    public void borra(LegPOJO obj) {
            StringBuilder q = new StringBuilder("");
            Object[] p = toParamsClave(obj);
            q.append("DELETE FROM ").append(TABLA).append(WHERE_ID_LEG);

            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace(QueryUtils.pintaQuery(q, p));
            }
            this.jdbcTemplate.update(q.toString(), p);
    }

    /**
     * Método devuelve una lista con todos los registros de la tabla <code>leg</code> de la base de datos.
     *
     * @return Una lista con todos los elementos de la tabla <code>leg</code>.
     */
    public List<LegPOJO> buscaTodos() {
        StringBuilder q = new StringBuilder("");
        Object[] p = {};
        q.append(SELECT).append(CAMPOS).append(FROM).append(TABLA);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), new LegRowMapper());
    }

    /**
     * Método devuelve una lista con todos los registros de la tabla <code>leg</code> de la base de datos a partir de
     * los campos de de un POJO.
     *
     * @param obj
     *            Objeto LegPOJO con los campos por los que se desea buscar informados.
     * @return Una lista con todos los elementos de la tabla <code>leg</code> que cumplan los criterios de búsqueda.
     */
    public List<LegPOJO> buscaVarios(LegPOJO obj) {
        if (obj == null) {
            return buscaTodos();
        }

        StringBuilder q = new StringBuilder("");
        q.append(SELECT).append(CAMPOS).append(FROM).append(TABLA).append(" WHERE 1 = 1 ");

        List<Object> parametros = new ArrayList<>();

        if (obj.getIdLeg() != null) {
            q.append(" AND id_leg = ? ");
            parametros.add(obj.getIdLeg());
        }
        if (obj.getIdRace() != null) {
            q.append(" AND id_race = ? ");
            parametros.add(obj.getIdRace());
        }
        if (obj.getDate() != null) {
            q.append(" AND date = ? ");
            parametros.add(obj.getDate());
        }
        if (obj.getName() != null) {
            q.append(" AND name = ? ");
            parametros.add(obj.getName());
        }
        Object[] p = parametros.toArray();

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(QueryUtils.pintaQuery(q, p));
        }
        return this.jdbcTemplate.query(q.toString(), p, new LegRowMapper());
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