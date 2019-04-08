package com.hankav.results;

public class HockeyResults {
	public String getResults(String market, String lines, String sublines, int fpscore1, int fpscore2, int tscore1,
			int tscore2, int exscore1, int exscore2, int penscore1, int penscore2, boolean extratime) {
		String result = "";
		int totalscore1 = tscore1 + exscore1 + penscore1;
		int totalscore2 = tscore2 + exscore2 + penscore2;

		if (market.equalsIgnoreCase("Total")) {

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

		if (market.equalsIgnoreCase("First Period Money Line 3 Way")) {

			String mresult = null;
			if (fpscore1 > fpscore2) {
				mresult = "team1";
			} else if (fpscore2 > fpscore1) {
				mresult = "team2";
			} else if (fpscore2 == fpscore1) {
				mresult = "draw";
			}

			if (lines.equalsIgnoreCase(mresult)) {
				result = "won";
			} else {
				result = "lost";
			}

			return result;

		}

		if (market.equalsIgnoreCase("First Period Money Line")) {

			String mresult = null;
			if (fpscore1 > fpscore2) {
				mresult = "team1";
			} else if (fpscore2 > fpscore1) {
				mresult = "team2";
			} else if (fpscore2 == fpscore1) {
				mresult = "void";
				return mresult;
			}

			if (lines.equalsIgnoreCase(mresult)) {
				result = "won";
			} else {
				result = "lost";
			}

			return result;

		}

		if (market.equalsIgnoreCase("First Period Total")) {

			Double tgoals = (double) (fpscore1 + fpscore2);
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

		if (market.equalsIgnoreCase("Handicap")) {

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

		if (market.equalsIgnoreCase("Money Line 3 Way")) {

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

		if (market.equalsIgnoreCase("Money Line")) {

			String mresult = null;
			if (tscore1 > tscore2) {
				mresult = "team1";
			} else if (tscore2 > tscore1) {
				mresult = "team2";
			} else if (tscore2 == tscore1) {
				mresult = "void";
				return mresult;
			}

			if (lines.trim().equalsIgnoreCase(mresult)) {
				result = "won";
			} else {
				result = "lost";
			}

			return result;

		}

		if (market.equalsIgnoreCase("Money Line(ext-pen)")) {
			String mresult = null;
			if (extratime) {
				if (totalscore1 > totalscore2) {
					mresult = "team1";
				} else if (totalscore2 > totalscore1) {
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
				if (totalscore1 > totalscore2) {
					mresult = "team1";
				} else if (totalscore2 > totalscore1) {
					mresult = "team2";
				} else if (totalscore2 == totalscore1) {
					mresult = "draw";
				}

				if (lines.equalsIgnoreCase(mresult)) {
					result = "won";
				} else {
					result = "lost";
				}

			}

			return result;

		}

		return result;

	}
}
