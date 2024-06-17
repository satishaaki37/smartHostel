package com.smarthostel.entity;


public class ComplaintRequest {
	
	 private Integer id;
	 private String description;
	 
	 public ComplaintRequest()
	 {
		 
	 }

	public ComplaintRequest(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
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

	@Override
	public String toString() {
		return "ComplaintRequest [id=" + id + ", description=" + description + "]";
	}
	 

}
