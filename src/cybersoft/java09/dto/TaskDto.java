package cybersoft.java09.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import cybersoft.java09.entity.*;

public class TaskDto {
	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
	private String user;
	private String job;
	private String status;
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
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public TaskDto() {
		
	}
}
