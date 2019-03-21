package com.hankav.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Preferences;
import com.hankav.model.User;

/**
 * Servlet implementation class UpdateUserOdds
 */
@WebServlet("/UpdateUserOdds")
public class UpdateUserOdds extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	  
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doPost(request, response);
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			String odds=request.getParameter("odds");
			String pageredirect=request.getParameter("pageredirect");
			SessionFactory factory=HibSessionFactory.getFactory();
			Session session=factory.openSession();
			session.beginTransaction();
			String email=(String) request.getSession().getAttribute("email");
			Criteria criteria=session.createCriteria(User.class);
			criteria.add(Restrictions.eq("email",email));
			User user=(User) criteria.uniqueResult();
			Preferences pref=user.getUser_preferences();
			user.setUser_preferences(new Preferences(odds,pref.getTimezone(),pref.getCurrency()));
			session.update(user);
			session.getTransaction().commit();
			session.close();
			request.getSession().setAttribute("odds",odds);
			response.sendRedirect(pageredirect);
		}
}
