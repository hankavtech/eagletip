package com.hankav.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hankav.model.Subscription;
import com.hankav.model.Tip;

public class GetTenTipsOfClient {
	SessionFactory factory = HibSessionFactory.getFactory();
	Criteria criteria;
	Session session;

	public List<Object[]> getAllSubscriptions(String username) {

		session = factory.openSession();
		criteria = session.createCriteria(Subscription.class);
		criteria.createAlias("subscriber", "user");
		criteria.createAlias("subscribed_tipster", "tipster");
		criteria.add(Restrictions.eq("user.username", username));
		criteria.addOrder(Order.desc("start_date"));
		criteria.setProjection(Projections.projectionList().add(Projections.property("subscription_id"))
				.add(Projections.property("tipster.tipster_id")).add(Projections.property("tipster.tipster_name"))
				.add(Projections.property("start_date")).add(Projections.property("end_date"))
				.add(Projections.property("subscription_status")));
		List<Object[]> subs = criteria.list();
		session.close();
		return subs;

	}

	public List<Object[]> getStats(String sub_id, String tipster_id, String start_date, String end_date)
			throws ParseException {
		int sub_id1 = Integer.parseInt(sub_id);
		int tipster_id1 = Integer.parseInt(tipster_id);
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date start_date1 = fm.parse(start_date);
		Date end_date1 = fm.parse(end_date);
		session = factory.openSession();
		criteria = session.createCriteria(Tip.class);
		criteria.createAlias("tipster", "tipster");
		criteria.createAlias("tip_sport", "sport");
		criteria.add(Restrictions.eq("tipster.tipster_id", tipster_id1));
		criteria.add(Restrictions.between("tip_match_time", start_date1, end_date1));
		criteria.add(Restrictions.eq("status", "finished"));
		criteria.addOrder(Order.desc("tip_match_time"));
		criteria.setProjection(Projections.projectionList().add(Projections.rowCount()).add(Projections.avg("tip_odds"))
				.add(Projections.sum("tip_profit")).add(Projections.property("tipster.tipster_name"))
				.add(Projections.property("sport.name")).add(Projections.property("tipster.tipster_price")));
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

	public List<Object[]> getTenTipsByPageAndSubscription(String sub_id, String tipster_id, String start_date,
			String end_date, String page) throws ParseException {
		int sub_id1 = Integer.parseInt(sub_id);
		int page1 = Integer.parseInt(page);
		int tipster_id1 = Integer.parseInt(tipster_id);
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date start_date1 = fm.parse(start_date);
		Date end_date1 = fm.parse(end_date);
		session = factory.openSession();
		criteria = session.createCriteria(Tip.class);
		criteria.createAlias("tipster", "tipster");
		criteria.createAlias("tip_sport", "sport");
		criteria.add(Restrictions.eq("tipster.tipster_id", tipster_id1));
		criteria.add(Restrictions.between("tip_match_time", start_date1, end_date1));
		criteria.add(Restrictions.eq("status", "finished"));
		criteria.setFirstResult((page1 - 1) * 10);
		criteria.setMaxResults(10);
		criteria.addOrder(Order.desc("tip_match_time"));
		criteria.setProjection(Projections.projectionList().add(Projections.property("team1"))
				.add(Projections.property("team2")).add(Projections.property("sport.name"))
				.add(Projections.property("tip_match_time")).add(Projections.property("tip_bookmaker"))
				.add(Projections.property("tip_market")).add(Projections.property("tip_lines"))
				.add(Projections.property("tip_sublines")).add(Projections.property("tip_odds"))
				.add(Projections.property("tip_units")).add(Projections.property("tip_result"))
				.add(Projections.property("tip_profit")).add(Projections.property("tip_id")));
		List<Object[]> tips = criteria.list();
		session.close();
		if (tips.size() > 0) {
			return tips;
		} else {
			return null;
		}
	}

}
