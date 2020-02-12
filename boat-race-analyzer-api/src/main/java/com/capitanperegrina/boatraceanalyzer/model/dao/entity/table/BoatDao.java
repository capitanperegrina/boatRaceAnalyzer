package com.capitanperegrina.boatraceanalyzer.model.dao.entity.table;

import java.util.List;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoatPOJO;

public interface BoatDao {

	void crea(BoatPOJO obj);

	boolean existe(BoatPOJO obj);

	BoatPOJO busca(BoatPOJO obj);

	void actualiza(BoatPOJO obj);

	void borra(BoatPOJO obj);

	List<BoatPOJO> buscaTodos();

	List<BoatPOJO> buscaVarios(BoatPOJO obj);

	List<BoatPOJO> findBoatsInRace(Integer idRace);
}