package com.hankav.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hankav.dao.GiveCountTenTips;
import com.hankav.dao.getTipsterRatings;

/**
 * Servlet implementation class buildRankingContent
 */
@WebServlet("/")
public class BuildMainPageForFreeTips extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Map<Object, Object>> lpaidmap = new getTipsterRatings().byProfitAndCategory("paid");
		List<Map<Object, Object>> lfreemap = new getTipsterRatings().byProfitAndCategory("free");
		List<Map<Object, Object>> freeactivetips = null;
		List<Map<Object, Object>> freefinishedtips = null;
		try {
			freeactivetips = new GiveCountTenTips().getLast10ActiveTips(1, "free");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			freefinishedtips = freefinishedtips = new GiveCountTenTips().getLast10FinishedTips(1, "free");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("toppaidtipsters", lpaidmap);
		request.setAttribute("topfreetipsters", lfreemap);
		request.setAttribute("freeactivetips", freeactivetips);
		request.setAttribute("freefinishedtips", freefinishedtips);
		request.getRequestDispatcher("freetips10.jsp").forward(request, response);
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
