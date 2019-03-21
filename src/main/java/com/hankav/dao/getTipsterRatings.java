package com.hankav.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.hankav.model.Tip;

public class getTipsterRatings {
	private List<Map<Object, Object>> fmap = new ArrayList<>();

	public List<Map<Object, Object>> byProfitAndCategory(String category) {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Tip.class);
		criteria.createAlias("tipster", "tipster");
		criteria.createAlias("tip_sport", "sport");
		criteria.add(Restrictions.eq("tipster.tipster_category", category));
		criteria.setProjection(Projections.projectionList().add(Projections.count("tip_id").as("tips"))
				.add(Projections.property("tipster.tipster_name").as("name"))
				.add(Projections.property("sport.name").as("sportname")).add(Projections.sum("tip_profit").as("profit"))
				.add(Projections.groupProperty("tipster.tipster_id")));
		criteria.addOrder(Order.desc("profit"));
		criteria.setMaxResults(10);
		criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List list = criteria.list();
		session.close();
		for (Object object : list) {
			System.out.println(object.toString());
			Map<Object, Object> map = (Map<Object, Object>) object;
			fmap.add(map);
		}

		return fmap;

	}

	/*
	 * 
	 * .add(Projections.sum("fieldA").as("prop2"))
	 * .add(Projections.sum("fieldB").as("prop3")) .add(Projections.sqlProjection(
	 * "sum(fieldA) + sum(fieldB) as total", new String[] { "total" }, new Type[] {
	 * StandardBasicTypes.INTEGER }), "total")
	 * .add(Projections.groupProperty("remarks")));
	 * criteria.addOrder(Order.desc("total")); criteria.setMaxResults(5);
	 * criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); List list =
	 * criteria.list(); for (Object object : list) { Map<Object, Object> map =
	 * (Map<Object, Object>) object; System.out.println(map);
	 * 
	 */

}
