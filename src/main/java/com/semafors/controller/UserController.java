package com.semafors.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semafors.entity.Reservation;
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
}
