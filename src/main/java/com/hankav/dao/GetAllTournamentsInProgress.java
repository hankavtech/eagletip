package com.hankav.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GetAllTournamentsInProgress {

	public static List<String> main(String args[]) {

		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Query query = session.createSQLQuery("SELECT DISTINCT tipt_league as l FROM TipsterTip ORDER BY l");
		List<String> leagues = query.list();
		for (String league : leagues) {
			System.out.println(league);
		}
		session.close();
		return leagues;
		/*
		 * forgquery=session.
		 * createSQLQuery("SELECT DISTINCT tipt_tournament as tour FROM TipsterTip WHERE tipt_league=:league"
		 * ); query.setParameter("league",league); List<String>tournaments=query.list();
		 * for(String tournament:tournaments) {
		 * System.out.println("            "+tournament);
		 * 
		 * 
		 * Criteria criteria=session.createCriteria(TipsterTip.class);
		 * criteria.add(Restrictions.eq("tipt_league",league));
		 * criteria.add(Restrictions.eq("tipt_tournament",tournament));
		 * criteria.setProjection(Projections.property("tipt_team1"));
		 * criteria.addOrder(org.hibernate.criterion.Order.asc("tipt_matchtime"));
		 * List<String> matches=criteria.list(); for(String s:matches) {
		 * System.out.println("                     "+s );
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * }
		 * 
		 * System.out.println("--------------------------------------");
		 * 
		 * 
		 * }
		 */

	}

}
