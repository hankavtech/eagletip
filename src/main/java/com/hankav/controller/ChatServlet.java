package com.hankav.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hankav.dao.ChatMsg;

/**
 * Servlet implementation class Chat
 */
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int start = Integer.parseInt(request.getParameter("start"));
		int max = Integer.parseInt(request.getParameter("max"));
		String chats = new ChatMsg().getLastTen(start, max);
		response.getWriter().write(chats);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
