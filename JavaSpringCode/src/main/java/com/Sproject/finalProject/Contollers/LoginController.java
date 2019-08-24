package com.Sproject.finalProject.Contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Sproject.finalProject.Models.UserModel;
import com.Sproject.finalProject.Services.ILoginService;
import com.Sproject.finalProject.Validator.UserValidator;

@Controller
public class LoginController {
	@Autowired
	private ILoginService iLoginService;
	
	@Autowired
	private UserValidator userValidator;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public UserModel login(@RequestBody UserModel userModel) {
		if(userValidator.validateLogin(userModel)) {
			return iLoginService.loginUser(userModel.getUserName(), userModel.getPassword());
		}else {
			return null;	
		}
	}
	
	@RequestMapping(value = "/getUserProfile", method = RequestMethod.GET)
	@ResponseBody
	public UserModel getUserProfile(@RequestParam String userName) {
		if(!userName.equals(null) && !userName.isEmpty()) {
			return iLoginService.getUserProfile(userName);
		}else {
			return null;
		}
		
	}
}
