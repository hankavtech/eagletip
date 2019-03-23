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
import com.hankav.results.HockeyResults;

public class HockeyResultUpdate {
	private List<String> plist1 = new ArrayList<>();
	private List<String> plist2 = new ArrayList<>();
	private int i;
	private String line;
	private String matchid;
	private int tscore1;
	private int tscore2;
	private int fpscore1;
	private int fpscore2;
	private int exscore1;
	private int exscore2;
	private int penscore1;
	private int penscore2;
	private boolean extratime;
	private String mwinner = "";
	private String status;
	private SessionFactory factory;
	private Session session;
	private Transaction tx;

	public void updateResults() throws IOException {
		factory = HibSessionFactory.getFactory();
		session = factory.openSession();
		tx = session.beginTransaction();
		FileReader reader = null;
		try {
			reader = new FileReader("/var/lib/etresources/results/hockeyresults.txt");
		} catch (IOException IOException) {
			System.out.println("no file found");
			return;
		}
		BufferedReader br = new BufferedReader(reader);
		List<String> list;
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

				matchid = plist1.get(0).trim();
				status = plist1.get(1).trim();
				extratime = false;
				if (!(status.equals("Canc") || status.equals("Postp") || status.equals("Abn"))) {

					if (status.equalsIgnoreCase("Fin")) {
						if (plist1.contains("bold")) {
							mwinner = "team1";
							tscore1 += Integer.parseInt(plist1.get(3));
							fpscore1 = Integer.parseInt(plist1.get(4));

						}

					} else if (status.equalsIgnoreCase("AOT")) {
						exscore1 = Integer.parseInt(plist1.get(plist1.size() - 1));
						tscore1 += Integer.parseInt(plist1.get(3)) - exscore1;
						fpscore1 = Integer.parseInt(plist1.get(4));
						extratime = true;

					} else if (status.equalsIgnoreCase("Pen")) {
						exscore1 = Integer.parseInt(plist1.get(plist1.size() - 2));
						penscore1 = Integer.parseInt(plist1.get(plist1.size() - 1));
						tscore1 += Integer.parseInt(plist1.get(3)) - exscore1 - penscore1;
						fpscore1 = Integer.parseInt(plist1.get(4));
						extratime = true;

					}

				} else {
					mwinner = "void";
					printcontents();
					System.out.println();
					i++;
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
				if (status.equalsIgnoreCase("Fin")) {

					if (plist2.contains("bold")) {
						mwinner = "team2";
					}

					tscore2 += Integer.parseInt(plist2.get(2));
					fpscore2 = Integer.parseInt(plist2.get(3));
				} else if (status.equalsIgnoreCase("AOT")) {

					exscore2 = Integer.parseInt(plist2.get(plist2.size() - 1));
					tscore2 += Integer.parseInt(plist2.get(2)) - exscore1;
					fpscore2 = Integer.parseInt(plist2.get(3));
				} else if (status.equalsIgnoreCase("Pen")) {
					exscore2 = Integer.parseInt(plist2.get(plist2.size() - 2));
					penscore2 = Integer.parseInt(plist2.get(plist2.size() - 1));
					tscore2 += Integer.parseInt(plist2.get(2)) - exscore2 - penscore2;
					fpscore2 = Integer.parseInt(plist2.get(3));
				}

				printcontents();
				System.out.println();
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
		System.out.print("player 1 First qUAR =" + fpscore1);
		System.out.print("player 2 SECOND quar =" + fpscore2);
		System.out.print(" " + exscore1);
		System.out.print(" " + exscore2);
		System.out.print(" " + mwinner);
		updateTip();

		plist1.clear();
		plist2.clear();
		mwinner = "";
		tscore1 = 0;
		tscore2 = 0;
		fpscore1 = 0;
		fpscore2 = 0;
		exscore1 = 0;
		exscore2 = 0;
		penscore1 = 0;
		penscore2 = 0;

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
				HockeyResults results = new HockeyResults();
				if (lines.equalsIgnoreCase(team1)) {
					lines = team1;
				} else if (lines.equalsIgnoreCase(team2)) {
					lines = team2;
				}
				result = results.getResults(market, lines, sublines, fpscore1, fpscore2, tscore1, tscore2, exscore1,
						exscore2, penscore1, penscore2, extratime);
			}

			tip.setTip_result(result);
			tip.setTipscore("" + (tscore1 + exscore1 + penscore1) + "-" + (tscore2 + exscore2 + penscore2));
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
