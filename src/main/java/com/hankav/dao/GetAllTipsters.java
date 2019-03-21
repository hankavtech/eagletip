package com.hankav.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.hankav.model.Tipster;

public class GetAllTipsters {

	public String ByCategoryAndOrderByName(String category, String[] sports, Integer page) {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Tipster.class);
		if (!(category.equalsIgnoreCase("all"))) {
			criteria.add(Restrictions.eq("tipster_category", category));
		}
		criteria.createAlias("tipster_sport", "t_sport");
		criteria.createAlias("tipster_stats", "stats");
		if (!(sports[0].equalsIgnoreCase("all"))) {
			criteria.add(Restrictions.in("t_sport.name", (Object[]) sports));
		}
		criteria.add(Restrictions.gt("stats.tips", 0));
		int count = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
		criteria.setFirstResult((page - 1) * 10);
		criteria.setMaxResults(10);
		criteria.setProjection(Projections.projectionList().add(Projections.property("tipster_id"))
				.add(Projections.property("tipster_name")).add(Projections.property("t_sport.name"))
				.add(Projections.property("tipster_price")).add(Projections.property("stats.profit"))
				.add(Projections.property("stats.tips")).add(Projections.property("stats.avodds"))
				.add(Projections.property("stats.yield")).add(Projections.property("stats.winpercentage")));
		criteria.addOrder(Order.asc("tipster_name"));
		List<Object[]> list = criteria.list();
		list.add(new Object[] { count });
		session.close();
		return new Gson().toJson(list);

	}

	public String ByCategoryAndOrderByProfit(String category, String[] sports, Integer page) {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Tipster.class);
		if (!(category.equalsIgnoreCase("all"))) {
			criteria.add(Restrictions.eq("tipster_category", category));
		}
		criteria.createAlias("tipster_sport", "t_sport");
		criteria.createAlias("tipster_stats", "stats");
		criteria.add(Restrictions.gt("stats.tips", 0));
		if (!(sports[0].equalsIgnoreCase("all"))) {
			criteria.add(Restrictions.in("t_sport.name", (Object[]) sports));
		}
		int count = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());

		criteria.setFirstResult((page - 1) * 10);
		criteria.setMaxResults(10);
		criteria.setProjection(Projections.projectionList().add(Projections.property("tipster_id"))
				.add(Projections.property("tipster_name")).add(Projections.property("t_sport.name"))
				.add(Projections.property("tipster_price")).add(Projections.property("stats.profit"))
				.add(Projections.property("stats.tips")).add(Projections.property("stats.avodds"))
				.add(Projections.property("stats.yield")).add(Projections.property("stats.winpercentage")));
		criteria.addOrder(Order.desc("stats.profit"));
		List<Object[]> list = criteria.list();
		list.add(new Object[] { count });
		session.close();
		return new Gson().toJson(list);

	}

	public String ByCategoryAndOrderByWinPercentage(String category, String[] sports, Integer page) {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Tipster.class);
		if (!(category.equalsIgnoreCase("all"))) {
			criteria.add(Restrictions.eq("tipster_category", category));
		}
		criteria.createAlias("tipster_sport", "t_sport");
		criteria.createAlias("tipster_stats", "stats");
		criteria.add(Restrictions.gt("stats.tips", 0));
		if (!(sports[0].equalsIgnoreCase("all"))) {
			criteria.add(Restrictions.in("t_sport.name", (Object[]) sports));
		}
		int count = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
		criteria.setFirstResult((page - 1) * 10);
		criteria.setMaxResults(10);
		criteria.setProjection(Projections.projectionList().add(Projections.property("tipster_id"))
				.add(Projections.property("tipster_name")).add(Projections.property("t_sport.name"))
				.add(Projections.property("tipster_price")).add(Projections.property("stats.profit"))
				.add(Projections.property("stats.tips")).add(Projections.property("stats.avodds"))
				.add(Projections.property("stats.yield")).add(Projections.property("stats.winpercentage")));
		criteria.addOrder(Order.desc("stats.winpercentage"));
		List<Object[]> list = criteria.list();
		list.add(new Object[] { count });
		session.close();
		return new Gson().toJson(list);

	}

	public String ByCategoryAndOrderByTips(String category, String[] sports, Integer page) {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Tipster.class);
		if (!(category.equalsIgnoreCase("all"))) {
			criteria.add(Restrictions.eq("tipster_category", category));
		}
		criteria.createAlias("tipster_sport", "t_sport");
		criteria.createAlias("tipster_stats", "stats");
		criteria.add(Restrictions.gt("stats.tips", 0));
		if (!(sports[0].equalsIgnoreCase("all"))) {
			criteria.add(Restrictions.in("t_sport.name", (Object[]) sports));
		}

		int count = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
		criteria.setFirstResult((page - 1) * 10);
		criteria.setMaxResults(10);
		criteria.setProjection(Projections.projectionList().add(Projections.property("tipster_id"))
				.add(Projections.property("tipster_name")).add(Projections.property("t_sport.name"))
				.add(Projections.property("tipster_price")).add(Projections.property("stats.profit"))
				.add(Projections.property("stats.tips")).add(Projections.property("stats.avodds"))
				.add(Projections.property("stats.yield")).add(Projections.property("stats.winpercentage")));
		criteria.addOrder(Order.desc("stats.tips"));
		List<Object[]> list = criteria.list();
		list.add(new Object[] { count });
		session.close();
		return new Gson().toJson(list);

	}

	public String ByCategoryAndOrderByYield(String category, String[] sports, Integer page) {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Tipster.class);
		if (!(category.equalsIgnoreCase("all"))) {
			criteria.add(Restrictions.eq("tipster_category", category));
		}
		criteria.createAlias("tipster_sport", "t_sport");
		criteria.createAlias("tipster_stats", "stats");
		criteria.add(Restrictions.gt("stats.tips", 0));
		if (!(sports[0].equalsIgnoreCase("all"))) {
			criteria.add(Restrictions.in("t_sport.name", (Object[]) sports));
		}
		int count = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
		criteria.setFirstResult((page - 1) * 10);
		criteria.setMaxResults(10);
		criteria.setProjection(Projections.projectionList().add(Projections.property("tipster_id"))
				.add(Projections.property("tipster_name")).add(Projections.property("t_sport.name"))
				.add(Projections.property("tipster_price")).add(Projections.property("stats.profit"))
				.add(Projections.property("stats.tips")).add(Projections.property("stats.avodds"))
				.add(Projections.property("stats.yield")).add(Projections.property("stats.winpercentage")));
		criteria.addOrder(Order.desc("stats.yield"));
		List<Object[]> list = criteria.list();
		list.add(new Object[] { count });
		session.close();
		return new Gson().toJson(list);

	}
}
