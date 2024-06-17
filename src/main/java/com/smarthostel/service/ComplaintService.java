package com.smarthostel.service;

import java.util.List;

import com.smarthostel.entity.Complaint;

public interface ComplaintService {

	List<Complaint> getPendingComplaints();

	Complaint submitComplaint(Complaint complaint);

	Complaint resolveComplaint(Integer complaintId);

}
