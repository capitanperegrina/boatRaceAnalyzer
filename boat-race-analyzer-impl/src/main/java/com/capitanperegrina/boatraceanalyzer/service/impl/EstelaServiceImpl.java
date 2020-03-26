package com.capitanperegrina.boatraceanalyzer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capitanperegrina.boatraceanalyzer.adapters.EstelaAdapters;
import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.BoardDao;
import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.BoatDao;
import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.LegDao;
import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.RaceDao;
import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.RouteDao;
import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.TrackDao;
import com.capitanperegrina.boatraceanalyzer.model.dao.entity.table.TrackpointDao;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoardPOJO;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.BoatPOJO;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.LegPOJO;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.RacePOJO;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.RoutePOJO;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackPOJO;
import com.capitanperegrina.boatraceanalyzer.model.entity.table.TrackpointPOJO;
import com.capitanperegrina.boatraceanalyzer.service.IEstelaService;
import com.capitanperegrina.boatraceanalyzer.util.EstelaUtils;
import com.capitanperegrina.estela.bean.EstelaBoard;
import com.capitanperegrina.estela.bean.EstelaRace;
import com.capitanperegrina.estela.bean.EstelaRaceLeg;
import com.capitanperegrina.estela.bean.EstelaTrack;
import com.capitanperegrina.geo.elements.Line;
import com.capitanperegrina.geo.elements.Point;

@Service
@Transactional
public class EstelaServiceImpl implements IEstelaService {

	@Autowired
	private RaceDao raceDao;
	
	@Autowired
	private LegDao legDao;
	
	@Autowired
	private RouteDao routeDao;
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private BoatDao boatDao;
	
	@Autowired
	private TrackDao trackDao;
	
	@Autowired
	private TrackpointDao trackpointDao;
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public EstelaRace readEstelaRace( Integer idRace ) {
		EstelaRace race = new EstelaRace();
		
		// Race
		race.setRace(new RacePOJO());
		race.getRace().setIdRace(idRace);
		race.setRace(this.raceDao.busca(race.getRace()));
		
		// Boats per race
		List<BoatPOJO> boats = this.boatDao.findBoatsInRace(idRace);
		for (BoatPOJO boat : boats) {
			race.getBoats().put(boat.getIdBoat(), boat);
		}

		// Legs per race
		LegPOJO legFilter = new LegPOJO();
		legFilter.setIdRace(idRace);
		List<LegPOJO> legs = this.legDao.buscaVarios(legFilter);
		for (LegPOJO leg : legs) {
			EstelaRaceLeg raceLeg = new EstelaRaceLeg();
			raceLeg.setLeg(leg);

			// Route per leg
			raceLeg.getRouteElements().addAll(this.routeDao.findRouteElements(leg.getIdLeg()));
			raceLeg.getDecorationElements().addAll(this.routeDao.findDecorationElements(leg.getIdLeg()));
			
			// Tracks per leg
			TrackPOJO trackFilter = new TrackPOJO();
			trackFilter.setIdLeg(leg.getIdLeg());
			List<TrackPOJO> tracks = this.trackDao.buscaVarios(trackFilter);
			for (TrackPOJO track : tracks) {
				EstelaTrack estelaTrack = new EstelaTrack();
				estelaTrack.setTrack(track);

				// Boards per track
				BoardPOJO boardFilter = new BoardPOJO();
				boardFilter.setIdTrack(track.getIdTrack());
				List<BoardPOJO> boards = this.boardDao.buscaVarios(boardFilter);
				for ( BoardPOJO board : boards ) {
					EstelaBoard estelaBoard = new EstelaBoard();
					RoutePOJO routeFilter = new RoutePOJO();
					routeFilter.setIdRoute(board.getIni());
					estelaBoard.setIniRouteElement(this.routeDao.busca(routeFilter));
					if ( board.getFin() != null ) {
						routeFilter = new RoutePOJO();
						routeFilter.setIdRoute(board.getFin());
						estelaBoard.setFinRouteElement(this.routeDao.busca(routeFilter));						
					}
					estelaBoard.setBoard(board);
					estelaTrack.getBoards().put(estelaBoard.getIniRouteElement().getSeq(), estelaBoard);
				}
				
				// Points per track
				TrackpointPOJO trackpointFilter = new TrackpointPOJO();
				trackpointFilter.setIdTrack(track.getIdTrack());
				estelaTrack.getPoints().addAll(this.trackpointDao.buscaVarios(trackpointFilter));
				raceLeg.getTracks().put(track.getIdTrack(), estelaTrack);
				
			}
			race.getLegs().put(leg.getIdLeg(), raceLeg);
		}
		return race;
	}
	
    @Override
    @Transactional
    public void importCvsToDatabase(Integer idRace, Integer idLeg, Integer idBoat, Integer idTrack, String fileName) {
        List<TrackpointPOJO> estelaTrackPoints = EstelaAdapters.toTrackpointPOJOList(idTrack,
                EstelaUtils.readEstelaCsvFile(fileName));
        int i = 0;
        for ( TrackpointPOJO tp : estelaTrackPoints ) {
        	this.trackpointDao.crea(tp);
        }
    }

    @Override
	public Point findLegCenter(Integer idLeg) {
    	Line boundaries = this.trackpointDao.findLegBoundaries(idLeg);
    	return boundaries.centralPosition();
    }

	@Override
	public List<BoatPOJO> readBoatsByRaceAndLeg(Integer idRace) {
		return this.boatDao.findBoatsInRace(idRace);
	}
}
