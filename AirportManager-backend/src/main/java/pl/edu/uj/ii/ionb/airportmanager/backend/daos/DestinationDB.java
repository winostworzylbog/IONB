package pl.edu.uj.ii.ionb.airportmanager.backend.daos;

import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Destination;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Flight;
import java.util.List;

// Data access object (DAO) layer for Destination entity. //
public interface DestinationDB {
    

//     Creates new destination.
//     @param destination specifies the destination to be created

    public void createDestination(Destination destination);
    

//     Updates destination.
//     @param destination specifies new destination

    public void updateDestination(Destination destination);
    

//     Removes destination.
//     @param destination specifies destination to be removed

    public void removeDestination(Destination destination);
    

//     Returns all destinations.
//     @return List of destinations

    public List<Destination> getAllDestinations();
    

//     Returns one destination according to id.
//     @param id specifies id of destination
//     @return desired destination

    public Destination getDestination(Long id);
    

//     Returns all flights that have this destination set as target.
//     @param destination specifies target destination
//     @return List of flights

    public List<Flight> getAllIncomingFlights(Destination destination);
    

//     Returns all flights that have this destination set as origin.
//     @param destination specifies origin destination
//     @return List of flights

    public List<Flight> getAllOutcomingFlights(Destination destination);       
}
