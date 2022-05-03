package Util;

import java.util.ArrayList;

public class Task {
	private String TaskName;
	private String TaskDescription;
	
	public Task(String TaskName, ArrayList<User> AssignedUsers, String TaskDescription) {
		this.TaskName = TaskName;
		this.TaskDescription = TaskDescription;
	}

	
}
