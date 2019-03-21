package com.hankav.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hankav.dao.GetDataForTipsterProfileGraphs;

/**
 * Servlet implementation class GetDataForPieCharts
 */
@WebServlet("/GetDataForPieCharts")
public class GetDataForPieCharts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tipster_name = request.getParameter("name");
		List<String> list = new GetDataForTipsterProfileGraphs().ByTipsterName(tipster_name);
		response.setCharacterEncoding("UTF-8");
		String jsonString = new Gson().toJson(list);
		response.getWriter().write(jsonString);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
