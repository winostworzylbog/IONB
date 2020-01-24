
package pl.edu.uj.ii.ionb.airportmanager.web.userservice;

public class CannotCreateUserException extends RuntimeException{

    public CannotCreateUserException(String message) {
        super(message);
    }

    public CannotCreateUserException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
