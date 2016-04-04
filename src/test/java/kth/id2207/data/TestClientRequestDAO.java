/**
 * 
 */
package kth.id2207.data;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import kth.id2207.model.ClientRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author pradeeppeiris
 *
 */
public class TestClientRequestDAO {
	private static final String DATA_FILE = "data/test.data";
	private ClientRequestDAO requestDAO;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Files.createFile(Paths.get(DATA_FILE));
		requestDAO = new ClientRequestDAO(DATA_FILE);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		Files.delete(Paths.get(DATA_FILE));
	}

	@Test
	public void testCreate() {
		ClientRequest request = new ClientRequest();
		request.setId("1000");
		request.setName("TestClient");
		request.setType("event1");
		request.setAttendees(100);
		request.setBudget(1000d);
		request.setStatus("open");
		request.setAssignee("manager");
		
		requestDAO.create(request);
		
		try {
			List<String> lines = Files.readAllLines(Paths.get(DATA_FILE), Charset.forName("UTF8"));
			String line = lines.get(0);
			ClientRequest assertRequest = new ClientRequest(line.split("\","));
			

			assertEquals("It's same client request", request.getId(), assertRequest.getId());
			assertEquals("It's same client request", request.getName(), assertRequest.getName());
			assertEquals("It's same client request", request.getType(), assertRequest.getType());
			assertEquals("It's same client request", request.getAttendees(), assertRequest.getAttendees());
			assertEquals("It's same client request", request.getBudget(), assertRequest.getBudget());
			assertEquals("It's same client request", request.getStatus(), assertRequest.getStatus());
			assertEquals("It's same client request", request.getAssignee(), assertRequest.getAssignee());
			
		} catch (IOException e) {
			fail("Data file not found");
		}
	}
	
	
	@Test
	public void testUpdate() {
		ClientRequest request = new ClientRequest();
		request.setId("1000");
		request.setName("TestClient");
		request.setType("event1");
		request.setAttendees(100);
		request.setBudget(1000d);
		request.setStatus("open");
		request.setAssignee("manager");
		
		requestDAO.create(request);
		
		request.setStatus("reject");
		requestDAO.update(request);
		
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(DATA_FILE), Charset.forName("UTF8"));
			String line = lines.get(0);
			ClientRequest assertRequest = new ClientRequest(line.split("\","));
			assertEquals("Status should be rejected", assertRequest.getStatus(), "reject");
			
		} catch (IOException e) {
			fail("Data file not found");
		}
		
		
	}
	
	@Test
	public void testFindAll() {
		ClientRequest request1 = new ClientRequest();
		request1.setId("111");
		request1.setName("TestClient2");
		request1.setType("event1");
		request1.setAttendees(100);
		request1.setBudget(1000d);
		request1.setStatus("open");
		request1.setAssignee("manager");
		
		requestDAO.create(request1);
		
		ClientRequest request2 = new ClientRequest();
		request2.setId("222");
		request2.setName("TestClient2");
		request2.setType("event1");
		request2.setAttendees(100);
		request2.setBudget(1000d);
		request2.setStatus("open");
		request2.setAssignee("manager");
		
		requestDAO.create(request2);
		
		List<ClientRequest> requests = requestDAO.findAll();
		assertEquals("Number of requests should be two", requests.size(), 2);
		assertEquals("Id of the first request", requests.get(0).getId(), "111");
		assertEquals("Id of the first request", requests.get(1).getId(), "222");
	}
	
	@Test
	public void testFindByRole() {
		
		ClientRequest request1 = new ClientRequest();
		request1.setId("111");
		request1.setName("TestClient2");
		request1.setType("event1");
		request1.setAttendees(100);
		request1.setBudget(1000d);
		request1.setStatus("open");
		request1.setAssignee("manager");
		
		requestDAO.create(request1);
		
		ClientRequest request2 = new ClientRequest();
		request2.setId("222");
		request2.setName("TestClient2");
		request2.setType("event1");
		request2.setAttendees(100);
		request2.setBudget(1000d);
		request2.setStatus("open");
		request2.setAssignee("staff");
		
		requestDAO.create(request2);
		
		List<ClientRequest> requests = requestDAO.findAll("manager");
		assertEquals("Number of requests assigned to manager should be 2", requests.size(), 1);
	}
}
