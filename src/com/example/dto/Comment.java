package com.example.dto;

public class Comment {
	private String title;
	private String score;

	public Comment(String title, String score) {
		super();
		this.title = title;
		this.score = score;
	}

	public Comment() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
}
