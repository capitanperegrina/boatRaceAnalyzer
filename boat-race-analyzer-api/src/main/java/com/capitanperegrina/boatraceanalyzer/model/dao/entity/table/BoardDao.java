package com.capitanperegrina.boatraceanalyzer.model.dao.entity.table;

import java.util.List;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoardPOJO;

/**
 * The Interface BoardDao.
 */
public interface BoardDao {

	/**
	 * Crea.
	 *
	 * @param obj the obj
	 */
	void crea(BoardPOJO obj);

	/**
	 * Existe.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	boolean existe(BoardPOJO obj);

	/**
	 * Busca.
	 *
	 * @param obj the obj
	 * @return the board POJO
	 */
	BoardPOJO busca(BoardPOJO obj);

	/**
	 * Actualiza.
	 *
	 * @param obj the obj
	 */
	void actualiza(BoardPOJO obj);

	/**
	 * Borra.
	 *
	 * @param obj the obj
	 */
	void borra(BoardPOJO obj);

	/**
	 * Busca todos.
	 *
	 * @return the list
	 */
	List<BoardPOJO> buscaTodos();

	/**
	 * Busca varios.
	 *
	 * @param obj the obj
	 * @return the list
	 */
	List<BoardPOJO> buscaVarios(BoardPOJO obj);

}