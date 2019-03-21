package com.hankav.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hankav.dao.GetTopPortionStatsForTipsterPage;

/**
 * Servlet implementation class TipsterStatsPage
 */
@WebServlet("/tipster/stats")
public class TipsterStatsPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tipster_name = request.getParameter("name");
		GetTopPortionStatsForTipsterPage topstats = new GetTopPortionStatsForTipsterPage();
		List<Object[]> toplist = null;
		try {
			toplist = topstats.giveStats(tipster_name);
			Integer size = toplist.get(0).length - 1;
			if (toplist.get(0)[size].toString().equalsIgnoreCase("free")) {
				request.setAttribute("category", "free");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {

		}
		request.setAttribute("toplist", toplist);
		request.setAttribute("tipstername", tipster_name);
		request.getRequestDispatcher("detailsoftipsterstats.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
