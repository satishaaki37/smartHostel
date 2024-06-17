package com.smarthostel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Complaint {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private boolean resolved;
    
    public Complaint()
    {
    	
    }

	public Complaint(Integer id, String description, boolean resolved) {
		super();
		this.id = id;
		this.description = description;
		this.resolved = resolved;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

	@Override
	public String toString() {
		return "Complaint [id=" + id + ", description=" + description + ", resolved=" + resolved + "]";
	}
    

}
