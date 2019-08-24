package com.Sproject.finalProject.Dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.Sproject.finalProject.Models.BookingModel;
import com.Sproject.finalProject.Services.IBookingDAO;
import com.Sproject.finalProject.hibernateUtil.HibernateUtil;

@Repository
public class IBookingDAOImpl implements IBookingDAO {
	
	public boolean bookTkt(BookingModel bookingModel) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if(session != null && bookingModel != null) {
		        session.beginTransaction();
		        session.save(bookingModel);
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
	
	
	
	public boolean cancelEvent(Integer eventId, Integer userId) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if(session != null) {
				Query query = session.createQuery("DELETE FROM BookingModel WHERE eventId = :eventId AND userId = :userId");
			    query.setParameter("eventId", eventId);
			    query.setParameter("userId", userId);
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
}
