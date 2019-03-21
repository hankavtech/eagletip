package com.hankav.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Stats {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double profit = 0.0d;
	private Integer tips = 0;
	private Double avodds = 0.0d;
	private Double yield = 0.0d;
	private Double winpercentage = 0.0d;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Tipster stats_tipster;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Integer getTips() {
		return tips;
	}

	public void setTips(Integer tips) {
		this.tips = tips;
	}

	public Double getAvodds() {
		return avodds;
	}

	public void setAvodds(Double avodds) {
		this.avodds = avodds;
	}

	public Double getYield() {
		return yield;
	}

	public void setYield(Double yield) {
		this.yield = yield;
	}

	public Tipster getStats_tipster() {
		return stats_tipster;
	}

	public void setStats_tipster(Tipster stats_tipster) {
		this.stats_tipster = stats_tipster;
	}

	public Double getWinpercentage() {
		return winpercentage;
	}

	public void setWinpercentage(Double winpercentage) {
		this.winpercentage = winpercentage;
	}

}
