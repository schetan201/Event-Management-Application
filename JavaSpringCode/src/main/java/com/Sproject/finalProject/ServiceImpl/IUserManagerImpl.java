package com.Sproject.finalProject.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sproject.finalProject.Models.AddressModel;
import com.Sproject.finalProject.Models.UserModel;
import com.Sproject.finalProject.Services.IUserDAO;
import com.Sproject.finalProject.Services.IUserManagerService;

@Service
public class IUserManagerImpl implements IUserManagerService {
	@Autowired
	private IUserDAO iUserDAO;
	public boolean addUser(UserModel userModel) {
		return iUserDAO.addUser(userModel);
	}
	
	public boolean addAddress(AddressModel addressModel, long addressModel_userId) {
		return iUserDAO.addAddress(addressModel, addressModel_userId);

	}
}
