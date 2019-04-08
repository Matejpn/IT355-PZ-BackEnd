package com.project.it355pz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.it355pz.dto.request.LoginDto;
import com.project.it355pz.dto.response.LoggedInUserDTO;
import com.project.it355pz.model.User;
import com.project.it355pz.security.TokenUtils;
import com.project.it355pz.service.UserService;

@RestController
public class LoginController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Qualifier("userDetailsServiceImpl")
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginDto user) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(),
                    user.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(user.getEmail());
            User userDb = userService.findByEmail(user.getEmail());
            LoggedInUserDTO loggedIn = new LoggedInUserDTO(userDb.getIdUser(), tokenUtils.generateToken(details), userDb.getEmail(), details.getAuthorities());
            return new ResponseEntity<>(loggedIn, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
