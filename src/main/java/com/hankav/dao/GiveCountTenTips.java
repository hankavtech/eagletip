package com.hankav.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.hankav.model.Tip;

public class GiveCountTenTips {
	private List<Map<Object, Object>> fmap = new ArrayList<>();

	public List<Map<Object, Object>> getLast10ActiveTips(int page, String category) throws ParseException {

		SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		fm.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String txt = fm.format(new Date());
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Tip.class);
		criteria.add(Restrictions.eq("tip_category", category));
		criteria.add(Restrictions.eq("status", "waiting"));
		criteria.add(Restrictions.gt("tip_match_time", sdf.parse(txt)));
		criteria.createAlias("tip_sport", "sport");
		criteria.createAlias("tipster", "t");
		/*
		 * criteria.setFirstResult((page-1)*10); criteria.setMaxResults(10);
		 */
		criteria.setProjection(Projections.projectionList().add(Projections.property("tip_id").as("tid"))
				.add(Projections.property("team1").as("team1")).add(Projections.property("team2").as("team2"))
				.add(Projections.property("tip_bookmaker").as("bookmaker"))
				.add(Projections.property("tip_match_time").as("time"))
				.add(Projections.property("sport.name").as("sportname"))
				.add(Projections.property("t.tipster_name").as("tipstername")));
		criteria.addOrder(Order.desc("tip_match_time"));
		criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List list = criteria.list();
		for (Object object : list) {
			Map<Object, Object> map = (Map<Object, Object>) object;
			fmap.add(map);
		}
		return fmap;
	}

	public List<Map<Object, Object>> getLast10FinishedTips(int page, String category) throws ParseException {

		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Tip.class);
		criteria.add(Restrictions.eq("tip_category", category));
		criteria.add(Restrictions.eq("status", "finished"));
		criteria.createAlias("tip_sport", "sport");
		criteria.createAlias("tipster", "t");
		criteria.setFirstResult((page - 1) * 10);
		criteria.setMaxResults(10);
		criteria.setProjection(Projections.projectionList().add(Projections.property("tip_id").as("tid"))
				.add(Projections.property("team1").as("team1")).add(Projections.property("team2").as("team2"))
				.add(Projections.property("tip_bookmaker").as("bookmaker"))
				.add(Projections.property("tip_match_time").as("time"))
				.add(Projections.property("sport.name").as("sportname"))
				.add(Projections.property("t.tipster_name").as("tipstername"))
				.add(Projections.property("tip_result").as("result")));
		criteria.addOrder(Order.desc("tip_match_time"));
		criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List list = criteria.list();
		for (Object object : list) {
			Map<Object, Object> map = (Map<Object, Object>) object;
			fmap.add(map);
		}
		return fmap;
	}

}
