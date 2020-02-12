package com.capitanperegrina.boatraceanalyzer.model.dao.entity.table;

import java.util.List;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.LegPOJO;

public interface LegDao {

	void crea(LegPOJO obj);

	boolean existe(LegPOJO obj);

	LegPOJO busca(LegPOJO obj);

	void actualiza(LegPOJO obj);

	void borra(LegPOJO obj);

	List<LegPOJO> buscaTodos();

	List<LegPOJO> buscaVarios(LegPOJO obj);

}