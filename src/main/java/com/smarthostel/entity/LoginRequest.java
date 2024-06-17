package com.smarthostel.entity;

public class LoginRequest {
	  private Integer id;
	  private String username;
	  public LoginRequest()
	  {
		  
	  }
	public LoginRequest(Integer id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "LoginRequest [id=" + id + ", username=" + username + "]";
	}
	  

}
