package com.webshop.main.services.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.main.dtos.RegistrationDto;
import com.webshop.main.models.Role;
import com.webshop.main.models.UserEntity;
import com.webshop.main.repositories.RoleRepository;
import com.webshop.main.repositories.UserRepository;
import com.webshop.main.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	private UserRepository userRepo;
	private RoleRepository roleRepo;

	@Autowired
	public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo) {
		super();
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}


	@Override
	public void saveUser(RegistrationDto registrationDto) {
		UserEntity user = new UserEntity();
		user.setUsername(registrationDto.getUsername());
		user.setEmail(registrationDto.getEmail());
		user.setPassword(registrationDto.getPassword());
		Role role = roleRepo.findByName("USER");
		user.setRoles(Arrays.asList(role));
		userRepo.save(user);
	}


	@Override
	public UserEntity findByUsername(String name) {
		return userRepo.findByUsername(name);
	}


	@Override
	public UserEntity findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

}
