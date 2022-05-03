package Util;

import java.util.ArrayList;

public class Project {
	private String Title;
	private TodoList todo;
	private ArrayList<String> members = new ArrayList<String>();
	
	public String getTitle() {
		return Title;
	}
	
	public TodoList getTasks(){
		return todo;
	}
	
	public void addTask(Task t) {
		todo.addTask(t);
	}
	
	public ArrayList<String> getMemeber(){
		return members;
	}
	
	public void addMember(String u) {
		members.add(u);
	}
}
