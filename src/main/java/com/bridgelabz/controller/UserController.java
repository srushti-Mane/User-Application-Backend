package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.Response;
import com.bridgelabz.Dto.LoginDto;
import com.bridgelabz.Dto.RegisteredDto;
import com.bridgelabz.model.UserModel;
import com.bridgelabz.repositery.UserRepositery;
import com.bridgelabz.service.IuserService;
import com.bridgelabz.service.UserService;
import java.util.List;



@RestController
@RequestMapping("/user")
public class UserController {
	
        @Autowired 
        UserRepositery userRepositery;
        
        @Autowired
        IuserService iuserService;
        
        @Autowired
        private UserService userService;
        
		@GetMapping("/hello")
		public String printhello() {
			return "HEllO WORLD";
			
		}
	
		/*@PostMapping("/AddUser")
		public String addUser(@RequestBody UserModel userModel) {
			userRepositery.save(userModel);
			return ("object added successfully");*/
			
       
    
        @PostMapping("/addUser")
       public ResponseEntity<Response> addUser(@RequestBody RegisteredDto registeredDto){
       iuserService.registerUser(registeredDto);
       Response response = new Response(registeredDto, "UserData Added Successfull");
        return new ResponseEntity<>(response, HttpStatus.OK);
     }

       @GetMapping("/getData/{id}")
       public ResponseEntity<Response> getUser(@PathVariable int id) {
       UserModel getUserData = iuserService.getUserData(id);
       Response response = new Response(getUserData, "Get call for id successfull");
       return new ResponseEntity<>(response, HttpStatus.OK);
     }

       @DeleteMapping("/delete")
       public ResponseEntity<Response> deleteUser(@RequestParam int id) {
       iuserService.deleteUserData(id);
       Response response = new Response("Deleted for id: " + id, "Deleted Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }

       @PutMapping("/update/{id}")
       public ResponseEntity<Response> updateFirstLastName(@PathVariable int id, @RequestBody RegisteredDto registeredDto) {
       UserModel update = iuserService.updateUserData(id, registeredDto);
       Response response = new Response(update, "User Updated Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
       
       
       ////////////////////// login for token  //////////////////
       
       
       @PostMapping("/login")
       public ResponseEntity<Response> loginPage(@RequestBody LoginDto loginDto) {
           String token= iuserService.login(loginDto);
           Response response = new Response(token, "Login Succesfull");
           return new ResponseEntity<>(response, HttpStatus.OK);
       }
       
       
       @PutMapping("/updateData/")
       public ResponseEntity<Response> updateData(@RequestHeader String token, RegisteredDto registeredDto) {
           UserModel update = iuserService.update(token, registeredDto);
           Response response = new Response(update, "User Updated Successfully");
           return new ResponseEntity<>(response, HttpStatus.OK);
       }

       @DeleteMapping("/deleteUser")
       public ResponseEntity<Response> deleteUser(@RequestParam String token ,@RequestParam String email) {
           iuserService.delete(token,email);
           Response response = new Response("Deleted for User: " + email, "Deleted Successfully");
           return new ResponseEntity<>(response, HttpStatus.OK);
       }
       
       //////// delete for login  token  //////////////////////

      @DeleteMapping("/deleteUserData")
       public ResponseEntity<Response> deleteUser(@RequestHeader String token) {
           iuserService.deleteUserData(token);
           Response response = new Response("Deleted for User: ", "Deleted Successfully");
           return new ResponseEntity<>(response, HttpStatus.OK);
       }
      
      ////////////////// logout api ////////////////s
      @PutMapping("/LogOut")
      public ResponseEntity<Response> logOutUser(@RequestHeader String token) {
          iuserService.logOut(token);
          Response response = new Response("User Logout", "SuccessFully");
          return  new ResponseEntity<>(response, HttpStatus.OK);
      }
       
      
      @GetMapping
      public ResponseEntity<Response> getAllUser(@RequestParam int id) {
    	  List<UserModel> userList = iuserService.getAllUser(id);
          Response response = new Response(userList,"All User Get SuccessFully");
          return new ResponseEntity<>(response, HttpStatus.OK);
    	  
      }

      
      
}
     

   

		/*@GetMapping("/getData{id}")
		public UserModel getUserData(@PathVariable("id") int id) {
			return iuserService.getUserData(id);
		}
		 
		@DeleteMapping("/delete")
		public ResponseEntity<Response> deleteUser(@RequestBody RegisteredDto registereddto,@PathVariable int id) {
			UserModel usermodel = iuserService.updateUser(id, registereddto);
			Response response = new Response(usermodel, "User deleted Successfully");
			return new ResponseEntity<>(response,HttpStatus.OK);
		
		//public String deleteUserData(@RequestParam int id) {
			//return iuserService.deleteUser(id);
		}
		
		@PutMapping("/updateUser/{id}")
		public ResponseEntity<Response> updateUser(@RequestBody RegisteredDto registereddto,@PathVariable int id) {
			UserModel usermodel = iuserService.updateUser(id, registereddto);
			Response response = new Response(usermodel, "User Updated Successfully");
			return new ResponseEntity<>(response,HttpStatus.OK);
			
			}
		
		//public UserModel updateUser(@RequestBody RegisteredDto registereddto,@PathVariable int id) {
			//return iuserService.updateUser(id, registereddto);
				
		}*/

