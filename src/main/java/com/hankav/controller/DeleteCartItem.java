package com.hankav.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.hankav.model.Item;
import com.hankav.model.User;

/**
 * Servlet implementation class DeleteCartItem
 */
@WebServlet("/DeleteCartItem")
public class DeleteCartItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = (String) request.getSession().getAttribute("username");
		String itemname = request.getParameter("itemname");
		System.out.println("iamtobedeleted " + itemname);
		System.out.println("param is" + itemname);
		System.out.println(request.getSession().getAttribute("cartitems"));
		List<Object[]> items = (List<Object[]>) request.getSession().getAttribute("cartitems");
		Iterator<Object[]> iter = items.iterator();

		while (iter.hasNext()) {
			Object[] str = iter.next();

			if (str[0].equals(itemname)) {
				iter.remove();
			}
		}
		request.getSession().setAttribute("cartitems", items);
		if (username != null) {
			// update user carttitems
			// save it to database
			SessionFactory factory = HibSessionFactory.getFactory();
			Session session = factory.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("username", username));
			User user = (User) criteria.uniqueResult();
			Set<Item> retrieveditems = (Set<Item>) user.getCartitems();
			Long item_id = null;
			/*
			 * for(Item item:retrieveditems) { if(item.getName().equalsIgnoreCase(itemname))
			 * { retrieveditems.remove(item); item_id=item.getId(); Item
			 * loitem=session.load(Item.class,item_id); session.delete(loitem); } }
			 */
			Item str = null;
			Iterator itr = retrieveditems.iterator();
			while (itr.hasNext()) {
				str = (Item) itr.next();
				if (str.getName().equalsIgnoreCase(itemname)) {
					item_id = str.getId();
					break;
				}
			}
			Item loitem = session.get(Item.class, item_id);
			retrieveditems.remove(str);
			session.delete(loitem);
			user.setCartitems(retrieveditems);
			session.update(user);
			session.getTransaction().commit();
			session.close();
		}
		response.getWriter().write("deleted");

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
