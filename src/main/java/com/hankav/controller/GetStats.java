package com.hankav.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hankav.dao.TipstersStats;
import com.hankav.model.Tipster;

/**
 * Servlet implementation class GetStats
 */
@WebServlet("/GetStats")
public class GetStats extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		TipstersStats stats = new TipstersStats();
		Map<Tipster, Map<String, Object>> statmap = stats.getStats();
		request.getSession().setAttribute("statobject", statmap);
		Set<Tipster> tipsters = (Set<Tipster>) statmap.keySet();
		for (Tipster tipster : tipsters) {
			System.out.print("the twins" + statmap.get(tipster).get("l5ids").toString());
		}
		response.sendRedirect("tipstersstats.jsp");
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
