package com.Sproject.finalProject.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sproject.finalProject.Models.EventModel;
import com.Sproject.finalProject.Services.IEventDAO;
import com.Sproject.finalProject.Services.IEventService;

@Service
public class IEventImpl implements IEventService{
	@Autowired
	private IEventDAO iEventDAO;
	
	public boolean addEvent(EventModel eventModel) {
		return iEventDAO.addEvent(eventModel);
		
	}
	
	public List<EventModel> getEvents() {
		return iEventDAO.getEvents();
	}
	
	public List<EventModel> getEventsById(Integer userId) {
		return iEventDAO.getEventsById(userId);
	}
	
	public boolean deleteEvent(Integer eventId) {
		return iEventDAO.deleteEvent(eventId);
	}
	
	public boolean editEvent(EventModel eventModel) {
		return iEventDAO.editEvent(eventModel);
	}
	
	public List<EventModel> getBookedEvents(Integer userId) {
		return iEventDAO.getBookedEvents(userId);
	}
}
