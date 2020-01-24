package pl.edu.uj.ii.ionb.airportmanager.backend.services.impl;

import pl.edu.uj.ii.ionb.airportmanager.backend.daos.AirPlaneDB;
import pl.edu.uj.ii.ionb.airportmanager.backend.daos.DestinationDB;
import pl.edu.uj.ii.ionb.airportmanager.backend.daos.FlightDB;
import pl.edu.uj.ii.ionb.airportmanager.backend.daos.StewardDB;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Flight;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.EntityDTOTransformer;
import static pl.edu.uj.ii.ionb.airportmanager.backend.entities.EntityDTOTransformer.*;
import pl.edu.uj.ii.ionb.airportmanager.services.FlightService;
import pl.edu.uj.ii.ionb.airportmanager.transferobjects.FlightTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Implementation of interface FlightService. //
@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightDB flightDB;
    @Autowired
    private StewardDB stewardDB;
    @Autowired
    private DestinationDB destinationDB;
    @Autowired
    private AirPlaneDB AirPlaneDB;

    public void setFlightDB(FlightDB flightDB) {
        this.flightDB = flightDB;
    }

    public void setStewardDB(StewardDB stewardDB) {
        this.stewardDB = stewardDB;
    }

    public void setDesttinationDB(DestinationDB destinationDB) {
        this.destinationDB = destinationDB;
    }

    public void setAirPlaneDB(AirPlaneDB airplaneDB) {
        this.AirPlaneDB = airplaneDB;
    }

    @Override
    @Transactional//
    public void createFlight(FlightTO flightTO) throws DataAccessException {
        Flight flight = EntityDTOTransformer.flightTOConvert(flightTO);
        if (flight != null) {
            for (int i = 0; i < flight.getStewardList().size(); i++) {
                if (flight.getStewardList().get(i).getId() == null){
                    stewardDB.createSteward(flight.getStewardList().get(i));
                    flightTO.getStewList().get(i).setId(flight.getStewardList().get(i).getId());
                }
            }

            if (flight.getAirplane() != null && flight.getAirplane().getId() == null) {
                AirPlaneDB.createAirplane(flight.getAirplane());
                flightTO.getAirplaneTO().setId(flight.getAirplane().getId());
            }
            if (flight.getOrigin() != null && flight.getOrigin().getId() == null) {
                destinationDB.createDestination(flight.getOrigin());
                flightTO.getOrigin().setId(flight.getOrigin().getId());
            }
            if (flight.getTarget() != null  && flight.getTarget().getId() == null) {
                destinationDB.createDestination(flight.getTarget());
                flightTO.getTarget().setId(flight.getTarget().getId());
            }
        }

        flightDB.createFlight(flight);
        flightTO.setId(flight.getId());
    }

    @Override
    @Transactional
    public void updateFlight(FlightTO flightTO) throws DataAccessException {
        flightDB.updateFlight(EntityDTOTransformer.flightTOConvert(flightTO));
    }

    @Override
    @Transactional
    public void removeFlight(FlightTO flightTO) throws DataAccessException {
        flightDB.removeFlight(EntityDTOTransformer.flightTOConvert(flightTO));
    }

    @Override
    @Transactional
    public FlightTO getFlight(Long id) throws DataAccessException {
        return EntityDTOTransformer.flightConvert(flightDB.getFlight(id));
    }

    @Override
    @Transactional
    public List<FlightTO> getAllFlights() throws DataAccessException {
        List<Flight> flights = flightDB.getAllFlight();
        return flightListConvert(flights);
    }
}
