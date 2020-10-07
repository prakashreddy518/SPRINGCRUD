package com.prakash.springtask.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.prakash.springtask.model.User;
import com.prakash.springtask.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
