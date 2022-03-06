package com.capitanperegrina.boatraceanalyzer.rest.api.impl;

import com.capitanperegrina.boatraceanalyzer.rest.api.BoatApi;
import com.capitanperegrina.boatraceanalyzer.rest.api.mapper.BoatMapper;
import com.capitanperegrina.boatraceanalyzer.rest.model.BoatDTO;
import com.capitanperegrina.boatraceanalyzer.service.IBoatService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoatApiImpl implements BoatApi {

    private final IBoatService boatService;

    private final BoatMapper boatMapper;

    public BoatApiImpl(@Autowired final IBoatService boatService) {
        this.boatService = boatService;
        this.boatMapper = Mappers.getMapper(BoatMapper.class);
    }

    @Override
    public ResponseEntity<BoatDTO> getBoatById(final Integer boatId) {
        return ResponseEntity.ok(this.boatMapper.toBoatDTO(this.boatService.findBoatById(boatId)));
    }

    @Override
    public ResponseEntity<BoatDTO> getBoatByName(final String boatName) {
        return ResponseEntity.ok(this.boatMapper.toBoatDTO(this.boatService.findBoatByName(boatName)));
    }

}
