package com.smarthostel.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthostel.entity.Room;
import com.smarthostel.entity.UserRooms;
import com.smarthostel.repository.RoomRepository;
import com.smarthostel.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	RoomRepository roomRepository;
	
	
	@Override
	public List<Room> getAllRooms() {
		
		return roomRepository.findAll();
	}

	@Override
	public void allocateRoom(String roomNumber) {
		
//		List<Room> availableRooms = roomRepository.findAvailableRooms(false);
//		UserRooms userRoom=new UserRooms();
//		for(Room r:availableRooms)
//		{
//			userRoom.setRoomId(r.getRoomNumber());
//			userRoom.setUserId(userId);
//			r.setOccupied(true);
//			roomRepository.save(r);
//			break;
//		}
		
		Room room = roomRepository.findRoomById(roomNumber);
		room.setShare(room.getShare()-1);
		if(room.getShare()==0)
		{
			room.setOccupied(true);
		}
		roomRepository.save(room);
	}

	@Override
	public Room updateRoom(Integer roomId, Room room) {
		room.setId(roomId);
		roomRepository.save(room);
		return room;
	}

	@Override
	public void addRoom(Room room) {
		roomRepository.save(room);
	}

	@Override
	public Room getRoomById(String roomNumber) {
		
		return roomRepository.findRoomById(roomNumber);
	}

	@Override
	public Room getRoomByUserId(Integer id) {
		
		return null;
	}

	@Override
	public Room getAvailableRoomNumber() {
		List<Room> availableRooms = roomRepository.findAvailableRooms(3);
		Room room=new Room();
		for(Room r:availableRooms)
		{
			room=r;
		}
		return room;

	}

	@Override
	public List<Room> getAvailableRooms() {
		List<Room> availableRooms = roomRepository.findAvailableRooms(3);
		return availableRooms;
	}

}
