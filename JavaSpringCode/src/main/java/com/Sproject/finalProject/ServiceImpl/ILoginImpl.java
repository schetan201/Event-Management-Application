package com.Sproject.finalProject.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sproject.finalProject.Models.UserModel;
import com.Sproject.finalProject.Services.ILoginDAO;
import com.Sproject.finalProject.Services.ILoginService;

@Service
public class ILoginImpl implements ILoginService{
	@Autowired
	private ILoginDAO iLoginDAOService;
	public UserModel loginUser(String userName, String password) {
		return iLoginDAOService.loginUser(userName, password);
		
	}
	
	public UserModel getUserProfile(String userName) {
		return iLoginDAOService.getUserProfile(userName);
	}
}
