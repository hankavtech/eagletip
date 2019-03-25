package com.hankav.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hankav.model.Tip;
import com.hankav.results.FootballResults;

public class FootballResultUpdate {
	private String line;
	private String matchid;
	private int tscore1;
	private int tscore2;
	private int fhscore1;
	private int fhscore2;
	private int exscore1;
	private int exscore2;
	private boolean extratime;
	private String mwinner = "";
	private SessionFactory factory;
	private Session session;
	private Transaction tx;

	public void updateResults() throws IOException {
		factory = HibSessionFactory.getFactory();
		session = factory.openSession();
		tx = session.beginTransaction();
		FileReader reader = null;
		try {
			reader = new FileReader("/var/lib/etresources/results/footballresults.txt");
		} catch (IOException IOException) {
			System.out.println("no file found");
			return;
		}
		BufferedReader br = new BufferedReader(reader);
		List<String> list;
		while ((line = br.readLine()) != null) {
			list = new ArrayList<>();
			String[] match = line.split("\\|\\|");
			for (String prop : match) {
				String prop2 = prop.replaceAll("\\s", "");
				list.add(prop2);
			}
			matchid = list.get(0).trim();
			String status = list.get(1).trim();
			extratime = false;
			if (!(status.equals("Canc") || status.equals("Postp") || status.equals("Abn"))) {

				String fhscore = null;
				fhscore1 = 0;
				fhscore2 = 0;

				try {
					fhscore = list.get(2).trim().substring(1, list.get(2).length() - 1).trim();
					fhscore1 = Integer.parseInt(fhscore.split("-")[0].trim().replaceAll("\\u00A0", ""));
					fhscore2 = Integer.parseInt(fhscore.split("-")[1].trim().replaceAll("\\u00A0", ""));
				} catch (Exception e) {

				}

				String[] tscore;
				String[] exscore = null;
				if (status.equalsIgnoreCase("Pen") || status.equalsIgnoreCase("AET")) {
					tscore = list.get(3).substring(1, list.get(3).length() - 1).trim().split("-");
					exscore = list.get(4).trim().split("-");
					extratime = true;
				} else {
					tscore = list.get(3).trim().split("-");
				}
				try {
					tscore1 = Integer.parseInt(tscore[0].trim().replaceAll("\\P{Print}", ""));
					tscore2 = Integer.parseInt(tscore[1].trim().replaceAll("\\P{Print}", ""));
					if (extratime) {
						exscore1 = Integer.parseInt(exscore[0].trim().replaceAll("\\P{Print}", ""));
						exscore2 = Integer.parseInt(exscore[1].trim().replaceAll("\\P{Print}", ""));
					}
				} catch (Exception e) {

				}

			} else {
				mwinner = "void";
			}
			updateTip();
			mwinner = "";
			tscore1 = 0;
			tscore2 = 0;
			fhscore1 = 0;
			fhscore2 = 0;
			exscore1 = 0;
			exscore2 = 0;
			matchid = "";
			extratime = false;

		}
		br.close();
		tx.commit();
		session.close();

	}

	public void updateTip() {
		String result;
		Query query = session.createQuery("FROM Tip WHERE tip_matchid=:matchid AND status=:status");
		query.setParameter("matchid", matchid);
		query.setParameter("status", "waiting");
		Tip tip = (Tip) query.uniqueResult();
		if (tip != null) {
			String market = tip.getTip_market();
			String lines = tip.getTip_lines();
			String sublines = tip.getTip_sublines();
			String team1 = tip.getTeam1();
			String team2 = tip.getTeam2();
			if (mwinner.equalsIgnoreCase("void")) {
				result = "void";
			} else {
				FootballResults results = new FootballResults();
				if (lines.equalsIgnoreCase(team1)) {
					lines = "team1";
				} else if (lines.equalsIgnoreCase(team2)) {
					lines = "team2";
				}
				result = results.getResults(market, lines, sublines, mwinner, fhscore1, fhscore2, tscore1, tscore2,
						exscore1, exscore2, extratime);
			}

			tip.setTip_result(result);
			tip.setTipscore(tscore1 + "-" + tscore2);
			Double odds = tip.getTip_odds();
			Integer units = tip.getTip_units();
			Integer tipsterid = tip.getTipster().getTipster_id();
			String s = tip.getTip_sublines();
			String fra = s.substring(s.indexOf(".") + 1);
			Double tipprofit = 0.0;
			if (result.equalsIgnoreCase("won")) {
				tipprofit = Double.parseDouble(new DecimalFormat("##.##").format((odds * units) - units));
				tip.setTip_profit(tipprofit);
			} else if (result.equalsIgnoreCase("lost")) {
				tipprofit = Double.parseDouble(new DecimalFormat("##.##").format(units * -1));
				tip.setTip_profit(tipprofit);

			} else if (result.equalsIgnoreCase("void")) {
				tipprofit = Double.parseDouble(new DecimalFormat("##.##").format(0));
				tip.setTip_profit(tipprofit);

			} else if (result.equalsIgnoreCase("halfwon")) {
				tipprofit = Double.parseDouble(new DecimalFormat("##.##").format(((odds * units) - units) / 2));
				tip.setTip_profit(tipprofit);

			} else if (result.equalsIgnoreCase("halflost")) {
				tipprofit = Double.parseDouble(new DecimalFormat("##.##").format((units * -1) / 2));
				tip.setTip_profit(tipprofit);

			}

			tip.setStatus("finished");

		}

	}

}
