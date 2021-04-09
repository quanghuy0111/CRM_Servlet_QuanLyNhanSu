package cybersoft.java09.dto;

import java.util.ArrayList;
import java.util.List;

import cybersoft.java09.entity.*;

public class JobDto {
	private int id;
	private User user;
	private List<Task> taskNotDone = new ArrayList<Task>();
	private List<Task> taskPending  = new ArrayList<Task>();
	private List<Task> taskDone  = new ArrayList<Task>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Task> getTaskNotDone() {
		return taskNotDone;
	}
	public void setTaskNotDone(List<Task> taskNotDone) {
		this.taskNotDone = taskNotDone;
	}
	public List<Task> getTaskPending() {
		return taskPending;
	}
	public void setTaskPending(List<Task> taskPending) {
		this.taskPending = taskPending;
	}
	public List<Task> getTaskDone() {
		return taskDone;
	}
	public void setTaskDone(List<Task> taskDone) {
		this.taskDone = taskDone;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return taskDone.get(0).toString();
	}
	
}
