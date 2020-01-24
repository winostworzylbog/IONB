package pl.edu.uj.ii.ionb.airportmanager.backend.services.impl;

import pl.edu.uj.ii.ionb.airportmanager.backend.daos.FlightDB;
import pl.edu.uj.ii.ionb.airportmanager.backend.daos.StewardDB;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Flight;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Steward;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.EntityDTOTransformer;
import pl.edu.uj.ii.ionb.airportmanager.services.StewardService;
import pl.edu.uj.ii.ionb.airportmanager.transferobjects.FlightTO;
import pl.edu.uj.ii.ionb.airportmanager.transferobjects.StewardTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Implementation of steward service. //
@Service
@Transactional(readOnly = true)
public class StewardServiceImpl implements StewardService{

    @Autowired
    private StewardDB stewardDB;
    
    @Autowired
    private FlightDB flightDB;

    // Sets {@link StewardDB} for manipulating with DB. //
    public void setStewardDB(StewardDB stewardDB) {
        this.stewardDB = stewardDB;
    }
    
    //  Sets {@link FlightDB} for manipulating with DB. //
    public void setFlightDb(FlightDB flightDB) {
        this.flightDB = flightDB;
    }
    
    @Override
    @Transactional(readOnly = false)
    public void createSteward(StewardTO steward) throws DataAccessException {
        Steward stew = EntityDTOTransformer.stewardTOConvert(steward);
        stewardDB.createSteward(stew);
        steward.setId(stew.getId());
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSteward(StewardTO steward) throws DataAccessException {
        Steward stew = EntityDTOTransformer.stewardTOConvert(steward);
        stewardDB.updateSteward(stew);
    }

    @Override
    @Transactional(readOnly = false)
    public void removeSteward(StewardTO steward) throws DataAccessException {
        Steward stew = EntityDTOTransformer.stewardTOConvert(steward);
        List<Flight> flights = stewardDB.getAllStewardsFlights(stew);
        for(Flight f : flights){
            f.getStewardList().remove(stew);
            flightDB.updateFlight(f);
        }
        stewardDB.removeSteward(stew);
    }

    @Override
    public StewardTO getSteward(Long id) throws DataAccessException {
        Steward s = stewardDB.getSteward(id);
        return EntityDTOTransformer.stewardConvert(s);
    }

    @Override
    public List<StewardTO> getAllStewards() throws DataAccessException {
        List<Steward> list = stewardDB.getAllStewards();
        List<StewardTO> out = new ArrayList<>(list.size());
        for(Steward s : list){
            out.add(EntityDTOTransformer.stewardConvert(s));
        }
        return out;
    }

    @Override
    public List<FlightTO> getAllStewardsFlights(StewardTO steward) throws DataAccessException {
        Steward stew = EntityDTOTransformer.stewardTOConvert(steward);
        List<Flight> flights = stewardDB.getAllStewardsFlights(stew);
        List<FlightTO> out = new ArrayList<>(flights.size());
        for(Flight f : flights){
            out.add(EntityDTOTransformer.flightConvert(f));
        }
        return out;
    }
}