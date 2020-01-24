package pl.edu.uj.ii.ionb.airportmanager.services;

import pl.edu.uj.ii.ionb.airportmanager.transferobjects.FlightTO;
import pl.edu.uj.ii.ionb.airportmanager.transferobjects.StewardTO;
import java.util.List;


// Service interface for manipulating with {@link StewardTO} transfer object.

public interface StewardService {
    

//     Creates new steward record.
//     @param steward {@link StewardTO} to be created.

    void createSteward(StewardTO steward);
    

//     Updates steward record.
//     @param steward {@link StewardTO} to be updated.

    void updateSteward(StewardTO steward);
    

//     Remove steward record.
//     @param steward {@link StewardTO} to be removed.

    void removeSteward(StewardTO steward);
    

//     Find and return {@link StewardTO} record.
//     @param id ID of record.
//     @return {@link StewardTO} with ID {@code id}.

    StewardTO getSteward(Long id);
    

//     Finds and returns all {@link StewardTO} records.
//     @return All {@link StewardTO} records or empty list.

    List<StewardTO> getAllStewards();
    

//     Finds and returns all stewards flights.
//     @param steward Demanded {@link StewardTO}.
//     @return List of {@link FlightTO} or empty list.

    List<FlightTO> getAllStewardsFlights(StewardTO steward);
}