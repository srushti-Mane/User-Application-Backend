package com.bridgelabz.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.ui.Model;

@Entity
public class UserModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	int id;
	private String name;
	private String lastName;
	private String password;
	private String email;
	private boolean isLogin=false;
	
    String role;
    
    @OneToOne(cascade = CascadeType.ALL)
    EmailModel emailModel;
    
	public EmailModel getEmailModel() {
		return emailModel;
	}
	public void setEmailModel(EmailModel emailModel) {
		this.emailModel = emailModel;
	}
	public UserModel() {
		// TODO Auto-generated constructor stub
	}
	public UserModel(String role) {
		super();
		this.role = role;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
