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
import com.hankav.results.BasketballResults;

public class BasketballResultUpdate {

	private List<String> plist1 = new ArrayList<>();
	private List<String> plist2 = new ArrayList<>();
	private int i;
	private String mwinner;
	private int tscore1;
	private int tscore2;
	private int fhscore1;
	private int fhscore2;
	private String matchid;
	private int fqscore1;
	private int fqscore2;
	private String line;
	SessionFactory factory = HibSessionFactory.getFactory();
	Session session = factory.openSession();
	private Transaction tx = session.beginTransaction();

	public void updateResults() throws FileNotFoundException, IOException {
		FileReader reader = null;
		try {
			reader = new FileReader("/var/lib/etresources/results/basketballresults.txt");
		} catch (FileNotFoundException ex) {
			System.out.println("no such file");
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
					fhscore1 = Integer.parseInt(plist1.get(4)) + Integer.parseInt(plist1.get(5));
					if (plist1.contains("bold")) {
						mwinner = "team1";
						tscore1 += Integer.parseInt(plist1.get(3));
						fqscore1 = Integer.parseInt(plist1.get(4));

					} else {
						tscore1 += Integer.parseInt(plist1.get(3));
						fqscore1 = Integer.parseInt(plist1.get(4));
						mwinner = "team2";
					}
				} else if (plist1.get(1).equalsIgnoreCase("AOT")) {
					fhscore1 = Integer.parseInt(plist1.get(4) + plist1.get(5));
					if (plist1.contains("bold")) {
						mwinner = "team1";
						tscore1 += Integer.parseInt(plist1.get(3)) - Integer.parseInt(plist1.get(plist1.size() - 1));
						fqscore1 = Integer.parseInt(plist1.get(4));

					} else {
						tscore1 += Integer.parseInt(plist1.get(3));
						fqscore1 = Integer.parseInt(plist1.get(4));
						mwinner = "team2";
					}
				}

				else {
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

				fhscore2 = Integer.parseInt(plist2.get(3)) + Integer.parseInt(plist2.get(4));
				if (plist1.get(1).equalsIgnoreCase("Fin")) {
					tscore2 += Integer.parseInt(plist2.get(2));
				} else if (plist1.get(1).equalsIgnoreCase("AOT")) {
					tscore2 += Integer.parseInt(plist2.get(2)) - Integer.parseInt(plist2.get(plist2.size() - 1));
				}
				fqscore2 = Integer.parseInt(plist2.get(3));
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
		System.out.print("player 1 First qUAR =" + fqscore1);
		System.out.print("player 2 SECOND quar =" + fqscore2);
		System.out.print(" " + fhscore1);
		System.out.print(" " + fhscore2);
		System.out.print(plist1.get(plist1.size() - 1));
		System.out.print(plist2.get(plist2.size() - 1));
		updateTip();

		plist1.clear();
		plist2.clear();
		mwinner = "";
		tscore1 = 0;
		tscore2 = 0;
		fqscore1 = 0;
		fqscore2 = 0;
		fhscore1 = 0;
		fhscore2 = 0;

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
				BasketballResults results = new BasketballResults();
				result = results.getResults(market, lines, sublines, mwinner, fqscore1, fqscore2, tscore1, tscore2,
						fhscore1, fhscore2);
			}

			tip.setTip_result(result);
			tip.setTipscore(tscore1 + "-" + tscore2);
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
