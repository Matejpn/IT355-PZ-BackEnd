package com.project.it355pz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.it355pz.dto.request.RegisterDto;
import com.project.it355pz.model.Role;
import com.project.it355pz.model.User;
import com.project.it355pz.repository.RoleRepository;
import com.project.it355pz.repository.UserRepository;
import com.project.it355pz.security.SecurityConfiguration;
import com.project.it355pz.service.UserService;
import com.project.it355pz.util.ShoppingException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	SecurityConfiguration securityConfiguration;

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public RegisterDto register(RegisterDto registerDto) {
		if(registerDto.getAddress() == null || registerDto.getAddress() == "" || registerDto.getFirstname() == null || registerDto.getFirstname() == "" || registerDto.getLastname() == null || registerDto.getLastname()=="" || registerDto.getEmail()==null || registerDto.getEmail()=="" || registerDto.getPassword()==null || registerDto.getPassword()=="") {
			throw new ShoppingException(HttpStatus.BAD_REQUEST, "All fields must be filled!");
		}
		User user = userRepository.findByEmail(registerDto.getEmail());
		if(user != null) {
			throw new ShoppingException(HttpStatus.BAD_REQUEST, "Email already exists!");
		}
		User newUser = new User();
		newUser.setEmail(registerDto.getEmail());
		newUser.setAdress(registerDto.getAddress());
		newUser.setFirstname(registerDto.getFirstname());
		newUser.setLastname(registerDto.getLastname());
		newUser.setPass(securityConfiguration.passwordEncoder().encode(registerDto.getPassword()));
		Role role = roleRepository.findById(2).orElseThrow(()-> new ShoppingException(HttpStatus.NOT_FOUND,"Role doesn't exist"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		newUser.setRoles(roles);
		userRepository.saveAndFlush(newUser);
		registerDto.setIdUser(newUser.getIdUser());
		return registerDto;
	}

}
