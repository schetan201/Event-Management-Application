package com.Sproject.finalProject.Services;

import com.Sproject.finalProject.Models.BookingModel;

public interface IBookingDAO {
	public boolean bookTkt(BookingModel bookingModel);
	public boolean cancelEvent(Integer eventId, Integer userId);
}
