package com.smarthostel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smarthostel.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value="select * from user where location=?1 and interests=?2",nativeQuery=true)
	public List<User> findRoomMates(String location,String interests);

}
