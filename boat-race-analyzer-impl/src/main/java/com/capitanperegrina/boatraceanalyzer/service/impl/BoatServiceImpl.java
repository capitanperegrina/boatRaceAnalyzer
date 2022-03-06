package com.capitanperegrina.boatraceanalyzer.service.impl;

import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.BoatRepository;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoatEntity;
import com.capitanperegrina.boatraceanalyzer.service.IBoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BoatServiceImpl implements IBoatService {

    private BoatRepository boatRepository;

    public BoatServiceImpl(@Autowired final BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public BoatEntity findBoatById(final Integer id) {
        BoatEntity b = new BoatEntity();
        b.setIdBoat(id);
        return this.boatRepository.busca(b);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public BoatEntity findBoatByName(final String name) {
        return this.boatRepository.findBoatByName(name);
    }
}
