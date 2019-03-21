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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Subscription;

/**
 * Servlet implementation class GiveSubscriptionsList
 */
@WebServlet("/mysubscriptions")
public class GiveSubscriptionsList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = (String) request.getSession().getAttribute("username");
		if (username == null) {
			response.getWriter().println("Please log in");
			return;
		}

		String status = request.getParameter("status");
		String subtype = request.getParameter("subtype");

		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Subscription.class);
		criteria.createAlias("subscriber", "user");
		criteria.createAlias("subscribed_tipster", "tipster");
		criteria.add(Restrictions.eq("user.username", "test"));
		if (subtype != null) {
			criteria.add(Restrictions.eq("subscription_type", subtype));
		}
		if (status != null) {
			criteria.add(Restrictions.eq("subscription_status", status));
		}
		criteria.setProjection(Projections.projectionList().add(Projections.property("subscription_id"))
				.add(Projections.property("tipster.tipster_name")).add(Projections.property("start_date"))
				.add(Projections.property("end_date")).add(Projections.property("subscription_price")));
		criteria.addOrder(Order.desc("start_date"));
		List<Object[]> subs = criteria.list();
		for (Object[] sub : subs) {
			System.out.print(sub[0] + " " + sub[1] + " " + sub[2] + " " + sub[3] + " " + sub[4]);
			System.out.println("");
		}
		session.close();
		request.setAttribute("subscriptions", subs);
		request.getRequestDispatcher("clientsubscriptions.jsp").forward(request, response);

	}

}
