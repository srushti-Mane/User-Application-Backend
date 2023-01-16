package com.bridgelabz.utilities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.Dto.LoginDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtils implements Serializable {
	
	
	@Autowired
	LoginDto loginDto;
	
	String secretKey = "Srushti";
	
	public String generateToken (LoginDto logindto) {
  
	Map<String,Object> claims=new HashMap<>();
	claims.put ("email",logindto.getEmail());
	claims.put ("password",logindto.getPassword());
	return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256,secretKey).compact();
	
}
  public LoginDto decodeToken(String token) {
	  Map<String, Object> claims=new HashMap<>();
	 claims= Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	  loginDto.setEmail((String)claims.get("email"));
	  loginDto.setPassword((String)claims.get("password"));
	return loginDto; 
	}
}