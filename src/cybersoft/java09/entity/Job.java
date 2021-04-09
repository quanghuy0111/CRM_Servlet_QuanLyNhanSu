package cybersoft.java09.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Job {
	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
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
	public Job(int id, String name, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Job(String name, Date startDate, Date endDate) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Job() {
		
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
	
	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
	
	
}
