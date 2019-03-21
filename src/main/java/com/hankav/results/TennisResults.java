package com.hankav.results;

public class TennisResults {

	public String getResults(String market, String lines, String sublines, int fscore1, int fscore2, int tscore1,
			int tscore2, String mwinner, int tsets1, int tsets2) {

		System.out.print("player1 games =" + tscore1 + "---");
		System.out.print("player2 games =" + tscore2 + "---");
		System.out.print("player 1 First set games =" + fscore1);
		System.out.print("player 2 First set games =" + fscore2);
		String result = "";

		if (market.equalsIgnoreCase("Set Betting")) {

			String[] arr = lines.split("-");
			int p1 = Integer.parseInt(arr[0]);
			int p2 = Integer.parseInt(arr[1]);

			if ((p1 == tsets1) && (p2 == tsets2)) {
				result = "won";
			} else {
				result = "lost";
			}
			return result;

		} else if (market.equalsIgnoreCase("First Set Winner")) {
			String mresult;
			if (fscore1 > fscore2) {
				mresult = "team1";
			} else {
				mresult = "team2";
			}

			if (lines.equalsIgnoreCase(mresult)) {
				result = "won";
			} else {
				result = "lost";
			}

			return result;

		} else if (market.equalsIgnoreCase("Total Sets")) {
			String mresult;
			Double totalsets = Double.valueOf(tsets1 + tsets2);
			Double psets = Double.parseDouble(sublines);
			if (lines.equalsIgnoreCase("Over")) {
				if (totalsets > psets) {
					result = "won";
					if (Math.abs(totalsets - psets) < 0.5) {
						result = "halfwon";
					}
				} else if (totalsets < psets) {
					result = "lost";
					if (Math.abs(totalsets - psets) < 0.5) {
						result = "halflost";
					}
				} else if (totalsets == psets) {
					result = "void";
				}

			} else if (lines.equalsIgnoreCase("Under")) {

				if (totalsets < psets) {
					result = "won";
					if (Math.abs(totalsets - psets) < 0.5) {
						result = "halfwon";
					}
				} else if (totalsets > psets) {
					result = "lost";
					if (Math.abs(totalsets - psets) < 0.5) {
						result = "halflost";
					}
				} else if (totalsets == psets) {
					result = "void";
				}

			}

			return result;

		} else if (market.equalsIgnoreCase("1st Set Total Games")) {
			Double totalgames = Double.valueOf(fscore1 + fscore2);
			Double pgames = Double.parseDouble(sublines);
			if (lines.equalsIgnoreCase("Under")) {
				if (pgames > totalgames) {
					result = "won";
					if (Math.abs(pgames - totalgames) < 0.5) {
						result = "halfwon";
					}
				} else if (pgames < totalgames) {
					result = "lost";
					if (Math.abs(pgames - totalgames) < 0.5) {
						result = "halflost";
					}
				} else if (pgames == totalgames) {
					result = "void";
				}

			} else if (lines.equalsIgnoreCase("Over")) {
				if (pgames < totalgames) {
					result = "won";
					if (Math.abs(pgames - totalgames) < 0.5) {
						result = "halfwon";
					}
				} else if (pgames > totalgames) {
					result = "lost";
					if (Math.abs(pgames - totalgames) < 0.5) {
						result = "halflost";
					}
				} else if (pgames == totalgames) {
					result = "void";
				}

			}
			return result;

		} else if (market.equalsIgnoreCase("Handicap Games")) {
			Double p1games = Double.valueOf(tscore1);
			Double p2games = Double.valueOf(tscore2);
			Double psublines = Double.valueOf(sublines);
			if (lines.equalsIgnoreCase("team1")) {
				if ((p1games + psublines) > p2games) {
					result = "won";
					if (Math.abs(p1games + psublines - p2games) < 0.5) {
						result = "halfwon";
					}
				} else if ((p1games + psublines) < p2games) {
					result = "lost";
					if (Math.abs(p1games + psublines - p2games) < 0.5) {
						result = "halflost";
					}
				} else if ((p1games + psublines) == p2games) {
					result = "void";
				}

			} else if (lines.equalsIgnoreCase("team2")) {
				if ((p2games + psublines) > p1games) {
					result = "won";
					if (Math.abs(p1games + psublines - p2games) < 0.5) {
						result = "halfwon";
					}
				} else if ((p2games + psublines) < p1games) {
					result = "lost";
					if (Math.abs(p1games + psublines - p2games) < 0.5) {
						result = "halflost";
					}
				} else if ((p2games + psublines) == p1games) {
					result = "void";
				}

			}
			return result;

		}

		else if (market.equalsIgnoreCase("To Win The Match")) {
			String mresult;
			if (tsets1 > tsets2) {
				mresult = "team1";
			} else {
				mresult = "team2";
			}

			if (lines.equalsIgnoreCase(mresult)) {
				result = "won";
			} else {
				result = "lost";
			}

			return result;

		} else if (market.equalsIgnoreCase("Total Games")) {
			String mresult;
			Double totalgames = Double.valueOf(tscore1 + tscore2);
			Double pgames = Double.parseDouble(sublines);
			if (lines.equalsIgnoreCase("Over")) {
				if (totalgames > pgames) {
					result = "won";
					if (Math.abs(totalgames - pgames) < 0.5) {
						result = "halfwon";
					}
				} else if (totalgames < pgames) {
					result = "lost";
					if (Math.abs(totalgames - pgames) < 0.5) {
						result = "halflost";
					}
				} else if (totalgames == pgames) {
					result = "void";
				}

			} else if (lines.equalsIgnoreCase("Under")) {

				if (totalgames < pgames) {
					result = "won";
					if (Math.abs(totalgames - pgames) < 0.5) {
						result = "halfwon";
					}
				} else if (totalgames > pgames) {
					result = "lost";
					if (Math.abs(totalgames - pgames) < 0.5) {
						result = "halflost";
					}
				} else if (totalgames == pgames) {
					result = "void";
				}

			}

			return result;

		}

		return result;
	}

}
