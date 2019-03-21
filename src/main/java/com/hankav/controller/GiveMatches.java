package com.hankav.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hankav.results.GetMatches;

/**
 * Servlet implementation class GiveLeagues
 */
@WebServlet("/GiveMatches")
public class GiveMatches extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GetMatches tournaments = new GetMatches();
		String json = null;
		try {
			json = tournaments.byTournament(request.getParameter("tournament"), request.getParameter("league"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}
