package com.hankav.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import com.hankav.model.Market;

public class GetMarketByProperty {

	public Market byName(String market_name) {
		// TODO Auto-generated method stub
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();
		session.beginTransaction();
		Criteria criteria=session.createCriteria(Market.class);
		criteria.add(Restrictions.eq("name",market_name));
		Market nmarket=(Market) criteria.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return nmarket;
	}

}