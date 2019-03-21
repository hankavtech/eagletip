package com.hankav.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Subscription;
import com.hankav.model.Tip;

/**
 * Servlet implementation class GetAllTipsForSubscription
 */
@WebServlet("/mytips")
public class GetAllTipsForSubscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Check if user has logged in
		String username = (String) request.getSession().getAttribute("username");
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		Integer intpage = Integer.parseInt(page);
		Integer sub = Integer.parseInt(request.getParameter("sub"));
		if (username == null) {
			response.getWriter().println("Please Log in");
			return;
		}
		// check if it is a cross site request and the user is subscribed for that
		// particular subscription id

		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Subscription.class);
		criteria.createAlias("subscriber", "user");
		criteria.add(Restrictions.eq("subscription_id", sub));
		criteria.add(Restrictions.eq("user.username", username));
		Subscription subscription = (Subscription) criteria.uniqueResult();
		if (subscription == null) {
			response.getWriter().println("You do not have access to this subscription");
			return;
		}

		Date end = subscription.getEnd_date();
		Date start = subscription.getStart_date();
		String tipstername = subscription.getSubscribed_tipster().getTipster_name();
		String sportname = subscription.getSubscribed_tipster().getTipster_sport().getName();
		System.out.println("tipster is" + tipstername);
		System.out.println("sport is" + sportname);
		criteria = session.createCriteria(Tip.class);
		criteria.createAlias("tipster", "tipster");
		criteria.createAlias("tip_sport", "sport");
		criteria.add(Restrictions.eq("tipster.tipster_name", tipstername));
		criteria.add(Restrictions.between("tip_match_time", start, end));
		criteria.add(Restrictions.eq("status", "finished"));
		criteria.setFirstResult((intpage - 1) * 10 + 0);
		criteria.setMaxResults(10);
		criteria.setProjection(
				Projections.projectionList().add(Projections.property("tip_id")).add(Projections.property("sport.name"))
						.add(Projections.property("team1")).add(Projections.property("team2"))
						.add(Projections.property("tip_match_time")).add(Projections.property("tip_market"))
						.add(Projections.property("tip_lines")).add(Projections.property("tip_sublines"))
						.add(Projections.property("tip_units")).add(Projections.property("tip_odds"))
						.add(Projections.property("tip_bookmaker")).add(Projections.property("tip_profit"))
						.add(Projections.property("tip_result")).add(Projections.property("tipscore")));

		List<Object[]> tips = (List<Object[]>) criteria.list();
		System.out.println("size is" + tips.size());

		for (Object[] obj : tips) {
			System.out.println(obj[2].toString() + " vs " + obj[3].toString());
		}

		request.setAttribute("tipstername", tipstername);
		request.setAttribute("sportname", sportname);

		criteria = session.createCriteria(Subscription.class);
		criteria.createAlias("subscriber", "user");
		criteria.createAlias("subscribed_tipster", "tipster");
		criteria.add(Restrictions.eq("user.username", username));
		criteria.addOrder(Order.desc("start_date"));
		criteria.setProjection(Projections.projectionList().add(Projections.property("subscription_id"))
				.add(Projections.property("tipster.tipster_id")).add(Projections.property("tipster.tipster_name"))
				.add(Projections.property("start_date")).add(Projections.property("subscription_status")));
		List<Object[]> subs = criteria.list();

		request.setAttribute("subs", subs);

		Query query = session.createQuery(
				"select count(*),sum(tip_profit),avg(tip_odds),sum(case when tip_result=?1 then 1 else 0 end),sum(case when tip_result=?2 then 1 else 0 end),sum(case when tip_result=?3 then 1 else 0 end) from Tip where tipster.tipster_name=?4 and tip_match_time between ?5 and ?6 and status=?7");
		query.setParameter(1, "won");
		query.setParameter(2, "lost");
		query.setParameter(3, "void");
		query.setParameter(4, tipstername);
		query.setParameter(5, start);
		query.setParameter(6, end);
		query.setParameter(7, "finished");

		List<Object[]> substats = query.list();
		request.setAttribute("substats", substats);
		request.setAttribute("page", intpage);
		request.setAttribute("sub", sub);
		request.setAttribute("tips", tips);
		session.getTransaction().commit();
		session.close();

		request.getRequestDispatcher("alltipsofclient.jsp").forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
