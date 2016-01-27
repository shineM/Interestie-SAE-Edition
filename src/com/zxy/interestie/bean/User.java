package com.zxy.interestie.bean;

public class User {
	private String username;
	private String password;
	private String email;
	private String tags;

	public User(String username, String password, String email,String tags) {
		// TODO Auto-generated constructor stub
		this.email = email;
		this.username = username;
		this.password = password;
		this.setTags(tags);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTags() {
		// TODO Auto-generated method stub
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}
