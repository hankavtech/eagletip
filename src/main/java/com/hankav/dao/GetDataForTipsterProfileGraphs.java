package com.hankav.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;

public class GetDataForTipsterProfileGraphs {

	public List<String> ByTipsterName(String name) {

		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		List<String> list;
		Query query = session
				.createQuery("SELECT DISTINCT tip_market FROM Tip WHERE tipster.tipster_name=?1 and status=?2");
		query.setParameter(1, name);
		query.setParameter(2, "finished");
		Map<String, String> bookmakermap = new LinkedHashMap<>();
		Map<String, String> marketmap = new LinkedHashMap<>();
		Map<String, String> oddsmap = new LinkedHashMap<>();
		Map<String, List<String>> bookmakertablemap = new LinkedHashMap<>();
		Map<String, List<String>> markettablemap = new LinkedHashMap<>();
		Map<String, List<String>> oddstablemap = new LinkedHashMap<>();
		list = query.list();
		for (String obj : list) {
			query = session.createQuery(
					"SELECT COUNT(tip_market),SUM(tip_profit),AVG(tip_odds) FROM Tip WHERE tip_market=?1 AND tipster.tipster_name=?2 AND status=?3");
			query.setParameter(1, obj);
			query.setParameter(2, name);
			query.setParameter(3, "finished");
			List<Object[]> list1 = query.list();
			String totaltips = list1.get(0)[0].toString();
			String totalprofit = list1.get(0)[1].toString();
			String avgodds = list1.get(0)[2].toString();

			/* get number of won tips */

			query = session.createQuery(
					"SELECT COUNT(*) FROM Tip WHERE tip_market=?1 AND tipster.tipster_name=?2 AND status=?3 and tip_result=?4");
			query.setParameter(1, obj);
			query.setParameter(2, name);
			query.setParameter(3, "finished");
			query.setParameter(4, "won");
			String wontips = query.uniqueResult().toString();
			Double winpercentage = (double) ((Double.valueOf(wontips) / Double.valueOf(totaltips)) * 100);
			String winper = winpercentage.toString();
			Double yield = (double) ((Double.valueOf(totalprofit) / Double.valueOf(totaltips)));
			String styield = yield.toString();

			markettablemap.put(obj, Arrays.asList(totaltips, totalprofit, winper, styield, avgodds));
			marketmap.put(obj, totaltips);

		}
		String first = new Gson().toJson(marketmap);
		String firsttable = new Gson().toJson(markettablemap);
		System.out.println(firsttable);

		query = session
				.createQuery("SELECT DISTINCT tip_bookmaker FROM Tip WHERE tipster.tipster_name=?1 and status=?2");
		query.setParameter(1, name);
		query.setParameter(2, "finished");
		list = query.list();
		for (String obj : list) {
			query = session.createQuery(
					"SELECT COUNT(tip_bookmaker),SUM(tip_profit),AVG(tip_odds) FROM Tip WHERE tip_bookmaker=?1 AND tipster.tipster_name=?2 and status=?3");
			query.setParameter(1, obj);
			query.setParameter(2, name);
			query.setParameter(3, "finished");

			List<Object[]> list1 = query.list();
			String totaltips = list1.get(0)[0].toString();
			String totalprofit = list1.get(0)[1].toString();
			String avgodds = list1.get(0)[2].toString();
			System.out.println("average odds for bookmaker" + avgodds);

			/* get number of won tips */

			query = session.createQuery(
					"SELECT COUNT(*) FROM Tip WHERE tip_bookmaker=?1 AND tipster.tipster_name=?2 AND status=?3 and tip_result=?4");
			query.setParameter(1, obj);
			query.setParameter(2, name);
			query.setParameter(3, "finished");
			query.setParameter(4, "won");
			String wontips = query.uniqueResult().toString();
			Double winpercentage = (double) ((Double.valueOf(wontips) / Double.valueOf(totaltips)) * 100);
			String winper = winpercentage.toString();
			Double yield = (double) ((Double.valueOf(totalprofit) / Double.valueOf(totaltips)));
			String styield = yield.toString();

			bookmakertablemap.put(obj, Arrays.asList(totaltips, totalprofit, winper, styield, avgodds));

			bookmakermap.put(obj, totaltips);
		}
		String second = new Gson().toJson(bookmakermap);
		String secondtable = new Gson().toJson(bookmakertablemap);

		query = session.createQuery(
				"SELECT COUNT(*),SUM(tip_profit),AVG(tip_odds) FROM Tip WHERE tip_odds < 1.5 AND tipster.tipster_name=?1 and status=?2");
		query.setParameter(1, name);
		query.setParameter(2, "finished");
		List<Object[]> list1 = query.list();
		String totaltips = list1.get(0)[0].toString();
		String totalprofit = "0";
		String wontips = "0";
		Double yield = 0.00;
		String styield = "0";
		Double winpercentage = 0.00;
		String winper = "0";
		String third = "";
		String thirdtable = "";
		String avgodds = "";
		if (!(totaltips.equals("0"))) {
			totalprofit = list1.get(0)[1].toString();
			avgodds = list1.get(0)[2].toString();
			query = session.createQuery(
					"SELECT COUNT(*) FROM Tip WHERE tip_odds < 1.5 AND tipster.tipster_name=?1 and status=?2 and tip_result=?3");
			query.setParameter(1, name);
			query.setParameter(2, "finished");
			query.setParameter(3, "won");
			wontips = query.uniqueResult().toString();
			winpercentage = (double) ((Double.valueOf(wontips) / Double.valueOf(totaltips)) * 100);
			winper = winpercentage.toString();
			yield = (double) ((Double.valueOf(totalprofit) / Double.valueOf(totaltips)));
			styield = yield.toString();
			oddsmap.put("lesser than 1.5 ", totaltips);
			oddstablemap.put("less than 1.5", Arrays.asList(totaltips, totalprofit, winper, styield, avgodds));
		}
		query = session.createQuery(
				"SELECT COUNT(*),SUM(tip_profit),AVG(tip_odds) FROM Tip WHERE tip_odds BETWEEN 1.5 AND 2.0 and tipster.tipster_name=?1 and status=?2");
		query.setParameter(1, name);
		query.setParameter(2, "finished");
		list1 = query.list();
		totaltips = list1.get(0)[0].toString();
		if (!(totaltips.equals("0"))) {
			totalprofit = list1.get(0)[1].toString();
			avgodds = list1.get(0)[2].toString();
			query = session.createQuery(
					"SELECT COUNT(*) FROM Tip WHERE tip_odds BETWEEN 1.5 AND 2.0 AND tipster.tipster_name=?1 and status=?2 and tip_result=?3");
			query.setParameter(1, name);
			query.setParameter(2, "finished");
			query.setParameter(3, "won");
			wontips = query.uniqueResult().toString();
			winpercentage = (double) ((Double.valueOf(wontips) / Double.valueOf(totaltips)) * 100);
			winper = winpercentage.toString();
			yield = (double) ((Double.valueOf(totalprofit) / Double.valueOf(totaltips)));
			styield = yield.toString();
			oddsmap.put("1.5-2.0", totaltips);
			oddstablemap.put("1.5-2.0", Arrays.asList(totaltips, totalprofit, winper, styield, avgodds));
		}
		query = session.createQuery(
				"SELECT COUNT(*),SUM(tip_profit),AVG(tip_odds) FROM Tip WHERE tip_odds BETWEEN 2.0 AND 2.5 and tipster.tipster_name=?1 and status=?2");
		query.setParameter(1, name);
		query.setParameter(2, "finished");
		query.setParameter(1, name);
		query.setParameter(2, "finished");
		list1 = query.list();
		totaltips = list1.get(0)[0].toString();
		if (!(totaltips.equals("0"))) {
			totalprofit = list1.get(0)[1].toString();
			avgodds = list1.get(0)[2].toString();
			query = session.createQuery(
					"SELECT COUNT(*) FROM Tip WHERE tip_odds BETWEEN 2.0 AND 2.5 AND tipster.tipster_name=?1 and status=?2 and tip_result=?3");
			query.setParameter(1, name);
			query.setParameter(2, "finished");
			query.setParameter(3, "won");
			wontips = query.uniqueResult().toString();
			winpercentage = (double) ((Double.valueOf(wontips) / Double.valueOf(totaltips)) * 100);
			winper = winpercentage.toString();
			yield = (double) ((Double.valueOf(totalprofit) / Double.valueOf(totaltips)));
			styield = yield.toString();
			oddsmap.put("2.0-2.5", totaltips);
			oddstablemap.put("2.0-2.5", Arrays.asList(totaltips, totalprofit, winper, styield, avgodds));
		}
		query = session.createQuery(
				"SELECT COUNT(*),SUM(tip_profit),AVG(tip_odds) FROM Tip WHERE tip_odds BETWEEN 2.5 AND 3.0 and tipster.tipster_name=?1 and status=?2");
		query.setParameter(1, name);
		query.setParameter(2, "finished");
		query.setParameter(1, name);
		query.setParameter(2, "finished");
		list1 = query.list();
		totaltips = list1.get(0)[0].toString();
		if (!(totaltips.equals("0"))) {
			totalprofit = list1.get(0)[1].toString();
			avgodds = list1.get(0)[2].toString();
			query = session.createQuery(
					"SELECT COUNT(*) FROM Tip WHERE tip_odds BETWEEN 2.5 AND 3.0 AND tipster.tipster_name=?1 and status=?2 and tip_result=?3");
			query.setParameter(1, name);
			query.setParameter(2, "finished");
			query.setParameter(3, "won");
			wontips = query.uniqueResult().toString();
			winpercentage = (double) ((Double.valueOf(wontips) / Double.valueOf(totaltips)) * 100);
			winper = winpercentage.toString();
			yield = (double) ((Double.valueOf(totalprofit) / Double.valueOf(totaltips)));
			styield = yield.toString();
			oddsmap.put("2.5-3.0", totaltips);
			oddstablemap.put("2.5-3.0", Arrays.asList(totaltips, totalprofit, winper, styield, avgodds));
		}
		query = session.createQuery(
				"SELECT COUNT(*),SUM(tip_profit),AVG(tip_odds) FROM Tip WHERE tip_odds > 3.0 AND tipster.tipster_name=?1 and status=?2");
		query.setParameter(1, name);
		query.setParameter(2, "finished");
		list1 = query.list();
		totaltips = list1.get(0)[0].toString();
		if (!(totaltips.equals("0"))) {
			totalprofit = list1.get(0)[1].toString();
			avgodds = list1.get(0)[2].toString();
			query = session.createQuery(
					"SELECT COUNT(*) FROM Tip WHERE tip_odds > 3.0 AND tipster.tipster_name=?1 and status=?2 and tip_result=?3");
			query.setParameter(1, name);
			query.setParameter(2, "finished");
			query.setParameter(3, "won");
			wontips = query.uniqueResult().toString();
			winpercentage = (double) ((Double.valueOf(wontips) / Double.valueOf(totaltips)) * 100);
			winper = winpercentage.toString();
			yield = (double) ((Double.valueOf(totalprofit) / Double.valueOf(totaltips)));
			styield = yield.toString();
			oddsmap.put("greater than 3.0", totaltips);
			oddstablemap.put("greater than 3.0", Arrays.asList(totaltips, totalprofit, winper, styield, avgodds));
		}
		third = new Gson().toJson(oddsmap);
		thirdtable = new Gson().toJson(oddstablemap);

		session.close();
		List<String> flist = new ArrayList<>();
		flist.add(first);
		flist.add(second);
		flist.add(third);
		flist.add(firsttable);
		flist.add(secondtable);
		flist.add(thirdtable);

		return flist;

	}

}
