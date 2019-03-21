package com.hankav.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import com.hankav.model.Chat;

public class UsersChat {
	public List<Chat> getLastTen(int start,int max) {
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();
		Query query=session.createQuery("FROM Chat ORDER BY ID DESC");
	    query.setFirstResult(start);
	    query.setMaxResults(max);
	    List<Chat>chatlist=query.list();
	    session.close();
	    return chatlist;		
	}
	
}
