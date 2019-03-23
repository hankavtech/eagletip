package com.hankav.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hankav.dao.HibSessionFactory;
import com.hankav.dao.OddConversion;
import com.hankav.dao.TipPost;
import com.hankav.model.Tipster;

/**
 * Servlet implementation class TipsForm
 */

@WebServlet("/TipsForm")
public class TipsForm extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httsession = request.getSession();
		String username = (String) httsession.getAttribute("tipster_name");

		String datetime = request.getParameter("matchdate");
		System.out.println("date from sessio " + datetime);
		String timezone = (String) request.getSession().getAttribute("timezone");
		String team1 = request.getParameter("team1");
		String team2 = request.getParameter("team2");
		String market = request.getParameter("market");
		String lines = request.getParameter("lines");
		String sublines = request.getParameter("sublines");

		if (lines.equalsIgnoreCase("team1")) {
			lines = team1;
		} else if (lines.equalsIgnoreCase("team2")) {
			lines = team2;
		}

		String bookmaker = request.getParameter("bookmaker");
		String odds = request.getParameter("odds");
		if (request.getSession().getAttribute("odds").equals("FRACTIONAL")) {
			OddConversion conv = new OddConversion();
			odds = conv.fromFractionalToDecimal(odds).toString();

		} else if (request.getSession().getAttribute("odds").equals("AMERICAN")) {
			OddConversion conv = new OddConversion();
			odds = conv.fromAmericanToDecimal(odds).toString();

		}
		String units = request.getParameter("units");
		String tipster_sport = (String) httsession.getAttribute("user_sport");
		String league = request.getParameter("league");
		String tournament = request.getParameter("tournament");
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Tipster.class);
		criteria.add(Restrictions.eq("tipster_name", username));
		criteria.setProjection(Projections.property("tipster_category"));
		String tip_category = criteria.uniqueResult().toString();
		session.close();
		String match_id = request.getParameter("matchid");
		TipPost tipPost = new TipPost();
		String poststatus = null;
		try {
			poststatus = tipPost.insertion(league, tournament, datetime, match_id, team1, team2, market, lines,
					sublines, bookmaker, odds, units, username, tipster_sport, tip_category, timezone);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (poststatus.equalsIgnoreCase("failed")) {
			Cookie ck = new Cookie("tipstatus", "failed");
			response.addCookie(ck);
			response.sendRedirect("https://www.eagletip.com/matches");
			return;
		}
		Cookie ck = new Cookie("tipstatus", "success");
		response.addCookie(ck);
		response.sendRedirect("https://www.eagletip.com/matches");
	}

}
