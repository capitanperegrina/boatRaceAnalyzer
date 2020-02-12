package com.capitanperegrina.boatraceanalyzer.model.dao.entity.table;

import java.util.List;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackpointPOJO;
import com.capitanperegrina.geo.elements.Line;

public interface TrackpointDao {

	void crea(TrackpointPOJO obj);

	boolean existe(TrackpointPOJO obj);

	TrackpointPOJO busca(TrackpointPOJO obj);

	void actualiza(TrackpointPOJO obj);

	void borra(TrackpointPOJO obj);

	List<TrackpointPOJO> buscaTodos();

	List<TrackpointPOJO> buscaVarios(TrackpointPOJO obj);
	
	Line findLegBoundaries(Integer idLeg );

}