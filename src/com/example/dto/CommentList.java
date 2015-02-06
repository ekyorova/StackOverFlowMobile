package com.example.dto;

import java.util.ArrayList;
import java.util.List;

public class CommentList {
	private List<Comment> items = new ArrayList<Comment>();

	public List<Comment> getComments() {
		return items;
	}

	public void setComments(List<Comment> comments) {
		this.items = comments;
	}
	
}
