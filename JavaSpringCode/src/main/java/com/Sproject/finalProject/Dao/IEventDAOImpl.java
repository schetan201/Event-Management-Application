package com.Sproject.finalProject.Dao;

import java.util.ArrayList;
import java.util.List;



import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.Sproject.finalProject.Models.EventModel;
import com.Sproject.finalProject.Services.IEventDAO;
import com.Sproject.finalProject.hibernateUtil.HibernateUtil;

@Repository
public class IEventDAOImpl implements IEventDAO{
	
	public boolean addEvent(EventModel eventModel) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if(session != null && eventModel != null) {
		        session.beginTransaction();
		        session.save(eventModel);
		        session.getTransaction().commit();	
				return true;
			}
		}catch(HibernateException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			if(session != null) {
				//session.close();
			}
		}
		return false;
	}
	
	public List<EventModel> getEvents() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if(session != null) {
				Criteria crit = session.createCriteria(EventModel.class);
				List<EventModel> eventModel = crit.list();
				if(eventModel != null) {
					return eventModel;
				}else
					return null;
			}
	}catch(Exception e) {
		return null;
	}finally {
		if(session != null) {
			//session.close();
		}
	}
	return null;
}
	
	public List<EventModel> getEventsById(Integer userId) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if(session != null) {
				Criteria crit = session.createCriteria(EventModel.class);
				crit.add(Restrictions.eq("userId", userId));
				List<EventModel> eventModel = crit.list();
				if(eventModel != null) {
					return eventModel;
				}else
					return null;
			}
	}catch(Exception e) {
		return null;
	}finally {
		if(session != null) {
			//session.close();
		}
	}
	return null;
}
	
	
	public boolean deleteEvent(Integer eventId) {
			Session session = null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				if(session != null) {
					Query query = session.createQuery("DELETE FROM EventModel WHERE eventId = :eventId");
				    query.setParameter("eventId", eventId);
				    int deleted = query.executeUpdate();
					if(deleted > 0) {
						return true;
					}else
						return false;
				}
			}catch(Exception e) {
				return false;		
		}finally {
			if(session != null) {
				//session.close();
			}
		}
		return false;
	}
	
	public boolean editEvent(EventModel eventModel) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if(session != null && eventModel != null) {
		        session.beginTransaction();
		        session.saveOrUpdate("EventModel", eventModel);
		        session.getTransaction().commit();	
				return true;
			}
		}catch(HibernateException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			if(session != null) {
				//session.close();
			}
		}
		return false;
	}
	
	public List<EventModel> getBookedEvents(Integer userId) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if(session != null) {
				List<EventModel> eventList = new ArrayList<EventModel>();
				Query query = session.createSQLQuery("select event.eventId as eventId, event.eventName as eventName, event.eventDetails as eventDetails, event.eventDate as eventDate from EventModel as event where event.eventId in (select booking.eventId from BookingModel as booking where booking.userId = :userId)");
				query.setParameter("userId", userId);
				List<Object[]> rows = query.list();
				for(Object[] row : rows){
					EventModel eventModel = new EventModel();
					eventModel.setEventId(Integer.parseInt(row[0].toString()));
					eventModel.setEventName(row[1].toString());
					eventModel.setEventDetails(row[2].toString());
					eventModel.setEventDate(row[3].toString());
					eventList.add(eventModel);
				}
				if(eventList != null) {
					return eventList;
				}else
					return null;
			}
	}catch(Exception e) {
		return null;
	}finally {
		if(session != null) {
			//session.close();
		}
	}
	return null;
}
}
