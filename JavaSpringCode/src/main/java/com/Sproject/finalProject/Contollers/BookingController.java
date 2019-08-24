package com.Sproject.finalProject.Contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Sproject.finalProject.Models.BookingModel;
import com.Sproject.finalProject.Services.IBookingService;

@Controller
public class BookingController {

	@Autowired
	private IBookingService iBookingService;
	
	@RequestMapping(value = "/bookTkt", method = RequestMethod.POST)
	@ResponseBody
	public boolean bookTkt(@RequestBody BookingModel bookingModel) {
		if(bookingModel != null) {
		return iBookingService.bookTkt(bookingModel);
		}
		else {
			return false;
		}
	}
	
	@RequestMapping(value = "/cancelEvent", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean cancelEvent(@RequestParam Integer eventId, @RequestParam Integer userId) {
		if(eventId != null && eventId > 0 && userId != null && userId > 0) {
		return iBookingService.cancelEvent(eventId, userId);
		}
		else {
			return false;
		}
	}
}
