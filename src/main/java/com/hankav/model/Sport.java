package com.hankav.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.annotations.Expose;
import com.hankav.dao.HibSessionFactory;

@Entity
public class Sport implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Expose
	@Column(unique = true)
	private String name;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Expose
	private Set<Market> markets;

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

	public Set<Market> getMarkets() {
		return markets;
	}

	public void setMarkets(Set<Market> markets) {
		this.markets = markets;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Sport sport = new Sport();
		sport.setName("football");
		Market m1 = new Market();
		m1.setName("Full Time Result");
		m1.setMylines(Arrays.asList("team1", "team2", "draw"));
		m1.setSport(sport);
		m1.setSublines(false);
		Market m2 = new Market();
		m2.setName("Total Goals");
		m2.setMylines(Arrays.asList("over", "under"));
		m2.setSport(sport);
		m2.setSublines(true);
		Market m3 = new Market();
		m3.setName("Half Time Result");
		m3.setMylines(Arrays.asList("team1", "team2", "draw"));
		m3.setSport(sport);
		m3.setSublines(false);
		Market m4 = new Market();
		m4.setName("1st Half Goals");
		m4.setMylines(Arrays.asList("over", "under"));
		m4.setSport(sport);
		m4.setSublines(true);
		Market m5 = new Market();
		m5.setName("Handicap Goals");
		m5.setMylines(Arrays.asList("team1", "team2"));
		m5.setSport(sport);
		m5.setSublines(true);

		Market m6 = new Market();
		m6.setName("1st Half Score");
		m6.setMylines(Arrays.asList("0-0", "0-1", "0-2", "1-0", "2-0", "1-1", "1-2", "2-1", "2-2"));
		m6.setSport(sport);
		m6.setSublines(false);

		Market m7 = new Market();
		m7.setName("Full Time Score");
		m7.setMylines(Arrays.asList("0-0", "0-1", "0-2", "1-0", "2-0", "1-1", "1-2", "2-1", "2-2", "1-3", "3-1", "3-3",
				"0-3", "3-0", "2-3", "3-2"));
		m7.setSport(sport);
		m7.setSublines(false);
		Market m8 = new Market();
		m8.setName("Double Chance");
		m8.setMylines(Arrays.asList("1x", "2x", "12"));
		m8.setSport(sport);
		m8.setSublines(false);

		Market m9 = new Market();
		m9.setName("Both Teams To Score");
		m9.setMylines(Arrays.asList("yes", "no"));
		m9.setSport(sport);
		m9.setSublines(false);

		Set<Market> markets = new LinkedHashSet<>();
		markets.add(m1);
		markets.add(m2);
		markets.add(m3);
		markets.add(m4);
		markets.add(m5);
		markets.add(m6);
		markets.add(m7);
		markets.add(m8);
		markets.add(m9);
		sport.setMarkets(markets);
		session.save(m1);
		session.save(m2);
		session.save(m3);
		session.save(m4);
		session.save(m5);
		session.save(m6);
		session.save(m7);
		session.save(m8);
		session.save(m9);
		session.save(sport);
		session.getTransaction().commit();
		session.close();

	}

}
