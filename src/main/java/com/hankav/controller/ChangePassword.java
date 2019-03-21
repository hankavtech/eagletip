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
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.hankav.dao.HibSessionFactory;
import com.hankav.model.User;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		System.out.println("email to be changed"+email);
		System.out.println("pass"+ password);
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();
		session.beginTransaction();
		Criteria criteria=session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email",email.trim()));
		User user=null;
		user=(User) criteria.uniqueResult();
		if(user!=null) {
			String hashpw = BCrypt.hashpw(password,BCrypt.gensalt());
			user.setPassword(hashpw);
			session.update(user);
			System.out.println("hashed pass is"+hashpw);
			session.getTransaction().commit();
		}
		else {
			System.out.println("cannot get null muser");
		}
		
		
		
		session.close();
		response.getWriter().println("success");
	}

}
