package com.Sproject.finalProject.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sproject.finalProject.Models.BookingModel;
import com.Sproject.finalProject.Services.IBookingDAO;
import com.Sproject.finalProject.Services.IBookingService;

@Service
public class IBookingImpl implements IBookingService{
	@Autowired
	private IBookingDAO iBookingDAO;
	
	public boolean bookTkt(BookingModel bookingModel) {
		return iBookingDAO.bookTkt(bookingModel);
	}
	
	public boolean cancelEvent(Integer eventId, Integer userId) {
		return iBookingDAO.cancelEvent(eventId, userId);
	}
}
