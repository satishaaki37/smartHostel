package com.smarthostel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smarthostel.entity.UserRooms;


@Repository
public interface UserRoomsServiceRepository extends JpaRepository< UserRooms, Integer>{
	
	@Query(value="select * from user_rooms where user_id=?1",nativeQuery=true)
	public UserRooms findRoomByUserId(Integer userId);

	@Query(value="select * from user_rooms where is_allocated=?1",nativeQuery=true)
	public List<UserRooms> findPendingAllocations(boolean isAllocated);
	
	
	@Query(value="select * from user_rooms where user_id=?1 and is_allocated=?2",nativeQuery=true)
	public UserRooms findAllocatedRoom(Integer userId,boolean isAllocated);
}
