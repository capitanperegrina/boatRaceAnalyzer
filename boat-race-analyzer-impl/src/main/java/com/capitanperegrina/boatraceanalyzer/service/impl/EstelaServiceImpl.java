package com.capitanperegrina.boatraceanalyzer.service.impl;

import com.capitanperegrina.boatraceanalyzer.adapters.EstelaAdapters;
import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.*;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.*;
import com.capitanperegrina.boatraceanalyzer.service.IEstelaService;
import com.capitanperegrina.boatraceanalyzer.util.EstelaUtils;
import com.capitanperegrina.estela.bean.EstelaBoard;
import com.capitanperegrina.estela.bean.EstelaRace;
import com.capitanperegrina.estela.bean.EstelaRaceLeg;
import com.capitanperegrina.estela.bean.EstelaTrack;
import com.capitanperegrina.geo.elements.Line;
import com.capitanperegrina.geo.elements.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EstelaServiceImpl implements IEstelaService {

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private LegRepository legRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoatRepository boatRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private TrackpointRepository trackpointRepository;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public EstelaRace readEstelaRace(final Integer idRace) {
        final EstelaRace race = new EstelaRace();

        // Race
        race.setRace(new RaceEntity());
        race.getRace().setIdRace(idRace);
        race.setRace(this.raceRepository.busca(race.getRace()));

        // Boats per race
        final List<BoatEntity> boats = this.boatRepository.findBoatsInRace(idRace);
        for (final BoatEntity boat : boats) {
            race.getBoats().put(boat.getIdBoat(), boat);
        }

        // Legs per race
        final LegEntity legFilter = new LegEntity();
        legFilter.setIdRace(idRace);
        final List<LegEntity> legs = this.legRepository.buscaVarios(legFilter);
        for (final LegEntity leg : legs) {
            final EstelaRaceLeg raceLeg = new EstelaRaceLeg();
            raceLeg.setLeg(leg);

            // Route per leg
            raceLeg.getRouteElements().addAll(this.routeRepository.findRouteElements(leg.getIdLeg()));
            raceLeg.getDecorationElements().addAll(this.routeRepository.findDecorationElements(leg.getIdLeg()));

            // Tracks per leg
            final TrackEntity trackFilter = new TrackEntity();
            trackFilter.setIdLeg(leg.getIdLeg());
            final List<TrackEntity> tracks = this.trackRepository.buscaVarios(trackFilter);
            for (final TrackEntity track : tracks) {
                final EstelaTrack estelaTrack = new EstelaTrack();
                estelaTrack.setTrack(track);

                // Boards per track
                final BoardEntity boardFilter = new BoardEntity();
                boardFilter.setIdTrack(track.getIdTrack());
                final List<BoardEntity> boards = this.boardRepository.buscaVarios(boardFilter);
                for (final BoardEntity board : boards) {
                    final EstelaBoard estelaBoard = new EstelaBoard();
                    RouteEntity routeFilter = new RouteEntity();
                    routeFilter.setIdRoute(board.getIni());
                    estelaBoard.setIniRouteElement(this.routeRepository.busca(routeFilter));
                    if (board.getFin() != null) {
                        routeFilter = new RouteEntity();
                        routeFilter.setIdRoute(board.getFin());
                        estelaBoard.setFinRouteElement(this.routeRepository.busca(routeFilter));
                    }
                    estelaBoard.setBoard(board);
                    estelaTrack.getBoards().put(estelaBoard.getIniRouteElement().getSeq(), estelaBoard);
                }

                // Points per track
                final TrackpointEntity trackpointFilter = new TrackpointEntity();
                trackpointFilter.setIdTrack(track.getIdTrack());
                estelaTrack.getPoints().addAll(this.trackpointRepository.buscaVarios(trackpointFilter));
                raceLeg.getTracks().put(track.getIdTrack(), estelaTrack);

            }
            race.getLegs().put(leg.getIdLeg(), raceLeg);
        }
        return race;
    }

    @Override
    @Transactional
    public void importCvsToDatabase(final Integer idRace, final Integer idLeg, final Integer idBoat, final Integer idTrack, final String fileName) {
        final List<TrackpointEntity> estelaTrackPoints = EstelaAdapters.toTrackpointPOJOList(idTrack,
                EstelaUtils.readEstelaCsvFile(fileName));
        final int i = 0;
        for (final TrackpointEntity tp : estelaTrackPoints) {
            this.trackpointRepository.crea(tp);
        }
    }

    @Override
    public Point findLegCenter(final Integer idLeg) {
        final Line boundaries = this.trackpointRepository.findLegBoundaries(idLeg);
        return boundaries.centralPosition();
    }

    @Override
    public List<BoatEntity> readBoatsByRaceAndLeg(final Integer idRace) {
        return this.boatRepository.findBoatsInRace(idRace);
    }
}
