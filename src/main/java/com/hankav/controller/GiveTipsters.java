package com.hankav.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hankav.dao.GetAllTipsters;

/**
 * Servlet implementation class GiveTipsters
 */
@WebServlet("/GiveTipsters")
public class GiveTipsters extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException {
		// TODO Auto-generated method stub
		String category = request.getParameter("category");
		System.out.println(category);
		if (category == null || category.equalsIgnoreCase("all") || category.equalsIgnoreCase("")) {
			category = "paid";
		}

		String[] sports;
		sports = request.getParameterValues("sport[]");
		System.out.println(sports);
		if ((sports == null) || (sports[0].equals(""))) {
			sports = new String[] { "all" };

		}

		String pagestr = request.getParameter("page");
		if (pagestr == null) {
			pagestr = "1";
		}
		Integer page = Integer.parseInt(pagestr);
		String order = request.getParameter("order");
		if (order == null) {
			order = "name";
		}

		if (order.equalsIgnoreCase("name")) {
			response.getWriter().write(new GetAllTipsters().ByCategoryAndOrderByName(category, sports, page));
		} else if (order.equalsIgnoreCase("profit")) {
			response.getWriter().write(new GetAllTipsters().ByCategoryAndOrderByProfit(category, sports, page));
		} else if (order.equalsIgnoreCase("winpercentage")) {
			response.getWriter().write(new GetAllTipsters().ByCategoryAndOrderByWinPercentage(category, sports, page));
		} else if (order.equalsIgnoreCase("tips")) {
			response.getWriter().write(new GetAllTipsters().ByCategoryAndOrderByTips(category, sports, page));
		} else if (order.equalsIgnoreCase("yield")) {
			response.getWriter().write(new GetAllTipsters().ByCategoryAndOrderByYield(category, sports, page));
		}

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
