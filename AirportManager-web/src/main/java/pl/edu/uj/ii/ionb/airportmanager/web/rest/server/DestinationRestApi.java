package pl.edu.uj.ii.ionb.airportmanager.web.rest.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.edu.uj.ii.ionb.airportmanager.services.DestinationService;
import pl.edu.uj.ii.ionb.airportmanager.transferobjects.DestinationTO;
import pl.edu.uj.ii.ionb.airportmanager.transferobjects.FlightTO;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.XmlWebApplicationContext;

@Path("/destination")
public class DestinationRestApi {

    private static final XmlWebApplicationContext APP_CONF =
            new XmlWebApplicationContext();
    @Context
    private ServletContext context;
    
    private DestinationService destService;
    private ObjectMapper mapper = new ObjectMapper();
    static Authentication authentication = 
            new UsernamePasswordAuthenticationToken("rest", "rest", 
                Arrays.asList(new SimpleGrantedAuthority[] 
                    {new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")}));

    public DestinationRestApi() {
        APP_CONF.setNamespace("spring-context");
    }
    
    private void initBeforeRequest() {
        APP_CONF.setServletContext(context);
        APP_CONF.refresh();
        destService = APP_CONF.getBean(DestinationService.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    
    private void destroyAfterRequest(){
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllDestinations() {
        try {
            initBeforeRequest();
            List<DestinationTO> ll = destService.getAllDestinations();
            return mapper.writerWithType(new TypeReference<List<DestinationTO>>() {})
                    .writeValueAsString(ll);
        } catch (DataAccessException ex) {
            throw new WebApplicationException(ex, Status.SERVICE_UNAVAILABLE);
        } catch (Exception ex) {
            throw new WebApplicationException(ex, Status.INTERNAL_SERVER_ERROR);
        } finally {
            destroyAfterRequest();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDestination(@PathParam("id") Long id) {
        try {
            initBeforeRequest();
            return mapper.writerWithType(new TypeReference<DestinationTO>() {})
                    .writeValueAsString(destService.getDestination(id));
        } catch (DataAccessException ex) {
            throw new WebApplicationException(ex, Status.SERVICE_UNAVAILABLE);
        } catch (Throwable ex) {
            throw new WebApplicationException(ex, Status.INTERNAL_SERVER_ERROR);
        } finally {
            destroyAfterRequest();
        }
    }

    @GET
    @Path("/{id}/incoming")
    @Produces(MediaType.APPLICATION_JSON)
    public String getIncomingFlights(@PathParam("id") Long id) {
        try {
            initBeforeRequest();
            DestinationTO des = destService.getDestination(id);
            return mapper.writerWithType(new TypeReference<List<FlightTO>>() {})
                    .writeValueAsString(destService.getAllIncomingFlights(des));
        } catch (DataAccessException ex) {
            throw new WebApplicationException(ex, Status.SERVICE_UNAVAILABLE);
        } catch (Throwable ex) {
            throw new WebApplicationException(ex, Status.INTERNAL_SERVER_ERROR);
        } finally {
            destroyAfterRequest();
        }
    }

    @GET
    @Path("/{id}/outcoming")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOutcomintFlights(@PathParam("id") Long id) {
        try {
            initBeforeRequest();
            DestinationTO des = destService.getDestination(id);
            return mapper.writerWithType(new TypeReference<List<FlightTO>>() {})
                    .writeValueAsString(destService.getAllOutcomingFlights(des));
        } catch (DataAccessException ex) {
            throw new WebApplicationException(ex, Status.SERVICE_UNAVAILABLE);
        } catch (Throwable ex) {
            throw new WebApplicationException(ex, Status.INTERNAL_SERVER_ERROR);
        } finally {
            destroyAfterRequest();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDestination(@PathParam("id") Long id) {
        try {
            initBeforeRequest();
            DestinationTO des = destService.getDestination(id);
            destService.removeDestination(des);
            return Response.status(Status.NO_CONTENT).build();
        } catch (DataAccessException ex) {
            throw new WebApplicationException(ex, Status.SERVICE_UNAVAILABLE);
        } catch (Throwable ex) {
            throw new WebApplicationException(ex, Status.INTERNAL_SERVER_ERROR);
        } finally {
            destroyAfterRequest();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String createDestination(String destination) {
        try {
            initBeforeRequest();
            DestinationTO des = mapper
                    .readValue(destination, new TypeReference<DestinationTO>() {});
            destService.createDestination(des);
            return mapper.writerWithType(new TypeReference<DestinationTO>() {})
                    .writeValueAsString(des);
        } catch (JsonProcessingException ex) {
            throw new WebApplicationException(ex, Status.BAD_REQUEST);
        } catch (DataAccessException ex) {
            throw new WebApplicationException(ex, Status.SERVICE_UNAVAILABLE);
        } catch (Throwable ex) {
            throw new WebApplicationException(ex, Status.INTERNAL_SERVER_ERROR);
        } finally {
            destroyAfterRequest();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateDestination(String destination, @PathParam("id") Long id) {
        try {
            initBeforeRequest();
            DestinationTO des = mapper
                    .readValue(destination, new TypeReference<DestinationTO>() {});
            des.setId(id);
            destService.updateDestination(des);
            return Response.status(Status.OK).build();
        } catch (JsonProcessingException ex) {
            throw new WebApplicationException(ex, Status.BAD_REQUEST);
        } catch (DataAccessException ex) {
            throw new WebApplicationException(ex, Status.SERVICE_UNAVAILABLE);
        } catch (Throwable ex) {
            throw new WebApplicationException(ex, Status.INTERNAL_SERVER_ERROR);
        } finally {
            destroyAfterRequest();
        }
    }
}