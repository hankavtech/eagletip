package com.hankav.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Tipster;

/**
 * Servlet implementation class CheckOneSportPerUser
 */
@WebServlet("/CheckOneSportPerUser")
public class CheckOneSportPerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> sports = new ArrayList<>(
				Arrays.asList("tennis", "cricket", "football", "basketball", "hockey", "american football"));

		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Tipster.class);
		criteria.createAlias("user", "user");
		criteria.createAlias("tipster_sport", "sport");
		criteria.add(Restrictions.eq("user.username", request.getSession().getAttribute("username")));
		criteria.setProjection(Projections.property("sport.name"));
		List<String> tipsports = criteria.list();
		sports.removeAll(tipsports);

		response.getWriter().println(new Gson().toJson(sports));

	}

}
