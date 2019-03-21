package com.hankav.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import com.hankav.model.User;

public class LoggedOnUser {
		
	    private User user;
		public User getUser(String username) {
			SessionFactory factory=HibSessionFactory.getFactory();
			Session session=factory.openSession();
			session.beginTransaction();
			Query query=session.createQuery("FROM User WHERE username=?1");
			query.setParameter(1,username);
			try {
		     user = (User)query.uniqueResult();
			}
			catch(NoResultException e) {
				System.out.println("You must be logged in to post a message");
				
			}
		    session.close();
			return user;
		}		


}
