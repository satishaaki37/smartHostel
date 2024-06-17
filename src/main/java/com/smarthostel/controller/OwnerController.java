package com.smarthostel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarthostel.entity.Complaint;
import com.smarthostel.entity.Owner;
import com.smarthostel.entity.Room;
import com.smarthostel.entity.User;
import com.smarthostel.entity.UserRooms;
import com.smarthostel.service.ComplaintService;
import com.smarthostel.service.OwnerService;
import com.smarthostel.service.RoomService;
import com.smarthostel.service.UserRoomsService;
import com.smarthostel.service.UserService;

@RestController
@RequestMapping("/owner")
public class OwnerController {
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
    private RoomService roomService;
	@Autowired
    private UserService userService;
	
	@Autowired
    private UserRoomsService userRoomsService;
	
	  @Autowired
	    private ComplaintService complaintService;
	  
	  String userName;
 
	@PostMapping("/login")
 	public ResponseEntity<?> login(@RequestBody Owner owner)
 	{
 		List<Owner> owners = ownerService.getAllOwners();
    	boolean flag=false;
    	for(Owner o:owners)
    	{
    		if(o.getId()==owner.getId() && o.getUsername().equals(owner.getUsername()))
    		{
    			flag=true;
    			break;
    		}
    		
    	}
    	if(flag==true)
    	{
    		userName=owner.getUsername();
    		return new ResponseEntity<>(owner.getUsername() +"Login Successfully",HttpStatus.OK);
    	}
    		return new ResponseEntity<>(owner.getUsername() +"Can't Login because U haven't registered",HttpStatus.BAD_REQUEST);
 	}
	
	@PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Owner owner) {
    	List<Owner> owners = ownerService.getAllOwners();
    	boolean flag=false;
    	for(Owner o:owners)
    	{
    		if(o.getId()==owner.getId() && o.getUsername().equals(owner.getUsername()))
    		{
    			flag=true;
    		}
    		
    	}
    	if(flag==true)
    	{
    		return new ResponseEntity<>(owner.getUsername() +"already registered u can't register again",HttpStatus.BAD_REQUEST);
    	}
        Owner newOwner = ownerService.registerOwner(owner);
		return new ResponseEntity<>("Registered Successfully",HttpStatus.OK);
    }
	
	// Endpoint for room allocation based on user preferences
    @PostMapping("/addRoom")
    public ResponseEntity<?> addRoom(@RequestBody Room room) {
    	if(userName==null)
    	{
    		return new ResponseEntity<>("You should login first!!!!",HttpStatus.OK);

    	}
    	List<Room> rooms = roomService.getAllRooms();
    	boolean flag=false;
    	for(Room r:rooms)
    	{
    		if(r.getRoomNumber().equalsIgnoreCase(room.getRoomNumber()))
    		{
    			flag=true;
    			break;
    		}
    	}
    	if(flag==true)
    	{
    		return new ResponseEntity<>(room.getRoomNumber() +" already exist u can't add again",HttpStatus.OK);

    	}
        roomService.addRoom(room);
		return new ResponseEntity<>(room.getRoomNumber() +" added successfully",HttpStatus.OK);
    }
    
    // Endpoint for retrieving all rooms
    @GetMapping
    public ResponseEntity<?> getAllRooms() {
    	if(userName==null)
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
 
    
 
    // Endpoint for updating room details (for admin use)
    @PutMapping("/update")
    public ResponseEntity<?> updateRoomDetails( @RequestBody Room room) {
    	if(userName==null)
    	{
    		return new ResponseEntity<>("You should login first!!!!",HttpStatus.OK);

    	}
    	Room room2 = roomService.getRoomById(room.getRoomNumber());
    	if(room2==null)
    	{
    		return new ResponseEntity<>(room.getRoomNumber()+" doesn't exist so can't update",HttpStatus.NOT_FOUND);
    	}
    	else
    	{
        Room updatedRoom = roomService.updateRoom(room2.getId(), room);
        return ResponseEntity.ok(updatedRoom);
    	}
    }
    
    
    
    // Endpoint for retrieving all users
    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
    	if(userName==null)
    	{
    		return new ResponseEntity<>("You should login first!!!!",HttpStatus.OK);

    	}
    	List<User> users = userService.getAllUsers();
    	if(users!=null && !users.isEmpty())
    	{
    		return new ResponseEntity<>(users,HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity<>("OOPS!!! There are no users",HttpStatus.NOT_FOUND);
    	}
        
    }
    
  
 
    // Endpoint for retrieving all complaints
    @GetMapping("/getPendingComplaints")
    public ResponseEntity<?> getPendingComplaints() {
    	if(userName==null)
    	{
    		return new ResponseEntity<>("You should login first!!!!",HttpStatus.OK);

    	}
    	List<Complaint> complaints = complaintService.getPendingComplaints();
    	if(complaints!=null && !complaints.isEmpty())
    	{
    		return ResponseEntity.ok(complaints);
    	}
    	else
    	{
     		return new ResponseEntity<>("OOPS!!! there are no Complaints",HttpStatus.OK);

    	}
    }
    
    // Endpoint for resolving a complaint (admin use)
    @PutMapping("/resolve/{complaintId}")
    public ResponseEntity<?> resolveComplaint(@PathVariable Integer complaintId) {
    	if(userName==null)
    	{
    		return new ResponseEntity<>("You should login first!!!!",HttpStatus.OK);

    	}
        Complaint resolvedComplaint = complaintService.resolveComplaint(complaintId);
        if (resolvedComplaint != null) {
            return ResponseEntity.ok(resolvedComplaint);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/getPendingAllocations")
    public ResponseEntity<?> getPendingAllocations()
    {
    	if(userName==null)
    	{
    		return new ResponseEntity<>("You should login first!!!!",HttpStatus.OK);

    	}
    	List<UserRooms> listUsers = userRoomsService.getPendingAllocations();
    	if(listUsers!=null && !listUsers.isEmpty())
    		return new ResponseEntity<>(listUsers,HttpStatus.OK);
    	else
        	return new ResponseEntity<>("Their is no pending Allocations",HttpStatus.OK); 	
    }
    
    // Endpoint for room allocation based on user preferences
    @PostMapping("/allocateRoom/{userId}/{roomNumber}")
    public ResponseEntity<?> allocateRoom(@PathVariable Integer userId,@PathVariable String roomNumber) {
    	if(userName==null)
    	{
    		return new ResponseEntity<>("You should login first!!!!",HttpStatus.OK);

    	}
    	User user = userService.getUserByID(userId);
    	if(user==null)
    	{
    		return new ResponseEntity<>(userId +" doesn't exist so can't allocate room",HttpStatus.NOT_FOUND);
    	}
    	
    	roomService.allocateRoom(roomNumber);
    	userRoomsService.updateAllocationStatus(userId,true);
        return ResponseEntity.status(HttpStatus.CREATED).body(userId+" for u "+roomNumber+" is allocated");
    }
 

}
