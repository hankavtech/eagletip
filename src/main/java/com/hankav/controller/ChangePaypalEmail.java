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
import com.hankav.model.Tipster;

/**
 * Servlet implementation class ChangePaypalEmail
 */
@WebServlet("/ChangePaypalEmail")
public class ChangePaypalEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("tipster_name").equals(null)){
			response.getWriter().println("Please switch to your tipster account");
		}
		String paymentemail=request.getParameter("paymentemail");
		
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();
		session.beginTransaction();
		Criteria criteria=session.createCriteria(Tipster.class);
		criteria.add(Restrictions.eq("tipster_name",request.getSession().getAttribute("tipster_name")));
        Tipster tipster=(Tipster) criteria.uniqueResult();
        tipster.setPaymentemail(paymentemail);
		session.update(tipster);
		session.getTransaction().commit();
	    response.sendRedirect("https://www.eagletip.com/tipsterpersonal/earnings");	
		
	}

}
