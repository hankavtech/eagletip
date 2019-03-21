package com.hankav.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.hankav.dao.GetAllTipstersOfUser;
import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Item;
import com.hankav.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String hashedpass;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("SELECT password FROM User WHERE username=?1");
		query.setParameter(1, username);
		hashedpass = (String) query.uniqueResult();
		if (hashedpass == null) {
			System.out.print("You are not registered" + ".Please Register");
			session.close();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("nouser");
			return;
		}

		boolean authenticate = BCrypt.checkpw(password, hashedpass);
		if (authenticate) {
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("username", username));
			if (request.getSession().getAttribute("cartitems") != null) {
				User user = (User) criteria.uniqueResult();
				List<Object[]> preitems = null;
				user.getCartitems().clear();
				preitems = new ArrayList<Object[]>();
				preitems.addAll((List<Object[]>) request.getSession().getAttribute("cartitems"));
				if (preitems.size() > 0) {
					for (Object[] item : preitems) {
						Item preitem = new Item();
						preitem.setName(item[0].toString());
						preitem.setPrice(Double.parseDouble(item[1].toString()));
						preitem.setMonths(Integer.parseInt(item[2].toString()));
						preitem.setUser(user);
						user.getCartitems().add(preitem);
						session.save(preitem);
					}
				}
				session.update(user);

				request.getSession().setAttribute("email", user.getEmail());
				request.getSession().setAttribute("username", user.getUsername());
				request.getSession().setAttribute("currency", user.getUser_preferences().getCurrency());
				request.getSession().setAttribute("odds", user.getUser_preferences().getOddsformat());
				request.getSession().setAttribute("timezone", user.getUser_preferences().getTimezone());

			}

			else {

				criteria.setProjection(Projections.projectionList().add(Projections.property("email"))
						.add(Projections.property("user_preferences.currency"))
						.add(Projections.property("user_preferences.oddsformat"))
						.add(Projections.property("user_preferences.timezone")));
				List<Object[]> usersettings = criteria.list();
				criteria.createAlias("cartitems", "cartitems");
				criteria.setProjection(Projections.projectionList().add(Projections.property("cartitems.name"))
						.add(Projections.property("cartitems.price")).add(Projections.property("cartitems.months")));
				List<Object[]> items = criteria.list();
				request.getSession().setAttribute("email", usersettings.get(0)[0]);
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("currency", usersettings.get(0)[1]);
				request.getSession().setAttribute("odds", usersettings.get(0)[2]);
				request.getSession().setAttribute("timezone", usersettings.get(0)[3]);
				request.getSession().setAttribute("cartitems", items);

			}
			session.close();
			response.setCharacterEncoding("UTF-8");
			Map<String, String> map = new GetAllTipstersOfUser().byUserName(username);
			if (map.size() != 0) {
				request.getSession().setAttribute("num_of_tipsters", "not_empty");
			} else {
				request.getSession().setAttribute("num_of_tipsters", "s_empty");
			}
			request.getSession().setAttribute("tipstersmap", map);
			request.getSession().setAttribute("user_type", "user");
			request.getSession().setAttribute("user_sport", "");
			response.getWriter().write("loggedin");
			return;
		} else {
			session.close();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("passerror");
		}

	}

}
