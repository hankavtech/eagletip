package com.hankav.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hankav.dao.GetAllTipsByCategory;

/**
 * Servlet implementation class GetAllTipsterTipsForHisPersonalPage
 */

@WebServlet("/tips")
public class GetAllTipsByCategoryAndPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		String category = request.getParameter("category");
		String[] sports;
		sports = request.getParameterValues("s1[]");
		System.out.println(sports);
		if ((sports == null) || (sports[0].equals(""))) {
			sports = new String[] { "all" };

		}

		if (category == null) {
			category = "paid";
		}

		List<Object[]> tips = null;
		GetAllTipsByCategory gtips = new GetAllTipsByCategory();
		try {
			tips = gtips.getAllTips(category, page, sports);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long tipsofcategory = null;
		try {
			tipsofcategory = gtips.getCountOfCategoryTips(category, sports);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> sporty = gtips.getDistinctSportname(category);
		request.setAttribute("sports", sporty);
		request.setAttribute("selsport", sports);
		request.setAttribute("category", category);
		request.setAttribute("tips", tips);
		request.setAttribute("tipsofcategory", tipsofcategory);
		request.getRequestDispatcher("alltips.jsp").forward(request, response);

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
