package com.hankav.results;

public class AmericanFootballResults {
	public String getResults(String market, String lines, String sublines, String mwinner, int tscore1, int tscore2) {
		String result = "";
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

		if (market.equalsIgnoreCase("Spread")) {

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

		if (market.equalsIgnoreCase("Money Line")) {

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

		return result;

	}

}
