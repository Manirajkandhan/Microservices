package com.abc.mobilestore.service;

import com.abc.mobilestore.entity.UserEntity;

public interface AuthService {

	UserEntity register(UserEntity userEntity);
	
	String login(String usernameOrEmail, String password);
}
