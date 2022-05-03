package Util;

import java.util.ArrayList;

public class ProjectOwner extends User {
	ArrayList<Integer> OwnedProjects = new ArrayList<Integer>();
	public ProjectOwner(String UserName, String UserEmail, String Password) {
		super(UserName, UserEmail, Password);
	}
	
	public boolean isOwner(int p) {
		for(int a:OwnedProjects) {
			if(a == p)
				return true;
		}
		return false;
	}

	
}
