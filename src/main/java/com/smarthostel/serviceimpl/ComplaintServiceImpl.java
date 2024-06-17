package com.smarthostel.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthostel.entity.Complaint;
import com.smarthostel.repository.ComplaintRepository;
import com.smarthostel.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService{

	@Autowired
	ComplaintRepository complaintRepository;
	@Override
	public List<Complaint> getPendingComplaints() {
		
		return complaintRepository.findPendingComplaints(false);
	}
	@Override
	public Complaint submitComplaint(Complaint complaint) {
		
		return complaintRepository.save(complaint);
	}
	@Override
	public Complaint resolveComplaint(Integer complaintId) {
		Complaint complaint = complaintRepository.findById(complaintId).orElse(null);
		complaint.setResolved(true);
		complaintRepository.save(complaint);
		return complaint;
	}

}
