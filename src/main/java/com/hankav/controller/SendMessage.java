package com.hankav.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Message;

/**
 * Servlet implementation class SendMessage
 */
@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String contacter=request.getParameter("contacter");
		String contacteremail=request.getParameter("contacteremail");
		String subject=request.getParameter("subject");
		String content=request.getParameter("contactcontent");
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();
		session.beginTransaction();
		Message message=new Message();
		message.setUsername(contacter);
		message.setEmail(contacteremail);
		message.setSubject(subject);
		message.setContent(content);
		session.save(message);
		session.getTransaction().commit();
		session.close();
		response.getWriter().println("done");
	}

}
