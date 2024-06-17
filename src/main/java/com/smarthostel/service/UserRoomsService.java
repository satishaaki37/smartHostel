package com.smarthostel.service;

import java.util.List;

import com.smarthostel.entity.UserRooms;

public interface UserRoomsService {

	UserRooms getRoomByUserId(Integer id);

	void addNewUser(UserRooms newUser);

	List<UserRooms> getPendingAllocations();

	void updateAllocationStatus(Integer userId,boolean b);

	UserRooms getAllocatedRoom(Integer userId);

}
