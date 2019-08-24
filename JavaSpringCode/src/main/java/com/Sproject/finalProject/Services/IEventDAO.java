package com.Sproject.finalProject.Services;

import java.util.List;

import com.Sproject.finalProject.Models.EventModel;

public interface IEventDAO {
	public boolean addEvent(EventModel eventModel);
	public List<EventModel> getEvents();
	public List<EventModel> getEventsById(Integer userId);
	public boolean deleteEvent(Integer eventId);
	public boolean editEvent(EventModel eventModel);
	public List<EventModel> getBookedEvents(Integer userId);
}
