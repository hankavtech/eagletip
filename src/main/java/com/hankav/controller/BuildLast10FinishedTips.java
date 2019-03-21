package com.hankav.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hankav.dao.GiveCountTenTips;

/**
 * Servlet implementation class BuildLast10ActivePaidTips
 */
@WebServlet("/BuildLast10FinishedTips")
public class BuildLast10FinishedTips extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pagerec=request.getParameter("page");
		int page=Integer.valueOf(pagerec);
		String category=request.getParameter("category");
		
		try {
			response.getWriter().println(new GiveCountTenTips().getLast10FinishedTips(page,category));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
