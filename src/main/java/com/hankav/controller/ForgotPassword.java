package com.hankav.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.hankav.dao.ForgotEmailSender;
import com.hankav.dao.HibSessionFactory;
import com.hankav.model.User;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		User user = null;
		user = (User) criteria.uniqueResult();
		if (user == null) {
			response.getWriter().print("noemailregistered");
			return;
		}
		final String uuid = UUID.randomUUID().toString().replace("-", "");
		user.setForgotlink(uuid);
		session.update(user);
		session.getTransaction().commit();
		ForgotEmailSender remail = new ForgotEmailSender();
		try {
			remail.sendemail(email, uuid);
		} catch (AddressException e) {
			response.getWriter().print("nonetwork");
			e.printStackTrace();
		} catch (ParseException e) {
			response.getWriter().print("nonetwork");
			e.printStackTrace();
		}

		session.close();
		response.getWriter().println("emailsent");

	}

}
