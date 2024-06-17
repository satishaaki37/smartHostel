package com.smarthostel.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthostel.entity.User;
import com.smarthostel.entity.UserRooms;
import com.smarthostel.repository.UserRoomsServiceRepository;
import com.smarthostel.service.UserRoomsService;

@Service
public class UserRoomsServiceImpl implements UserRoomsService{
	@Autowired
	UserRoomsServiceRepository userRoomsServiceRepository;
	@Override
	public UserRooms getRoomByUserId(Integer id) {
		return userRoomsServiceRepository.findRoomByUserId(id);
		
	}
	@Override
	public void addNewUser(UserRooms newUser) {
		userRoomsServiceRepository.save(newUser);
		
	}
	@Override
	public List<UserRooms> getPendingAllocations() {
		
		return userRoomsServiceRepository.findPendingAllocations(false);
	}
	@Override
	public void updateAllocationStatus(Integer userId,boolean b) {
		
		UserRooms userRooms = userRoomsServiceRepository.findRoomByUserId(userId);
		userRooms.setIsAllocated(b);
		userRoomsServiceRepository.save(userRooms);
		
	}
	@Override
	public UserRooms getAllocatedRoom(Integer userId) {
		
		return userRoomsServiceRepository.findAllocatedRoom(userId, true);
	}

}
