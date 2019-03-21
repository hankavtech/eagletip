package com.hankav.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hankav.dao.GetTipsForPersonalPageOfTipster;
import com.hankav.dao.GetTopPortionStatsForTipsterPage;

/**
 * Servlet implementation class GetAllTipsterTipsForHisPersonalPage
 */
public class GetAllTipsterTipsForHisPersonalPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		String tipster_name = request.getParameter("name");
		GetTopPortionStatsForTipsterPage top = new GetTopPortionStatsForTipsterPage();
		List<Object[]> topstats = null;
		List<Object[]> tips = null;
		try {
			topstats = top.giveStats(tipster_name);
			Integer size = topstats.get(0).length - 1;
			if (topstats.get(0)[size].toString().equalsIgnoreCase("free")) {
				System.out.println("checking what it is" + topstats.get(0)[size].toString());
				request.setAttribute("category", "free");
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {

		}
		GetTipsForPersonalPageOfTipster gtips = new GetTipsForPersonalPageOfTipster();
		try {
			tips = gtips.getAllTips(tipster_name, page);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("topstats", topstats);
		request.setAttribute("tips", tips);
		request.setAttribute("tipstername", tipster_name);
		System.out.println(new Gson().toJson(topstats));
		request.getRequestDispatcher("/tipster/detailsoftipster_alltips.jsp").forward(request, response);

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
