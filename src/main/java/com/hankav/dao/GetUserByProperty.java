package com.hankav.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.hankav.model.User;

public class GetUserByProperty {

	public User byName(String user_name, Session session) {
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", user_name).ignoreCase());
		User user = (User) criteria.uniqueResult();
		return user;
	}

	public User byName(String user_name) {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", user_name).ignoreCase());
		User user = (User) criteria.uniqueResult();
		session.close();
		return user;

	}

}
