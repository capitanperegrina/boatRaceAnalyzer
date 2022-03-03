package com.capitanperegrina.boatraceanalyzer.model.dao.entity.table;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoatEntity;

import java.util.List;

public interface BoatRepository {

    void crea(BoatEntity obj);

    boolean existe(BoatEntity obj);

    BoatEntity busca(BoatEntity obj);

    void actualiza(BoatEntity obj);

    void borra(BoatEntity obj);

    List<BoatEntity> buscaTodos();

    List<BoatEntity> buscaVarios(BoatEntity obj);

    List<BoatEntity> findBoatsInRace(Integer idRace);
}