
package pl.edu.uj.ii.ionb.airportmanager.backend.services.impl;

import pl.edu.uj.ii.ionb.airportmanager.backend.daos.DestinationDB;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Destination;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.EntityDTOTransformer;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Flight;
import pl.edu.uj.ii.ionb.airportmanager.services.DestinationService;
import pl.edu.uj.ii.ionb.airportmanager.transferobjects.DestinationTO;
import pl.edu.uj.ii.ionb.airportmanager.transferobjects.FlightTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DestinationServiceImpl implements DestinationService {
    
    @Autowired
    private DestinationDB destinationDB;
    
    public void setDestinationDB(DestinationDB destinationDB){
        this.destinationDB = destinationDB;
    }

    @Override
    @Transactional
    public void createDestination(DestinationTO destinationTo) throws DataAccessException {
        Destination destination = EntityDTOTransformer.destinationTOConvert(destinationTo);
        destinationDB.createDestination(destination);
        destinationTo.setId(destination.getId());
    }

    @Override
    @Transactional
    public void updateDestination(DestinationTO destinationTo) throws DataAccessException {
        Destination destination = EntityDTOTransformer.destinationTOConvert(destinationTo);
        destinationDB.updateDestination(destination);
    }

    @Override
    @Transactional
    public void removeDestination(DestinationTO destinationTo) throws DataAccessException {
        Destination destination = EntityDTOTransformer.destinationTOConvert(destinationTo);
        destinationDB.removeDestination(destination);
    }
    
    @Override
    @Transactional
    public DestinationTO getDestination(Long id) throws DataAccessException {
        Destination destination = destinationDB.getDestination(id);
        return EntityDTOTransformer.destinationConvert(destination);
    }

    @Override
    @Transactional
    public List<DestinationTO> getAllDestinations() throws DataAccessException {
        List<DestinationTO> destinationsToList = new ArrayList<>();
        List<Destination> destinationsList;
        destinationsList = destinationDB.getAllDestinations();
        for(Destination des : destinationsList){
            DestinationTO desTo = EntityDTOTransformer.destinationConvert(des);
            destinationsToList.add(desTo);
        }
        return destinationsToList;
    }

    @Override
    @Transactional
    public List<FlightTO> getAllIncomingFlights(DestinationTO destinationTo) throws DataAccessException {
        List<FlightTO> flightsToList = new ArrayList<>();
        List<Flight> flightsList;
        flightsList = destinationDB.getAllIncomingFlights
                (EntityDTOTransformer.destinationTOConvert(destinationTo));
        for(Flight flight : flightsList){
            FlightTO flightTo = EntityDTOTransformer.flightConvert(flight);
            flightsToList.add(flightTo);
        }
        return flightsToList;
    }

    @Override
    @Transactional
    public List<FlightTO> getAllOutcomingFlights(DestinationTO destinationTo) throws DataAccessException {
        List<FlightTO> flightsToList = new ArrayList<>();
        List<Flight> flightsList;
        flightsList = destinationDB.getAllOutcomingFlights
                (EntityDTOTransformer.destinationTOConvert(destinationTo));
        for(Flight flight : flightsList){
            FlightTO flightTo = EntityDTOTransformer.flightConvert(flight);
            flightsToList.add(flightTo);
        }
        return flightsToList;
    }    
}
