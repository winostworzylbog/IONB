package pl.edu.uj.ii.ionb.airportmanager.backend.daos.impl;

import javax.persistence.PersistenceException;


public class AirportDBException extends PersistenceException {
    
    public AirportDBException() {
    }

    public AirportDBException(String msg) {
        super(msg);
    }
    
    public AirportDBException(Throwable cause) {
        super(cause);
    }
    
    public AirportDBException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
