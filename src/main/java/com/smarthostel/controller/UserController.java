package com.smarthostel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smarthostel.entity.Complaint;
import com.smarthostel.entity.ComplaintRequest;
import com.smarthostel.entity.LoginRequest;
import com.smarthostel.entity.Room;
import com.smarthostel.entity.User;
import com.smarthostel.entity.UserRooms;
import com.smarthostel.service.ComplaintService;
import com.smarthostel.service.RoomService;
import com.smarthostel.service.UserRoomsService;
import com.smarthostel.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	 @Autowired
	    private UserService userService;
	 
	 @Autowired
	    private RoomService roomService;
	 
	 @Autowired
	    private UserRoomsService userRoomsService;
	 
	 @Autowired
	    private ComplaintService complaintService;
	 Integer initialShare;
	 String username;
	 
	// Endpoint for registering a new user
	    @PostMapping("/register")
	    public ResponseEntity<?> registerUser(@RequestBody User user) {
	    	List<User> users = userService.getAllUsers();
	    	boolean flag=false;
	    	for(User u:users)
	    	{
	    		if(u.getId()==user.getId() && u.getUsername().equals(user.getUsername()))
	    		{
	    			flag=true;
	    		}
	    		
	    	}
	    	if(flag==true)
	    	{
	    		return new ResponseEntity<>(user.getUsername() +"already registered u can't register again",HttpStatus.BAD_REQUEST);
	    	}
	        User newUser = userService.registerUser(user);
 		return new ResponseEntity<>("Registered Successfully",HttpStatus.OK);
	    }
	    
	 	@PostMapping("/login")
	 	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
	 	{
	 		List<User> users = userService.getAllUsers();
	    	boolean flag=false;
	    	for(User u:users)
	    	{
	    		if(u.getId()==loginRequest.getId() && u.getUsername().equals(loginRequest.getUsername()))
	    		{
	    			flag=true;
	    			break;
	    		}
	    		
	    	}
	    	if(flag==true)
	    	{
	    		username=loginRequest.getUsername();
	    		return new ResponseEntity<>(loginRequest.getUsername() +"Login Successfully",HttpStatus.OK);
	    	}
	    		return new ResponseEntity<>(loginRequest.getUsername() +"Can't Login because U haven't registered",HttpStatus.BAD_REQUEST);
	 	}
	   
	   
	    
	    // Endpoint for retrieving all rooms
	    @GetMapping
	    public ResponseEntity<?> getAllRooms() {
	    	if(username==null)
	    	{
	    		return new ResponseEntity<>("You should login first!!!!",HttpStatus.OK);

	    	}
	    	List<Room> rooms = roomService.getAllRooms();
	    	if(rooms!=null && !rooms.isEmpty())
	    	{
	    		return new ResponseEntity<>(rooms,HttpStatus.OK);
	    	}
    		return new ResponseEntity<>("OOPS!!! There are no rooms",HttpStatus.OK);
	    }
	    
	 // Endpoint for submitting a new complaint
	    @PostMapping("/submit")
	    public ResponseEntity<?> submitComplaint(@RequestBody ComplaintRequest complaintRequest) {
	    	if(username==null)
	    	{
	    		return new ResponseEntity<>("You should login first!!!!",HttpStatus.OK);

	    	}
	    	Complaint complaint=new Complaint(complaintRequest.getId(),complaintRequest.getDescription(),false);
	        Complaint newComplaint = complaintService.submitComplaint(complaint);
	        return ResponseEntity.status(HttpStatus.CREATED).body(newComplaint);
	    }
	    
	    // Endpoint for finding potential roommates based on location and interests

	    @GetMapping("/findRoom")
	    public ResponseEntity<?> findRoom(@RequestParam Integer userId,@RequestParam String location, @RequestParam String interests) 
	    {
	    	if(username==null)
	    	{
	    		return new ResponseEntity<>("You should login first!!!!",HttpStatus.OK);

	    	}
	    	List<User> roommates = userService.findRoommates(location, interests);
	    	if(roommates!=null && !roommates.isEmpty())
	    	{
	    		for(User u:roommates)
	    		{
	    			UserRooms userRoom = userRoomsService.getRoomByUserId(u.getId());
	    			if(userRoom!=null)
	    			{
	    				Room room = roomService.getRoomById(userRoom.getRoomId());
	    				if(room.getShare()>0)
	    				{
	    					UserRooms newUser=new UserRooms(userId,room.getRoomNumber(),false);
	    					userRoomsService.addNewUser(newUser);
	    					return new ResponseEntity<>(newUser.getUserId()+" Your allocation is pending",HttpStatus.OK);

	    				}
	    			}
	    			else
	    			{
	    				Room room2=roomService.getAvailableRoomNumber();
	    				UserRooms newUser2=new UserRooms(userId,room2.getRoomNumber(),false);
    					userRoomsService.addNewUser(newUser2);
    					return new ResponseEntity<>(newUser2.getUserId()+" Your allocation is pending",HttpStatus.OK);
	    			}
	    		}
				return new ResponseEntity<>("Their is no rooms",HttpStatus.OK);

	    	}
	    	else
	    	{
	    		return new ResponseEntity<>("OOPS!!! There are no Matching Rooms select other interests ",HttpStatus.NOT_FOUND);

	    	}
	        
	    }
	    
	    @GetMapping("/getAllocatedRoomDetails/{userId}")
	    public ResponseEntity<?> getAllocatedRoomDetails(@PathVariable Integer userId)
	    {
	    	UserRooms userRooms = userRoomsService.getAllocatedRoom(userId);
	    	if(userRooms!=null)
	    	{
	    		return new ResponseEntity<>(userRooms,HttpStatus.OK);
	    	}
	    	else
	    		return new ResponseEntity<>("No room is allocated at",HttpStatus.NOT_FOUND);
	    }
	    
	    @GetMapping("/getAvailableRooms")
	    public ResponseEntity<?> getAvailableRooms()
	    {
	    	if(username==null)
	    	{
	    		return new ResponseEntity<>("You should login first!!!!",HttpStatus.OK);

	    	}
	    	List<Room> rooms = roomService.getAvailableRooms();
	    	if(rooms!=null && !rooms.isEmpty())
	    	{
	    		return new ResponseEntity<>("rooms",HttpStatus.OK);
	    	}
	    	else
	    	{
	    		return new ResponseEntity<>("There are no available rooms",HttpStatus.NOT_FOUND);

	    	}
	    }
	 
	 
	    

}
