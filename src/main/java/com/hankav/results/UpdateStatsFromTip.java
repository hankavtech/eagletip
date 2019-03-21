package com.hankav.results;

import java.text.DecimalFormat;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hankav.model.Stats;

public class UpdateStatsFromTip {

	// TODO Auto-generated method stub

	public void update(Double profit, Double odds, int tipsterid, Session session) {
		Query query = session.createQuery("SELECT tipster_stats FROM Tipster WHERE tipster_id=:tipsterid1");
		query.setParameter("tipsterid1", tipsterid);
		Stats stats = (Stats) query.uniqueResult();
		int tips = stats.getTips();
		Double avodds = stats.getAvodds();
		Double profit1 = stats.getProfit();
		Double yield = stats.getYield();
		stats.setTips(tips + 1);
		stats.setAvodds(Double.parseDouble(new DecimalFormat("##.##").format(((avodds * tips) + odds) / (tips + 1))));
		stats.setProfit(Double.parseDouble(new DecimalFormat("##.##").format(profit + profit1)));
		stats.setYield(Double.parseDouble(new DecimalFormat("##.##").format(stats.getProfit() / stats.getTips())));
	}

}
