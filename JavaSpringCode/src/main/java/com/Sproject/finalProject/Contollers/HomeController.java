package com.Sproject.finalProject.Contollers;

import java.text.DateFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Sproject.finalProject.Models.AddressModel;
import com.Sproject.finalProject.Models.UserModel;
import com.Sproject.finalProject.hibernateUtil.HibernateUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {		
		return "home";
	}
	
}
