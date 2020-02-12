package com.capitanperegrina.boatraceanalyzer.model.dao.entity.table;

import java.util.List;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackPOJO;

public interface TrackDao {

	void crea(TrackPOJO obj);

	boolean existe(TrackPOJO obj);

	TrackPOJO busca(TrackPOJO obj);

	void actualiza(TrackPOJO obj);

	void borra(TrackPOJO obj);

	List<TrackPOJO> buscaTodos();

	List<TrackPOJO> buscaVarios(TrackPOJO obj);

}