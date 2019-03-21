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

public class GetAllTipsByCategory {

	public List<Object[]> getAllTips(String category, String page, String[] sports) throws ParseException {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Tip.class);
		int page1 = Integer.parseInt(page);
		criteria.createAlias("tipster", "tipster");
		criteria.createAlias("tip_sport", "sport");
		criteria.add(Restrictions.eq("tip_category", category));
		criteria.add(Restrictions.eq("status", "finished"));
		if (!(sports[0].equalsIgnoreCase("all"))) {
			criteria.add(Restrictions.in("sport.name", (Object[]) sports));
		}
		criteria.addOrder(Order.desc("tip_match_time"));
		criteria.setFirstResult((page1 - 1) * 10);
		criteria.setMaxResults(10);
		criteria.setProjection(
				Projections.projectionList().add(Projections.property("tip_id")).add(Projections.property("team1"))
						.add(Projections.property("team2")).add(Projections.property("tip_match_time"))
						.add(Projections.property("tip_league")).add(Projections.property("tip_tournament"))
						.add(Projections.property("sport.name")).add(Projections.property("tip_market"))
						.add(Projections.property("tip_lines")).add(Projections.property("tip_sublines"))
						.add(Projections.property("tip_odds")).add(Projections.property("tip_units"))
						.add(Projections.property("tip_bookmaker")).add(Projections.property("tip_result"))
						.add(Projections.property("tip_profit")).add(Projections.property("tipster.tipster_name")));
		List<Object[]> tips = criteria.list();
		session.close();
		return tips;

	}

	public Long getCountOfCategoryTips(String category, String[] sports) throws ParseException {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Tip.class);
		criteria.createAlias("tip_sport", "sport");
		criteria.add(Restrictions.eq("tip_category", category));
		if (!(sports[0].equalsIgnoreCase("all"))) {
			criteria.add(Restrictions.in("sport.name", (Object[]) sports));
		}
		criteria.add(Restrictions.eq("status", "finished"));
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		session.close();
		return count;

	}

	public List<String> getDistinctSportname(String category) {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Tip.class);
		criteria.createAlias("tip_sport", "sport");
		criteria.add(Restrictions.eq("tip_category", category));
		criteria.add(Restrictions.eq("status", "finished"));
		criteria.setProjection(Projections.distinct(Projections.property("sport.name")));
		List<String> sports = criteria.list();
		session.close();
		return sports;

	}

}
