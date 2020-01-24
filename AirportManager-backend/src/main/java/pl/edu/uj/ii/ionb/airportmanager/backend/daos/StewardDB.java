
package pl.edu.uj.ii.ionb.airportmanager.backend.daos;

import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Flight;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Steward;
import java.util.List;


public interface StewardDB{
    
    // Creates new steward. //
    public void createSteward(Steward steward);
    
    // Updates steward. //
    public void updateSteward(Steward steward);
    
    // Deletes steward. //
    public void removeSteward(Steward steward);
    
    // Returns steward specified by id. //
    public Steward getSteward(Long id);
    
    // Returns list of all stewards. //
    public List<Steward> getAllStewards();
    
    // Returns List of all flights associated to steward. //
    public List<Flight> getAllStewardsFlights(Steward steward);
}