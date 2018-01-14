package com.semafors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.semafors.entity.Token;
import com.semafors.entity.User;
import com.semafors.service.UserService;

@RestController
@RequestMapping("/user/")
public class UserController {

	@Autowired UserService userService;
	
	@PutMapping("add")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@PostMapping("login")
	public ResponseEntity<Token> loginUser(@RequestBody User user) throws Exception {
		return new ResponseEntity<Token>(userService.loginUser(user),HttpStatus.OK);
	}
	
	@PostMapping("logout")
	public void logout(@RequestBody User user) throws Exception {
		userService.logout(user);
	}

	@CrossOrigin
	@PostMapping("admin/login")
	public ResponseEntity<Token> loginAdmin(@RequestBody User user) throws Exception{
		return new ResponseEntity<>(userService.loginAdmin(user),HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping("add/admin")
	public void addAdmin(@RequestBody User user) {
		userService.addAdmin(user);
	}
}
