package com.capitanperegrina.boatraceanalyzer.model.dao.entity.table;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.RaceEntity;

import java.util.List;

public interface RaceRepository {

    void crea(RaceEntity obj);

    boolean existe(RaceEntity obj);

    RaceEntity busca(RaceEntity obj);

    void actualiza(RaceEntity obj);

    void borra(RaceEntity obj);

    List<RaceEntity> buscaTodos();

    List<RaceEntity> buscaVarios(RaceEntity obj);

}