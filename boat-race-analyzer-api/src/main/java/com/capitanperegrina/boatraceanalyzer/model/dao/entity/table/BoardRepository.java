package com.capitanperegrina.boatraceanalyzer.model.dao.entity.table;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoardEntity;

import java.util.List;

/**
 * The Interface BoardRepository.
 */
public interface BoardRepository {

    /**
     * Crea.
     *
     * @param obj the obj
     */
    void crea(BoardEntity obj);

    /**
     * Existe.
     *
     * @param obj the obj
     * @return true, if successful
     */
    boolean existe(BoardEntity obj);

    /**
     * Busca.
     *
     * @param obj the obj
     * @return the board POJO
     */
    BoardEntity busca(BoardEntity obj);

    /**
     * Actualiza.
     *
     * @param obj the obj
     */
    void actualiza(BoardEntity obj);

    /**
     * Borra.
     *
     * @param obj the obj
     */
    void borra(BoardEntity obj);

    /**
     * Busca todos.
     *
     * @return the list
     */
    List<BoardEntity> buscaTodos();

    /**
     * Busca varios.
     *
     * @param obj the obj
     * @return the list
     */
    List<BoardEntity> buscaVarios(BoardEntity obj);

}