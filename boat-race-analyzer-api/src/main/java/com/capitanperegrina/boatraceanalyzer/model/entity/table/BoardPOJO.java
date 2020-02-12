package com.capitanperegrina.boatraceanalyzer.model.entity.table;

import java.io.Serializable;
import java.util.Date;

/**
 * POJO identificado con la tabla <code>board</code>.
 */
public class BoardPOJO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -58284194235L;

	/** The id board. */
	protected Integer idBoard;

	/** The id track. */
	protected Integer idTrack;

	/** The ini. */
	protected Integer ini;

	/** The date ini. */
	protected Date dateIni;

	/** The fin. */
	protected Integer fin;

	/** The date fin. */
	protected Date dateFin;

	/**
	 * Constructor por defecto.
	 */
	public BoardPOJO() {
		super();
	}

	/**
	 * Gets the id board.
	 *
	 * @return the id board
	 */
	public Integer getIdBoard() {
		return this.idBoard;
	}

	/**
	 * Sets the id board.
	 *
	 * @param idBoard the new id board
	 */
	public void setIdBoard(Integer idBoard) {
		this.idBoard = idBoard;
	}

	/**
	 * Gets the id track.
	 *
	 * @return the id track
	 */
	public Integer getIdTrack() {
		return this.idTrack;
	}

	/**
	 * Sets the id track.
	 *
	 * @param idTrack the new id track
	 */
	public void setIdTrack(Integer idTrack) {
		this.idTrack = idTrack;
	}

	/**
	 * Gets the ini.
	 *
	 * @return the ini
	 */
	public Integer getIni() {
		return this.ini;
	}

	/**
	 * Sets the ini.
	 *
	 * @param ini the new ini
	 */
	public void setIni(Integer ini) {
		this.ini = ini;
	}

	/**
	 * Gets the date ini.
	 *
	 * @return the date ini
	 */
	public Date getDateIni() {
		return this.dateIni;
	}

	/**
	 * Sets the date ini.
	 *
	 * @param dateIni the new date ini
	 */
	public void setDateIni(Date dateIni) {
		this.dateIni = dateIni;
	}

	/**
	 * Gets the fin.
	 *
	 * @return the fin
	 */
	public Integer getFin() {
		return this.fin;
	}

	/**
	 * Sets the fin.
	 *
	 * @param fin the new fin
	 */
	public void setFin(Integer fin) {
		this.fin = fin;
	}

	/**
	 * Gets the date fin.
	 *
	 * @return the date fin
	 */
	public Date getDateFin() {
		return this.dateFin;
	}

	/**
	 * Sets the date fin.
	 *
	 * @param dateFin the new date fin
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "BoardPOJO [idBoard=" + this.idBoard + ", idTrack=" + this.idTrack + ", ini=" + this.ini + ", dateIni="
				+ this.dateIni + ", fin=" + this.fin + ", dateFin=" + this.dateFin + "]";
	}
}