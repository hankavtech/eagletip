package com.hankav.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hankav.model.Sport;

public class GetMarketsBySport {

	public String byName(String name) {
		// TODO Auto-generated method stub
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Sport.class);
		criteria.add(Restrictions.eq("name", name));
		Sport sport = (Sport) criteria.uniqueResult();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(sport);
		System.out.print(json);
		tx.commit();
		session.close();
		return json;

	}

}
