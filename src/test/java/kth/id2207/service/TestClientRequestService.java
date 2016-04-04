/**
 * 
 */
package kth.id2207.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.dropwizard.testing.junit.ResourceTestRule;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import kth.id2207.data.ClientRequestDAO;
import kth.id2207.model.ClientRequest;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
/**
 * @author pradeeppeiris
 *
 */
public class TestClientRequestService {

	private static final ClientRequestDAO requestDAO = mock(ClientRequestDAO.class);
	
	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new ClientRequestService(requestDAO)).build();
	
	
	private ClientRequest request1;
	
	@Before
	public void setUp() {
		request1 = new ClientRequest();
		request1.setId("111");
		request1.setName("TestClient2");
		request1.setType("event1");
		request1.setAttendees(100);
		request1.setBudget(1000d);
		request1.setStatus("open");
		request1.setAssignee("manager");
	}
	
	@Test
	public void testLoad() {
		final ImmutableList<ClientRequest> requests = ImmutableList.of(request1);
		when(requestDAO.findAll()).thenReturn(requests);
		
		final List<ClientRequest> response = 
				resources.client().target("/clientrequest").request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<ClientRequest>>() {});
		
		verify(requestDAO).findAll();
		assertEquals("It return same id", response.get(0).getId(), request1.getId());
		
	}

}
