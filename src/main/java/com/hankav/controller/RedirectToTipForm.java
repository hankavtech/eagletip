package com.hankav.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RedirectToTipForm
 */
@WebServlet("/matches")
public class RedirectToTipForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=(String) request.getSession().getAttribute("username");
		System.out.println(request.getHeader("referer").toString());
		if(request.getHeader("referer").toString().equals("https://www.eagletip.com/matches")) {
		Cookie[]cookies=request.getCookies();
		String tipstatus=null;
		for(Cookie c:cookies) {
			if(c.getName().toString().equals("tipstatus")){
				tipstatus=c.getValue();
				break;
			}
		}
		request.setAttribute("tipstatus",tipstatus);
		
		}
		if(username==null) {
			response.getWriter().print("Please Log in");
			return;
		}
		String tipstername=(String) request.getSession().getAttribute("tipster_name");
		if(tipstername==null) {
			response.getWriter().println("Please switch to Tipster account");
			return;
		}
		System.out.println(request.getSession().getAttribute("tipster_name"));
		System.out.println(request.getSession().getAttribute("username"));
		System.out.println(request.getSession().getAttribute("num_of_tipsters"));
		System.out.println(request.getSession().getAttribute("user_type"));
		request.getRequestDispatcher("matches-test.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
