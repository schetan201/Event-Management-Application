package com.Sproject.finalProject.Services;

import com.Sproject.finalProject.Models.AddressModel;
import com.Sproject.finalProject.Models.UserModel;

public interface IUserDAO {
	public boolean addUser(UserModel userModel);
	public boolean addAddress(AddressModel addressModel, long addressModel_userId);
}
