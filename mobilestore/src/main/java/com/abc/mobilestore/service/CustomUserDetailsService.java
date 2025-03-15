package com.abc.mobilestore.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abc.mobilestore.entity.RoleEntity;
import com.abc.mobilestore.entity.UserEntity;
import com.abc.mobilestore.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		
		Optional<UserEntity> optionalUserEntity = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
		
		if(optionalUserEntity.isEmpty()) {
			throw new UsernameNotFoundException("User not existing");
		}
		
		UserEntity userEntity = optionalUserEntity.get();		
		Set<RoleEntity> userRoles = userEntity.getRoles();
		
		Set<GrantedAuthority> authorities= userRoles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
		
		User user = new User(usernameOrEmail,userEntity.getPassword(),authorities);		
		
		return user;
	}

}