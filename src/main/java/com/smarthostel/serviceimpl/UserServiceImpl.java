package com.smarthostel.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthostel.entity.User;
import com.smarthostel.repository.UserRepository;
import com.smarthostel.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository UserRepository;
	@Override
	public List<User> getAllUsers() {
		
		return UserRepository.findAll();
	}
	@Override
	public List<User> findRoommates(String location, String interests) {
		List<User> users = UserRepository.findRoomMates(location,interests);
		return users;
	}
	@Override
	public User registerUser(User user) {	
		return UserRepository.save(user);
	}
	@Override
	public User getUserByID(Integer id) {
		
		return UserRepository.findById(id).orElse(null);
	}

}
