package com.project.it355pz.service;

import com.project.it355pz.dto.request.RegisterDto;
import com.project.it355pz.model.User;

public interface UserService {

	User findByEmail(String email);

	RegisterDto register(RegisterDto registerDto);

}
