package com.smarthostel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserRooms {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Integer userId;
	private String roomId;
	private boolean isAllocated;
	public UserRooms()
	{
		
	}
	public UserRooms(Integer userId, String roomId,boolean isAllocated) {
		super();
		this.userId = userId;
		this.roomId = roomId;
		this.isAllocated=isAllocated;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	public void setIsAllocated(boolean isAllocated) {
		this.isAllocated = isAllocated;
	}
	
	public boolean getIsAllocated() {
		return isAllocated;
	}
	@Override
	public String toString() {
		return "UserRooms [userId=" + userId + ", roomId=" + roomId +"isAllocated="+isAllocated+ "]";
	}
	

}
