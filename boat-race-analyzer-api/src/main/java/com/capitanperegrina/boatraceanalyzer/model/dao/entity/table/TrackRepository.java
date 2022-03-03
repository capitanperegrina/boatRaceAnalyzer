package com.capitanperegrina.boatraceanalyzer.model.dao.entity.table;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackEntity;

import java.util.List;

public interface TrackRepository {

    void crea(TrackEntity obj);

    boolean existe(TrackEntity obj);

    TrackEntity busca(TrackEntity obj);

    void actualiza(TrackEntity obj);

    void borra(TrackEntity obj);

    List<TrackEntity> buscaTodos();

    List<TrackEntity> buscaVarios(TrackEntity obj);

}