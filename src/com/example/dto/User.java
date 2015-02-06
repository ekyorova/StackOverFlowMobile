package com.example.dto;

import com.google.gson.annotations.SerializedName;

public class User {
	
	@SerializedName("display_name")
	private String displayName;
	@SerializedName("location")
	private String location;
	@SerializedName("user_id")
	private String userId;
	@SerializedName("profile_image")
	private String profileImage;
	@SerializedName("website_url")
	private String websiteUrl;
	
	public User() {
	}
	
	
	public User(String displayName, String location, String userId,
			String profileImage, String websiteUrl) {
		super();
		this.displayName = displayName;
		this.location = location;
		this.userId = userId;
		this.profileImage = profileImage;
		this.websiteUrl = websiteUrl;
	}


	public String getDisplayName() {
		return displayName;
	}


	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getProfileImage() {
		return profileImage;
	}


	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}


	public String getWebsiteUrl() {
		return websiteUrl;
	}


	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}


}
