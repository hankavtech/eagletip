package com.hankav.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
import com.hankav.results.TennisResults;

public class TennisResultUpdate {

	private List<String> plist1 = new ArrayList<>();
	private List<String> plist2 = new ArrayList<>();
	private int i;
	private String mwinner;
	private int tscore1;
	private int tsets1;
	private int tsets2;
	private String matchid;
	private int fscore1;
	private int fscore2;
	private int tscore2;
	private String line;
	SessionFactory factory = HibSessionFactory.getFactory();
	Session session = factory.openSession();
	private Transaction tx = session.beginTransaction();

	public void updateResults() throws FileNotFoundException, IOException {

		FileReader reader = null;
		try {
			reader = new FileReader("/var/lib/etresources/results/tennisresults.txt");
		} catch (FileNotFoundException e) {
			return;
		}
		BufferedReader br = new BufferedReader(reader);

		while ((line = br.readLine()) != null) {
			i = i + 1;
			if (i % 2 == 1) {
				String[] arr = line.split("\\|\\|");
				for (String s1 : arr) {
					String str2 = s1.replaceAll("\\s", "");
					if (str2.matches(".*\\w.*")) {
						plist1.add(str2);
					}

				}
				if (plist1.get(1).equals("Fin")) {
					tsets1 = Integer.parseInt(plist1.get(3));
					if (plist1.contains("bold")) {
						mwinner = "team1";
						for (int score = 4; score < plist1.size(); score++) {
							tscore1 += Integer.parseInt(plist1.get(score));
						}
						fscore1 = Integer.parseInt(plist1.get(4));

					} else {
						for (int score = 4; score < plist1.size(); score++) {
							tscore1 += Integer.parseInt(plist1.get(score));
						}
						fscore1 = Integer.parseInt(plist1.get(4));
						mwinner = "team2";
					}
				} else {
					mwinner = "void";
					i++;
					printcontents();
					br.readLine();

				}

			}

			else {

				String[] arr1 = line.split("\\|\\|");
				for (String s2 : arr1) {
					String str3 = s2.replaceAll("\\s", "");
					if (str3.matches(".*\\w.*")) {
						plist2.add(str3);
					}

				}
				tsets2 = Integer.parseInt(plist2.get(2));

				for (int score = 3; score < plist2.size(); score++) {
					tscore2 += Integer.parseInt(plist2.get(score));
				}
				fscore2 = Integer.parseInt(plist2.get(3));
				printcontents();

			}

		}
		br.close();
		tx.commit();

		session.close();

	}

	public void printcontents() {
		matchid = plist1.get(0);

		System.out.print("player1 games =" + tscore1 + "---");
		System.out.print("player2 games =" + tscore2 + "---");
		System.out.print("player 1 First set games =" + fscore1);
		System.out.print("player 2 First set games =" + fscore2);
		updateTip();

		plist1.clear();
		plist2.clear();
		mwinner = "";
		tscore1 = 0;
		tscore2 = 0;
		fscore1 = 0;
		fscore2 = 0;

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
			if (mwinner.equals("team1")) {
				mwinner = team1;
			} else if (mwinner.equals("team2")) {
				mwinner = team2;
			}

			if (mwinner.equalsIgnoreCase("void")) {
				result = "void";
			} else {
				TennisResults results = new TennisResults();
				if (lines.equalsIgnoreCase(team1)) {
					lines = team1;
				} else if (lines.equalsIgnoreCase(team2)) {
					lines = team2;
				}
				result = results.getResults(market, lines, sublines, fscore1, fscore2, tscore1, tscore2, mwinner,
						tsets1, tsets2);
			}

			tip.setTip_result(result);
			tip.setTipscore(tsets1 + "-" + tsets2);
			Double odds = tip.getTip_odds();
			Integer units = tip.getTip_units();
			String s = tip.getTip_sublines();
			String fra = s.substring(s.indexOf(".") + 1);

			if (result.equalsIgnoreCase("won")) {
				tip.setTip_profit(Double.parseDouble(new DecimalFormat("##.##").format((odds * units) - units)));
			} else if (result.equalsIgnoreCase("lost")) {
				tip.setTip_profit(Double.parseDouble(new DecimalFormat("##.##").format(units * -1)));

			} else if (result.equalsIgnoreCase("void")) {
				tip.setTip_profit(Double.parseDouble(new DecimalFormat("##.##").format(0)));

			} else if (result.equalsIgnoreCase("halfwon")) {
				tip.setTip_profit(Double.parseDouble(new DecimalFormat("##.##").format(((odds * units) - units) / 2)));

			} else if (result.equalsIgnoreCase("halflost")) {
				tip.setTip_profit(Double.parseDouble(new DecimalFormat("##.##").format((units * -1) / 2)));

			}

			tip.setStatus("finished");
		}

	}

}
