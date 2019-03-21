package com.hankav.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hankav.dao.GetStatsForFirstPage;

/**
 * Servlet implementation class GetTipsterFirstPage
 */
@WebServlet("/tipster/activetips")
public class GetTipsterFirstPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		System.out.println(name);
		List<List<Object[]>> list = null;
		try {
			list = new GetStatsForFirstPage().giveStats(name);

			Integer size = list.get(1).get(0).length - 1;
			if (list.get(1).get(0)[size].toString().equalsIgnoreCase("free")) {
				request.setAttribute("category", "free");
			}

			request.setAttribute("firstpagelist", list);

		} catch (Exception e) {

		}
		request.setAttribute("tipstername", name);
		request.getRequestDispatcher("details_oftipster.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
