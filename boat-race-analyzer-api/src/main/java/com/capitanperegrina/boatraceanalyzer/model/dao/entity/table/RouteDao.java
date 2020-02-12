package com.capitanperegrina.boatraceanalyzer.model.dao.entity.table;

import java.util.List;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.RoutePOJO;

public interface RouteDao {

	void crea(RoutePOJO obj);

	boolean existe(RoutePOJO obj);

	RoutePOJO busca(RoutePOJO obj);

	void actualiza(RoutePOJO obj);

	void borra(RoutePOJO obj);

	List<RoutePOJO> buscaTodos();

	List<RoutePOJO> buscaVarios(RoutePOJO obj);
	
	List<RoutePOJO> findRouteElements(Integer idLeg);
	
	List<RoutePOJO> findDecorationElements(Integer idLeg);
}