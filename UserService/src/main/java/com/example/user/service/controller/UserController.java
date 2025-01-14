package com.example.user.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.service.entity.User;
import com.example.user.service.payload.ApiResponse;
import com.example.user.service.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	
	@PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

	
	  @GetMapping("/{userId}")
	    public ResponseEntity<User> getUserById(@PathVariable String userId) {
	        try {
	            // Get the user with associated employee data
	            User user = userService.getUserById(userId);
	            return ResponseEntity.ok(user);
	        } catch (Exception ex) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }

	    // Fetch all users
	    @GetMapping
	    public ResponseEntity<List<User>> getAllUsers() {
	        try {
	            // Get all users with associated employee data
	            List<User> users = userService.getAllUsers();
	            return ResponseEntity.ok(users);
	        } catch (Exception ex) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        ApiResponse response = ApiResponse.builder()
                .message("User deleted successfully!")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }


}





//@Autowired
//private UserService userService;
//
////@Autowired
//private static final Logger logger = LoggerFactory.getLogger(UserController.class);
//
//// create
//@PostMapping
//public ResponseEntity<User> createUser(@RequestBody User user) {
//	User user1 = userService.saveUser(user);
//	return ResponseEntity.status(HttpStatus.CREATED).body(user1);
//}
//// single user
//
//@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//@GetMapping("/{userId}")
//public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
//	User user = userService.getUser(userId);
//	return ResponseEntity.ok(user);
//
//}
//
//// creating fallback method for circut return type should be same 
//
//public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
//	logger.info("Fallback is executed because service is down: {}", ex.getMessage());
//	User user = User.builder()
//			.email("dummy@gmail.com")
//			.name("Dummy")
//			.about("user has created the dummy data becuse some service are down ")
//			.userId("123241").build();
//	return new ResponseEntity<>(user, HttpStatus.OK);
//
//}
//
//// alluser
//@GetMapping
//public ResponseEntity<List<User>> getAllUser() {
//	List<User> allUser = userService.getAllUser();
//	return ResponseEntity.ok(allUser);
//}