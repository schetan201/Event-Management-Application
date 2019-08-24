package com.Sproject.finalProject.Dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.Sproject.finalProject.Models.AddressModel;
import com.Sproject.finalProject.Models.UserModel;
import com.Sproject.finalProject.Services.IUserDAO;
import com.Sproject.finalProject.hibernateUtil.HibernateUtil;

@Repository
public class IUserDAOImpl implements IUserDAO {
	public boolean addUser(UserModel userModel) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if(session != null && userModel != null) {
		        session.beginTransaction();
		        session.save(userModel);
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
	
	public boolean addAddress(AddressModel addressModel, long addressModel_userId) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if(session != null && addressModel != null && addressModel != null) {
					String hqlUpdate = "update AddressModel address set address.aptNumber = :aptNumber, address.streetName = :streetName, address.cityName = :cityName where address.userModel.userId = :addressModel_userId";
					int updatedEntities = session.createQuery( hqlUpdate )
					        .setString( "aptNumber", addressModel.getAptNumber())
					        .setString( "streetName", addressModel.getStreetName())
					        .setString( "cityName", addressModel.getCityName())
					        .setLong( "addressModel_userId", addressModel_userId)
					        .executeUpdate();
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
	
	public List<String> getUserName(){
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if(session != null) {
				Criteria crit = session.createCriteria(UserModel.class);
				Projection projection = Projections.property("userName"); 
				crit.setProjection(projection);
				List<String> userNameList = crit.list();
				if(userNameList.size() > 0) {
					System.out.println("hell");
					return userNameList;
				}else {
					return null;
				}
			}
		}catch(HibernateException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			if(session != null) {
				//session.close();
			}
		}
		return null;
	}
}
