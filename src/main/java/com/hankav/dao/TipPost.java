package com.hankav.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hankav.model.Tip;

public class TipPost {

	public String insertion(String league, String tournament, String datetime, String match_id, String team1,
			String team2, String market, String lines, String sublines, String bookmaker, String odds, String units,
			String username, String sport, String category, String timezone) throws ParseException {

		DateFormat formatterIST1 = new SimpleDateFormat("MMM dd, yyyy HH:mm");
		formatterIST1.setTimeZone(TimeZone.getTimeZone(timezone));
		Date date = formatterIST1.parse(datetime);

		Date datenowintimezonesent = formatterIST1.parse(formatterIST1.format(new Date()));

		DateFormat formatterIST = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		formatterIST.setTimeZone(TimeZone.getTimeZone(timezone)); // better than using IST

		DateFormat formatterUTC = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		formatterUTC.setTimeZone(TimeZone.getTimeZone("GMT+00:00")); // GMT timezone

		DateFormat formatterIST3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		System.out.println(datenowintimezonesent);
		System.out.println(date);

		if (date.getTime() - datenowintimezonesent.getTime() <= 2 * 60 * 60 * 1000) {
			return "failed";
		} else {
			SessionFactory factory = HibSessionFactory.getFactory();
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();

			// check if tip is already posted by tipster

			Criteria criteria = session.createCriteria(Tip.class);
			criteria.createAlias("tipster", "tipster");
			criteria.add(Restrictions.eq("tipster.tipster_name", username));
			criteria.add(Restrictions.eq("tip_matchid", match_id));
			criteria.setProjection(Projections.property("tip_id"));
			List<Long> alreadycount = criteria.list();
			if (alreadycount.size() > 0) {
				return "failed";
			}

			Tip tip1 = new Tip();

			tip1.setTip_matchid(match_id);
			tip1.setTeam1(team1);
			tip1.setTeam2(team2);
			tip1.setTip_date(formatterIST3.parse(formatterUTC.format(new Date())));
			tip1.setTip_market(market);
			tip1.setTip_lines(lines);
			tip1.setStatus("waiting");
			tip1.setTip_sublines(sublines);
			tip1.setTip_match_time(formatterIST3.parse(formatterUTC.format(date)));
			tip1.setTip_bookmaker(bookmaker);
			tip1.setTip_odds(Double.valueOf(odds));
			tip1.setTip_result("Unknown");
			tip1.setTip_tournament(tournament);
			tip1.setTip_league(league);
			tip1.setTip_units(Integer.valueOf(units));
			tip1.setTipster(new GetTipsterByProperty().byName(username));
			tip1.setTip_sport(new GetSportByProperty().byName(sport));
			tip1.setTip_category(category);
			session.save(tip1);
			tx.commit();
			if (session.isConnected()) {
				session.close();
			}
			return "success";

		}
	}

}
