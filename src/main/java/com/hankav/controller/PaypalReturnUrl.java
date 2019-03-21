package com.hankav.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hankav.dao.GetTipsterByProperty;
import com.hankav.dao.GetUserByProperty;
import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Subscription;
import com.hankav.model.Tipster;
import com.hankav.model.User;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

/**
 * Servlet implementation class PaypalReturnUrl
 */
@WebServlet("/PaypalReturnUrl")
public class PaypalReturnUrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String crunchifyID = "AevFN2sNV6izA3cHtRyKiZuveRl5NOA-6Y5yqsbGjBQUFsqmABrYRCiTuyzKVqzIv317gyYMWyRnrUEe";
	private static String crunchifySecret = "ENRBVwjtEhM5DNLdpGwlxHyAQGXE_mN5D7mky7lNMo8XLcfDFH31nWHE0aKdxqBTRGFV-xV7HRhTA_8D";

	private static String executionMode = "live";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		APIContext apiContext = new APIContext(crunchifyID, crunchifySecret, executionMode);
		Payment payment = new Payment();
		payment.setId(request.getParameter("paymentId"));

		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(request.getParameter("PayerID"));
		try {
			Payment createdPayment = payment.execute(apiContext, paymentExecution);
			System.out.println(createdPayment.getLastResponse());
			System.out.println(createdPayment.getState());
			if (createdPayment.getState().toString().equalsIgnoreCase("approved")) {
				SessionFactory factory = HibSessionFactory.getFactory();
				Session session = factory.openSession();
				session.beginTransaction();
				Criteria criteria = session.createCriteria(User.class);
				criteria.add(Restrictions.eq("username", request.getSession().getAttribute("username")));
				User user = (User) criteria.uniqueResult();
				Set<com.hankav.model.Item> puritems = (Set<com.hankav.model.Item>) user.getCartitems();
				Iterator itr = puritems.iterator();
				Tipster tipster = null;
				;
				while (itr.hasNext()) {
					com.hankav.model.Item item = (com.hankav.model.Item) itr.next();
					Double purprice = item.getPrice();
					Integer purmonths = item.getMonths();
					Subscription subscription = new Subscription(purmonths,
							new GetTipsterByProperty().byName(item.getName()),
							new GetUserByProperty().byName(request.getSession().getAttribute("username").toString()),
							purmonths * purprice);
					if (purprice > 0) {
						subscription.setSubscription_type("paid");
					} else {
						subscription.setSubscription_type("free");
					}

					// check if item is already in cart
					criteria = session.createCriteria(Subscription.class);
					criteria.createAlias("subscribed_tipster", "tipster");
					criteria.createAlias("subscriber", "user");
					criteria.add(Restrictions.eq("tipster.tipster_name", item.getName().trim()));
					criteria.add(
							Restrictions.eq("user.username", (String) request.getSession().getAttribute("username")));
					criteria.addOrder(Order.desc("end_date"));
					criteria.setMaxResults(1);
					criteria.setProjection(Projections.property("end_date"));
					List<Date> dates = criteria.list();
					Date restart_date = null;
					Date reend_date = null;
					if (dates.size() != 0) {
						restart_date = dates.get(0);
						System.out.println("restart date is" + restart_date);
						Calendar c1 = Calendar.getInstance();
						c1.setTime(restart_date);
						c1.add(Calendar.DATE, 30 * item.getMonths());
						reend_date = c1.getTime();
						System.out.println(reend_date);
					}

					if (restart_date == null) {
						SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						fm.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						System.out.println("FORMATTE DSTART DATE" + fm.format(new Date()).toString());
						restart_date = sdf.parse(fm.format(new Date()));

						Calendar c = Calendar.getInstance();
						c.setTime(new Date());
						c.add(Calendar.DATE, 30 * item.getMonths());
						reend_date = sdf.parse(fm.format(c.getTime()));
					}
					subscription.setStart_date(restart_date);
					subscription.setEnd_date(reend_date);

					session.save(subscription);
					com.hankav.model.Item loitem = session.get(com.hankav.model.Item.class, item.getId());
					session.delete(loitem);
					criteria = session.createCriteria(Tipster.class);
					criteria.add(Restrictions.eq("tipster_name", item.getName()));
					/* criteria.setProjection(Projections.property("mailsubscribers")); */
					tipster = (Tipster) criteria.uniqueResult();
					if (tipster.getMailsubscribers()
							.contains(request.getSession().getAttribute("email").toString().trim())) {

					} else {
						tipster.getMailsubscribers().add(request.getSession().getAttribute("email").toString().trim());
						session.update(tipster);
					}

				}
				user.setCartitems(null);
				session.update(user);
				session.getTransaction().commit();
				session.close();
				request.getSession().setAttribute("cartitems", null);
			}

		} catch (PayPalRESTException e) {
			System.out.println(e.getDetails());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("https://www.eagletip.com/paymentsuccess.html");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
