package com.hankav.dao;

import java.text.ParseException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hankav.model.Tip;

public class GetTopPortionStatsForTipsterPage {

	public List<Object[]> giveStats(String tipster_name) throws ParseException {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Tip.class);
		criteria.createAlias("tipster", "tipster");
		criteria.createAlias("tip_sport", "sport");
		criteria.add(Restrictions.eq("tipster.tipster_name", tipster_name));
		criteria.add(Restrictions.eq("status", "finished"));
		criteria.addOrder(Order.desc("tip_match_time"));
		criteria.setProjection(Projections.projectionList().add(Projections.rowCount()).add(Projections.avg("tip_odds"))
				.add(Projections.sum("tip_profit")).add(Projections.property("tipster.tipster_name"))
				.add(Projections.property("sport.name")).add(Projections.property("tipster.tipster_price"))
				.add(Projections.property("tipster.tipster_category")));
		List<Object[]> tips = criteria.list();
		criteria.setProjection(
				Projections.projectionList().add(Projections.rowCount()).add(Projections.groupProperty("tip_result")));
		List<Object[]> results = criteria.list();
		Object[] toadd = new Object[2];
		for (Object[] obj : results) {
			if (obj[1].toString().equalsIgnoreCase("won")) {
				toadd[0] = obj[0];
			} else if (obj[1].toString().equalsIgnoreCase("lost")) {
				toadd[1] = obj[0];
			}
		}
		tips.add(toadd);
		session.close();
		return tips;

	}

}
