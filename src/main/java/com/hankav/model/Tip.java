package com.hankav.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tip implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tip_id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Sport tip_sport;
	private String tip_matchid;
	private String team1;
	private String team2;
	private String tip_bookmaker;
	private Date tip_date;
	private Date tip_match_time;
	private String tip_result;
	private String tip_market;
	private String tip_lines;
	private String tip_sublines;
	private int tip_units;
	private double tip_odds;
	private double tip_profit;
	private String tip_league;
	private String tip_tournament;
	private String tip_category;
	@ManyToOne(fetch = FetchType.LAZY)
	private Tipster tipster;
	private String status;
	private String tipscore;
	private String emailsent = "no";

	public Long getTip_id() {
		return tip_id;
	}

	public void setTip_id(Long tip_id) {
		this.tip_id = tip_id;
	}

	public Sport getTip_sport() {
		return tip_sport;
	}

	public void setTip_sport(Sport tip_sport) {
		this.tip_sport = tip_sport;
	}

	public String getTip_bookmaker() {
		return tip_bookmaker;
	}

	public void setTip_bookmaker(String tip_bookmaker) {
		this.tip_bookmaker = tip_bookmaker;
	}

	public Date getTip_date() {
		return tip_date;
	}

	public void setTip_date(Date tip_date) {
		this.tip_date = tip_date;
	}

	public Date getTip_match_time() {
		return tip_match_time;
	}

	public void setTip_match_time(Date tip_match_time) {
		this.tip_match_time = tip_match_time;
	}

	public String getTip_result() {
		return tip_result;
	}

	public void setTip_result(String tip_result) {
		this.tip_result = tip_result;
	}

	public String getTeam1() {
		return team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public String getTeam2() {
		return team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	public String getTip_lines() {
		return tip_lines;
	}

	public void setTip_lines(String tip_lines) {
		this.tip_lines = tip_lines;
	}

	public String getTip_sublines() {
		return tip_sublines;
	}

	public void setTip_sublines(String tip_sublines) {
		this.tip_sublines = tip_sublines;
	}

	public String getTip_market() {
		return tip_market;
	}

	public void setTip_market(String tip_market) {
		this.tip_market = tip_market;
	}

	public int getTip_units() {
		return tip_units;
	}

	public void setTip_units(int tip_units) {
		this.tip_units = tip_units;
	}

	public double getTip_odds() {
		return tip_odds;
	}

	public void setTip_odds(double tip_odds) {
		this.tip_odds = tip_odds;
	}

	public double getTip_profit() {
		return tip_profit;
	}

	public void setTip_profit(double tip_profit) {
		this.tip_profit = tip_profit;
	}

	public Tipster getTipster() {
		return tipster;
	}

	public void setTipster(Tipster tipster) {
		this.tipster = tipster;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTip_league() {
		return tip_league;
	}

	public void setTip_league(String tip_league) {
		this.tip_league = tip_league;
	}

	public String getTip_tournament() {
		return tip_tournament;
	}

	public void setTip_tournament(String tip_tournament) {
		this.tip_tournament = tip_tournament;
	}

	public String getTip_matchid() {
		return tip_matchid;
	}

	public void setTip_matchid(String tip_matchid) {
		this.tip_matchid = tip_matchid;
	}

	public String getTip_category() {
		return tip_category;
	}

	public void setTip_category(String tip_category) {
		this.tip_category = tip_category;
	}

	public String getTipscore() {
		return tipscore;
	}

	public void setTipscore(String tipscore) {
		this.tipscore = tipscore;
	}

	public String getEmailsent() {
		return emailsent;
	}

	public void setEmailsent(String emailsent) {
		this.emailsent = emailsent;
	}

}
