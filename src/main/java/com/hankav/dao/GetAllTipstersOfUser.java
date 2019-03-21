package com.hankav.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hankav.model.Tipster;

public class GetAllTipstersOfUser {

	public Map<String, String> byUserName(String name) {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Tipster.class);
		criteria.createAlias("user", "user");
		criteria.createAlias("tipster_sport", "sport");
		criteria.add(Restrictions.eq("user.username", name));
		criteria.setProjection(Projections.projectionList().add(Projections.property("tipster_name"))
				.add(Projections.property("sport.name")));
		List<Object[]> list = criteria.list();
		Map<String, String> map = new HashMap<>();
		for (Object[] tipster_profile : list) {
			map.put(tipster_profile[0].toString(), tipster_profile[1].toString());

		}

		session.close();
		return map;
	}

}
