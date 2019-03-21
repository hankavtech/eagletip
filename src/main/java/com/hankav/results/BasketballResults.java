package com.hankav.results;

public class BasketballResults {

	public String getResults(String market, String lines, String sublines, String mwinner, int fqscore1, int fqscore2,
			int tscore1, int tscore2, int fhscore1, int fhscore2) {

		System.out.print("player1 games =" + tscore1 + "---");
		System.out.print("player2 games =" + tscore2 + "---");
		System.out.print("player 1 First set games =" + fqscore1);
		System.out.print("player 2 First set games =" + fqscore2);
		String result = "";

		if (market.equalsIgnoreCase("Spread")) {

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

		} else if (market.equalsIgnoreCase("1st Quarter Spread")) {

			Double p1games = Double.valueOf(fqscore1);
			Double p2games = Double.valueOf(fqscore2);
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

		else if (market.equalsIgnoreCase("1st Half Spread")) {

			Double p1games = Double.valueOf(fhscore1);
			Double p2games = Double.valueOf(fhscore2);
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
					result = "draw";
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

		else if (market.equalsIgnoreCase("1st Quarter Money Line")) {
			String mresult = null;
			if (fqscore1 > fqscore2) {
				mresult = "team1";
			} else if (fqscore2 > fqscore1) {
				mresult = "team2";
			} else if (fqscore2 == fqscore1) {
				mresult = "draw";
			}

			if (lines.equalsIgnoreCase(mresult)) {
				result = "won";
			} else {
				result = "lost";
			}

			return result;

		} else if (market.equalsIgnoreCase("1st Half Total")) {
			String mresult;
			Double fhtotal = Double.valueOf(fhscore1 + fhscore2);
			Double phtotal = Double.parseDouble(sublines);
			if (lines.equalsIgnoreCase("Over")) {
				if (fhtotal > phtotal) {
					result = "won";
					if (Math.abs(fhtotal - phtotal) < 0.5) {
						result = "halfwon";
					}
				} else if (fhtotal < phtotal) {
					result = "lost";
					if (Math.abs(fhtotal - phtotal) < 0.5) {
						result = "halflost";
					}
				} else if (fhtotal == phtotal) {
					result = "void";
				}

			} else if (lines.equalsIgnoreCase("Under")) {

				if (fhtotal < phtotal) {
					result = "won";
					if (Math.abs(fhtotal - phtotal) < 0.5) {
						result = "halfwon";
					}
				} else if (fhtotal > phtotal) {
					result = "lost";
					if (Math.abs(fhtotal - phtotal) < 0.5) {
						result = "halflost";
					}
				} else if (fhtotal == phtotal) {
					result = "void";
				}

			}

			return result;

		} else if (market.equalsIgnoreCase("1st Quarter Total")) {
			String mresult;
			Double fqtotal = Double.valueOf(fqscore1 + fqscore2);
			Double pqtotal = Double.parseDouble(sublines);
			if (lines.equalsIgnoreCase("Over")) {
				if (fqtotal > pqtotal) {
					result = "won";
					if (Math.abs(fqtotal - pqtotal) < 0.5) {
						result = "halfwon";
					}
				} else if (fqtotal < pqtotal) {
					result = "lost";
					if (Math.abs(fqtotal - pqtotal) < 0.5) {
						result = "halflost";
					}
				} else if (fqtotal == pqtotal) {
					result = "void";
				}

			} else if (lines.equalsIgnoreCase("Under")) {

				if (fqtotal < pqtotal) {
					result = "won";
					if (Math.abs(fqtotal - pqtotal) < 0.5) {
						result = "halfwon";
					}
				} else if (fqtotal > pqtotal) {
					result = "lost";
					if (Math.abs(fqtotal - pqtotal) < 0.5) {
						result = "halflost";
					}
				} else if (fqtotal == pqtotal) {
					result = "void";
				}

			}

			return result;

		} else if (market.equalsIgnoreCase("1st Half Money Line")) {
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

		else if (market.equalsIgnoreCase("Money Line")) {
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

		else if (market.equalsIgnoreCase("Total")) {
			String mresult;
			Double fhtotal = Double.valueOf(tscore1 + tscore2);
			Double phtotal = Double.parseDouble(sublines);
			if (lines.equalsIgnoreCase("Over")) {
				if (fhtotal > phtotal) {
					result = "won";
					if (Math.abs(fhtotal - phtotal) < 0.5) {
						result = "halfwon";
					}
				} else if (fhtotal < phtotal) {
					result = "lost";
					if (Math.abs(fhtotal - phtotal) < 0.5) {
						result = "halflost";
					}
				} else if (fhtotal == phtotal) {
					result = "void";
				}

			} else if (lines.equalsIgnoreCase("Under")) {

				if (fhtotal < phtotal) {
					result = "won";
					if (Math.abs(fhtotal - phtotal) < 0.5) {
						result = "halfwon";
					}
				} else if (fhtotal > phtotal) {
					result = "lost";
					if (Math.abs(fhtotal - phtotal) < 0.5) {
						result = "halflost";
					}
				} else if (fhtotal == phtotal) {
					result = "void";
				}

			}

			return result;
		}
		return result;
	}

}
