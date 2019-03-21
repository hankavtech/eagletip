package com.hankav.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import com.hankav.model.MatchTip;

public class GetMatchTipById {

	public MatchTip getMatch(String match_id) {
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();
		Criteria criteria=session.createCriteria(MatchTip.class);
		criteria.add(Restrictions.eq("match_id",match_id));
		MatchTip mt=(MatchTip)criteria.uniqueResult();
		session.close();
		return mt;

	}

}
