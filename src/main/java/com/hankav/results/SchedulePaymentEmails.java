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
import com.hankav.dao.PaymentEmailSender;
import com.hankav.model.Subscription;

public class SchedulePaymentEmails implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();
		session.beginTransaction();
		Criteria criteria=session.createCriteria(Subscription.class);
		criteria.add(Restrictions.eq("emailsent",false));
		criteria.createAlias("subscriber","user");
		criteria.createAlias("subscribed_tipster","tipster");
		
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("subscription_id").as("subid"))
				.add(Projections.property("user.username").as("username"))
		        .add(Projections.property("user.email").as("email"))
		        .add(Projections.property("tipster.tipster_name").as("product"))
		        .add(Projections.property("subscription_plan").as("plan"))
		        .add(Projections.property("start_date").as("start"))
		        .add(Projections.property("end_date").as("end"))
		        .add(Projections.property("subscription_price").as("price")));
		criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> list=criteria.list();
		try {
		for(Map<String,Object> obj:list) {
			PaymentEmailSender sender=new PaymentEmailSender();
			try {
				sender.send(obj.get("subid").toString(),obj.get("username").toString(),obj.get("email").toString(),obj.get("product").toString(),obj.get("plan").toString(),obj.get("start").toString(),obj.get("end").toString(),obj.get("price").toString());
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		  criteria=session.createCriteria(Subscription.class);
		  criteria.add(Restrictions.eq("subscription_id",obj.get("subid")));
		  Subscription selsub=(Subscription) criteria.uniqueResult();
		  selsub.setEmailsent(true);
		  session.update(selsub);
		}
		}
		catch(Exception e) {
			
		}
		        
		session.getTransaction().commit();
		session.close();
		
		
	}
	
	

}
