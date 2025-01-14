package com.example.user.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.user.service.entity.Employee;
import com.example.user.service.entity.User;
import com.example.user.service.exception.ResourceNotFoundException;
import com.example.user.service.externalservice.EmployeeManagementService;
import com.example.user.service.repository.UserRepository;
import com.example.user.service.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private EmployeeManagementService employeeManagementService;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public User createUser(User user) {
		try {
			// Encrypt the password before saving
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			return userRepository.save(user);
		} catch (Exception ex) {
			throw new RuntimeException("Failed to create user: " + ex.getMessage());
		}
	}


	@Override
	public User getUserById(String userId) {
	    try {
	        // Fetch the user by ID
	        User user = userRepository.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

	        // Fetch employee details for the user
	        ResponseEntity<List<Employee>> response = employeeManagementService.getEmployeesByUserId(userId);
	        List<Employee> employees = (List<Employee>) response.getBody();
	        
	        // Assign employees only if they exist
	        if (employees != null && !employees.isEmpty()) {
	            user.setEmployeeManagementData(employees);
	        } else {
	            user.setEmployeeManagementData(Collections.emptyList());
	        }

	        return user;
	    } catch (Exception ex) {
	        throw new RuntimeException("Failed to fetch user: " + ex.getMessage());
	    }
	}


	@Override
	public List<User> getAllUsers() {
	    try {
	        // Fetch all users
	        List<User> users = userRepository.findAll();

	        // Create a map to hold user-specific employee data
	        Map<String, List<Employee>> userEmployeeMap = new HashMap<>();

	        // Fetch all employees once to reduce API calls
	        ResponseEntity<List<Employee>> response = employeeManagementService.getAllEmployees();
	        List<Employee> employees = response.getBody();

	        // Group employees by userId
	        if (employees != null) {
	            for (Employee employee : employees) {
	                if (employee.getUserId() != null) {
	                    userEmployeeMap.computeIfAbsent(employee.getUserId(), k -> new ArrayList<>()).add(employee);
	                }
	            }
	        }

	        // Map employees to their respective users
	        for (User user : users) {
	            user.setEmployeeManagementData(userEmployeeMap.getOrDefault(user.getUserId(), Collections.emptyList()));
	        }

	        return users;
	    } catch (Exception ex) {
	        throw new RuntimeException("Failed to fetch users: " + ex.getMessage());
	    }
	}

	 

	@Override
	public User updateUser(String userId, User user) {
		try {
			User existingUser = getUserById(userId);

			if (user.getUsername() != null && !user.getUsername().isEmpty()) {
				existingUser.setUsername(user.getUsername());
			}

			if (user.getEmail() != null && !user.getEmail().isEmpty()) {
				existingUser.setEmail(user.getEmail());
			}

			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				String encryptedPassword = passwordEncoder.encode(user.getPassword());
				existingUser.setPassword(encryptedPassword);
			}
			return userRepository.save(existingUser);
		} catch (Exception ex) {
			throw new RuntimeException("Failed to update user: " + ex.getMessage());
		}
	}

	@Override
	public void deleteUser(String userId) {
		try {
			User user = getUserById(userId);
			userRepository.delete(user);
		} catch (Exception ex) {
			throw new RuntimeException("Failed to delete user: " + ex.getMessage());
		}

	}
	

}

//@Autowired
//private UserRepository userRepository;
//
//@Autowired
//private RestTemplate restTemplate;
//
//@Autowired
//private HotelService hotelService;
//
//private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
//
//@Override
//public User saveUser(User user) {
//	String randomUserId = UUID.randomUUID().toString();
//	user.setUserId(randomUserId);
//	return userRepository.save(user);
//}
//
//@Override
//public List<User> getAllUser() {
//	return userRepository.findAll();
//}
//
//@Override
//public User getUser(String UserId) {
//	//get user from database
//	User user = userRepository.findById(UserId).orElseThrow(() -> new ResourceNotFoundException("User with Given id is not found on Server" + UserId));
//	//fetch ratings of the user from rating service 
////	http://localhost:8083/ratings/users/4a97c465-2829-40b8-a34d-1a99cc872723
//	//we have to call the rating service from user service 
//	Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
//	logger.info("{} " ,ratingsOfUser);
//	
//	List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
//	
//	List<Rating> ratingList = ratings.stream().map(rating -> {
//		//api call to hotel service to get hotel
//		//http://localhost:8082/hotels/e6759449-eabd-4edc-b639-25ae0578bed5
//		//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//		Hotel hotel = hotelService.getHotel(rating.getHotelId());
//		//logger.info("response status code: {}",forEntity.getStatusCode());
//		//set the hotel to rating
//		rating.setHotel(hotel);
//		
//		//return the rating
//		return rating;
//	}).collect(Collectors.toList());
//	
//	
//	user.setRatings(ratingList);
//	
//	
//	
//return user;
//}
