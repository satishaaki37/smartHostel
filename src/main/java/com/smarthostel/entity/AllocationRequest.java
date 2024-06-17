package com.smarthostel.entity;

public class AllocationRequest {
	 private Integer userId;
	 private String preferences;
	 public AllocationRequest()
	 {
		 
	 }
	public AllocationRequest(Integer userId, String preferences) {
		super();
		this.userId = userId;
		this.preferences = preferences;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPreferences() {
		return preferences;
	}
	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}
	@Override
	public String toString() {
		return "AllocationRequest [userId=" + userId + ", preferences=" + preferences + "]";
	}
	 

}
