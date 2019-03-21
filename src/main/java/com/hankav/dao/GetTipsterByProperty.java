package com.hankav.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.hankav.model.Tipster;

public class GetTipsterByProperty {

	public Tipster byName(String tipster_name) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Tipster.class);
		criteria.add(Restrictions.eq("tipster_name", tipster_name).ignoreCase());
		Tipster ntipster = (Tipster) criteria.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return ntipster;
	}

}
