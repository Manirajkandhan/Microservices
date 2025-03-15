package com.abc.jwtsecurity.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDTO {

	private String usernameOrEmail;
	private String password;
}