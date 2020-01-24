package pl.edu.uj.ii.ionb.airportmanager.services;

import pl.edu.uj.ii.ionb.airportmanager.transferobjects.AirplaneTO;
import pl.edu.uj.ii.ionb.airportmanager.transferobjects.FlightTO;
import java.util.List;


public interface AirplaneService {


//    Creates airplane
//    @param airplane airplane to be created

    public void createAirplane(AirplaneTO airplane);


//     Updates airplane
//     @param airplane airplane to be updated

    public void updateAirplane(AirplaneTO airplane);
    

//     Removes airplane
//     @param airplane airplane to be removed

    public void removeAirplane(AirplaneTO airplane);


//     Returns airplane according to ID
//
//     @param id id of desired airplane
//     @return airplane from DB

    public AirplaneTO getAirplane(Long id);


//     Returns all airplanes from DB
//     @return all airplanes from DB

    public List<AirplaneTO> getAllAirplanes();


//     Returns all flights for one airplane
//     @param airplane airplane, whose flights we are looking for
//     @return List of flights

    public List<FlightTO> getAllAirplanesFlights(AirplaneTO airplane);
}
