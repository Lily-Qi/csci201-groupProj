package Util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import net.sourceforge.jwebunit.util.TestingEngineRegistry;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;


//****TESTING*****//
//all tests are run as single batch job
//test number corresponds to scenario from testing doc e.g. scenario1 --> test1()

public class tests {
	
	@Before
	public void prepare() {
		setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
        setBaseUrl("http://localhost:8080/final201__");
    }
	 
	//Scenario 1: Unlogged user try to add a project
	@Test
	void test1() {
		//start at index
		beginAt("index.jsp");
		assertTitleEquals("Home Page");
		
		//try to add project while signed out, ensure redirection to login page
		clickLink("addButton");
		assertTitleEquals("Login/Register");
		
	}
	

	//Scenario 2: User tries to login
	@Test
	void test2() {
		//ensure redirection from home to login
		beginAt("index.jsp");
		clickLinkWithText("Login/Register");
		assertTitleEquals("Login/Register");
		
		//try to submit blank form, ensure still on login page
		setWorkingForm("login-form");
		submit();
		assertTitleEquals("Login/Register");
		
		//try to submit invalid user/password
		setWorkingForm("login-form");
		setTextField("email","invalid-user000@invalid.com");
		setTextField("pwd","invalid-pwd000");
		submit();
		assertTitleEquals("Login/Register");
		
		
	}
	
	
	
