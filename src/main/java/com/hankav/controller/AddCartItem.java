package com.hankav.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Item;
import com.hankav.model.User;

/**
 * Servlet implementation class UpdateCartItems
 */
@WebServlet("/AddCartItem")
public class AddCartItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = (String) request.getSession().getAttribute("username");
		String itemname = request.getParameter("itemname");
		System.out.println(itemname);
		String itemprice = request.getParameter("itemprice");
		System.out.println("item price is" + itemprice);
		String itemmonths = request.getParameter("itemmonths");
		List<Object[]> items = new ArrayList<Object[]>();
		if (request.getSession().getAttribute("cartitems") != null) {
			items.addAll((List<Object[]>) request.getSession().getAttribute("cartitems"));
		}
		if (items.size() > 0) {
			for (Object[] item : items) {
				System.out.println("item already is" + item[0]);
				if (item[0].equals(itemname)) {
					response.getWriter().write("error");
					return;
				}
			}
		}
		Object[] toadd = new Object[] { itemname, itemprice, itemmonths };
		items.add(toadd);
		request.getSession().setAttribute("cartitems", items);
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Item item = new Item();
		item.setName(itemname);
		item.setPrice(Double.parseDouble(itemprice));
		item.setMonths(Integer.parseInt(itemmonths));
		item.setUser(null);
		if (username != null) {

			// update user carttitems
			// save it to database

			session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("username", username));
			User user = (User) criteria.uniqueResult();
			item.setUser(user);
			user.getCartitems().add(item);

			session.save(item);
			session.update(user);
			session.getTransaction().commit();

		}
		session.close();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		response.getWriter().write(gson.toJson(item));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
