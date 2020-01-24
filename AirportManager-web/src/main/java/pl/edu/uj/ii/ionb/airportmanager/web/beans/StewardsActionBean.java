
package pl.edu.uj.ii.ionb.airportmanager.web.beans;

import pl.edu.uj.ii.ionb.airportmanager.services.FlightService;
import pl.edu.uj.ii.ionb.airportmanager.services.StewardService;
import pl.edu.uj.ii.ionb.airportmanager.transferobjects.FlightTO;
import pl.edu.uj.ii.ionb.airportmanager.transferobjects.StewardTO;
import java.util.LinkedList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.springframework.dao.DataAccessException;

@UrlBinding("/stewards/{$event}/{steward.id}")
public class StewardsActionBean extends BaseActionBean{
    
    @SpringBean
    private StewardService stewService;
    
    @SpringBean
    private FlightService flightService;
    
    private List<StewardTO> stewards;
    private List<FlightTO> flights;
    
    @ValidateNestedProperties({
            @Validate(on = {"addsteward", "savesteward"}, field = "firstName", 
                    required = true, trim = true, minlength = 1),
            @Validate(on = {"addsteward", "savesteward"}, field = "lastName", 
                    required = true, trim = true, minlength = 1)
            })
    private StewardTO steward;
    
    // @return flights list //
    public List<FlightTO> getFlights() {
        return flights;
    }

    // @return steward //
    public StewardTO getSteward() {
        return steward;
    }

    // @return stewards list //
    public List<StewardTO> getStewards() {
        return stewards;
    }

    // Sets steward //
    public void setSteward(StewardTO steward) {
        this.steward = steward;
    }
    
