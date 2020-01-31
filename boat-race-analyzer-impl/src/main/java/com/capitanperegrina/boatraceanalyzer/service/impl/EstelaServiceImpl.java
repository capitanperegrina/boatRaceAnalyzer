package com.capitanperegrina.boatraceanalyzer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitanperegrina.boatraceanalyzer.adapters.EstelaAdapters;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackpointPOJO;
import com.capitanperegrina.boatraceanalyzer.service.IEstelaService;
import com.capitanperegrina.boatraceanalyzer.util.EstelaUtils;

@Service
@Transactional
public class EstelaServiceImpl implements IEstelaService {

    @Override
    public void importCvsToDatabase(Integer idTrack, String fileName) {
        List<TrackpointPOJO> estelaTrackPoints = EstelaAdapters.toTrackpointPOJOList(idTrack,
                EstelaUtils.readEstelaCsvFile(fileName));

    }

}
