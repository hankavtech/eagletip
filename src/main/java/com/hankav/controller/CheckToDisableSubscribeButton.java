package com.hankav.controller;

import java.io.IOException;
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

import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Subscription;

/**
 * Servlet implementation class CheckToDisableSubscribeButton
 */
@WebServlet("/CheckToDisableSubscribeButton")
public class CheckToDisableSubscribeButton extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = (String) request.getSession().getAttribute("email");
		String username = (String) request.getSession().getAttribute("username");
		if (email == null) {
			response.getWriter().println("notloggedin");
			return;
		}
		String tipstername = request.getParameter("tipstername");
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Subscription.class);
		criteria.add(Restrictions.eq("subscription_type", "free"));
		criteria.add(Restrictions.eq("subscription_status", "active"));
		criteria.createAlias("subscribed_tipster", "tipster");
		criteria.createAlias("subscriber", "user");
		criteria.add(Restrictions.eq("tipster.tipster_name", tipstername.trim()));
		criteria.add(Restrictions.eq("user.username", username));
		criteria.setProjection(Projections.property("subscription_id"));
		List<Integer> subs = criteria.list();
		if (subs.size() > 0) {
			response.getWriter().println("disable");
			session.close();
			return;
		} else {
			response.getWriter().println("enable");
		}

		session.close();
	}
}
