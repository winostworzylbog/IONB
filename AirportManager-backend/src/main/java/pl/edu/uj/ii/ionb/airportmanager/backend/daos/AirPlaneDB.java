package pl.edu.uj.ii.ionb.airportmanager.backend.daos;

import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Airplane;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Flight;
import java.util.List;

// Data access object (DAO) layer for Airplane entity. //
public interface AirPlaneDB {
    
    // Creates new airplane. //
    public void createAirplane(Airplane airplane);
    
    // Updates an existing airplane //
    public void updateAirplane(Airplane airplane);
    
    // Removes an existing airplane. //
    public void removeAirplane(Airplane airplane);
    
    // Returns Airplane with desired id. //
    public Airplane getAirplane(Long id);

    // Returns all airplanes. //
    public List<Airplane> getAllAirplanes();

    // Returns all airplanes for a flight. //
    public List<Flight> getAllAirplanesFlights(Airplane airplane);
    
}
