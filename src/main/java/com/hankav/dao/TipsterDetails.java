package com.hankav.dao;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TipsterDetails {
	Map<String, Double> map = new LinkedHashMap<>();

	public Map<String, Double> getMonthlyProfit(String username) {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Query query = session.createSQLQuery(
				"select distinct g.t,g.h FROM(select year(match_time) as t,monthname(match_time) as h FROM Tip)AS g");

		List<Object[]> months = query.list();

		Collections.sort(months, new Comparator<Object[]>() {

			@Override
			public int compare(Object[] o1, Object[] o2) {
				int res = 0;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMMM");
				try {
					Date m1 = sdf.parse(o1[0] + o1[1].toString());
					Date m2 = sdf.parse(o2[0] + o2[1].toString());
					res = m2.compareTo(m1);
					return res;
				} catch (Exception e) {

				}
				return 0;
			}

		});

		for (Object[] obj : months) {
			Double profit = 0.0;
			query = session.createQuery(
					"select profit FROM Tip WHERE tipster.username=:username AND year(match_time)=:year AND monthname(match_time)=:month");
			query.setParameter("username", username);
			query.setParameter("year", (Integer) obj[0]);
			query.setParameter("month", obj[1].toString());
			List<Double> tips = query.list();
			for (Double tipprofit : tips) {
				profit = tipprofit + tipprofit;
			}

			map.put(obj[0] + " " + obj[1], profit);
			profit = 0.0;
		}
		return map;

	}

}
