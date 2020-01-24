package pl.edu.uj.ii.ionb.airportmanager.backend.daos.impl;

import pl.edu.uj.ii.ionb.airportmanager.backend.daos.AirPlaneDB;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Airplane;
import pl.edu.uj.ii.ionb.airportmanager.backend.entities.Flight;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;


@Repository
public class AirPlaneDBimpl implements AirPlaneDB {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createAirplane(Airplane airplane) {

        if (airplane == null) {
            throw new IllegalArgumentException("Airplane argument is null");
        } else if (airplane.getName() == null) {
            throw new IllegalArgumentException("Airplane name wasn't set");
        } else if (airplane.getType() == null) {
            throw new IllegalArgumentException("Airplane type wasn't set");
        } else if (airplane.getCapacity() <= 0) {
            throw new IllegalArgumentException("Airplane capacity must be greater than 0");
        }

        em.persist(airplane);
    }

    @Override
    public void updateAirplane(Airplane airplane) {

        if (airplane == null) {
            throw new IllegalArgumentException("Airplane argument is null");
        } else if (airplane.getName() == null) {
            throw new IllegalArgumentException("Airplane name wasn't set");
        } else if (airplane.getType() == null) {
            throw new IllegalArgumentException("Airplane type wasn't set");
        } else if (airplane.getCapacity() <= 0) {
            throw new IllegalArgumentException("Airplane capacity must be greater than 0");
        }

        if (airplane.getId() == null) {
            throw new IllegalArgumentException("airplane id is null");
        }

        em.merge(airplane);

    }

    @Override
    public void removeAirplane(Airplane airplane){

        if (airplane == null) {
            throw new IllegalArgumentException("airplane argument is null");
        }

        if (airplane.getId() == null) {
            throw new IllegalArgumentException("airplane id is null");
        }

        Airplane airplaneFromDB = em.find(Airplane.class, airplane.getId());

        if (airplaneFromDB == null) {
            throw new AirportDBException("Airplane isn't in database");
        }

        em.remove(airplaneFromDB);

    }

    @Override
    public Airplane getAirplane(Long id){

        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        Airplane airplane = em.find(Airplane.class, id);

        if (airplane == null){
            throw new AirportDBException("Airplane with given id isn't in database");
        } else {
            return airplane;
        }

    }

    @Override
    public List<Airplane> getAllAirplanes() {

        Query allAirplanes = em.createNamedQuery("Airplane.findAllAirplanes");

        List<Airplane> airplanes = allAirplanes.getResultList();

        return airplanes;
    }

    @Override
    public List<Flight> getAllAirplanesFlights(Airplane airplane) {

        if (airplane == null) {
            throw new IllegalArgumentException("airplane arugument is null");
        }

        Query flightsQuery = em.createNamedQuery("Flight.findByAirplane");
        flightsQuery.setParameter("airplane", airplane.getId());

        List<Flight> flights = flightsQuery.getResultList();

        return flights;
    }
}
