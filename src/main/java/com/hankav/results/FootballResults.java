package com.hankav.results;

public class FootballResults {

	public String getResults(String market, String lines, String sublines, String mwinner, int fhscore1, int fhscore2,
			int tscore1, int tscore2, int exscore1, int exscore2, boolean extratime) {
		String result = "";
		if (market.equalsIgnoreCase("Full Time Result")) {

			String mresult = null;
			if (tscore1 > tscore2) {
				mresult = "team1";
			} else if (tscore2 > tscore1) {
				mresult = "team2";
			} else if (tscore2 == tscore1) {
				mresult = "draw";
			}

			if (lines.equalsIgnoreCase(mresult)) {
				result = "won";
			} else {
				result = "lost";
			}

			return result;

		}

		if (market.equalsIgnoreCase("Half Time Result")) {

			String mresult = null;
			if (fhscore1 > fhscore2) {
				mresult = "team1";
			} else if (fhscore2 > fhscore1) {
				mresult = "team2";
			} else if (fhscore2 == fhscore1) {
				mresult = "draw";
			}

			if (lines.equalsIgnoreCase(mresult)) {
				result = "won";
			} else {
				result = "lost";
			}

			return result;

		}

		if (market.equalsIgnoreCase("Both Teams To Score")) {

			if (lines.equalsIgnoreCase("yes")) {
				if (tscore1 > 0 && tscore2 > 0) {
					result = "won";
				} else {
					result = "lost";
				}

			} else if (lines.equalsIgnoreCase("no")) {
				if (tscore1 > 0 && tscore2 > 0) {
					result = "lost";
				} else {
					result = "won";
				}

			}

			return result;

		}

		if (market.equalsIgnoreCase("Handicap Goals")) {

			String mresult = null;
			Double phandicap = Double.parseDouble(sublines);
			if (lines.equalsIgnoreCase("team1")) {
				if ((tscore1 + phandicap) > tscore2) {
					result = "won";
					if (Math.abs(tscore1 + phandicap - tscore2) < 0.5) {
						result = "halfwon";
					}
				} else if ((tscore1 + phandicap) < tscore2) {
					result = "lost";
					if (Math.abs(tscore1 + phandicap - tscore2) < 0.5) {
						result = "halflost";
					}
				} else if ((tscore1 + phandicap) == tscore2) {
					result = "draw";
				}

			} else if (lines.equalsIgnoreCase("team2")) {
				if ((tscore2 + phandicap) > tscore1) {
					result = "won";
					if (Math.abs(tscore1 + phandicap - tscore2) < 0.5) {
						result = "halfwon";
					}
				} else if ((tscore2 + phandicap) < tscore1) {
					result = "lost";
					if (Math.abs(tscore1 + phandicap - tscore2) < 0.5) {
						result = "halflost";
					}
				} else if ((tscore2 + phandicap) == tscore1) {
					result = "void";
				}

			}
			return result;

		}

		if (market.equalsIgnoreCase("Total Goals")) {
			Double tgoals = (double) (tscore1 + tscore2);
			Double pgoals = Double.parseDouble(sublines);
			if (lines.equalsIgnoreCase("Under")) {
				if (tgoals > pgoals) {
					result = "lost";
					if (Math.abs(tgoals - pgoals) < 0.5) {
						result = "halflost";
					}
				} else if (tgoals < pgoals) {
					result = "won";
					if (Math.abs(pgoals - tgoals) < 0.5) {
						result = "halfwon";
					}
				} else if (tgoals == pgoals) {
					result = "void";
				}

				result = "won";
			} else if (lines.equalsIgnoreCase("Over")) {

				if (tgoals < pgoals) {
					result = "lost";
					if (Math.abs(pgoals - tgoals) < 0.5) {
						result = "halflost";
					}
				} else if (tgoals > pgoals) {
					result = "won";
					if (Math.abs(tgoals - pgoals) < 0.5) {
						result = "halfwon";
					}
				} else if (tgoals == pgoals) {
					result = "void";
				}

			}

			return result;

		}
		if (market.equalsIgnoreCase("1st Half Goals")) {
			Double tgoals = (double) (fhscore1 + fhscore2);
			Double pgoals = Double.parseDouble(sublines);
			if (lines.equalsIgnoreCase("Under")) {
				if (tgoals > pgoals) {
					result = "lost";
					if (Math.abs(tgoals - pgoals) < 0.5) {
						result = "halflost";
					}
				} else if (tgoals < pgoals) {
					result = "won";
					if (Math.abs(pgoals - tgoals) < 0.5) {
						result = "halfwon";
					}
				} else if (tgoals == pgoals) {
					result = "void";
				}

				result = "won";
			} else if (lines.equalsIgnoreCase("Over")) {

				if (tgoals < pgoals) {
					result = "lost";
					if (Math.abs(pgoals - tgoals) < 0.5) {
						result = "halflost";
					}
				} else if (tgoals > pgoals) {
					result = "won";
					if (Math.abs(tgoals - pgoals) < 0.5) {
						result = "halfwon";
					}
				} else if (tgoals == pgoals) {
					result = "void";
				}

			}

			return result;

		}

		if (market.equalsIgnoreCase("Double Chance")) {

			if (lines.equalsIgnoreCase("1x")) {
				if (tscore1 < tscore2) {
					result = "lost";
				} else if (tscore1 >= tscore2) {
					result = "won";
				}

			} else if (lines.equalsIgnoreCase("2x")) {

				if (tscore1 > tscore2) {
					result = "lost";
				} else if (tscore1 <= tscore2) {
					result = "won";
				}

			} else if (lines.equalsIgnoreCase("12")) {
				if (tscore1 == tscore2) {
					result = "lost";
				} else {
					result = "won";
				}

			}
			return result;

		} else if (market.equalsIgnoreCase("1st Half Score")) {
			String[] mylines = lines.split("-");
			int score1 = Integer.parseInt(mylines[0]);
			int score2 = Integer.parseInt(mylines[1]);

			if ((score1 == fhscore1) && (score2 == fhscore2)) {
				result = "won";
			} else {
				result = "lost";
			}
			return result;

		} else if (market.equalsIgnoreCase("Full Time Score")) {
			String[] mylines = lines.split("-");
			int score1 = Integer.parseInt(mylines[0]);
			int score2 = Integer.parseInt(mylines[1]);

			if ((score1 == tscore1) && (score2 == tscore2)) {
				result = "won";
			} else {
				result = "lost";
			}

			return result;

		} else if (market.equalsIgnoreCase("Full Time Result(ext-pen)")) {
			String mresult = null;
			if (extratime) {
				if (exscore1 > exscore2) {
					mresult = "team1";
				} else if (exscore2 > exscore1) {
					mresult = "team2";
				} else if (exscore2 == exscore1) {
					mresult = "draw";
				}

				if (lines.equalsIgnoreCase(mresult)) {
					result = "won";
				} else {
					result = "lost";
				}
			} else {
				if (tscore1 > tscore2) {
					mresult = "team1";
				} else if (tscore2 > tscore1) {
					mresult = "team2";
				} else if (tscore2 == tscore1) {
					mresult = "draw";
				}

				if (lines.equalsIgnoreCase(mresult)) {
					result = "won";
				} else {
					result = "lost";
				}

			}

			return result;

		} else if (market.equalsIgnoreCase("Draw No Bet")) {
			if (lines.equalsIgnoreCase("team1")) {
				if (tscore1 > tscore2) {
					result = "won";
				} else if (tscore1 < tscore2) {
					result = "lost";
				} else if (tscore1 == tscore2) {
					result = "void";
				}

			} else if (lines.equalsIgnoreCase("team2")) {
				if (tscore1 < tscore2) {
					result = "won";
				} else if (tscore1 > tscore2) {
					result = "lost";
				} else if (tscore1 == tscore2) {
					result = "void";
				}

			}
			return result;

		}

		return result;

	}

}
