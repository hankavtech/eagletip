package com.hankav.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Preferences implements Serializable {
	private String oddsformat;
	private String timezone;
	private String currency;

	public Preferences() {
		this.oddsformat = "DECIMAL";
		this.timezone = "0";
		this.currency = "USD";
	}

	public Preferences(String oddsformat, String timezone, String currency) {
		this.oddsformat = oddsformat;
		this.timezone = timezone;
		this.currency = currency;
	}

	public String getOddsformat() {
		return oddsformat;
	}

	public void setOddsformat(String oddsformat) {
		this.oddsformat = oddsformat;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}