package com.example.dto;

import java.util.ArrayList;
import java.util.List;

public class UserList {
	private List<User> items = new ArrayList<User>();

	public List<User> getUsers() {
		return items;
	}

	public void setUsers(List<User> items) {
		this.items = items;
	}
}
