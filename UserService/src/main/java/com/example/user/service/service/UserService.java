package com.example.user.service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.user.service.entity.User;

@Service
public interface UserService {
	User createUser(User user);

    User getUserById(String userId);

    List<User> getAllUsers();

    User updateUser(String userId, User user);

    void deleteUser(String userId);
		
}

//create
//User saveUser(User user);

//List<User> getAllUser();

//get singleuser

//User getUser(String UserId);

// todo
//update
