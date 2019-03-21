package com.hankav.results;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hankav.dao.HibSessionFactory;
import com.hankav.model.MatchTip;

public class GetMatches {

	public String byTournament(String tournament, String league) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date datenow = sdf1.parse(sdf.format(new Date()));

		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		/* Query query=session.createQuery("FROM Matchtip"); */
		Criteria criteria = session.createCriteria(MatchTip.class);
		criteria.add(Restrictions.and(Restrictions.eq("tournament", tournament), Restrictions.eq("league", league)));
		criteria.add(Restrictions.gt("match_date", new Date(datenow.getTime() + 2 * 60 * 60 * 1000)));
		criteria.setProjection(
				Projections.projectionList().add(Projections.property("team1")).add(Projections.property("team2"))
						.add(Projections.property("match_date")).add(Projections.property("match_id")));
		List<Object[]> matches = criteria.list();
		List<String[]> matches1 = new ArrayList<String[]>();

		for (Object[] match : matches) {
			String[] dest = new String[4];
			dest[0] = match[0].toString();
			dest[1] = match[1].toString();
			dest[2] = match[2].toString();
			dest[3] = match[3].toString();
			matches1.add(dest);
		}

		Gson builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String gson = builder.toJson(matches1);
		session.close();
		return gson;

	}

}
