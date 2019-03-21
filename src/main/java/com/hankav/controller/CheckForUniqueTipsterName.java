package com.hankav.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Tipster;

/**
 * Servlet implementation class CheckForUniqueTipsterName
 */
@WebServlet("/CheckForUniqueTipsterName")
public class CheckForUniqueTipsterName extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tipstername=request.getParameter("tipstername");
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();
		Criteria criteria=session.createCriteria(Tipster.class);
		criteria.add(Restrictions.eq("tipster_name",tipstername));
		Tipster tipster=null;
	    tipster=(Tipster) criteria.uniqueResult();
		if(tipster==null) {
			session.close();
			response.getWriter().println("goahead");
			return;
		}
		else {
			session.close();
			response.getWriter().println("tipsteralready");
		}
		
		
		
	}

}
