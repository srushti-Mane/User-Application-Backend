package com.bridgelabz.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.Dto.RegisteredDto;
import com.bridgelabz.model.UserModel;
@Repository
public interface UserRepositery extends JpaRepository<UserModel,Integer> {

	void save(RegisteredDto registeredDto);
	  public UserModel findByEmail(String email);
	    public UserModel findByPassword(String password);
	    UserModel findByEmailAndPassword(String email, String password);
	    void deleteByEmail(String email);
	}

