package com.bridgelabz.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.Dto.LoginDto;
import com.bridgelabz.Dto.RegisteredDto;
import com.bridgelabz.exception.UserException;
import com.bridgelabz.model.UserModel;
import com.bridgelabz.repositery.UserRepositery;
import com.bridgelabz.utilities.EmailService;
import com.bridgelabz.utilities.JwtUtils;
import java.util.List;


@Service
public class UserService implements IuserService {

    @Autowired
    UserRepositery userRepositery;

    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    JwtUtils jwtutils;
    
    @Autowired
    EmailService emailService;

    Logger logger = LoggerFactory.getLogger(UserService.class);
    
    @Override
    public String registerUser(RegisteredDto registereddto) {
        if (userRepositery.findByEmail(registereddto.getEmail())==null) {
        	/*logger.error("In Error");
            logger.warn("In Warn");
            logger.info("In Info");
            logger.debug("In debug"); //this use max time as developer
            logger.trace("In Trace");*/
        	
            UserModel userModel = modelMapper.map(registereddto, UserModel.class);
            emailService.sendMail(registereddto.getEmail(),"User Registration Successfully" );
            userRepositery.save(userModel);
          
            return "User Added Successfully";
        }
        throw new UserException("User Already Exist");
    }

    @Override
    public UserModel getUserData(int id) {
        if (userRepositery.findById(id).isPresent()) {
            return userRepositery.findById(id).get();
        }
        throw new UserException("Id is invalid");
    }

    @Override
    public String deleteUserData(int id) {
        if (userRepositery.findById(id).isPresent()) {
            userRepositery.deleteById(id);
            return "data Deleted";
        }
        throw new UserException("User not Found");
    }

    @Override
    public UserModel updateUserData(int id, RegisteredDto registerddto) {
        if (userRepositery.findById(id).isPresent()) {
            UserModel model = userRepositery.findById(id).get();
            UserModel update = modelMapper.map(registerddto, UserModel.class);
            update.setId(id);
            if (userRepositery.findByEmail(registerddto.getEmail()).equals(null)) {
                System.out.println(update.toString());
                if (update.getName() == null){
                    update.setName(model.getName());
                }
                if (update.getLastName() == null){
                    update.setLastName(model.getLastName());
                }
                
                if (update.getEmail() == null){
                    update.setEmail(model.getEmail());
                }
                if (update.getPassword() == null){
                    update.setPassword(model.getPassword());
                }
                return userRepositery.save(update);
            }
            throw new UserException("This email id is already present in database...please try with anthor email id");
        }
        throw new UserException("Id is invalid");
    }
    
    
    /////////////////////////  login for token ///////////////////////////
    
    
    @Override
    public String login(LoginDto loginDto) {
        if (userRepositery.findByEmail(loginDto.getEmail()) != null) {
            if (userRepositery.findByEmail(loginDto.getEmail()).getPassword().equals(loginDto.getPassword())) {
               
            	String token=jwtutils.generateToken(loginDto);
            	UserModel userModel = userRepositery.findByEmail(loginDto.getEmail());
                userModel.setLogin(true);
                userModel.setId(userModel.getId());
                userRepositery.save(userModel);
                return token;
            }
            throw new UserException("please check Your Password");
        }
        throw new UserException("Check Your Email-ID");
    }

    
    ///////////////////// login for token /////////////////////////
    
    
    @Override
    public String userLogin(LoginDto loginDto) {
        if (userRepositery.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword()) != null) {
            return "Login Successfully";
        }
        throw new UserException("Check Your Email-id and Password");
    }
       
    
     /////////////////////// update api for token /////////////////////
    
    @Override
    public UserModel update(String token, RegisteredDto registeredDto) {
       LoginDto logindto= jwtutils.decodeToken(token);
      UserModel user= userRepositery.findByEmailAndPassword(logindto.getEmail(),logindto.getPassword());
            if (user !=null) {
        UserModel userSave = modelMapper.map(registeredDto, UserModel.class);
              userSave.setId(user.getId()) ;
             userSave.setLogin(true); 
             if (userRepositery.findByEmail(registeredDto.getEmail()) == null) {
                 System.out.println(userSave.toString());
                 if (userSave.getName() == null) {
                	 userSave.setName(user.getName());
                 }
                 if (userSave.getLastName() == null) {
                	 userSave.setLastName(user.getLastName());
                }
                 if (userSave.getEmail() == null) {
                	 userSave.setEmail(user.getEmail());
                 }
                 if (userSave.getPassword() == null) {
                	 userSave.setPassword(user.getPassword());
                 }
                 return userRepositery.save(userSave);
             }
             throw new UserException("This email id is already present in database...please try with anthor email id");
         }

    
			throw new UserException("Email And Password is not Matched");
    } 
      /////////////////////// Delete Api for token   ////////////////////////
    
    public String delete(String token) {
        LoginDto loginDto = jwtutils.decodeToken(token);
        UserModel user = userRepositery.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if (userRepositery.findByEmail(user.getEmail()).isLogin()) {
            userRepositery.deleteById(user.getId());
            return "data Deleted";
        }
        throw new UserException("User not Found");
    }

    
    
    ////////////////// logout api ////////////
    
    @Override
    public String logOut(String token) {
        LoginDto loginDto = jwtutils.decodeToken(token);
        UserModel userLogout = userRepositery.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if (userRepositery.findByEmail(userLogout.getEmail()).isLogin()) {
            userLogout.setLogin(false);
            userLogout.setId(userLogout.getId());
            userRepositery.save(userLogout);

        }
        return "User Logout Successfully";
    }
    
    
    
    @Override
    public List<UserModel> getAllUser(int id) {
    	if (userRepositery.findById(id).get().getRole().equals("Admin"))
    	{
    	List<UserModel> listSaved = userRepositery.findAll();
		return listSaved;
    }
    throw new UserException("Not Accessable to You");
    
    }


@Override
	public String deleteUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(String token, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserData(String token) {
		// TODO Auto-generated method stub
		
	}
}


	/*@Override
	public String userLogin() {
		// TODO Auto-generated method stub
		return null;
	}*/





	
	
	/*@Override
	public String addUserData(RegisteredDto registeredDto) {
    UserModel userModel= modelMapper.map(registeredDto, UserModel.class);
		userRepositery.save(userModel);
     return "User added successfully";
		
	}
	@Override
	public UserModel getUserData(int id) {
		return userRepositery.findById(id).get();
	}

	@Override
	public String userLogin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserModel updateUserData(int id,RegisteredDto registereddto) {
  UserModel userUpdate=modelMapper.map(registereddto, UserModel.class);
		return userRepositery.save(userUpdate);
	}

	@Override
	public String deleteUserData(int id) {
		userRepositery.deleteById(id);
	     return "data deleted";
					
	}
	

}*/
