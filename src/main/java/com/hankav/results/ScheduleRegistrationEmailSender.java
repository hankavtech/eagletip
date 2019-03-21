package com.hankav.results;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hankav.dao.HibSessionFactory;
import com.hankav.dao.RegistrationEmailSender;
import com.hankav.model.User;

public class ScheduleRegistrationEmailSender implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub

		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("verified", false));

		criteria.setProjection(Projections.projectionList().add(Projections.property("email").as("email"))
				.add(Projections.property("ruuid").as("uuid")));
		criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = criteria.list();
		try {
			for (Map<String, Object> obj : list) {
				RegistrationEmailSender sender = new RegistrationEmailSender();
				try {
					sender.sendemail(obj.get("email").toString(), obj.get("uuid").toString());
					criteria = session.createCriteria(User.class);
					criteria.add(Restrictions.eq("email", obj.get("email")));
					User user = (User) criteria.uniqueResult();
					user.setVerified(true);
					session.update(user);
					session.getTransaction().commit();

				} catch (AddressException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {

		}

		session.close();

	}

}
