/**
 * 
 */
package kth.id2207.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import kth.id2207.data.ClientRequestDAO;
import kth.id2207.model.ClientRequest;

/**
 * @author pradeeppeiris
 *
 */
@Path("/clientrequest")
@Produces(MediaType.APPLICATION_JSON)
public class ClientRequestService {

	private ClientRequestDAO requestDAO;
	
	public ClientRequestService(ClientRequestDAO requestDAO) {
		this.requestDAO = requestDAO;
	}
	
	@GET
    public List<ClientRequest> load() {
		return requestDAO.findAll();
    }
	
	@GET
	@Path("/{role}")
    public List<ClientRequest> load(@PathParam("role") String role) {
		System.out.println(role);
		return requestDAO.findAll(role);
    }
	
	@POST
	public void create(ClientRequest clientRequest) {
		System.out.println(clientRequest.toCSV());
		clientRequest.setId(String.valueOf(new Date().getTime()));
		requestDAO.create(clientRequest);
		
	}
	
	@PUT
	public void update(ClientRequest clientRequest) {
		System.out.println(clientRequest.toCSV());
		requestDAO.update(clientRequest);
		
	}
}
