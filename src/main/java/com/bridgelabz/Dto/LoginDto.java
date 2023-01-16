package com.bridgelabz.Dto;

import org.springframework.stereotype.Component;

@Component
public class LoginDto {

	private String Email;
	private String password;
	public String getEmail() {
		return Email;
	}
	public void setEmail(String Email) {
		this.Email = Email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/*public String getEmail() {
		// TODO Auto-generated method stub
		return Email;
	}
	*/
	
}
