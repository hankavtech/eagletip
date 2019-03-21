package com.hankav.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibSessionFactory {
	public static SessionFactory factory;
	//to disallow creating objects by other classes.

	    private HibSessionFactory() {
	    }
	//maling the Hibernate SessionFactory object as singleton

	    public static synchronized SessionFactory getFactory() {

	        if (factory == null) {
	            factory = new Configuration().configure("hibernate.cfg.xml").
	                    buildSessionFactory();
	        }
	        return factory;
	    }


}
