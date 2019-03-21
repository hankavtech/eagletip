package com.hankav.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hankav.model.Tip;

public class GetStatsForFirstPage {

	public static List<List<Object[]>> giveStats(String tipster_name) throws ParseException {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		fm.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String txt = fm.format(new Date());
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
		List<Object[]> toptips = criteria.list();
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
		toptips.add(toadd);

		criteria = session.createCriteria(Tip.class);
		criteria.createAlias("tipster", "tipster");
		criteria.add(Restrictions.eq("tipster.tipster_name", tipster_name));
		criteria.add(Restrictions.eq("status", "waiting"));
		criteria.createAlias("tip_sport", "sport");
		criteria.add(Restrictions.gt("tip_match_time", sdf.parse(txt)));
		criteria.setProjection(
				Projections.projectionList().add(Projections.property("team1")).add(Projections.property("team2"))
						.add(Projections.property("tip_match_time")).add(Projections.property("tip_league"))
						.add(Projections.property("tip_tournament")).add(Projections.property("sport.name")));
		List<Object[]> tips = criteria.list();
		criteria = session.createCriteria(Tip.class);
		criteria.createAlias("tipster", "tipster");
		criteria.createAlias("tip_sport", "sport");
		criteria.add(Restrictions.eq("tipster.tipster_name", tipster_name));
		criteria.add(Restrictions.eq("status", "finished"));
		criteria.addOrder(Order.desc("tip_match_time"));
		criteria.setFirstResult(0);
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
		List<Object[]> tips1 = criteria.list();
		session.close();
		return Arrays.asList(tips, toptips, tips1);

	}

}
