package com.Sproject.finalProject.Validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Sproject.finalProject.Dao.IUserDAOImpl;
import com.Sproject.finalProject.Models.EventModel;
import com.Sproject.finalProject.Models.UserModel;

@Repository
public class EventValidator {

public boolean supports(Class<?> aClass) {
return aClass.equals(EventModel.class);
}

private Pattern pattern;  
private Matcher matcher;  

String STRING_PATTERN = "[a-zA-Z]+";  

@Autowired
private IUserDAOImpl iUserDAOImpl;

public boolean validate(EventModel eventModel) {

	if(eventModel == null) {
		return false;
	}
	
	if (eventModel.getEventName() == null || eventModel.getEventName().isEmpty()) { 
		return false;
	}  

	if (eventModel.getEventDetails() == null || eventModel.getEventDetails().isEmpty()) {  
		return false;
	}

	if (eventModel.getEventContact() == null || eventModel.getEventContact().isEmpty()) { 
		return false;
	}else if(String.valueOf(eventModel.getEventContact()).length() != 10) {
		return false;
	}
	
	if (eventModel.getEventVenue() == null || eventModel.getEventVenue().isEmpty()) {  
		return false;
	}
return true;
}
}