	//Scenario 3: a) User tries to sign up unsuccessfully
	@Test
	void test3() {
		beginAt("auth.jsp");
		setWorkingForm("signup-form");
		
		//try to enter email/name without password
		setTextField("registerEmail","autotest@test.com");
		setTextField("registerName","autotest");
		submit();
		assertTitleEquals("Login/Register");
		
		//try to enter two different passwords
		setWorkingForm("signup-form");
		setTextField("registerEmail","autotest@test.com");
		setTextField("registerName","autotest");
		setTextField("registerPassword", "1");
		setTextField("registerPassword", "2");
		submit();
		assertTitleEquals("Login/Register");
		
				
	}
	//Scenario 4: User successfully logged in/registered, lead to the logged-in version home page
	@Test
	void test4() {
		//ensure successful login
		beginAt("auth.jsp");
		setWorkingForm("signup-form");
		setTextField("registerEmail","autotest@test.com");
		setTextField("registerName","autotest");
		setTextField("registerPassword", "password");
		setTextField("registerPassword", "password");
		submit();
		
		//ensure redirect to home with logout option
		assertTitleEquals("Home");
		assertLinkPresentWithText("Logout");
		
		
	}
	//Scenario 5: Logged-in user tries to create a new project
	@Test
	void test5() {
		//ensure successful login
		beginAt("auth.jsp");
		setWorkingForm("signup-form");
		setTextField("registerEmail","test5t@test.com");
		setTextField("registerName","test5");
		setTextField("registerPassword", "password");
		setTextField("registerPassword", "password");
		submit();
		
		//try to create project, ensure redirection to new project
		assertTitleEquals("Home");
		clickLink("addButton");
		assertTitleEquals("Create a new project");
		
		//try to create project with no data
		clickLink("createButton");
		assertTitleEquals("Create a new project");
		
		
		
		
	}
	//Scenario 6: Logged-in user tries to modify information of an existing project
	@Test
	void test6() {
		//create dummy user
		beginAt("auth.jsp");
		setWorkingForm("signup-form");
		setTextField("registerEmail","test6dummy@test.com");
		setTextField("registerName","test6dummy");
		setTextField("registerPassword", "password");
		setTextField("registerPassword", "password");
		submit();
		
		//create user for group creation
		assertTitleEquals("Home");
		clickLinkWithText("Logout");
		clickLinkWithText("PM System");
		clickLinkWithText("Login/Register");
		assertTitleEquals("Login/Register");
		setWorkingForm("signup-form");
		setTextField("registerEmail","test6t@test.com");
		setTextField("registerName","test6");
		setTextField("registerPassword", "password");
		setTextField("registerPassword", "password");
		submit();
				
		//try to create project, ensure redirection to new project
		assertTitleEquals("Home");
		clickLink("addButton");
		assertTitleEquals("Create a new project");
				
		
		//enter group data
		setWorkingForm("groupForm");
		setTextField("groupTitle","test6");
		setTextField("Member","test6dummy@test.com");
		clickLink("addMemberBtn");
		setTextField("groupDescription","test6 description");
		clickLink("createButton");
		
		//logout/login as dummy user
		clickLinkWithText("PM System");
		clickLinkWithText("Logout");
		clickLinkWithText("Login/Register");
		setWorkingForm("login-form");
		setTextField("email","test6dummy@test.com");
		setTextField("psw","password");
		clickLinkWithText("Sign in");
		
		//try to edit project as dummy user
		clickLinkWithText("Group Info");
		clickLinkWithText("Edit");
		assertTitleEquals("editInfo.jsp");
		setWorkingForm("groupForm");
		setTextField("groupTitle","alteredTitle");
		
	}
	//Scenario 7: Logged-in user tries to quit an existing project
	@Test
	void test7() {
		//create dummy user
		beginAt("auth.jsp");
		setWorkingForm("signup-form");
		setTextField("registerEmail", "test7dummy@test.com");
		setTextField("registerName", "test7dummy");
		setTextField("registerPassword", "password");
		setTextField("registerPassword", "password");
		submit();

		// create user for group creation
		assertTitleEquals("Home");
		clickLinkWithText("Logout");
		clickLinkWithText("PM System");
		clickLinkWithText("Login/Register");
		assertTitleEquals("Login/Register");
		setWorkingForm("signup-form");
		setTextField("registerEmail", "test7@test.com");
		setTextField("registerName", "test7");
		setTextField("registerPassword", "password");
		setTextField("registerPassword", "password");
		submit();

		// try to create project, ensure redirection to new project
		assertTitleEquals("Home");
		clickLink("addButton");
		assertTitleEquals("Create a new project");

		// enter group data
		setWorkingForm("grouForm");
		setTextField("groupTitle", "test7");
		setTextField("Member", "test7dummy@test.com");
		clickLink("addMemberBtn");
		setTextField("groupDescription", "test7 description");
		clickLink("createButton");

		// logout/login as dummy user
		clickLinkWithText("PM System");
		clickLinkWithText("Logout");
		clickLinkWithText("Login/Register");
		setWorkingForm("login-form");
		setTextField("email", "test7dummy@test.com");
		setTextField("psw", "password");
		clickLinkWithText("Sign in");

		// try to leave group
		clickLinkWithText("Group Info");
		clickLinkWithText("Quit");

		// go to home and ensure no groups available
		clickLinkWithText("PM SYSTEM");
		assertTextPresent("No groups yet... Try to create a new group!");
				
				
		
	}
	//Scenario 8,9,10:
	//Scenario 8: Logged-in user tries to add a task in the calendar page
	//Scenario 9: Logged-in user tries to add a task to the todo list page
	//Scenario 10: Logged-in user tries to check a task in the todo list page
		@Test
	@Test
	void tests8910() {
		//sign up
		beginAt("auth.jsp");
		setWorkingForm("signup-form");
		setTextField("registerEmail", "test8@test.com");
		setTextField("registerName", "test8");
		setTextField("registerPassword", "password");
		setTextField("registerPassword", "password");
		submit();
		clickLinkWithText("PM SYSTEM");
		
		//create project
		clickLink("addButton");
		// enter group data
		setWorkingForm("grouForm");
		setTextField("groupTitle","test8");
		setTextField("Member","test8@test.com");
		clickLink("addMemberBtn");
		setTextField("groupDescription","test8 description");
		clickLink("createButton");
		
		//try to add task to calendar
		clickLinkWithText("Calendar");
		setTextField("task-title", "test8task");
		setTextField("task-date", "test8date");
		clickLinkWithText("Add Task");
		
		//try to add task to to-do list
		clickLinkWithText("To-do List");
		clickLinkWithText("Add a new task");
		setTextField("name","test8task2name");
		setTextField("date","test8task2date");
		submit();
	
		
	}

	//Scenario 12: Logged-in user tries to log out
	@Test
	void test12() {
		//login
		beginAt("auth.jsp");
		setWorkingForm("signup-form");
		setTextField("registerEmail","test12t@test.com");
		setTextField("registerName","test12");
		setTextField("registerPassword", "password");
		setTextField("registerPassword", "password");
		submit();
		
		//try to logout
		assertTitleEquals("Home");
		clickLinkWithText("Logout");
		
		//ensure logout option has changed to login
		assertTitleEquals("Home");
		assertLinkPresentWithText("Login/Register");
		
		
	}
	//Scenario 12: Logged-in user tries to go back to the home page
	@Test
	void test13() {
		//login
		beginAt("auth.jsp");
		setWorkingForm("signup-form");
		setTextField("registerEmail","test13t@test.com");
		setTextField("registerName","test13");
		setTextField("registerPassword", "password");
		setTextField("registerPassword", "password");
		submit();
		
		//click home link and ensure return to home page
		clickLinkWithText("PM SYSTEM");
		assertTitleEquals("Home");
		
	}
	//Scenario 14: Logged-out user tries to go back to the home page
	@Test
	void test14() {
		beginAt("auth.jsp");
		
		//click home link and ensure return to home page
		clickLinkWithText("PM SYSTEM");
		assertTitleEquals("Home");
		
	}

}


