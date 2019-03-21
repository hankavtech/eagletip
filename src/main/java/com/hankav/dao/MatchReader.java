package com.hankav.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hankav.model.MatchTip;

public class MatchReader {

	public void readmatches(String sportname) throws IOException, ParseException {

		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		FileReader reader = new FileReader(
				"C:/Users/prashanth/eclipse-workspace/eagletip/src/main/webapp/matchesscraper/" + sportname
						+ "matches.txt");
		BufferedReader br = new BufferedReader(reader);

		int i = 0;
		Long start = System.currentTimeMillis();
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			i = i + 1;
			String[] arr = line.split("\\|\\|");
			List<String> list = new ArrayList<>();
			for (String s : arr) {
				list.add(s);
			}
			String match_id = list.get(4);
			Criteria criteria = session.createCriteria(MatchTip.class);
			criteria.add(Restrictions.eq("match_id", match_id));
			criteria.setProjection(Projections.property("match_id"));
			String mid = (String) criteria.uniqueResult();

			if (mid == null) {
				MatchTip match = new MatchTip();
				/*
				 * criteria=session.createCriteria(Sport.class);
				 * criteria.add(Restrictions.eq("name",list.get(0))); Sport nsport=(Sport)
				 * criteria.uniqueResult();
				 */
				match.setMatch_sport(new GetSportByProperty().byName(list.get(0), session));
				/* match.setMatch_sport(nsport); */
				match.setLeague(list.get(1));
				match.setTournament(list.get(2));
				match.setMatch_date(sdf.parse(list.get(3)));
				match.setMatch_id(list.get(4));
				match.setTeam1(list.get(5));
				match.setTeam2(list.get(6));
				list.clear();
				session.save(match);

				if (i % 20 == 0) { // 20, same as the JDBC batch size
					// flush a batch of inserts and release memory:
					session.flush();
					session.clear();
				}

			}
		}
		br.close();
		Long end = System.currentTimeMillis();
		System.out.println(end - start);
		tx.commit();
		session.close();

	}

}
