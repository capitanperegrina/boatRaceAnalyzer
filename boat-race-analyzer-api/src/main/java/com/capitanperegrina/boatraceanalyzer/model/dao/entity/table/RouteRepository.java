package com.capitanperegrina.boatraceanalyzer.model.dao.entity.table;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.RouteEntity;

import java.util.List;

public interface RouteRepository {

    void crea(RouteEntity obj);

    boolean existe(RouteEntity obj);

    RouteEntity busca(RouteEntity obj);

    void actualiza(RouteEntity obj);

    void borra(RouteEntity obj);

    List<RouteEntity> buscaTodos();

    List<RouteEntity> buscaVarios(RouteEntity obj);

    List<RouteEntity> findRouteElements(Integer idLeg);

    List<RouteEntity> findDecorationElements(Integer idLeg);
}