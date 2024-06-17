package com.smarthostel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smarthostel.entity.Complaint;


@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer>{
	
	@Query(value="select * from Complaint where resolved=?1",nativeQuery=true)
	public List<Complaint> findPendingComplaints(boolean resolved);

}
