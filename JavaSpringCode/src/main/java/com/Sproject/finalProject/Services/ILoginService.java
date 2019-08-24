package com.Sproject.finalProject.Services;


import java.util.List;

import com.Sproject.finalProject.Models.UserModel;

public interface ILoginService {
	public UserModel loginUser(String userName, String password);
	public UserModel getUserProfile(String userName);
}
