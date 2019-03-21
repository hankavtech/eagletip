package com.hankav.dao;

import java.util.Arrays;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hankav.model.Market;
import com.hankav.model.Sport;

public class AddMarketToSport {

	public void nyName(String sportname, String marketname, String[] lines, boolean sublines) {
		// TODO Auto-generated method stub
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(com.hankav.model.Sport.class);
		criteria.add(Restrictions.eq("name", sportname));
		com.hankav.model.Sport sport = (Sport) criteria.uniqueResult();
		Market m1 = new Market();
		m1.setName(marketname);
		m1.setMylines(Arrays.asList(lines));
		m1.setSublines(sublines);
		m1.setSport(sport);
		sport.getMarkets().add(m1);
		tx.commit();
		session.close();

	}

}
