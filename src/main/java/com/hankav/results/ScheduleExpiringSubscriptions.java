package com.hankav.results;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Subscription;

public class ScheduleExpiringSubscriptions implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		fm.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Subscription.class);
		criteria.createAlias("subscribed_tipster", "tipster");
		criteria.add(Restrictions.eq("subscription_status", "active"));
		criteria.add(Restrictions.eq("tipster.tipster_category", "paid"));
		try {
			criteria.add(Restrictions.lt("end_date", sdf.parse(fm.format(new Date()))));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		List<Subscription> subscriptions = (List<Subscription>) criteria.list();
		if (subscriptions.size() > 0) {
			for (Subscription sub : subscriptions) {
				System.out.println(sub.getEnd_date());
				criteria = session.createCriteria(Subscription.class);
				criteria.createAlias("subscribed_tipster", "tipster");
				criteria.createAlias("subscriber", "user");
				criteria.add(Restrictions.eq("subscription_status", "active"));
				criteria.add(Restrictions.eq("tipster.tipster_name", sub.getSubscribed_tipster().getTipster_name()));
				criteria.add(Restrictions.eq("user.username", sub.getSubscriber().getUsername()));
				try {
					criteria.add(Restrictions.gt("end_date", sdf.parse(fm.format(new Date()))));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				criteria.setProjection(Projections.property("subscription_id"));
				criteria.setMaxResults(1);
				List<Integer> subbs = criteria.list();

				if (subbs.size() == 0) {
					sub.setSubscription_status("expired");
					String email = sub.getSubscriber().getEmail();
					if (sub.getSubscribed_tipster().getMailsubscribers().contains(email)) {
						sub.getSubscribed_tipster().getMailsubscribers().remove(email);
					}
					session.update(sub);
				}
			}

			session.getTransaction().commit();
		}
		session.close();
	}

}
