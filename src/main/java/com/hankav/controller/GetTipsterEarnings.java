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
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Tipster;

/**
 * Servlet implementation class GetTipsterEarnings
 */

@WebServlet("/tipsterpersonal/earnings")
public class GetTipsterEarnings extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();
		session.beginTransaction();
		Criteria criteria=session.createCriteria(Tipster.class);
		criteria.add(Restrictions.eq("tipster_name",request.getSession().getAttribute("tipster_name")));
        criteria.setProjection(Projections.property("paymentemail"));
        String paymentemail=criteria.uniqueResult().toString();
        if(paymentemail!=null) {
        	request.setAttribute("paymentemail",paymentemail);
        }
		
        Query query=session.createQuery("select year(start_date),date_format(start_date,'%b'),count(*),sum(case when tipster_paymentmade=?3 then 1 else 0 end),sum(subscription_price),sum(case when tipster_paymentmade=?3 then subscription_price else 0 end) from Subscription  where subscription_type=?1 and subscribed_tipster.tipster_name=?2 group by YEAR(start_date), MONTH(start_date) order by start_date desc");
		query.setParameter(1,"paid");
		query.setParameter(2,request.getSession().getAttribute("tipster_name"));
		query.setParameter(3,true);
		List<Object[]>monthlypayments=query.list();
		for(Object[] monthpay:monthlypayments) {
			System.out.print(monthpay[0].toString()+" "+monthpay[1].toString()+" "+monthpay[2].toString()+" "+monthpay[3].toString()+" "+monthpay[4].toString());
			System.out.println();
		}
        request.setAttribute("monthlypays",monthlypayments);
        
		request.getRequestDispatcher("tipster-earnings.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
