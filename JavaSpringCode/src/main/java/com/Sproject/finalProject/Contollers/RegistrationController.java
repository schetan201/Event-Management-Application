package com.Sproject.finalProject.Contollers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Sproject.finalProject.Models.AddressModel;
import com.Sproject.finalProject.Models.UserModel;
import com.Sproject.finalProject.Services.IUserManagerService;
import com.Sproject.finalProject.Validator.UserValidator;


@Controller
public class RegistrationController {
		
		
		@Autowired
		private IUserManagerService iUserManager;
		
		@Autowired
		private UserValidator userValidator;
		
		/**
		 * Simply selects the home view to render by returning its name.
		 */
		/*
		 * @ModelAttribute(value="userModel") UserModel userModel, BindingResult result
		 */
		@RequestMapping(value = "/addUser", method = RequestMethod.POST)
		@ResponseBody
		public boolean addUser(@RequestBody UserModel userModel) {
			if(userValidator.validate(userModel)) {
				return iUserManager.addUser(userModel);
			}else {
				return false;	
			}
			
		}
		
		@RequestMapping(value = "/addAddress", method = RequestMethod.POST)
		@ResponseBody
		public boolean addAddress(@RequestBody AddressModel addressModel) {	
			 long addressModel_userId = 30;
			iUserManager.addAddress(addressModel, addressModel_userId);		
			return true;
		}
		
	}
