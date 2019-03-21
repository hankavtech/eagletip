package com.hankav.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hankav.dao.GetTipDetails;

/**
 * Servlet implementation class GoToTipDetailsPage
 */
@WebServlet("/tip")
public class GoToTipDetailsPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tip_id=request.getParameter("id");
		GetTipDetails tip=new GetTipDetails();
		List<Object[]>tipdetails=tip.byId(tip_id);
		request.setAttribute("tipdetails",tipdetails);
		request.getRequestDispatcher("tipdetail.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
