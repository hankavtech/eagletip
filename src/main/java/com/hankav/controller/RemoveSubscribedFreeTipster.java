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
import org.hibernate.criterion.Restrictions;

import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Subscription;
import com.hankav.model.Tip;

/**
 * Servlet implementation class RemoveSubscribedFreeTipster
 */
@WebServlet("/RemoveSubscribedFreeTipster")
public class RemoveSubscribedFreeTipster extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

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

		List<Subscription> subs = criteria.list();
		if (subs.size() > 0) {
			for (Subscription sub : subs) {
				criteria = session.createCriteria(Tip.class);
				criteria.createAlias("tipster", "tipster");
				criteria.add(Restrictions.eq("tipster.tipster_name", tipstername));
				criteria.add(Restrictions.between("tip_match_time", sub.getStart_date(), sub.getEnd_date()));
				if (criteria.list().size() < 1) {
					session.delete(sub);
				} else {
					sub.setSubscription_status("expired");
					session.update(sub);
				}

			}
			session.getTransaction().commit();
		}

		response.getWriter().println("success");
		session.close();

	}

}
