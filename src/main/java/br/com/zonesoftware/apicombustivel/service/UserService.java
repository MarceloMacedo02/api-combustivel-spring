package br.com.zonesoftware.apicombustivel.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.zonesoftware.apicombustivel.auth.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
