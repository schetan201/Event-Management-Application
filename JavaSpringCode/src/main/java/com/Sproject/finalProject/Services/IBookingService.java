package com.Sproject.finalProject.Services;

import com.Sproject.finalProject.Models.BookingModel;

public interface IBookingService {
	public boolean bookTkt(BookingModel bookingModel);
	public boolean cancelEvent(Integer eventId, Integer userId);
}
