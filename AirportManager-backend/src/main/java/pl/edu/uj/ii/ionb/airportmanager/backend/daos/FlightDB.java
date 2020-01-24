package pl.edu.uj.ii.ionb.airportmanager.backend.daos;

import pl.edu.uj.ii.ionb.airportmanager.backend.daos.impl.AirportDBException;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Flight;
import java.util.List;

// DAO layer for manipulatig with {@link Flight} entity and database. //
public interface FlightDB {
    
    // Creates {@link Flight} {@code flight} in DB. //
    public void createFlight(Flight flight) throws AirportDBException, IllegalArgumentException;
    
    // Updates {@link Flight} {@code flight} in DB. //
    public void updateFlight(Flight flight) throws AirportDBException, IllegalArgumentException;
    
    // Removes {@link Flight} {@code flight} from DB //
    public void removeFlight(Flight flight) throws AirportDBException, IllegalArgumentException;
    
    // Finds and return {@link Flight} {@code flight} in DB acording to {@code id} //
    public Flight getFlight(Long id) throws AirportDBException, IllegalArgumentException;
    
    // Finds all flights in DB //
    public List<Flight> getAllFlight() throws AirportDBException;
}
