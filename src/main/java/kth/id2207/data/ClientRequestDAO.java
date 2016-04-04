/**
 * 
 */
package kth.id2207.data;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import kth.id2207.model.ClientRequest;

/**
 * @author pradeeppeiris
 *
 */
public class ClientRequestDAO {
	private String dataFile;

	public String getDataFile() {
		return dataFile;
	}

	public void setDataFile(String dataFile) {
		this.dataFile = dataFile;
	}
	
	public ClientRequestDAO(String dataFile) {
		this.dataFile = dataFile;
	}
	
	public void create(ClientRequest clientRequest) {
		try {
			Files.write(Paths.get(dataFile), clientRequest.toCSV().getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(ClientRequest clientRequest) {
		try {
			Path tmpFile = Files.createTempFile("datafile", ".tmp");
			List<String> lines = Files.readAllLines(Paths.get(dataFile), Charset.forName("UTF8"));
			for(String line : lines) {
				if(line.contains(clientRequest.getId())) {
					line = clientRequest.toCSV();
				} else {
					line += "\n";
				}
				Files.write(tmpFile, line.getBytes(), StandardOpenOption.APPEND);
			}
			Files.delete(Paths.get(dataFile));
			Files.copy(tmpFile, Paths.get(dataFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<ClientRequest> findAll() {
		List<ClientRequest> requests = new ArrayList<ClientRequest>();
		try {
			
			List<String> lines = Files.readAllLines(Paths.get(dataFile), Charset.forName("UTF8"));
			for(String line : lines) {
				ClientRequest req = new ClientRequest(line.split("\","));
				requests.add(req);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return requests;
	}
	
	public List<ClientRequest> findAll(String role) {
		List<ClientRequest> requests = new ArrayList<ClientRequest>();
		try {
			
			List<String> lines = Files.readAllLines(Paths.get(dataFile), Charset.forName("UTF8"));
			for(String line : lines) {
				ClientRequest req = new ClientRequest(line.split("\","));
				if(req.getAssignee().equals(role)) {
					requests.add(req);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return requests;
	}
}
