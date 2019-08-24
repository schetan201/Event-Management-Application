package com.Sproject.finalProject.Contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Sproject.finalProject.Models.EventModel;
import com.Sproject.finalProject.Models.UserModel;
import com.Sproject.finalProject.Services.IEventService;
import com.Sproject.finalProject.Validator.EventValidator;

@Controller
public class EventController {
	@Autowired
	private IEventService iEventService;
	
	@Autowired
	private EventValidator eventValidator;
	
	
	@RequestMapping(value = "/addEvent", method = RequestMethod.POST)
	@ResponseBody
	public boolean addEvent(@RequestBody EventModel eventModel, Model model) {
		if(eventValidator.validate(eventModel)) {
			return iEventService.addEvent(eventModel);
		}
		return false;
	}
	
	@RequestMapping(value = "/getEvents", method = RequestMethod.GET)
	@ResponseBody
	public List<EventModel> getEvents() {
		return iEventService.getEvents();
	}
	
	@RequestMapping(value = "/getEventsById", method = RequestMethod.GET)
	@ResponseBody
	public List<EventModel> getEventsById(@RequestParam Integer userId) {
		if(userId != null && userId != 0) {
			return iEventService.getEventsById(userId);
		}
		return null;
	}
	
	@RequestMapping(value = "/deleteEvent", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteEvent(@RequestParam Integer eventId) {
		if(eventId != null && eventId != 0) {
			return iEventService.deleteEvent(eventId);
		}
		return false;
	}
	
	@RequestMapping(value = "/editEvent", method = RequestMethod.PUT)
	@ResponseBody
	public boolean editEvent(@RequestBody EventModel eventModel, Model model) {
		if(eventValidator.validate(eventModel)) {
			return iEventService.editEvent(eventModel);
		}
		return false;
	}
	
	@RequestMapping(value = "/getBookedEvents", method = RequestMethod.GET)
	@ResponseBody
	public List<EventModel> getBookedEvents(@RequestParam Integer userId) {
		if(userId != null && userId != 0) {
			return iEventService.getBookedEvents(userId);
		}
		return null;
	}
}
