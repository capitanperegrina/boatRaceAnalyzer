package com.capitanperegrina.boatraceanalyzer.model.dao.entity.table;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.LegEntity;

import java.util.List;

public interface LegRepository {

    void crea(LegEntity obj);

    boolean existe(LegEntity obj);

    LegEntity busca(LegEntity obj);

    void actualiza(LegEntity obj);

    void borra(LegEntity obj);

    List<LegEntity> buscaTodos();

    List<LegEntity> buscaVarios(LegEntity obj);

}