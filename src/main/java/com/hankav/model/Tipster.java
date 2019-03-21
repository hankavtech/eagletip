package com.hankav.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Tipster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tipster_id;
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	@Column(unique = true)
	private String tipster_name;
	@ManyToOne(fetch = FetchType.LAZY)
	private Sport tipster_sport;
	private String tipster_category;
	private Double tipster_price = 0.0d;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Stats tipster_stats;
	private Double tipster_discount = 0.0d;
	private String tipster_status = "active";
	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> mailsubscribers = new ArrayList<>();
	private String paymentemail;

	public int getTipster_id() {
		return tipster_id;
	}

	public void setTipster_id(int tipster_id) {
		this.tipster_id = tipster_id;
	}

	public String getTipster_name() {
		return tipster_name;
	}

	public void setTipster_name(String tipster_name) {
		this.tipster_name = tipster_name;
	}

	public Sport getTipster_sport() {
		return tipster_sport;
	}

	public void setTipster_sport(Sport tipster_sport) {
		this.tipster_sport = tipster_sport;
	}

	public String getTipster_category() {
		return tipster_category;
	}

	public void setTipster_category(String tipster_category) {
		this.tipster_category = tipster_category;
	}

	public Stats getTipster_stats() {
		return tipster_stats;
	}

	public void setTipster_stats(Stats tipster_stats) {
		this.tipster_stats = tipster_stats;
	}

	public Double getTipster_price() {
		return tipster_price - ((this.tipster_discount / 100) * this.tipster_price);
	}

	public void setTipster_price(Double tipster_price) {
		this.tipster_price = tipster_price;
	}

	public Double getTipster_discount() {
		return tipster_discount;
	}

	public void setTipster_discount(Double tipster_discount) {
		this.tipster_discount = tipster_discount;
	}

	public void setTipster_status(String tipster_status) {
		this.tipster_status = tipster_status;
	}

	public String getTipster_status() {
		return tipster_status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getMailsubscribers() {
		return this.mailsubscribers;
	}

	public void setMailsubscribers(List<String> mailsubscribers) {
		this.mailsubscribers = mailsubscribers;
	}

	public String getPaymentemail() {
		return paymentemail;
	}

	public void setPaymentemail(String paymentemail) {
		this.paymentemail = paymentemail;
	}

}
