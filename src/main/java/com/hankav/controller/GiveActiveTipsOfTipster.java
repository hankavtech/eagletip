package com.hankav.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hankav.dao.GetTipsForPersonalPageOfTipster;

/**
 * Servlet implementation class GiveActiveTipsOfTipster
 */
@WebServlet("/My_Tips")
public class GiveActiveTipsOfTipster extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tipster_name=(String)request.getSession().getAttribute("tipster_name");
		if(tipster_name==null) {
			response.getWriter().println("Please login");
		    return;
		}
		GetTipsForPersonalPageOfTipster ptips=new GetTipsForPersonalPageOfTipster();
		List<Object[]>tips=null;
		try {
			tips=ptips.byTipsterName(tipster_name);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("activetipsoftipster",tips);
		request.getRequestDispatcher("tipsterpersonal.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
