package com.smarthostel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String location;
//    private String wakeUpTime;
//    private String sleepTime;
    private String interests;
    
    public User()
    {
    	
    }

	public User(Integer id, String username, String location,String interests) {
		super();
		this.id = id;
		this.username = username;
		this.location = location;
//		this.wakeUpTime = wakeUpTime;
//		this.sleepTime = sleepTime;
		this.interests = interests;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

//	public String getWakeUpTime() {
//		return wakeUpTime;
//	}
//
//	public void setWakeUpTime(String wakeUpTime) {
//		this.wakeUpTime = wakeUpTime;
//	}
//
//	public String getSleepTime() {
//		return sleepTime;
//	}
//
//	public void setSleepTime(String sleepTime) {
//		this.sleepTime = sleepTime;
//	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", location=" + location 
				+", interests=" + interests + "]";
	}
    

	
    
}
