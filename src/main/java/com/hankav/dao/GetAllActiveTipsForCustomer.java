package com.hankav.dao;
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

import com.hankav.model.Subscription;
import com.hankav.model.Tip;

public class GetAllActiveTipsForCustomer {
	
	
	public List<Object[]> getTips(String username) throws ParseException {
		
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();
	    Criteria criteria=session.createCriteria(Subscription.class);
	    criteria.createAlias("subscriber","user");
	    criteria.createAlias("subscribed_tipster","tipster");
	    criteria.add(Restrictions.eq("user.username",username));
	    criteria.add(Restrictions.eq("subscription_status","active"));
	    criteria.setProjection(Projections.property("tipster.tipster_id"));
	    List<Integer>tipster_ids=criteria.list();
	    for(Integer num:tipster_ids) {
	    	System.out.println(num);
	    }
	    if(tipster_ids.size()>0) {
	    SimpleDateFormat fm=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    fm.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
	    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String txt=fm.format(new Date());
	    criteria=session.createCriteria(Tip.class);
	    criteria.createAlias("tipster","tipster");
	    criteria.createAlias("tip_sport","sport");
	    criteria.add(Restrictions.in("tipster.tipster_id",tipster_ids));
	    criteria.add(Restrictions.gt("tip_match_time",sdf.parse(txt)));
	    criteria.setProjection(Projections.projectionList().add(Projections.property("team1")).add(Projections.property("team2")).add(Projections.property("tip_match_time")).add(Projections.property("tip_league")).add(Projections.property("tip_tournament")).add(Projections.property("sport.name")).add(Projections.property("tip_market")).add(Projections.property("tip_lines")).add(Projections.property("tip_sublines")).add(Projections.property("tip_odds")).add(Projections.property("tip_bookmaker")).add(Projections.property("tipster.tipster_name")).add(Projections.property("tip_id")));
	    List<Object[]>tips=criteria.list();
	    session.close();
	    return tips;
	    }
	    else {
	    	session.close();
	    	return null;
	    }
		
	}
	
	

}
