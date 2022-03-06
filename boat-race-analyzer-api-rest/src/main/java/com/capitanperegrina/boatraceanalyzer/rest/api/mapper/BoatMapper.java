package com.capitanperegrina.boatraceanalyzer.rest.api.mapper;

import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoatEntity;
import com.capitanperegrina.boatraceanalyzer.rest.model.BoatDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BoatMapper {

    BoatEntity toBoatEntity(BoatDTO in);

    BoatDTO toBoatDTO(BoatEntity in);
}
