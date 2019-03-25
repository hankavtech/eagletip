package com.hankav.model;

import java.io.IOException;
import java.text.ParseException;

import javax.mail.internet.AddressException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hankav.dao.FootballResultUpdate;
import com.hankav.dao.HibSessionFactory;

public class Main {

	public static void main(String[] args) throws ParseException, HibernateException, IOException, AddressException {

		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		FootballResultUpdate up = new FootballResultUpdate();
		up.updateResults();
		session.getTransaction().commit();
		session.close();

	}

}
