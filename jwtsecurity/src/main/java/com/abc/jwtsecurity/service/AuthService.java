package com.abc.jwtsecurity.service;

import com.abc.jwtsecurity.entity.UserEntity;

public interface AuthService {

	UserEntity register(UserEntity userEntity);
	
	String login(String usernameOrEmail, String password);
}
