package com.capitanperegrina.estela.bean;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoardEntity;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.RouteEntity;

/**
 * The Class EstelaBoard.
 */
public class EstelaBoard {

    /**
     * The board.
     */
    private BoardEntity board;

    /**
     * The ini route element.
     */
    private RouteEntity iniRouteElement;

    /**
     * The fin route element.
     */
    private RouteEntity finRouteElement;

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
    public BoardEntity getBoard() {
        return this.board;
    }

    /**
     * Sets the board.
     *
     * @param board the new board
     */
    public void setBoard(final BoardEntity board) {
        this.board = board;
    }

    /**
     * Gets the ini route element.
     *
     * @return the ini route element
     */
    public RouteEntity getIniRouteElement() {
        return this.iniRouteElement;
    }

    /**
     * Sets the ini route element.
     *
     * @param iniRouteElement the new ini route element
     */
    public void setIniRouteElement(final RouteEntity iniRouteElement) {
        this.iniRouteElement = iniRouteElement;
    }

    /**
     * Gets the fin route element.
     *
     * @return the fin route element
     */
    public RouteEntity getFinRouteElement() {
        return this.finRouteElement;
    }

    /**
     * Sets the fin route element.
     *
     * @param finRouteElement the new fin route element
     */
    public void setFinRouteElement(final RouteEntity finRouteElement) {
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