    // Loads steward from DB. //
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"editsteward", "savesteward", 
        "flights", "addflight", "deletesteward", "removeflight"})
    public void loadSteward(){
        String id = getContext().getRequest().getParameter("steward.id");
        if(id == null){
            return;
        }
        try{
            steward = stewService.getSteward(Long.parseLong(id));
            flights = stewService.getAllStewardsFlights(steward);
        } catch (DataAccessException ex){
            LocalizableError err = new LocalizableError("steward.error.service", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        } catch (Exception ex){
            LocalizableError err = new LocalizableError("steward.error.uknown", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        }
    }
    
    // Load flight from DB. //
    public FlightTO loadFlight(){
        String id = getContext().getRequest().getParameter("flight.id");
        if(id == null){
            return null;
        }
        try{
            return flightService.getFlight(Long.parseLong(id));
        } catch (DataAccessException ex){
            LocalizableError err = new LocalizableError("steward.error.service", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        } catch (Exception ex){
            LocalizableError err = new LocalizableError("steward.error.uknown", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        }
        return null;
    }
    
    // Method for showing all stewards. //
    @DefaultHandler
    @HandlesEvent("list")
    public Resolution showStewardsList(){
        try{
            stewards = stewService.getAllStewards();
        } catch (DataAccessException ex){
            LocalizableError err = new LocalizableError("steward.error.service", 
                    escapeHTML(ex.getMessage()));
            getContext().getValidationErrors().addGlobalError(err);
        } catch (Exception ex){
            LocalizableError err = new LocalizableError("steward.error.uknown", 
                    escapeHTML(ex.getMessage()));
            getContext().getValidationErrors().addGlobalError(err);
        }
        return new ForwardResolution("/steward/list.jsp");
    }
    
    // Method for creating new steward //
    @HandlesEvent("addsteward")
    public Resolution addNewSteward(){
        try{
            System.out.println("created steward: " + steward);
            stewService.createSteward(steward);
            System.out.println("after created steward: " + steward);
            getContext().getMessages().add(new LocalizableMessage("steward.created", 
                    escapeHTML(steward.getFirstName()),
                    escapeHTML(steward.getLastName())));
            System.out.println("after after created steward: " + steward);
        } catch (DataAccessException ex){
            LocalizableError err = new LocalizableError("steward.error.service", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        } catch (Exception ex){
            LocalizableError err = new LocalizableError("steward.error.uknown", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        }
        return new RedirectResolution(this.getClass(), "list").flash(this);
    }
    
    // Method for edit formular. //
    @HandlesEvent("editsteward")
    public Resolution editFormular(){
        ForwardResolution f = new ForwardResolution("/steward/edit.jsp?createnew=false");
        f.addParameter("event", "edit");
        return f;
    }
    
    // Method for create formular. //
    @HandlesEvent("createsteward")
    public Resolution createFormular(){
        return new ForwardResolution("/steward/edit.jsp?createnew=true");
    }
    
    // Method for updating steward. //
    @HandlesEvent("savesteward")
    public Resolution saveStewardsEdit(){
        try{
            stewService.updateSteward(steward);
            getContext().getMessages().add(new LocalizableMessage("steward.updated", 
                    escapeHTML(steward.getFirstName()),
                    escapeHTML(steward.getLastName())));
        } catch (DataAccessException ex){
            LocalizableError err = new LocalizableError("steward.error.service", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        } catch (Exception ex){
            LocalizableError err = new LocalizableError("steward.error.uknown", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        }
        return new RedirectResolution(this.getClass(), "list");
    }
    
    // Method for deleting steward. //
    @HandlesEvent("deletesteward")
    public Resolution removeSteward(){
        try{
            loadSteward();
            stewService.removeSteward(steward);
            getContext().getMessages().add(new LocalizableMessage("steward.deleted", 
                    escapeHTML(steward.getFirstName()),
                    escapeHTML(steward.getLastName())));
        } catch (DataAccessException ex){
            LocalizableError err = new LocalizableError("steward.error.service", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        } catch (Exception ex){
            LocalizableError err = new LocalizableError("steward.error.uknown", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        }
        return new RedirectResolution(this.getClass(),"list");
    }
    
    // Method for showing all stewards flights. //
    @HandlesEvent("flights")
    public Resolution showAllStewardsFlights(){
        try{
            flights = stewService.getAllStewardsFlights(steward);
        } catch (DataAccessException ex){
            LocalizableError err = new LocalizableError("steward.error.service", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        } catch (Exception ex){
            LocalizableError err = new LocalizableError("steward.error.uknown", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        }
        return new ForwardResolution("/steward/flights.jsp?add=false");
    }
    
    // Method for showing possible flights to add for this {@code steward}. //
    @HandlesEvent("addflight")
    public Resolution showStewardsFlightToAdd(){
        try{
            flights = stewService.getAllStewardsFlights(steward);
            List<FlightTO> allFlights = flightService.getAllFlights();
            flights = getRemainingFights(flights, allFlights);
        } catch (DataAccessException ex){
            LocalizableError err = new LocalizableError("steward.error.service", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        } catch (Exception ex){
            LocalizableError err = new LocalizableError("steward.error.uknown", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        }
        return new ForwardResolution("/steward/flights.jsp?add=true");
    }
    
    // Method for removing stewards flight. //
    @HandlesEvent("removeflight")
    public Resolution removeStewardsFlight(){
       try{
            FlightTO flight = loadFlight();
            loadSteward();
            if(flight == null){
                throw new IllegalStateException("Missing flight id");
            }
            flight.getStewList().remove(steward);
            flightService.updateFlight(flight);
            flights = stewService.getAllStewardsFlights(steward);
            stewService.updateSteward(steward);
            getContext().getMessages().add(new LocalizableMessage("steward.flight.taken", 
                    escapeHTML(flight.getOrigin().getCity()),
                    escapeHTML(flight.getTarget().getCity())));
        } catch (DataAccessException ex){
            LocalizableError err = new LocalizableError("steward.error.service", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        } catch (Exception ex){
            LocalizableError err = new LocalizableError("steward.error.uknown", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        }
        if(getContext().getRequest().getParameter("event").equals("flights")){
            ForwardResolution res = new ForwardResolution(this.getClass(), "flights");
            return res;
        } else {
            return new ForwardResolution(this.getClass(), "editsteward");
        }
    }
    
    // Method for adding steards flights. //
    @HandlesEvent("addflighttolist")
    public Resolution addStewardsFlight(){
        try{
            FlightTO flight = loadFlight();
            if(flight == null){
                throw new IllegalStateException("Missing flight id");
            }
            flight.getStewList().add(steward);
            flightService.updateFlight(flight);
            getContext().getMessages().add(new LocalizableMessage("steward.flight.added", 
                    escapeHTML(flight.getOrigin().getCity()),
                    escapeHTML(flight.getTarget().getCity())));
        } catch (DataAccessException ex){
            LocalizableError err = new LocalizableError("steward.error.service", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        } catch (Exception ex){
            LocalizableError err = new LocalizableError("steward.error.uknown", 
                    escapeHTML(ex.toString()));
            getContext().getValidationErrors().addGlobalError(err);
        }
        return new ForwardResolution(this.getClass(), "addflight");
    }
    
    // Method for cancel button. //
    @HandlesEvent("cancelsteward")
    public Resolution doNothing(){
        return new RedirectResolution(this.getClass());
    }

    // Returns complement of {@code stewFlights} in {@code allFlights} //
    private List<FlightTO> getRemainingFights(List<FlightTO> stewFlights, List<FlightTO> allFlights){
        List<FlightTO> out = new LinkedList<>();
        for(FlightTO f : allFlights){
            if(!stewFlights.contains(f)){
                out.add(f);
            }
        }
        return out;
    }
}
