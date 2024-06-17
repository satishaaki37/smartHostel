package com.smarthostel.service;

import java.util.List;

import com.smarthostel.entity.Room;
import com.smarthostel.entity.UserRooms;

public interface RoomService {

	List<Room> getAllRooms();

	void allocateRoom(String roomNumber);

	Room updateRoom(Integer roomId, Room room);

	void addRoom(Room room);
	
	Room getRoomById(String roomNumber);

	Room getRoomByUserId(Integer id);

	Room getAvailableRoomNumber();

	List<Room> getAvailableRooms();

}
