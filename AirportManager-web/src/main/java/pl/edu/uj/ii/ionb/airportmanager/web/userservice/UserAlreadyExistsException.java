
package pl.edu.uj.ii.ionb.airportmanager.web.userservice;

public class UserAlreadyExistsException extends CannotCreateUserException{

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
