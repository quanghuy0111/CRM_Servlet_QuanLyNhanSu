package cybersoft.java09.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
	private int userID;
	private int jobID;
	private int statusID;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public Task(int id, String name, Date startDate, Date endDate, int userID, int jobID, int statusID) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userID = userID;
		this.jobID = jobID;
		this.statusID = statusID;
	}
	public Task() {
		
	}
	public String startDateToString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        String strDate = sdf.format(this.startDate);
	        return strDate;
	}
	
	public String endDateToString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        String strDate = sdf.format(this.endDate);
	        return strDate;
	}
	public Task(String name, Date startDate, Date endDate, int userID, int jobID, int statusID) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userID = userID;
		this.jobID = jobID;
		this.statusID = statusID;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", userID="
				+ userID + ", jobID=" + jobID + ", statusID=" + statusID + "]";
	}  
	
	
	
}
