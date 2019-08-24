package com.Sproject.finalProject.Dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.Sproject.finalProject.Models.UserModel;
import com.Sproject.finalProject.Services.ILoginDAO;
import com.Sproject.finalProject.hibernateUtil.HibernateUtil;

@Repository
public class ILoginDAOImpl implements ILoginDAO {
		public UserModel loginUser(String userName, String password) {
			Session session = null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				if(session != null) {
					Criteria crit = session.createCriteria(UserModel.class);
					crit.add(Restrictions.eq("userName", userName));
					UserModel userModel = (UserModel) crit.uniqueResult();
					if(userModel != null && checkLogin(userModel, userName, password)) {
						return userModel;
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
	
		private boolean checkLogin(UserModel userModel, String userName, String password) {
			if(userModel != null) {
				return userModel.getUserName().equals(userName) && userModel.getPassword().equals(password);
			}
			return false;
		}
		
		public UserModel getUserProfile(String userName) {
			Session session = null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				if(session != null) {
					Criteria crit = session.createCriteria(UserModel.class);
					crit.add(Restrictions.eq("userName", userName));
					UserModel userModel = (UserModel) crit.uniqueResult();
					if(userModel != null) {
						return userModel;
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
