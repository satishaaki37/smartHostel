package com.smarthostel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String roomNumber;
    private boolean occupied;
    private Integer share;
    
    public Room()
    {
    	
    }

	public Room(Integer id, String roomNumber, boolean occupied,Integer share) {
		super();
		this.id = id;
		this.roomNumber = roomNumber;
		this.occupied = occupied;
		this.share=share;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	public Integer getShare()
	{
		return share;
	}
	public void setShare(Integer share)
	{
		this.share=share;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", roomNumber=" + roomNumber + ", occupied=" + occupied +"Share="+share+ "]";
	}
    

}
