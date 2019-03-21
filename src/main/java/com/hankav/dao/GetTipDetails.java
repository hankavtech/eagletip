package com.hankav.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hankav.model.Tip;

public class GetTipDetails {

	public List<Object[]> byId(String id) {

		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Tip.class);
		criteria.createAlias("tipster", "tipster");
		criteria.createAlias("tip_sport", "sport");
		criteria.add(Restrictions.eq("tip_id", Long.parseLong(id)));
		criteria.setProjection(Projections.projectionList().add(Projections.property("tip_id"))
				.add(Projections.property("tipster.tipster_name")).add(Projections.property("sport.name"))
				.add(Projections.property("tip_category")).add(Projections.property("status"))
				.add(Projections.property("team1")).add(Projections.property("team2"))
				.add(Projections.property("tip_match_time")).add(Projections.property("tip_bookmaker"))
				.add(Projections.property("tip_market")).add(Projections.property("tip_lines"))
				.add(Projections.property("tip_sublines")).add(Projections.property("tip_odds"))
				.add(Projections.property("tip_units")).add(Projections.property("tip_profit"))
				.add(Projections.property("tip_result")).add(Projections.property("tip_league"))
				.add(Projections.property("tip_tournament")).add(Projections.property("tipster.tipster_price"))
				.add(Projections.property("tipscore")));
		List<Object[]> unfiltered = criteria.list();
		Object[] obj = new Object[20];
		String tip_id = unfiltered.get(0)[0].toString();
		String tipster_name = (String) unfiltered.get(0)[1];
		String sport_name = (String) unfiltered.get(0)[2];
		String tip_category = (String) unfiltered.get(0)[3];
		String tip_status = (String) unfiltered.get(0)[4];
		String team1 = (String) unfiltered.get(0)[5];
		String team2 = (String) unfiltered.get(0)[6];
		String match_time = unfiltered.get(0)[7].toString();
		String bookmaker = (String) unfiltered.get(0)[8];
		String market = (String) unfiltered.get(0)[9];
		String lines = (String) unfiltered.get(0)[10];
		String sublines = (String) unfiltered.get(0)[11];
		String odds = (String) unfiltered.get(0)[12].toString();
		String units = (String) unfiltered.get(0)[13].toString();
		String result = (String) unfiltered.get(0)[15];
		String profit = (String) unfiltered.get(0)[14].toString();
		String league = (String) unfiltered.get(0)[16];
		String tournament = (String) unfiltered.get(0)[17];
		String tipster_price = unfiltered.get(0)[18].toString();
		String tipscore = null;
		try {
			tipscore = unfiltered.get(0)[19].toString();
		} catch (NullPointerException e) {
			tipscore = "";
		}
		if (tip_category.equals("paid")) {
			if (tip_status.equals("waiting")) {
				obj[0] = tip_category;
				obj[1] = tip_status;
				obj[2] = tip_id;
				obj[3] = tipster_name;
				obj[4] = tipster_price;
				obj[5] = team1;
				obj[6] = team2;
				obj[7] = match_time;
				obj[8] = sport_name;
			} else if (tip_status.equals("finished")) {
				obj[0] = tip_category;
				obj[1] = tip_status;
				obj[2] = tip_id;
				obj[3] = tipster_name;
				obj[4] = tipster_price;
				obj[5] = team1;
				obj[6] = team2;
				obj[7] = match_time;
				obj[8] = sport_name;
				obj[9] = bookmaker;
				obj[10] = market;
				obj[11] = lines;
				obj[12] = sublines;
				obj[13] = odds;
				obj[14] = units;
				obj[15] = result;
				obj[16] = profit;
				obj[17] = league;
				obj[18] = tournament;
				obj[19] = tipscore;
			}
		} else if (tip_category.equals("free")) {
			obj[0] = tip_category;
			obj[1] = tip_status;
			obj[2] = tip_id;
			obj[3] = tipster_name;
			obj[4] = tipster_price;
			obj[5] = team1;
			obj[6] = team2;
			obj[7] = match_time;
			obj[8] = sport_name;
			obj[9] = bookmaker;
			obj[10] = market;
			obj[11] = lines;
			obj[12] = sublines;
			obj[13] = odds;
			obj[14] = units;
			obj[15] = result;
			obj[16] = profit;
			obj[17] = league;
			obj[18] = tournament;
			obj[19] = tipscore;
		}
		List<Object[]> rlist = new ArrayList<>();
		rlist.add(obj);
		session.close();
		return rlist;

	}

}
