package com.hankav.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import com.hankav.model.Tip;
import com.hankav.model.Tipster;

public class TipstersStats {
	
	
	public Map<Tipster, Map<String, Object>> getStats(){
		
		Map<Tipster, Map<String,Object>> map=new HashMap<>();
		Integer totaltips = 0;
		Integer totalwins = 0;
		Integer totallost = 0;
		Integer totalvoid = 0;
		Double avgodds;
		int k=0;
		SessionFactory factory=HibSessionFactory.getFactory();
		Session session=factory.openSession();
		session.beginTransaction();	
		Query query=session.createQuery("FROM Tipster");
		List<Tipster>tipsters=query.list();
		for(int i=0;i<tipsters.size();i++) {
			List<Tip> lastfiveids=new ArrayList<>();
			double odds=0;
			String matchstatus;
			query=session.createQuery("FROM Tip WHERE tipster.username =:username AND result !=:result ORDER BY date DESC");
			query.setParameter("username",tipsters.get(i).getTipster_name());
			query.setParameter("result","Unknown");
			List<Tip>tips=query.list();
			for(Tip tip:tips) {
				totaltips=totaltips+1;
				if(tip.getTip_result().equals("Won")) {
					matchstatus="Won";
					totalwins=totalwins+1;
				}
				else if(tip.getTip_result().equals("Lost")) {
					matchstatus="Lost";
					totallost=totallost+1;
				}
				else {
					matchstatus="Void";
					totalvoid=totalvoid+1;
				}
				odds=odds+tip.getTip_odds();
				k=k+1;
				if(k<6) {
					lastfiveids.add(tip);
					
				}
				
				
				
				
				
			}
			avgodds=(Double)odds/totaltips;
			Map<String,Object> inmap = new HashMap<>();
			inmap.put("ttips",totaltips);
			inmap.put("twins",totalwins);
			inmap.put("tlost",totallost);
			inmap.put("tvoid",totalvoid);
			inmap.put("aodds",Math.round(avgodds*100.0)/100.0);
			inmap.put("l5ids",lastfiveids);
			map.put(tipsters.get(i),inmap);
			totalwins=0;
			totallost=0;
			totalvoid=0;
			avgodds=0.0;
			k=0;
			
		}
		session.close();
		return map;
		
	}
	
	
	

}
