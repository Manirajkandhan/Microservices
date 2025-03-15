package com.abc.mobilestore.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abc.mobilestore.entity.RoleEntity;
import com.abc.mobilestore.entity.UserEntity;
import com.abc.mobilestore.repository.RoleRepository;
import com.abc.mobilestore.repository.UserRepository;
import com.abc.mobilestore.util.JwtTokenUtil;


@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserEntity register(UserEntity userEntity) {
		System.out.print("into Service");
		//TODO: check username and email already existing or not. If it is existing throw some exception
				
		//before saving userEntity, encode the password
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		System.out.print("password generated");
		Set<RoleEntity> userRoles = new HashSet<>();
		
		Set<RoleEntity> roles = userEntity.getRoles();  
		
		roles.forEach(r -> {
			Optional<RoleEntity> optionalRoleEntity = roleRepository.findById(r.getId());			
			if(optionalRoleEntity.isEmpty()) {
			throw new RuntimeException("no roles");	
			}
			RoleEntity roleEntity = optionalRoleEntity.get();			
			userRoles.add(roleEntity);
			
		});
		System.out.print("roles generated");
		userEntity.setRoles(userRoles);
		
		userRepository.save(userEntity);
		System.out.print("Registered");
		return userEntity;
	}
	@Override
	public String login(String usernameOrEmail, String password) {
		
		//write a logic to validate a user with password
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(usernameOrEmail, password));
				
		//if login is success, generate jwt token and return it
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenUtil.generateToken(authentication);
		
		return token;
	}

}
