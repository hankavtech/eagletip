package com.hankav.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddClickedUserToSession extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usertype = request.getParameter("usertype");
		System.out.println("user type is" + usertype);
		if (usertype.equals("tipster")) {
			String usersport = request.getParameter("usersport");
			request.getSession().setAttribute("user_sport", usersport);
			request.getSession().setAttribute("user_type", "tipster");
			System.out.println("user type is" + usersport);
		} else {
			request.getSession().setAttribute("user_sport", "");
			request.getSession().setAttribute("user_sport", "user");
		}
		String username = request.getParameter("username");
		request.getSession().setAttribute("user_type", usertype);
		System.out.println("user name is" + username);
		if (usertype.equals("tipster")) {
			request.getSession().setAttribute("tipster_name", username);
			System.out.println("user type is" + usertype);
		} else {
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("tipster_name", null);
		}
		request.getRequestDispatcher("matches-test.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
