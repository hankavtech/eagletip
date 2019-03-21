package com.hankav.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hankav.dao.GetPersonalTipsterFirstPage;

/**
 * Servlet implementation class GetPersonalTipsterActiveTips
 */
@WebServlet("/tipsterpersonal/activetips")
public class GetPersonalTipsterActiveTips extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tipster_name = (String) request.getSession().getAttribute("tipster_name");
		if (request.getSession().getAttribute("username") == null) {
			response.getWriter().println("Please Log in");
			return;
		}
		if (tipster_name == null) {
			response.getWriter().println("Please Switch to Tipster account");
			return;
		}

		List<List<Object[]>> list = null;
		try {
			list = new GetPersonalTipsterFirstPage().getActiveAndLast10Tips(tipster_name);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("firstpagelist", list);
		request.getRequestDispatcher("tipsterpersonalactive.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
