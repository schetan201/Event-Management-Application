package com.Sproject.finalProject.Validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Sproject.finalProject.Dao.IUserDAOImpl;
import com.Sproject.finalProject.Models.UserModel;


@Repository
public class UserValidator {

public boolean supports(Class<?> aClass) {
return aClass.equals(UserModel.class);
}

private Pattern pattern;  
private Matcher matcher;  

String STRING_PATTERN = "[a-zA-Z]+";  

@Autowired
private IUserDAOImpl iUserDAOImpl;

public boolean validate(UserModel userModel) {

	if(userModel == null) {
		return false;
	}
	
	if (userModel.getFirstName() == null || userModel.getFirstName().isEmpty()) { 
		return false;
	}else {
	  pattern = Pattern.compile(STRING_PATTERN);  
	  matcher = pattern.matcher(userModel.getFirstName());  
	  if (!matcher.matches()) {  
	   return false;  
	  }  
	 }  

	if (userModel.getLastName() == null || userModel.getLastName().isEmpty()) {  
		return false;
	}else {
	  pattern = Pattern.compile(STRING_PATTERN);  
	  matcher = pattern.matcher(userModel.getLastName());  
	  if (!matcher.matches()) {  
	   return false;  
	  }  
	 } 

	if (userModel.getUserName() == null || userModel.getUserName().isEmpty()) { 
		return false;
	}else {
		List<String> userNameList= iUserDAOImpl.getUserName();
		//System.out.println("*****"+userEnteredValue);
		//boolean result= true;
		try {
			for(String userName: userNameList) {
				if(userName.equals(userModel.getUserName())) {
					return false ;
				}
			}
			
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			
		}	
	}



	if (userModel.getPassword() == null || userModel.getPassword().isEmpty()) {  
	 return false;
	}
return true;
}

	public boolean validateLogin(UserModel userModel) {
		if (userModel.getUserName() == null || userModel.getUserName().isEmpty()) { 
			return false;
		}
		if (userModel.getPassword() == null || userModel.getPassword().isEmpty()) {  
			 return false;
			}
		return true;
	}

}