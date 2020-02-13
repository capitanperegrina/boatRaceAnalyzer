package com.capitanperegrina.estela.bean;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoardPOJO;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.RoutePOJO;

/**
 * The Class EstelaBoard.
 */
public class EstelaBoard {

	/** The board. */
	private BoardPOJO board;

	/** The ini route element. */
	private RoutePOJO iniRouteElement;

	/** The fin route element. */
	private RoutePOJO finRouteElement;

	/**
	 * Instantiates a new estela board.
	 */
	public EstelaBoard() {
		super();
	}

	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public BoardPOJO getBoard() {
		return this.board;
	}

	/**
	 * Sets the board.
	 *
	 * @param board the new board
	 */
	public void setBoard(BoardPOJO board) {
		this.board = board;
	}

	/**
	 * Gets the ini route element.
	 *
	 * @return the ini route element
	 */
	public RoutePOJO getIniRouteElement() {
		return this.iniRouteElement;
	}

	/**
	 * Sets the ini route element.
	 *
	 * @param iniRouteElement the new ini route element
	 */
	public void setIniRouteElement(RoutePOJO iniRouteElement) {
		this.iniRouteElement = iniRouteElement;
	}

	/**
	 * Gets the fin route element.
	 *
	 * @return the fin route element
	 */
	public RoutePOJO getFinRouteElement() {
		return this.finRouteElement;
	}

	/**
	 * Sets the fin route element.
	 *
	 * @param finRouteElement the new fin route element
	 */
	public void setFinRouteElement(RoutePOJO finRouteElement) {
		this.finRouteElement = finRouteElement;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "EstelaBoard [board=" + this.board + ", iniRouteElement=" + this.iniRouteElement + ", finRouteElement="
				+ this.finRouteElement + "]";
	}
}
