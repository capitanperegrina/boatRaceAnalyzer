package com.capitanperegrina.boatraceanalyzer.model.dao.entity.table;

import java.util.List;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.RacePOJO;

public interface RaceDao {

	void crea(RacePOJO obj);

	boolean existe(RacePOJO obj);

	RacePOJO busca(RacePOJO obj);

	void actualiza(RacePOJO obj);

	void borra(RacePOJO obj);

	List<RacePOJO> buscaTodos();

	List<RacePOJO> buscaVarios(RacePOJO obj);

}