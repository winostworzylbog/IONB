package pl.edu.uj.ii.ionb.airportmanager.backend.services.impl;
import pl.edu.uj.ii.ionb.airportmanager.backend.daos.AirPlaneDB;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Airplane;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.EntityDTOTransformer;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Flight;
import pl.edu.uj.ii.ionb.airportmanager.services.AirplaneService;
import pl.edu.uj.ii.ionb.airportmanager.transferobjects.AirplaneTO;
import pl.edu.uj.ii.ionb.airportmanager.transferobjects.FlightTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    private AirPlaneDB AirPlaneDB;

    public void setAirPlaneDB(AirPlaneDB AirPlaneDB) {
        this.AirPlaneDB = AirPlaneDB;
    }

    @Override
    @Transactional
    public void createAirplane(AirplaneTO airplane) throws DataAccessException {
        Airplane airplaneEntity = EntityDTOTransformer.airplaneTOConvert(airplane);
        AirPlaneDB.createAirplane(airplaneEntity);
    }

    @Override
    @Transactional
    public void updateAirplane(AirplaneTO airplane) throws DataAccessException {
        Airplane airplaneEntity = EntityDTOTransformer.airplaneTOConvert(airplane);
        AirPlaneDB.updateAirplane(airplaneEntity);
    }

    @Override
    @Transactional
    public void removeAirplane(AirplaneTO airplane) throws DataAccessException {
        Airplane airplaneEntity = EntityDTOTransformer.airplaneTOConvert(airplane);
        AirPlaneDB.removeAirplane(airplaneEntity);
    }

    @Override
    @Transactional
    public AirplaneTO getAirplane(Long id) throws DataAccessException {
        return EntityDTOTransformer.airplaneConvert(AirPlaneDB.getAirplane(id));
    }

    @Override
    @Transactional
    public List<AirplaneTO> getAllAirplanes() throws DataAccessException {
        List<AirplaneTO> airplanesTO = new ArrayList<>();
        List<Airplane> airplanes = AirPlaneDB.getAllAirplanes();
        for (Airplane a : airplanes) {
            AirplaneTO airplaneTO = EntityDTOTransformer.airplaneConvert(a);
            airplanesTO.add(airplaneTO);
        }
        return airplanesTO;
    }

    @Override
    @Transactional
    public List<FlightTO> getAllAirplanesFlights(AirplaneTO airplane) throws DataAccessException {
        List<Flight> flights;
        List<FlightTO> flightsTO = new ArrayList<>();

        Airplane airplaneEntity = EntityDTOTransformer.airplaneTOConvert(airplane);
        flights = AirPlaneDB.getAllAirplanesFlights(airplaneEntity);

        if (flights.isEmpty()) {
            return flightsTO;
        } else {
            for (Flight f : flights) {
                flightsTO.add(EntityDTOTransformer.flightConvert(f));
            }
            return flightsTO;
        }
    }
}
