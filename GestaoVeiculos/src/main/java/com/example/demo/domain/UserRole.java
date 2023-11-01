package com.example.demo.domain;

public enum UserRole {
	ADMIN("admin"),
	USER("user"),
	ESTUDANTE("estudante");
	
	UserRole(String role) {
		this.role = role;
	}

	private String role;
	
	public String getUserRole() {
		return role;
	}
	
}
