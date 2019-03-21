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
import com.hankav.model.User;

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uuid=request.getParameter("uuid");
		String email=request.getParameter("email");
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();
		session.beginTransaction();
		Criteria criteria=session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email",email));
		User user=(User) criteria.uniqueResult();
		String flink=user.getForgotlink();
		user.setForgotlink(null);
		session.update(user);
		session.getTransaction().commit();
		session.close();
		if(flink==null) {
			request.setAttribute("resetstatus","failure");
			request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
			return;
		}
		if(flink.trim().equals(uuid.trim())) {
			System.out.println("successfully verified token");
			request.setAttribute("resetstatus","success");
		}
		else {
			System.out.println("could not verify token token");
			request.setAttribute("resetstatus","failure");
		}
		
		request.setAttribute("email",email);
		request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
