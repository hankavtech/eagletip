package com.hankav.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hankav.model.Sport;
import com.hankav.model.Stats;
import com.hankav.model.Tipster;
import com.hankav.model.User;

public class RegisterTipsterOfUser {
	
	public void withUsernameandSportname(String username,String tipstername,String sportname) {
	SessionFactory factory=HibSessionFactory.getFactory();
	Session session=factory.openSession();
	session.beginTransaction();
	Tipster tipster=new Tipster();
	User user=new GetUserByProperty().byName(username,session);
	tipster.setUser(user);
	tipster.setTipster_category("free");
	tipster.setTipster_name(tipstername);
	Stats stats1=new Stats();
	tipster.setTipster_stats(stats1);
	Sport sport=new GetSportByProperty().byName(sportname);
	tipster.setTipster_sport(sport);
    session.save(stats1);
    session.save(tipster);
	session.getTransaction().commit();
	session.close();
	}
	
}
