package com.bridgelabz.Dto;

import com.bridgelabz.model.EmailModel;

public class RegisteredDto {
	
	private String name;
	private String lastName;
	private String password;
	private String email;
    private EmailModel emailModel;
    
    
	public RegisteredDto() {
		super();
	}
	public EmailModel getEmailModel() {
		return emailModel;
	}
	public void setEmailModel(EmailModel emailModel) {
		this.emailModel = emailModel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
