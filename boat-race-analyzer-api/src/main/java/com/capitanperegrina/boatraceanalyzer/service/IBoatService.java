package com.capitanperegrina.boatraceanalyzer.service;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoatEntity;

public interface IBoatService {

    BoatEntity findBoatById(final Integer id);

    BoatEntity findBoatByName(final String name);
}
