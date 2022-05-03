package Util;

import java.util.ArrayList;

public class User {
	private String UserName;
	private String UserEmail;
	private String Password;
	private ArrayList<String> Projects = new ArrayList<String>();
	
	public User(String UserName, String UserEmail, String Password) {
		this.UserName = UserName;
		this.UserEmail = UserEmail;
		this.Password = Password;
	}
	
	public String getUsername() {
		return this.UserName;
	}
	
	public String getUseremail() {
		return this.UserEmail;
	}
	
	public String getUserpassword() {
		return this.Password;
	}
	
	public ArrayList<String> getProjects(){
		return Projects;
		
	}
	
	public void addProject(String title) {
		Projects.add(title);
	}
}
