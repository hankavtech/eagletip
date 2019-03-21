package com.hankav.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;

@Entity
public class Market implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private int id;

	@ManyToOne
	private Sport sport;
	@Expose
	private String name;
	@ElementCollection(fetch = FetchType.LAZY)
	@Expose
	private List<String> Mylines;
	@Expose
	private boolean sublines;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMylines() {
		return Mylines;
	}

	public void setMylines(List<String> mylines) {
		Mylines = mylines;
	}

	public boolean isSublines() {
		return sublines;
	}

	public void setSublines(boolean sublines) {
		this.sublines = sublines;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

}
