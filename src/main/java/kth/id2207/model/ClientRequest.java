/**
 * 
 */
package kth.id2207.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author rafa
 *
 */
public class ClientRequest {

	private String id;
	private String name;
	private String type;
	private List<String> pref;
	private String fromDate;
	private String toDate;
	private Integer attendees;
	private Double budget;
	private String status; // rejected, accepted, open, closed, ...
	private String assignee;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public List<String> getPref() {
		return pref;
	}
	
	public void setPref(List<String> pref) {
		this.pref = pref;
	}
	
	public String getFromDate() {
		return fromDate;
	}
	
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	public Integer getAttendees() {
		return attendees;
	}
	
	public void setAttendees(Integer attendees) {
		this.attendees = attendees;
	}
	
	public Double getBudget() {
		return budget;
	}
	
	public void setBudget(Double budget) {
		this.budget = budget;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public ClientRequest() {
		
	}
	
	public ClientRequest(String[] params) {
		this.id =  params[0].replaceAll("\"", "");
		this.name = params[1].replaceAll("\"", "");
		this.type = params[2].replaceAll("\"", "");
		this.pref = params[3] != null ? Arrays.asList(params[3].replaceAll("\"", "").split(",")) : null;
		this.fromDate = params[4].replaceAll("\"", "");
		this.toDate = params[5].replaceAll("\"", "");
		this.attendees = params[6] != null ? Integer.valueOf(params[6].replaceAll("\"", "")) : null;
		this.budget = params[7] != null ? Double.valueOf(params[7].replaceAll("\"", "")) : null;
		this.status = params[8].replaceAll("\"", "");
		this.assignee = params[9].replaceAll("\"", "");
	}
	
	public String toCSV() {
		StringBuffer sb =  new StringBuffer();
		sb.append("\"").append(id).append("\",");
		sb.append("\"").append(name).append("\",");
		sb.append("\"").append(type).append("\",");
		sb.append("\"").append(pref != null ? parsePref(pref) : "").append("\",");
		sb.append("\"").append(fromDate).append("\",");
		sb.append("\"").append(toDate).append("\",");
		sb.append("\"").append(attendees).append("\",");
		sb.append("\"").append(budget).append("\",");
		sb.append("\"").append(status).append("\",");
		sb.append("\"").append(assignee).append("\"").append("\n");
		
		return sb.toString();
	}
	
	private String parsePref(List<String> prefs) {
		StringBuffer sb = new StringBuffer();
		for(String pref : prefs) {
			sb.append(pref).append(",");
		}
		return sb.toString();
	}
}
