package com.capitanperegrina.boatraceanalyzer.model.dao.entity.table;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackpointEntity;
import com.capitanperegrina.geo.elements.Line;

import java.util.List;

public interface TrackpointRepository {

    void crea(TrackpointEntity obj);

    boolean existe(TrackpointEntity obj);

    TrackpointEntity busca(TrackpointEntity obj);

    void actualiza(TrackpointEntity obj);

    void borra(TrackpointEntity obj);

    List<TrackpointEntity> buscaTodos();

    List<TrackpointEntity> buscaVarios(TrackpointEntity obj);

    Line findLegBoundaries(Integer idLeg);

}