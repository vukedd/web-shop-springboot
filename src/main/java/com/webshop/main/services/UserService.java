package com.webshop.main.services;


import org.springframework.stereotype.Service;

import com.webshop.main.dtos.RegistrationDto;
import com.webshop.main.models.UserEntity;

@Service
public interface UserService {
	void saveUser(RegistrationDto registrationDto);
	UserEntity findByUsername(String name);
	UserEntity findByEmail(String email);
}
