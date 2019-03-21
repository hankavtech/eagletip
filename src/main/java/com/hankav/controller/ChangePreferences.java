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
import com.hankav.model.Preferences;
import com.hankav.model.User;

/**
 * Servlet implementation class ChangePreferences
 */
@WebServlet("/ChangePreferences")
public class ChangePreferences extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = (String) request.getSession().getAttribute("username");
		if (username == null) {
			response.getWriter().println("Please log in");
			return;
		}
		String currency = (String) request.getParameter("currency");
		String oddsformat = (String) request.getParameter("odds");
		String timezone = (String) request.getParameter("timezone");
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", "test"));
		User user = (User) criteria.uniqueResult();
		System.out.println(user.getEmail());
		Preferences pref = new Preferences();
		pref.setCurrency(currency);
		pref.setOddsformat(oddsformat);
		pref.setTimezone(timezone);
		user.setUser_preferences(pref);
		session.update(user);
		request.getSession().setAttribute("currency", currency);
		request.getSession().setAttribute("odds", oddsformat);
		request.getSession().setAttribute("timezone", timezone);
		session.getTransaction().commit();
		session.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}