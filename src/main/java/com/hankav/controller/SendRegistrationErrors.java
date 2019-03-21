package com.hankav.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

import com.hankav.dao.HibSessionFactory;
import com.hankav.model.User;

/**
 * Servlet implementation class SendRegistrationErrors
 */
@WebServlet("/SendRegistrationErrors")
public class SendRegistrationErrors extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String fieldtobechecked = null;
		String field = null;
		if (username == null) {
			fieldtobechecked = "email";
			field = email;
		} else {
			fieldtobechecked = "username";
			field = username;
		}
		Criteria criteria = session.createCriteria(User.class);
		criteria.setProjection(Projections.property(fieldtobechecked));
		List<String> names = criteria.list();
		if (names.contains(field)) {
			response.getWriter().print("already");
			session.close();
			return;
		}
		response.getWriter().print("goahead");
		session.clear();
		session.close();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}
}
