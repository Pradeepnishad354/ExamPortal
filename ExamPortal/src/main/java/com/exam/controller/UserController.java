package com.exam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.helper.UserFoundException;
import com.exam.service.UserService;


@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public User crateUser(@RequestBody User user) throws Exception {

		List<UserRole> roles = new ArrayList<>();

		Role role = new Role();

		role.setRoleId(12L);
		role.setRoleName("NORMAL");

		UserRole userRole = new UserRole();

		userRole.setUser(user);
		userRole.setRole(role);

		roles.add(userRole);

		return userService.createUser(user, roles);

	}
	
	// get user by username
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		
		
	return userService.getUser(username);
		
		
	}
	
	//delete user  by id
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		
		userService.deleteUser(id);
		
		
		
		
	}
	
	
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> exceptionHandler(UserFoundException ex){
		
		return new ResponseEntity<UserFoundException>(HttpStatus.ALREADY_REPORTED);
		
		
	}
	

}
