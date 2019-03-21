package com.hankav.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import com.hankav.model.Tip;

public class TipsterTips {
	
	public List<Tip> lastTen(int start,int max){
	    List<Tip>list;
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();		
		Query query=session.createQuery("FROM Tip");
		query.setFirstResult(start);
		query.setMaxResults(max);
		list=query.list();
		session.close();
		return list;
		
	}
	
	public List<Tip> lastTenOfTipster(int start,int max){
	    List<Tip>list;
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();		
		Query query=session.createQuery("FROM Tip ORDER BY date DESC");
		query.setFirstResult(start);
		query.setMaxResults(max);
		list=query.list();
		session.close();
		return list;
		
	}

}
