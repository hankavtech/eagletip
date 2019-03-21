package com.hankav.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hankav.model.Subscription;

public class GetAllActiveSubscriptions {
	
	public List<List<Object[]>> byUsername(String username) {
		
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();
		Criteria criteria=session.createCriteria(Subscription.class);
		criteria.createAlias("subscriber","user");
		criteria.createAlias("subscribed_tipster","tipster");
		criteria.add(Restrictions.eq("user.username",username));
		criteria.add(Restrictions.eq("subscription_status","active"));
		criteria.setProjection(Projections.projectionList().add(Projections.property("subscription_id")).add(Projections.property("tipster.tipster_name")).add(Projections.property("start_date")).add(Projections.property("end_date")).add(Projections.property("subscription_price")));
		List<Object[]>actsubs=criteria.list();
		
		Criteria criteria1=session.createCriteria(Subscription.class);
		criteria1.createAlias("subscriber","user");
		criteria1.createAlias("subscribed_tipster","tipster");
		criteria1.add(Restrictions.eq("user.username",username));
		criteria1.add(Restrictions.eq("subscription_status","expired"));
		criteria1.setProjection(Projections.projectionList().add(Projections.property("subscription_id")).add(Projections.property("tipster.tipster_name")).add(Projections.property("start_date")).add(Projections.property("end_date")).add(Projections.property("subscription_price")));
		List<Object[]>expsubs=criteria1.list();
		
		Criteria criteria2=session.createCriteria(Subscription.class);
		criteria2.createAlias("subscriber","user");
		criteria2.createAlias("subscribed_tipster","tipster");
		criteria2.add(Restrictions.eq("user.username",username));
		criteria2.add(Restrictions.eq("subscription_status","paused"));
		criteria2.setProjection(Projections.projectionList().add(Projections.property("subscription_id")).add(Projections.property("tipster.tipster_name")).add(Projections.property("start_date")).add(Projections.property("end_date")).add(Projections.property("subscription_price")));
		List<Object[]>pausubs=criteria2.list();
		session.close();
		return Arrays.asList(actsubs,expsubs,pausubs);
		
	}

}
