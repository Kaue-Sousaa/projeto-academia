package br.com.academia.enums;

public enum UserRoleEn{
	
	ADMIN("ADMIN"),
	USER("USER");
	
	private String role;
	
	UserRoleEn(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
}
