package com.capitanperegrina.boatraceanalyzer.model.entity.table;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * POJO identificado con la tabla <code>route</code>.
 */
public class RoutePOJO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -47188108848L;

	/** The id route. */
	protected Integer idRoute;
	
	/** The id leg. */
	protected Integer idLeg;
	
	/** The seq. */
	protected Integer seq;
	
	/** The lat 1. */
	protected BigDecimal lat1;
	
	/** The lon 1. */
	protected BigDecimal lon1;
	
	/** The lat 2. */
	protected BigDecimal lat2;
	
	/** The lon 2. */
	protected BigDecimal lon2;
	
	/** The kind. */
	protected Integer kind;
	
	/** The name. */
	protected String name;
	
	/** The pass. */
	protected Integer pass;

	/**
	 * Constructor por defecto.
	 */
	public RoutePOJO() {
		super();
	}

	/**
	 * Gets the id route.
	 *
	 * @return the id route
	 */
	public Integer getIdRoute() {
		return this.idRoute;
	}

	/**
	 * Sets the id route.
	 *
	 * @param idRoute the new id route
	 */
	public void setIdRoute(Integer idRoute) {
		this.idRoute = idRoute;
	}

	/**
	 * Gets the id leg.
	 *
	 * @return the id leg
	 */
	public Integer getIdLeg() {
		return this.idLeg;
	}

	/**
	 * Sets the id leg.
	 *
	 * @param idLeg the new id leg
	 */
	public void setIdLeg(Integer idLeg) {
		this.idLeg = idLeg;
	}

	/**
	 * Gets the seq.
	 *
	 * @return the seq
	 */
	public Integer getSeq() {
		return this.seq;
	}

	/**
	 * Sets the seq.
	 *
	 * @param seq the new seq
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	/**
	 * Gets the lat 1.
	 *
	 * @return the lat 1
	 */
	public BigDecimal getLat1() {
		return this.lat1;
	}

	/**
	 * Sets the lat 1.
	 *
	 * @param lat1 the new lat 1
	 */
	public void setLat1(BigDecimal lat1) {
		this.lat1 = lat1;
	}

	/**
	 * Gets the lon 1.
	 *
	 * @return the lon 1
	 */
	public BigDecimal getLon1() {
		return this.lon1;
	}

	/**
	 * Sets the lon 1.
	 *
	 * @param lon1 the new lon 1
	 */
	public void setLon1(BigDecimal lon1) {
		this.lon1 = lon1;
	}

	/**
	 * Gets the lat 2.
	 *
	 * @return the lat 2
	 */
	public BigDecimal getLat2() {
		return this.lat2;
	}

	/**
	 * Sets the lat 2.
	 *
	 * @param lat2 the new lat 2
	 */
	public void setLat2(BigDecimal lat2) {
		this.lat2 = lat2;
	}

	/**
	 * Gets the lon 2.
	 *
	 * @return the lon 2
	 */
	public BigDecimal getLon2() {
		return this.lon2;
	}

	/**
	 * Sets the lon 2.
	 *
	 * @param lon2 the new lon 2
	 */
	public void setLon2(BigDecimal lon2) {
		this.lon2 = lon2;
	}

	/**
	 * Gets the kind.
	 *
	 * @return the kind
	 */
	public Integer getKind() {
		return this.kind;
	}

	/**
	 * Sets the kind.
	 *
	 * @param kind the new kind
	 */
	public void setKind(Integer kind) {
		this.kind = kind;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the pass.
	 *
	 * @return the pass
	 */
	public Integer getPass() {
		return this.pass;
	}

	/**
	 * Sets the pass.
	 *
	 * @param pass the new pass
	 */
	public void setPass(Integer pass) {
		this.pass = pass;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "RoutePOJO [idRoute=" + this.idRoute + ", idLeg=" + this.idLeg + ", seq=" + this.seq + ", lat1="
				+ this.lat1 + ", lon1=" + this.lon1 + ", lat2=" + this.lat2 + ", lon2=" + this.lon2 + ", kind="
				+ this.kind + ", name=" + this.name + ", pass=" + this.pass + "]";
	}
}