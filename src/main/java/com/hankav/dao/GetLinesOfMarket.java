package com.hankav.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.hankav.model.Market;
import com.hankav.model.Sport;

public class GetLinesOfMarket {

	public Map<String, Boolean> getLines(String sport, String market) {

		List<String> myres = new ArrayList<>();
		Map<String, Boolean> map = new LinkedHashMap<>();
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Sport WHERE name=?1");
		query.setParameter(1, sport);
		List<Sport> tipsters = (List<Sport>) query.list();
		session.close();
		for (Sport t : tipsters) {
			Set<Market> markets = t.getMarkets();
			for (Market m : markets) {
				if (m.getName().equals(market)) {
					myres.addAll(m.getMylines());
					String mylist = new Gson().toJson(myres);
					boolean sub = m.isSublines();
					map.put(mylist, sub);
					break;
				}
			}
		}
		return map;

	}

}
