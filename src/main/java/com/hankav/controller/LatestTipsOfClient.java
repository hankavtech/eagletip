package com.hankav.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hankav.dao.GetAllActiveTipsForCustomer;

/**
 * Servlet implementation class LatestTipsOfClient
 */
@WebServlet("/myactivetips")
public class LatestTipsOfClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String username=(String) request.getSession().getAttribute("username");
	        if(username==null) {
	        	response.getWriter().println("Please Log in");
	        	return;
	        }
	    	GetAllActiveTipsForCustomer activetipsforuser=new GetAllActiveTipsForCustomer();
			List<Object[]> tips = null;
			try {
				tips = activetipsforuser.getTips(username);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("activeuserlist",tips);
		request.getRequestDispatcher("recenttipsofclient.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
