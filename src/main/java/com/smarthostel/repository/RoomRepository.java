package com.smarthostel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smarthostel.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer>{
	
	@Query(value="select * from room where share=?1",nativeQuery=true)
	public List<Room> findAvailableRooms(int share);
	
	@Query(value="select * from room where room_number=?1",nativeQuery=true)
	public Room findRoomById(String roomNumber);
	
	@Query(value="select * from room where occupied=?1",nativeQuery=true)
	public List<Room> findAvailableRooms(boolean occupied);
	


}
