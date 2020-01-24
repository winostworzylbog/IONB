package pl.edu.uj.ii.ionb.airportmanager.services;

import pl.edu.uj.ii.ionb.airportmanager.transferobjects.DestinationTO;
import pl.edu.uj.ii.ionb.airportmanager.transferobjects.FlightTO;
import java.util.List;


public interface DestinationService {

//     Create new destination
//     @param destination specifies new destination to be created

    public void createDestination(DestinationTO destination);

//     Update destination
//     @param destination specifies new destination to be updated

    public void updateDestination(DestinationTO destination);

//     Remove destination
//     @param destination specifies new destination to deleted

    public void removeDestination(DestinationTO destination);

//     Get destination with specified id
//     @param id specifies id of destination
//     @return DestinationTO witch given id

    public DestinationTO getDestination(Long id);

//     Returns list of all destinations
//     @return List of all destinations

    public List<DestinationTO> getAllDestinations();

//     Returns list of all flights arriving to destination
//     @param destination specifies destination to which flights arrive
//     @return List of all flights arriving to specified destination

    public List<FlightTO> getAllIncomingFlights(DestinationTO destination);

//     Returns list of all flights departuring from destination
//     @param destination specifies destination from which flights departure
//     @return List of all flights departuring from specified destination

    public List<FlightTO> getAllOutcomingFlights(DestinationTO destination);
}
