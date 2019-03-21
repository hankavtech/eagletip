package com.hankav.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hankav.dao.GetUserByProperty;
import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Chat;

/**
 * Servlet implementation class SingleChatSubmit
 */
@WebServlet("/SingleChatSubmit")
public class SingleChatSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messagestring = request.getParameter("messagestring");
		String username = (String) request.getSession().getAttribute("username");
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Chat chat = new Chat();
		try {
			chat.setPostdate(sdf1.parse(sdf.format(new Date())));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chat.setUser(new GetUserByProperty().byName(username));
		chat.setChatstring(messagestring);
		session.save(chat);
		session.getTransaction().commit();
		session.close();

	}

}
