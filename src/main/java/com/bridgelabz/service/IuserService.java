package com.bridgelabz.service;

import java.util.List;

import com.bridgelabz.Dto.LoginDto;
import com.bridgelabz.Dto.RegisteredDto;
import com.bridgelabz.model.UserModel;

public interface IuserService {

	public String registerUser(RegisteredDto registeredDto);
	
	public UserModel getUserData(int id);
		
	//public String updateUser();
	
	public String deleteUser(int id);

	public UserModel updateUserData(int id, RegisteredDto registereddto);

	String deleteUserData(int id);

	    String login(LoginDto loginDto);
	    String userLogin(LoginDto loginDto);
	    
	    UserModel update(String token, RegisteredDto registeredDto);
	    
	    String delete(String token, String email);

		public void deleteUserData(String token);

		public String logOut(String token);
		public List<UserModel> getAllUser(int id);
}
