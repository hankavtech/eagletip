package com.hankav.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.google.gson.Gson;
import com.hankav.model.Chat;
import com.hankav.model.User;

public class ChatMsg {
	public void insert(String username, String chatstring) {
		User user = new LoggedOnUser().getUser(username);
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Chat chat = new Chat();
		chat.setChatstring(chatstring);
		chat.setUser(user);
		session.save(chat);
		session.getTransaction().commit();
		session.close();
	}

	public String getLastTen(int start, int max) {
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("FROM Chat");
		Criteria criteria = session.createCriteria(Chat.class);
		criteria.setFirstResult(start * 10);
		criteria.setMaxResults(max);
		criteria.createAlias("user", "user");
		criteria.setProjection(Projections.projectionList().add(Projections.property("id"))
				.add(Projections.property("user.username")).add(Projections.property("chatstring")));
		criteria.addOrder(Order.desc("id"));
		List<Object[]> chatlist = criteria.list();
		String chats = new Gson().toJson(chatlist);
		session.close();
		return chats;
	}

}
