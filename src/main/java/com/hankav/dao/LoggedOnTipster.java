package com.hankav.dao;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hankav.model.Tipster;

public class LoggedOnTipster {
	private Tipster tipster;

	/*
	 * public Tipster getTipster(String username) { System.out.println(username);
	 * SessionFactory factory=HibSessionFactory.getFactory(); Session
	 * session=factory.openSession(); session.beginTransaction(); Query
	 * query=session.createQuery("FROM User WHERE username=?1");
	 * query.setParameter(1,username); try { tipster=(Tipster)query.uniqueResult();
	 * } catch(NoResultException e) { tipster=new Tipster();
	 * tipster.setTipster_name(username); session.save(tipster);
	 * session.getTransaction().commit();
	 * 
	 * } session.close(); return tipster; }
	 */

	public Tipster getTipster(String username, Session session) {
		Query query = session.createQuery("FROM Tipster WHERE tipster_name=:tipstername");
		query.setParameter("tipstername", username);
		try {
			tipster = (Tipster) query.uniqueResult();
		} catch (NoResultException e) {
			System.out.println("You are not a tipster");
		}
		return tipster;
	}

}
