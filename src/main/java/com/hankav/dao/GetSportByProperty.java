package com.hankav.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.hankav.model.Sport;

public class GetSportByProperty {

	public Sport byName(String sport_name,Session session) {
		Criteria criteria=session.createCriteria(Sport.class);
		criteria.add(Restrictions.eq("name",sport_name));
		Sport nsport=(Sport) criteria.uniqueResult();
		return nsport;
	}

	public Sport byName(String sport_name) {
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();
		Criteria criteria=session.createCriteria(Sport.class);
		criteria.add(Restrictions.eq("name",sport_name));
		Sport nsport=(Sport) criteria.uniqueResult();
	    session.close();
		return nsport;
	
	}
}
