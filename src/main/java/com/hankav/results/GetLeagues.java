package com.hankav.results;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.hankav.dao.GetSportByProperty;
import com.hankav.dao.HibSessionFactory;
import com.hankav.model.MatchTip;

public class GetLeagues {

	public String bySport(String sport) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date datenow = sdf1.parse(sdf.format(new Date()));

		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Map<String, List<String>> map = new LinkedHashMap<>();
		Criteria criteria = session.createCriteria(MatchTip.class);
		criteria.add(Restrictions.eq("match_sport", new GetSportByProperty().byName(sport, session)));
		criteria.add(Restrictions.gt("match_date", new Date(datenow.getTime() + 2 * 60 * 60 * 1000)));
		criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("league"))));
		criteria.addOrder(Order.asc("league"));
		List<String> leagues = criteria.list();
		for (String s : leagues) {
			Criteria criteria1 = session.createCriteria(MatchTip.class);
			criteria1.add(Restrictions.gt("match_date", new Date(datenow.getTime() + 2 * 60 * 60 * 1000)));
			criteria1.add(Restrictions.eq("league", s));
			criteria1.add(Restrictions.eq("match_sport", new GetSportByProperty().byName(sport, session)));
			criteria1.setProjection(
					Projections.distinct(Projections.projectionList().add(Projections.property("tournament"))));
			criteria1.addOrder(Order.asc("tournament"));
			map.put(s, criteria1.list());
		}
		String gson1 = new Gson().toJson(map);
		session.close();
		return gson1;

	}

}
