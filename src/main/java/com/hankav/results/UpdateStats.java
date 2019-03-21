package com.hankav.results;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Stats;

public class UpdateStats {

	public void execute() {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = null;
		Query query = session.createQuery(
				"SELECT t.tipster.tipster_name,count(*),sum(t.tip_profit),avg(t.tip_odds),sum(case when t.tip_result='won' then 1 else 0 end),sum(case when t.tip_result = 'lost' then 1 else 0 end) from Tip t WHERE t.tip_result != ?1 AND t.tip_result != ?2 group by t.tipster.tipster_name");
		query.setParameter(1, "void");
		query.setParameter(2, "Unknown");
		List<Object[]> stats = query.list();
		Stats singlestat = null;
		session.beginTransaction();
		for (Object[] stat : stats) {
			criteria = session.createCriteria(Stats.class);
			criteria.createAlias("stats_tipster", "tipster");
			criteria.add(Restrictions.eq("tipster.tipster_name", stat[0].toString()));
			singlestat = (Stats) criteria.uniqueResult();
			try {
				System.out.println(singlestat.getAvodds().toString());
				singlestat.setAvodds(Double.parseDouble(stat[3].toString()));
				singlestat.setProfit(Double.parseDouble(stat[2].toString()));
				singlestat.setTips(Integer.parseInt(stat[1].toString()));
				singlestat.setWinpercentage(
						((Integer.parseInt(stat[4].toString())) * 100) / Double.parseDouble(stat[1].toString()));
				singlestat.setYield(Double.parseDouble(stat[2].toString()) / (Double.parseDouble(stat[1].toString())));
				session.flush();
				session.clear();

			} catch (Exception e) {

			}

		}
		session.getTransaction().commit();
		session.close();
	}

}
