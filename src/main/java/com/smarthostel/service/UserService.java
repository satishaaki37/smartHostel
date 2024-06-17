package com.smarthostel.service;

import java.util.List;

import com.smarthostel.entity.User;

public interface UserService {

	List<User> getAllUsers();

	List<User> findRoommates(String location, String interests);

	User registerUser(User user);
	
	User getUserByID(Integer id);

	

}
