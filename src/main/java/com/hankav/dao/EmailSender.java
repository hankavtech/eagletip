package com.hankav.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hankav.model.Tip;
import com.hankav.model.Tipster;

public class EmailSender {

	public void sendemails() throws ParseException, AddressException {

		SessionFactory factory = HibSessionFactory.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Tip.class);
		criteria.createAlias("tipster", "tipster1");
		criteria.createAlias("tip_sport", "sport");
		criteria.add(Restrictions.eq("emailsent", "no"));
		criteria.add(Restrictions.eq("status", "waiting"));
		criteria.setProjection(
				Projections.projectionList().add(Projections.property("team1")).add(Projections.property("team2"))
						.add(Projections.property("sport.name")).add(Projections.property("tip_match_time"))
						.add(Projections.property("tip_bookmaker")).add(Projections.property("tip_market"))
						.add(Projections.property("tip_lines")).add(Projections.property("tip_sublines"))
						.add(Projections.property("tip_odds")).add(Projections.property("tip_units"))
						.add(Projections.property("tipster1.tipster_name")).add(Projections.property("tip_id")));
		List<Object[]> dat = criteria.list();

		EmailCon con = new EmailCon();
		List<String> mysubs = new ArrayList<>();
		for (Object[] obj : dat) {

			criteria = session.createCriteria(Tipster.class);
			criteria.add(Restrictions.eq("tipster_name", obj[10].toString()));
			Tipster tipster = (Tipster) criteria.uniqueResult();
			String mstatus = "sending";
			if (tipster.getMailsubscribers().size() > 0) {

				mysubs.addAll(tipster.getMailsubscribers());
				mstatus = con.send(obj[0].toString(), obj[1].toString(), obj[2].toString(), obj[3].toString(),
						obj[4].toString(), obj[5].toString(), obj[6].toString(), obj[7].toString(), obj[8].toString(),
						obj[9].toString(), mysubs, obj[10].toString());

			} else {
				mstatus = "sent";
			}
			if (mstatus.equals("sent")) {
				criteria = session.createCriteria(Tip.class);
				criteria.add(Restrictions.eq("tip_id", obj[11]));
				Tip tip = (Tip) criteria.uniqueResult();
				tip.setEmailsent("yes");
				session.update(tip);
			}

		}
		session.getTransaction().commit();
		session.close();

	}

}
