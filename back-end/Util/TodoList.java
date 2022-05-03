package Util;

import java.util.ArrayList;

public class TodoList {
	String TodolistId;
	ArrayList<Task> TodoList = new ArrayList<Task>();
	public void addTask(Task t) {
		TodoList.add(t);
	}
}
