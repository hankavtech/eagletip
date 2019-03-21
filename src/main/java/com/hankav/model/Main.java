package com.hankav.model;

import java.io.IOException;
import java.text.ParseException;

import javax.mail.internet.AddressException;

import org.hibernate.HibernateException;

public class Main {

	public static void main(String[] args) throws ParseException, HibernateException, IOException, AddressException {

		/*
		 * SessionFactory factory = HibSessionFactory.getFactory(); Session session =
		 * factory.openSession();
		 * 
		 * MatchReader reader = new MatchReader(); reader.readmatches("football");
		 * 
		 * FootballResultUpdate up = new FootballResultUpdate(); up.updateResults();
		 * 
		 * session.close();
		 */

		/*
		 * MatchReader reader = new MatchReader(); reader.readmatches("hockey");
		 */
		/*
		 * SessionFactory factory = HibSessionFactory.getFactory(); Session session =
		 * factory.openSession(); session.beginTransaction(); SimpleDateFormat df = new
		 * SimpleDateFormat("dd/MM/yyyy HH:mm"); Calendar cal = Calendar.getInstance();
		 * cal.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		 * cal.setTime(df.parse("17/3/2019 20:10")); cal.add(Calendar.HOUR, -2); Tip tip
		 * = new Tip(); tip.setTipster(new
		 * GetTipsterByProperty().byName("abhiram samarth")); tip.setTip_sport(new
		 * GetSportByProperty().byName("tennis")); tip.setEmailsent("yes");
		 * tip.setStatus("finished"); tip.setTeam1("Kerber A.");
		 * tip.setTeam2("Andreescu B."); tip.setTip_category("free");
		 * tip.setTip_date(cal.getTime()); tip.setTip_league("WTA - SINGLES");
		 * tip.setTip_tournament("indian-wells"); tip.setTip_market("To Win The Match");
		 * tip.setTip_lines("team1");
		 * 
		 * tip.setTip_bookmaker("unibet");
		 * tip.setTip_match_time(df.parse("17/3/2019 20:10")); tip.setTip_odds(1.56);
		 * tip.setTipscore("1-2"); tip.setTip_units(100); tip.setTip_result("lost");
		 * tip.setTip_profit(-100); session.save(tip);
		 * session.getTransaction().commit(); session.close();
		 */
		/* tip.setTip_sublines("22.5"); */
		/*
		 * UpdateStats stats = new UpdateStats(); stats.execute();
		 */
		/*
		 * SessionFactory factory = HibSessionFactory.getFactory(); Session session =
		 * factory.openSession(); session.beginTransaction(); SimpleDateFormat sdf = new
		 * SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		 * sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00")); SimpleDateFormat sdf1 =
		 * new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); Chat chat = new Chat();
		 * chat.setPostdate(sdf1.parse(sdf.format(new Date()))); chat.setUser(new
		 * GetUserByProperty().byName("admin2")); chat.setChatstring(
		 * "I have problems setting up tipster account.Can you please guide me in the right direction?"
		 * ); session.save(chat); session.getTransaction().commit(); session.close();
		 */

		/*
		 * SessionFactory factory = HibSessionFactory.getFactory(); Session session =
		 * factory.openSession(); session.beginTransaction();
		 * 
		 * SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm"); Calendar cal
		 * = Calendar.getInstance(); cal.setTime(df.parse("05/03/2019 20:00"));
		 * cal.add(Calendar.HOUR, -2);
		 * 
		 * Tip tip = new Tip(); tip.setTipster(new
		 * GetTipsterByProperty().byName("sherwin")); tip.setTip_sport(new
		 * GetSportByProperty().byName("cricket")); tip.setEmailsent("yes");
		 * tip.setStatus("finished"); tip.setTeam1("West Indies");
		 * tip.setTeam2("England"); tip.setTip_category("free");
		 * tip.setTip_date(cal.getTime()); tip.setTip_league("World");
		 * tip.setTip_tournament("Twenty20 International");
		 * tip.setTip_market("To Win The Match"); tip.setTip_lines("team2");
		 * tip.setTip_sublines("22.5"); tip.setTip_bookmaker("1xbet");
		 * tip.setTip_match_time(df.parse("05/03/2019 20:00")); tip.setTip_odds(1.85);
		 * tip.setTipscore("160:161"); tip.setTip_units(100); tip.setTip_result("won");
		 * tip.setTip_profit(85); session.save(tip);
		 */

		/*
		 * Tip tip=new Tip(); tip.setTipster(new
		 * GetTipsterByProperty().byName("james franklin")); tip.setTip_sport(new
		 * GetSportByProperty().byName("tennis")); tip.setEmailsent("yes");
		 * tip.setStatus("finished"); tip.setTeam1("King D"); tip.setTeam2("Rubin N");
		 * tip.setTip_bookmaker("bet365"); tip.setTip_category("free");
		 * tip.setTip_date(tip_date); tip.setTip_league(tip_league);
		 * tip.setTip_tournament(tip_tournament); tip.setTip_market(tip_market);
		 * tip.setTip_lines(tip_lines); tip.setTip_match_time(tip_match_time);
		 * tip.setTip_odds(tip_odds); tip.setTipscore(tipscore);
		 * tip.setTip_units(tip_units); tip.setTip_result(tip_result);
		 * tip.setTip_profit(tip_profit);
		 */

		/*
		 * session.getTransaction().commit(); session.close();
		 */

		/*
		 * SessionFactory factory = HibSessionFactory.getFactory(); Session session =
		 * factory.openSession(); session.beginTransaction();
		 * 
		 * Criteria criteria = session.createCriteria(Sport.class);
		 * criteria.add(Restrictions.eq("name", "american football")); Sport hock =
		 * (Sport) criteria.uniqueResult();
		 * 
		 * Market m = new Market(); m.setName("Money Line(ext-pen)"); m.setSport(hock);
		 * List<String> arr = new ArrayList<>(); arr.add("team1"); arr.add("team2");
		 * arr.add("draw"); m.setMylines(arr); session.save(m);
		 * hock.getMarkets().add(m); session.update(m);
		 * session.getTransaction().commit(); session.close();
		 */

		/*
		 * SessionFactory factory = HibSessionFactory.getFactory(); Session session =
		 * factory.openSession(); session.beginTransaction();
		 * 
		 * Criteria criteria = session.createCriteria(Sport.class);
		 * criteria.add(Restrictions.eq("name", "american football")); Sport sport =
		 * (Sport) criteria.uniqueResult(); Market market = new Market();
		 * market.setName("Spread"); market.setSublines(true);
		 * 
		 * market.setMylines(new ArrayList<>(Arrays.asList("team1", "team2")));
		 * market.setSport(sport);
		 * 
		 * Market market1 = new Market(); market1.setName("Total");
		 * market1.setSublines(true);
		 * 
		 * market1.setMylines(new ArrayList<>(Arrays.asList("Over", "Under")));
		 * market1.setSport(sport); Set<Market> markets = new HashSet<>();
		 * markets.add(market); markets.add(market1);
		 * sport.getMarkets().addAll(markets); session.save(market);
		 * session.save(market1); session.update(sport);
		 * session.getTransaction().commit(); session.close();
		 */
		/*
		 * Criteria criteria; criteria = session.createCriteria(Tip.class);
		 * criteria.createAlias("tipster", "tipster"); criteria.createAlias("tip_sport",
		 * "sport"); criteria.add(Restrictions.eq("tipster.tipster_name",
		 * "tipstertest")); criteria.add(Restrictions.eq("status", "finished"));
		 * criteria.setFirstResult(0 * 10 + 0); criteria.setMaxResults(7);
		 * criteria.setProjection(Projections.projectionList().add(Projections.property(
		 * "tip_id"))
		 * .add(Projections.property("sport.name")).add(Projections.property("team1"))
		 * .add(Projections.property("team2")).add(Projections.property("tip_match_time"
		 * ))
		 * .add(Projections.property("tip_market")).add(Projections.property("tip_lines"
		 * )) .add(Projections.property("tip_sublines")).add(Projections.property(
		 * "tip_units"))
		 * .add(Projections.property("tip_odds")).add(Projections.property(
		 * "tip_bookmaker"))
		 * .add(Projections.property("tip_profit")).add(Projections.property("tipscore")
		 * ));
		 * 
		 * List<Object[]> tips = criteria.list(); System.out.println("size is" +
		 * tips.size());
		 * 
		 * for (Object[] obj : tips) { System.out.println(obj[2].toString() + " vs " +
		 * obj[3].toString()); }
		 */
		/*
		 * session.getTransaction().commit(); session.close();
		 */

		/*
		 * SessionFactory factory=HibSessionFactory.getFactory(); Session
		 * session=factory.openSession(); session.beginTransaction(); Criteria
		 * criteria=session.createCriteria(Subscription.class);
		 * criteria.createAlias("subscriber","user");
		 * criteria.add(Restrictions.eq("subscription_id",10));
		 * criteria.add(Restrictions.eq("user.username","test")); Subscription
		 * subscription=(Subscription) criteria.uniqueResult(); if(subscription==null) {
		 * System.out.println("You do not have access to this subscription"); return; }
		 * 
		 * Date end=subscription.getEnd_date(); Date start=subscription.getStart_date();
		 * System.out.println(end); System.out.println(start);
		 * 
		 * Query query=session.
		 * createQuery("select count(*),sum(tip_profit),avg(tip_odds),sum(case when tip_result=?1 then 1 else 0 end),sum(case when tip_result=?2 then 1 else 0 end),sum(case when tip_result=?3 then 1 else 0 end) from Tip where tipster.tipster_name=?4 and tip_match_time between ?5 and ?6"
		 * ); query.setParameter(1,"won"); query.setParameter(2,"lost");
		 * query.setParameter(3,"void"); query.setParameter(4,"tipstertest");
		 * query.setParameter(5,start); query.setParameter(6,end);
		 * 
		 * List<Object[]>arr=query.list(); for(Object[] obj:arr) {
		 * System.out.println(obj[0]+" "+obj[1]+" "+obj[2]+" "+obj[3]+" "+obj[4]+" "+obj
		 * [5]); }
		 */

		/*
		 * SessionFactory factory=HibSessionFactory.getFactory(); Session
		 * session=factory.openSession(); session.beginTransaction(); Query
		 * query=session.
		 * createQuery("select year(start_date),date_format(start_date,'%b'),count(*),sum(case when tipster_paymentmade=?3 then 1 else 0 end),sum(subscription_price),sum(case when tipster_paymentmade=?3 then subscription_price else 0 end) from Subscription  where subscription_type=?1 and subscribed_tipster.tipster_name=?2 group by YEAR(start_date), MONTH(start_date) order by start_date desc"
		 * ); query.setParameter(1,"paid"); query.setParameter(2,"tipstertest");
		 * query.setParameter(3,true); List<Object[]>monthlypayments=query.list();
		 * for(Object[] monthpay:monthlypayments) {
		 * System.out.print(monthpay[0].toString()+" "+monthpay[1].toString()+" "
		 * +monthpay[2].toString()+" "+monthpay[3].toString()+" "+monthpay[4].toString()
		 * ); System.out.println(); }
		 * 
		 * session.getTransaction().commit(); session.close();
		 */

		/*
		 * SimpleDateFormat fm=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		 * fm.setTimeZone(TimeZone.getTimeZone("GMT+00:00")); SimpleDateFormat sdf=new
		 * SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); SessionFactory
		 * factory=HibSessionFactory.getFactory(); Session
		 * session=factory.openSession(); session.beginTransaction(); Criteria
		 * criteria=session.createCriteria(Subscription.class);
		 * criteria.createAlias("subscribed_tipster","tipster");
		 * criteria.add(Restrictions.eq("subscription_status","active"));
		 * criteria.add(Restrictions.eq("tipster.tipster_category","paid")); try {
		 * criteria.add(Restrictions.lt("end_date",sdf.parse(fm.format(new Date())))); }
		 * catch (ParseException e1) { e1.printStackTrace(); }
		 * List<Subscription>subscriptions=(List<Subscription>) criteria.list();
		 * if(subscriptions.size()>0) { for(Subscription sub:subscriptions) {
		 * System.out.println(sub.getEnd_date());
		 * criteria=session.createCriteria(Subscription.class);
		 * criteria.createAlias("subscribed_tipster","tipster");
		 * criteria.createAlias("subscriber","user");
		 * criteria.add(Restrictions.eq("subscription_status","active"));
		 * criteria.add(Restrictions.eq("tipster.tipster_name",sub.getSubscribed_tipster
		 * ().getTipster_name()));
		 * criteria.add(Restrictions.eq("user.username",sub.getSubscriber().getUsername(
		 * ))); try { criteria.add(Restrictions.gt("end_date",sdf.parse(fm.format(new
		 * Date())))); } catch (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * criteria.setProjection(Projections.property("subscription_id"));
		 * criteria.setMaxResults(1); List<Integer>subbs=criteria.list();
		 * 
		 * if(subbs.size()==0) { System.out.println("i am here");
		 * sub.setSubscription_status("expired"); String
		 * email=sub.getSubscriber().getEmail();
		 * if(sub.getSubscribed_tipster().getMailsubscribers().contains(email)) {
		 * sub.getSubscribed_tipster().getMailsubscribers().remove(email); }
		 * session.update(sub); } }
		 * 
		 * session.getTransaction().commit(); } session.close();
		 */

		/*
		 * SessionFactory factory=HibSessionFactory.getFactory(); Session
		 * session=factory.openSession(); session.beginTransaction(); Criteria
		 * criteria=session.createCriteria(User.class);
		 * criteria.add(Restrictions.and(Restrictions.eq("email","ankavtech@gmail.com"),
		 * Restrictions.eq("username","test")));
		 * criteria.setProjection(Projections.property("email")); FileReader
		 * reader=null; try { reader = new FileReader("WebContent/footballresults.txt");
		 * } catch(IOException e) { System.out.println("hello1"); }
		 * 
		 * System.out.println("hello");
		 */

		/*
		 * SessionFactory factory=HibSessionFactory.getFactory(); Session
		 * session=factory.openSession(); session.beginTransaction(); Criteria
		 * criteria=session.createCriteria(Subscription.class);
		 * criteria.add(Restrictions.eq("subscription_status","active"));
		 * criteria.add(Restrictions.eq("subscription_type","paid"));
		 * criteria.add(Restrictions.lt("end_date",new Date()));
		 * criteria.createAlias("subscribed_tipster","tipster");
		 * criteria.createAlias("subscriber","user");
		 * List<Subscription>subscriptions=(List<Subscription>) criteria.list(); try {
		 * for(Subscription sub:subscriptions) { sub.setSubscription_status("expired");
		 * String email=sub.getSubscriber().getEmail(); String
		 * tipstername=sub.getSubscribed_tipster().getTipster_name();
		 * if(sub.getSubscribed_tipster().getMailsubscribers().contains(email)) {
		 * criteria=session.createCriteria(Tipster.class);
		 * criteria.add(Restrictions.eq("tipster_name",tipstername)); Tipster
		 * tipster=(Tipster) criteria.uniqueResult();
		 * tipster.getMailsubscribers().remove(email);
		 * sub.getSubscribed_tipster().getMailsubscribers().remove(email);
		 * session.update(tipster); } session.update(sub); }
		 * 
		 * session.getTransaction().commit(); } catch(Exception e) {
		 * 
		 * }
		 */

		/*
		 * SessionFactory factory=HibSessionFactory.getFactory(); Session
		 * session=factory.openSession(); session.beginTransaction(); Criteria
		 * criteria=session.createCriteria(Subscription.class);
		 * criteria.add(Restrictions.eq("emailsent",false));
		 * criteria.createAlias("subscriber","user");
		 * criteria.createAlias("subscribed_tipster","tipster");
		 * 
		 * criteria.setProjection(Projections.projectionList()
		 * .add(Projections.property("subscription_id").as("subid"))
		 * .add(Projections.property("user.username").as("username"))
		 * .add(Projections.property("user.email").as("email"))
		 * .add(Projections.property("tipster.tipster_name").as("product"))
		 * .add(Projections.property("subscription_plan").as("plan"))
		 * .add(Projections.property("start_date").as("start"))
		 * .add(Projections.property("end_date").as("end"))
		 * .add(Projections.property("subscription_price").as("price")));
		 * criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 * List<Map<String,Object>> list=criteria.list(); try { for(Map<String,Object>
		 * obj:list) { PaymentEmailSender sender=new PaymentEmailSender(); try {
		 * sender.send(obj.get("subid").toString(),obj.get("username").toString(),obj.
		 * get("email").toString(),obj.get("product").toString(),obj.get("plan").
		 * toString(),obj.get("start").toString(),obj.get("end").toString(),obj.get(
		 * "price").toString()); } catch (AddressException e) { e.printStackTrace(); }
		 * catch (ParseException e) { e.printStackTrace(); }
		 * criteria=session.createCriteria(Subscription.class);
		 * criteria.add(Restrictions.eq("subscription_id",obj.get("subid")));
		 * Subscription selsub=(Subscription) criteria.uniqueResult();
		 * selsub.setEmailsent(true); session.update(selsub); } } catch(Exception e) {
		 * 
		 * }
		 * 
		 * session.getTransaction().commit(); session.close();
		 */

		/*
		 * SessionFactory factory=HibSessionFactory.getFactory(); Session
		 * session=factory.openSession(); session.beginTransaction(); Criteria
		 * criteria=null; Query query=session.
		 * createQuery("SELECT t.tipster.tipster_name,count(*),sum(t.tip_profit),avg(t.tip_odds),sum(case when t.tip_result='won' then 1 else 0 end),sum(case when t.tip_result = 'lost' then 1 else 0 end) from Tip t WHERE t.tip_result != ?1 AND t.tip_result != ?2 group by t.tipster.tipster_name"
		 * ); query.setParameter(1,"void"); query.setParameter(2,"Unknown");
		 * List<Object[]>stats=query.list(); for(Object[] stat:stats) {
		 * System.out.println("name"+stat[0]+" "+" total="+stat[1]+" profit ="+stat[2]
		 * +" average odds ="+stat[3]+" win percentage ="+((Integer.parseInt(stat[4].
		 * toString())-Integer.parseInt(stat[5].toString()))*100)/(Integer.parseInt(stat
		 * [1].toString()))); criteria=session.createCriteria(Stats.class);
		 * criteria.createAlias("stats_tipster","tipster");
		 * criteria.add(Restrictions.eq("tipster.tipster_name",stat[0].toString()));
		 * Stats singlestat=(Stats) criteria.uniqueResult(); try {
		 * singlestat.setAvodds(Double.parseDouble(stat[3].toString()));
		 * singlestat.setProfit(Double.parseDouble(stat[2].toString()));
		 * singlestat.setTips(Integer.parseInt(stat[1].toString()));
		 * singlestat.setWinpercentage(((Integer.parseInt(stat[4].toString()))*100)/
		 * Double.parseDouble(stat[1].toString()));
		 * singlestat.setYield(Double.parseDouble(stat[2].toString())/(Double.
		 * parseDouble(stat[1].toString()))); session.update(singlestat); }
		 * catch(Exception e) {
		 * 
		 * } }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * session.getTransaction().commit(); session.close();
		 */

		/*
		 * try { SchedulerFactory sf = new StdSchedulerFactory(); Scheduler scheduler =
		 * sf.getScheduler(); //Tie the MailSenderJob to scheduler JobDetailImpl
		 * jobDetail = new JobDetailImpl(); jobDetail.setGroup("group1"); JobKey jobKey
		 * = new JobKey("job1"); jobDetail.setKey(jobKey);
		 * jobDetail.setJobClass(ScheduleGetFootballResults.class); //create a trigger
		 * and tie it to the job // Trigger the job to run on every minute
		 * CronExpression cronExpression = new CronExpression("0 0/2 * * * ? *");
		 * CronTriggerImpl cronTrigger = new CronTriggerImpl();
		 * cronTrigger.setName("mail trigger");
		 * cronTrigger.setCronExpression(cronExpression); //Tie the job and the trigger
		 * to the scheduler scheduler.scheduleJob(jobDetail, cronTrigger);
		 * scheduler.start(); } catch (SchedulerException se) { se.printStackTrace(); }
		 */

		/*
		 * UpdateStats stats=new UpdateStats(); stats.execute();
		 */
		/*
		 * String datetime="Jun 21, 2019 05:30"; DateFormat formatterIST1 = new
		 * SimpleDateFormat("MMM dd, yyyy HH:mm");
		 * formatterIST1.setTimeZone(TimeZone.getTimeZone("GMT+09:30")); Date
		 * date=formatterIST1.parse(datetime); System.out.println(
		 * formatterIST1.format(date));
		 * 
		 * DateFormat formatterIST = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		 * formatterIST.setTimeZone(TimeZone.getTimeZone("GMT+09:30")); // better than
		 * using IST Date date = formatterIST.("15-05-2014 00:00:00");
		 * System.out.println(formatterIST.format(date)); // output: 15-05-2014 00:00:00
		 * 
		 * DateFormat formatterUTC = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		 * formatterUTC.setTimeZone(TimeZone.getTimeZone("GMT+00:00")); // GMT timezone
		 * System.out.println(formatterUTC.format(date));
		 * 
		 * DateFormat formatterIST3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		 * System.out.println(formatterIST3.parse(formatterUTC.format(date)));
		 * 
		 * System.out.println(formatterIST3.parse(formatterUTC.format(new Date())));
		 */

		/*
		 * SimpleDateFormat serverformat=new SimpleDateFormat("MMM dd, yyyy HH:mm");
		 * SimpleDateFormat stdserverformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * 
		 * 
		 * 
		 * SimpleDateFormat utcformat=new SimpleDateFormat("MMM dd, yyyy HH:mm");
		 * SimpleDateFormat stdutcformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * utcformat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		 * stdutcformat.setTimeZone(TimeZone.getTimeZone("GMT+00:00")); Calendar
		 * calutc=Calendar.getInstance(TimeZone.getTimeZone("GMT+00:00"));
		 * calremote.setTime(remoteformat.parse(datetime));
		 */

		/*
		 * EmailSender sender=new EmailSender(); sender.sendemails();
		 */
		/*
		 * SessionFactory factory=HibSessionFactory.getFactory(); Session
		 * session=factory.openSession(); session.beginTransaction(); Criteria
		 * criteria=session.createCriteria(Tip.class);
		 * criteria.createAlias("tipster","tipster");
		 * criteria.add(Restrictions.eq("tipster.tipster_name","tipstertest"));
		 * criteria.setProjection(Projections.property("tipster.tipster_name")); String
		 * name=(String) criteria.uniqueResult(); session.getTransaction().commit();
		 * session.close(); System.out.println(name);
		 */

		/*
		 * SessionFactory factory = HibSessionFactory.getFactory(); Session session =
		 * factory.openSession(); session.beginTransaction(); Sport sport = new Sport();
		 * sport.setName("mma"); Market m1 = new Market(); m1.setName("To Win Fight");
		 * m1.setMylines(Arrays.asList("team1","team2")); m1.setSport(sport);
		 * m1.setSublines(false); Market m2 = new Market(); m2.setName("Total Rounds");
		 * m2.setMylines(Arrays.asList("Over","Under")); m2.setSport(sport);
		 * m2.setSublines(true); Market m3 = new Market();
		 * m3.setName("Will Fight Go The Distance");
		 * m3.setMylines(Arrays.asList("Yes","No")); m3.setSport(sport);
		 * m3.setSublines(false);
		 * 
		 * Set<Market> markets = new LinkedHashSet<>(); markets.add(m2);
		 * markets.add(m1); markets.add(m3); sport.setMarkets(markets);
		 * session.save(m2); session.save(m1); session.save(m3); session.save(sport);
		 * session.getTransaction().commit(); session.close();
		 */

		/*
		 * SessionFactory factory = HibSessionFactory.getFactory(); Session session =
		 * factory.openSession(); session.beginTransaction(); Sport sport = new Sport();
		 * sport.setName("american football"); Market m1 = new Market();
		 * m1.setName("Money Line"); m1.setMylines(Arrays.asList("team1","team2"));
		 * m1.setSport(sport); m1.setSublines(false); Market m2 = new Market();
		 * m2.setName("Total"); m2.setMylines(Arrays.asList("Over","Under"));
		 * m2.setSport(sport); m2.setSublines(true); Market m3 = new Market();
		 * m3.setName("Spread"); m3.setMylines(Arrays.asList("team1","team2"));
		 * m3.setSport(sport); m3.setSublines(true); Market m4 = new Market();
		 * m4.setName("First Half Money Line"); m4.setMylines(Arrays.asList("team1",
		 * "team2")); m4.setSport(sport); m4.setSublines(false); Market m5 = new
		 * Market(); m5.setName("First Half Total"); m5.setMylines(Arrays.asList("Over",
		 * "Under")); m5.setSport(sport); m5.setSublines(true);
		 * 
		 * Market m6 = new Market(); m6.setName("First Half Spread");
		 * m6.setMylines(Arrays.asList("team1","team2")); m6.setSport(sport);
		 * m6.setSublines(true);
		 * 
		 * Market m7 = new Market(); m7.setName("First Quarter Money Line");
		 * m7.setMylines(Arrays.asList("team1","team2")); m7.setSport(sport);
		 * m7.setSublines(false);
		 * 
		 * Market m8 = new Market(); m8.setName("First Quarter Total");
		 * m8.setMylines(Arrays.asList("Under","Over")); m8.setSport(sport);
		 * m8.setSublines(true); Market m9 = new Market();
		 * m9.setName("First Quarter Spread");
		 * m9.setMylines(Arrays.asList("team1","team2")); m9.setSport(sport);
		 * m9.setSublines(true); Set<Market> markets = new LinkedHashSet<>();
		 * markets.add(m6); markets.add(m2); markets.add(m1); markets.add(m3);
		 * markets.add(m4); markets.add(m5); markets.add(m7); markets.add(m8);
		 * markets.add(m9); sport.setMarkets(markets); session.save(m6);
		 * session.save(m2); session.save(m1); session.save(m3); session.save(m4);
		 * session.save(m5); session.save(m7); session.save(m8); session.save(m9);
		 * session.save(sport); session.getTransaction().commit(); session.close();
		 */

		/*
		 * SessionFactory factory = HibSessionFactory.getFactory(); Session session =
		 * factory.openSession(); session.beginTransaction(); Sport sport = new Sport();
		 * sport.setName("hockey"); Market m1 = new Market(); m1.setName("Money Line");
		 * m1.setMylines(Arrays.asList("team1","team2")); m1.setSport(sport);
		 * m1.setSublines(false); Market m2 = new Market(); m2.setName("Total");
		 * m2.setMylines(Arrays.asList("Over","Under")); m2.setSport(sport);
		 * m2.setSublines(true); Market m3 = new Market(); m3.setName("Handicap");
		 * m3.setMylines(Arrays.asList("team1","team2")); m3.setSport(sport);
		 * m3.setSublines(true); Market m4 = new Market();
		 * m4.setName("First Period Money Line"); m4.setMylines(Arrays.asList("team1",
		 * "team2")); m4.setSport(sport); m4.setSublines(false); Market m5 = new
		 * Market(); m5.setName("First Period Total");
		 * m5.setMylines(Arrays.asList("Over", "Under")); m5.setSport(sport);
		 * m5.setSublines(true);
		 * 
		 * 
		 * Market m6 = new Market(); m6.setName("First 10 Minute Money Line");
		 * m6.setMylines(Arrays.asList("team1","team2")); m6.setSport(sport);
		 * m6.setSublines(false);
		 * 
		 * Market m7 = new Market(); m7.setName("First 10 Minute Total");
		 * m7.setMylines(Arrays.asList("Under","Over")); m7.setSport(sport);
		 * m7.setSublines(true); Set<Market> markets = new LinkedHashSet<>();
		 * markets.add(m6); markets.add(m2); markets.add(m1); markets.add(m3);
		 * markets.add(m4); markets.add(m5); markets.add(m7); sport.setMarkets(markets);
		 * session.save(m6); session.save(m2); session.save(m1); session.save(m3);
		 * session.save(m4); session.save(m5); session.save(m7); session.save(sport);
		 * session.getTransaction().commit(); session.close();
		 */

		/*
		 * SessionFactory factory = HibSessionFactory.getFactory(); Session session =
		 * factory.openSession(); session.beginTransaction(); Sport sport = new Sport();
		 * sport.setName("basketball"); Market m1 = new Market();
		 * m1.setName("Money Line"); m1.setMylines(Arrays.asList("team1","team2"));
		 * m1.setSport(sport); m1.setSublines(false); Market m2 = new Market();
		 * m2.setName("Total"); m2.setMylines(Arrays.asList("Over","Under"));
		 * m2.setSport(sport); m2.setSublines(true); Market m3 = new Market();
		 * m3.setName("Spread"); m3.setMylines(Arrays.asList("team1","team2"));
		 * m3.setSport(sport); m3.setSublines(true); Market m4 = new Market();
		 * m4.setName("1st Half Money Line"); m4.setMylines(Arrays.asList("team1",
		 * "team2")); m4.setSport(sport); m4.setSublines(false); Market m5 = new
		 * Market(); m5.setName("1st Half Total"); m5.setMylines(Arrays.asList("Over",
		 * "Under")); m5.setSport(sport); m5.setSublines(true);
		 * 
		 * Market m6 = new Market(); m6.setName("1st Half Spread");
		 * m6.setMylines(Arrays.asList("team1","team2")); m6.setSport(sport);
		 * m6.setSublines(true);
		 * 
		 * Market m7 = new Market(); m7.setName("1st Quarter Money Line");
		 * m7.setMylines(Arrays.asList("team1","team2")); m7.setSport(sport);
		 * m7.setSublines(false);
		 * 
		 * Market m8 = new Market(); m8.setName("1st Quarter Total");
		 * m8.setMylines(Arrays.asList("Under","Over")); m8.setSport(sport);
		 * m8.setSublines(true); Market m9 = new Market();
		 * m9.setName("1st Quarter Spread");
		 * m9.setMylines(Arrays.asList("team1","team2")); m9.setSport(sport);
		 * m9.setSublines(true); Set<Market> markets = new LinkedHashSet<>();
		 * markets.add(m6); markets.add(m2); markets.add(m1); markets.add(m3);
		 * markets.add(m4); markets.add(m5); markets.add(m7); markets.add(m8);
		 * markets.add(m9); sport.setMarkets(markets); session.save(m6);
		 * session.save(m2); session.save(m1); session.save(m3); session.save(m4);
		 * session.save(m5); session.save(m7); session.save(m8); session.save(m9);
		 * session.save(sport); session.getTransaction().commit(); session.close();
		 */

		/*
		 * SessionFactory factory = HibSessionFactory.getFactory(); Session session =
		 * factory.openSession(); session.beginTransaction(); Sport sport = new Sport();
		 * sport.setName("cricket"); Market m1 = new Market();
		 * m1.setName("To Win The Match");
		 * m1.setMylines(Arrays.asList("team1","team2")); m1.setSport(sport);
		 * m1.setSublines(false); Market m2 = new Market();
		 * m2.setName("First Wicket Method");
		 * m2.setMylines(Arrays.asList("Caught","Bowled","LBW","Run Out","Stumped"));
		 * m2.setSport(sport); m2.setSublines(false); Market m3 = new Market();
		 * m3.setName("1st Over Total Runs");
		 * m3.setMylines(Arrays.asList("Over","Under")); m3.setSport(sport);
		 * m3.setSublines(true); Market m4 = new Market();
		 * m4.setName("Total Runs in Match"); m4.setMylines(Arrays.asList("Under",
		 * "Over")); m4.setSport(sport); m4.setSublines(true); Market m5 = new Market();
		 * m5.setName("Total Match Sixes"); m5.setMylines(Arrays.asList("Over",
		 * "Under")); m5.setSport(sport); m5.setSublines(true);
		 * 
		 * Market m6 = new Market(); m6.setName("Highest 1st 15 Overs Score");
		 * m6.setMylines(Arrays.asList("team1","team2")); m6.setSport(sport);
		 * m6.setSublines(false);
		 * 
		 * Market m7 = new Market(); m7.setName("A Hundred To Be Scored In Match");
		 * m7.setMylines(Arrays.asList("Yes","No")); m7.setSport(sport);
		 * m7.setSublines(false);
		 * 
		 * Market m8 = new Market(); m8.setName("Fall Of 1st Wicket");
		 * m8.setMylines(Arrays.asList("Under","Over")); m8.setSport(sport);
		 * m8.setSublines(true); Set<Market> markets = new LinkedHashSet<>();
		 * markets.add(m6); markets.add(m2); markets.add(m1); markets.add(m3);
		 * markets.add(m4); markets.add(m5); markets.add(m7); markets.add(m8);
		 * sport.setMarkets(markets); session.save(m6); session.save(m2);
		 * session.save(m1); session.save(m3); session.save(m4); session.save(m5);
		 * session.save(m7); session.save(m8); session.save(sport);
		 * session.getTransaction().commit(); session.close();
		 */

		/* System.out.println(new getTipsterRatings().byProfitAndCategory("free")); */

		/*
		 * SessionFactory factory=HibSessionFactory.getFactory(); Session
		 * session=factory.openSession(); Criteria
		 * criteria=session.createCriteria(Subscription.class);
		 * criteria.createAlias("subscriber","user");
		 * criteria.createAlias("subscribed_tipster","tipster");
		 * criteria.add(Restrictions.eq("user.username","nain"));
		 * criteria.add(Restrictions.eq("subscription_status","active"));
		 * criteria.setProjection(Projections.property("tipster.tipster_id"));
		 * List<Integer>tipster_ids=criteria.list(); for(Integer num:tipster_ids) {
		 * System.out.println(num); } SimpleDateFormat fm=new
		 * SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		 * fm.setTimeZone(TimeZone.getTimeZone("GMT+0")); SimpleDateFormat sdf=new
		 * SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); String txt=fm.format(new Date());
		 * criteria=session.createCriteria(Tip.class);
		 * criteria.createAlias("tipster","tipster");
		 * criteria.createAlias("tip_sport","sport");
		 * criteria.add(Restrictions.in("tipster.tipster_id",tipster_ids));
		 * criteria.add(Restrictions.gt("tip_match_time",sdf.parse(txt)));
		 * criteria.setProjection(Projections.projectionList().add(Projections.property(
		 * "team1")).add(Projections.property("team2")).add(Projections.property(
		 * "tip_match_time")).add(Projections.property("tip_league")).add(Projections.
		 * property("tip_tournament")).add(Projections.property("sport.name")).add(
		 * Projections.property("tip_market")).add(Projections.property("tip_lines")).
		 * add(Projections.property("tip_sublines")).add(Projections.property("tip_odds"
		 * )).add(Projections.property("tip_bookmaker")).add(Projections.property(
		 * "tipster.tipster_name"))); List<Object[]>tips=criteria.list();
		 * session.close();
		 */

		/*
		 * GetAllActiveTipsForCustomer acttips=new GetAllActiveTipsForCustomer();
		 * acttips.getTips("nain");
		 */

		/*
		 * GetTenTipsOfClient ten=new GetTenTipsOfClient();
		 * List<Object[]>myobjs=ten.getAllSubscriptions("nain");
		 * for(Object[]myobj:myobjs) { System.out.println(myobj[0]); }
		 * List<Object[]>objs=ten.getStats("5","1","2018-12-17 09:01:48"
		 * ,"2019-01-16 09:01:48"); for(Object obj:objs.get(0)) {
		 * System.out.print(obj.toString()+"  "); } System.out.println();
		 * 
		 * for(Object obj:objs.get(1)) { System.out.print(obj.toString()+"  "); }
		 */
		/* create a subscrition */
		/*
		 * SessionFactory factory=HibSessionFactory.getFactory(); Session
		 * session=factory.openSession(); session.beginTransaction();
		 * TimeZone.setDefault(TimeZone.getTimeZone("GMT+0")); Subscription sub=new
		 * Subscription(1,new GetTipsterByProperty().byName("jack keshav"),new
		 * GetUserByProperty().byName("nain"),30.0);
		 * System.out.println(sub.getStart_date());
		 * System.out.println(sub.getEnd_date()); session.save(sub);
		 * session.getTransaction().commit(); session.close();
		 */

		/*
		 * Date d=new Date(); System.out.println(d); SimpleDateFormat sdf=new
		 * SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		 * sdf.setTimeZone(TimeZone.getTimeZone("GMT+0")); String utctext=sdf.format(d);
		 * SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		 * sdf1.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
		 * System.out.println(sdf1.format(d));
		 */

		/*
		 * String data=new GetDataForTipsterProfileGraphs().ByTipsterName("gund mikes");
		 */
		/*
		 * SessionFactory factory = HibSessionFactory.getFactory(); Session session =
		 * factory.openSession(); session.beginTransaction(); Sport sport = new Sport();
		 * sport.setName("tennis"); Market m1 = new Market(); m1.setName("Set Betting");
		 * m1.setMylines(Arrays.asList("2-0", "2-1",
		 * "0-2","1-2","3-0","3-1","3-2","0-3","1-3","2-3")); m1.setSport(sport);
		 * m1.setSublines(false); Market m2 = new Market();
		 * m2.setName("First Set Winner"); m2.setMylines(Arrays.asList("team1",
		 * "team2")); m2.setSport(sport); m2.setSublines(false); Market m3 = new
		 * Market(); m3.setName("Total Sets");
		 * m3.setMylines(Arrays.asList("Over","Under")); m3.setSport(sport);
		 * m3.setSublines(true); Market m4 = new Market();
		 * m4.setName("1st Set Total Games"); m4.setMylines(Arrays.asList("Under",
		 * "Over")); m4.setSport(sport); m4.setSublines(true); Market m5 = new Market();
		 * m5.setName("Handicap Games"); m5.setMylines(Arrays.asList("team1", "team2"));
		 * m5.setSport(sport); m5.setSublines(true);
		 * 
		 * Market m6 = new Market(); m6.setName("To Win the Match");
		 * m6.setMylines(Arrays.asList("team1","team2")); m6.setSport(sport);
		 * m6.setSublines(false);
		 * 
		 * Market m7 = new Market(); m7.setName("Total Games");
		 * m7.setMylines(Arrays.asList("Over","Under")); m7.setSport(sport);
		 * m7.setSublines(true); Set<Market> markets = new LinkedHashSet<>();
		 * 
		 * markets.add(m6); markets.add(m2); markets.add(m1); markets.add(m3);
		 * markets.add(m4); markets.add(m5); markets.add(m7); sport.setMarkets(markets);
		 * session.save(m6); session.save(m2); session.save(m1); session.save(m3);
		 * session.save(m4); session.save(m5); session.save(m7); session.save(sport);
		 * session.getTransaction().commit(); session.close();
		 */

		/*
		 * System.out.println(new GetAllTipsters().ByCategoryAndOrderByProfit("free",new
		 * String[] {"all"},1));
		 */

		/*
		 * SessionFactory factory=HibSessionFactory.getFactory(); Session
		 * session=factory.openSession(); session.beginTransaction(); User user=new
		 * GetUserByProperty().byName("nain"); Tipster tipster2=new Tipster();
		 * tipster2.setTipster_category("free"); tipster2.setTipster_name("evan gorak");
		 * tipster2.setTipster_sport(new GetSportByProperty().byName("tennis")); Stats
		 * stat2=new Stats(); tipster2.setTipster_stats(stat2); tipster2.setUser(user);
		 * stat2.setStats_tipster(tipster2); session.save(tipster2);
		 * 
		 * session.getTransaction().commit(); session.close();
		 */
		/*
		 * FootballResultUpdate upd=new FootballResultUpdate(); upd.updateResults();
		 */

		/*
		 * SessionFactory factory=HibSessionFactory.getFactory(); Session
		 * session=factory.openSession(); session.beginTransaction(); User user=new
		 * GetUserByProperty().byName("keshav"); Chat chat1=new Chat();
		 * chat1.setChatstring("hello"); chat1.setUser(user); session.save(chat1);
		 * session.getTransaction().commit(); session.close();
		 */
		/*
		 * register a user SessionFactory factory=HibSessionFactory.getFactory();
		 * Session session=factory.openSession(); session.beginTransaction(); User
		 * user=new User(); user.setUsername("gund");
		 * user.setPassword(BCrypt.hashpw("gund", BCrypt.gensalt())); Preferences
		 * pref=new Preferences(); pref.setOddsformat("DECIMAL");
		 * pref.setTimezone("GMT+05:00"); user.setUser_preferences(pref);
		 * session.save(user);
		 * 
		 * 
		 * session.getTransaction().commit(); session.close();
		 * 
		 */

		/*
		 * SessionFactory factory = HibSessionFactory.getFactory(); Session session =
		 * factory.openSession(); Transaction tx = session.beginTransaction();
		 * SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); FileReader
		 * reader = new FileReader("WebContent/footyesterday.txt"); BufferedReader br =
		 * new BufferedReader(reader);
		 * 
		 * int i = 0; Long start = System.currentTimeMillis(); for (String line =
		 * br.readLine(); line != null; line = br.readLine()) { i = i + 1; String[] arr
		 * = line.split("\\|\\|"); List<String> list = new ArrayList<>(); for (String s
		 * : arr) { list.add(s); } String match_id = list.get(4); Criteria criteria =
		 * session.createCriteria(Matchtip.class);
		 * criteria.add(Restrictions.eq("match_id", match_id));
		 * criteria.setProjection(Projections.property("match_id")); String mid =
		 * (String) criteria.uniqueResult();
		 * 
		 * if (mid == null) { Matchtip match = new Matchtip();
		 * 
		 * criteria=session.createCriteria(Sport.class);
		 * criteria.add(Restrictions.eq("name",list.get(0))); Sport nsport=(Sport)
		 * criteria.uniqueResult();
		 * 
		 * match.setMatch_sport(new GetSportByProperty().byName(list.get(0), session));
		 * match.setMatch_sport(nsport); match.setLeague(list.get(1));
		 * match.setTournament(list.get(2));
		 * match.setMatch_date(sdf.parse(list.get(3))); match.setMatch_id(list.get(4));
		 * match.setTeam1(list.get(5)); match.setTeam2(list.get(6)); list.clear();
		 * session.save(match);
		 * 
		 * if (i % 20 == 0) { // 20, same as the JDBC batch size // flush a batch of
		 * inserts and release memory: session.flush(); session.clear(); }
		 * 
		 * } } Long end = System.currentTimeMillis(); System.out.println(end - start);
		 * tx.commit(); session.close();
		 */
		/*
		 * SessionFactory factory = HibSessionFactory.getFactory(); Session session =
		 * factory.openSession(); session.beginTransaction(); Sport sport = new Sport();
		 * sport.setName("football"); Market m1 = new Market();
		 * m1.setName("Full Time Result"); m1.setMylines(Arrays.asList("team1", "team2",
		 * "draw")); m1.setSport(sport); m1.setSublines(false); Market m2 = new
		 * Market(); m2.setName("Total Goals"); m2.setMylines(Arrays.asList("over",
		 * "under")); m2.setSport(sport); m2.setSublines(true); Market m3 = new
		 * Market(); m3.setName("Half Time Result");
		 * m3.setMylines(Arrays.asList("team1", "team2", "draw")); m3.setSport(sport);
		 * m3.setSublines(false); Market m4 = new Market();
		 * m4.setName("1st Half Goals"); m4.setMylines(Arrays.asList("over", "under"));
		 * m4.setSport(sport); m4.setSublines(true); Market m5 = new Market();
		 * m5.setName("Handicap Goals"); m5.setMylines(Arrays.asList("team1", "team2"));
		 * m5.setSport(sport); m5.setSublines(true);
		 * 
		 * Market m6 = new Market(); m6.setName("1st Half Score");
		 * m6.setMylines(Arrays.asList("0-0", "0-1", "0-2", "1-0", "2-0", "1-1", "1-2",
		 * "2-1", "2-2")); m6.setSport(sport); m6.setSublines(false);
		 * 
		 * Market m7 = new Market(); m7.setName("Full Time Score");
		 * m7.setMylines(Arrays.asList("0-0", "0-1", "0-2", "1-0", "2-0", "1-1", "1-2",
		 * "2-1", "2-2", "1-3", "3-1", "3-3", "0-3", "3-0", "2-3", "3-2"));
		 * m7.setSport(sport); m7.setSublines(false); Market m8 = new Market();
		 * m8.setName("Double Chance"); m8.setMylines(Arrays.asList("1x", "2x", "12"));
		 * m8.setSport(sport); m8.setSublines(false);
		 * 
		 * Market m9 = new Market(); m9.setName("Both Teams To Score");
		 * m9.setMylines(Arrays.asList("yes", "no")); m9.setSport(sport);
		 * m9.setSublines(false);
		 * 
		 * Market m10 = new Market(); m10.setName("Draw No Bet");
		 * m10.setMylines(Arrays.asList("team1", "team2")); m10.setSport(sport);
		 * m10.setSublines(false);
		 * 
		 * Market m11 = new Market(); m11.setName("Full Time Result(ext-pen)");
		 * m11.setMylines(Arrays.asList("team1", "team2","draw")); m11.setSport(sport);
		 * m11.setSublines(false); Set<Market> markets = new LinkedHashSet<>();
		 * markets.add(m1); markets.add(m2); markets.add(m3); markets.add(m4);
		 * markets.add(m5); markets.add(m6); markets.add(m7); markets.add(m8);
		 * markets.add(m9); markets.add(m10); markets.add(m11);
		 * sport.setMarkets(markets); session.save(m1); session.save(m2);
		 * session.save(m3); session.save(m4); session.save(m5); session.save(m6);
		 * session.save(m7); session.save(m8); session.save(m9); session.save(m10);
		 * session.save(m11); session.save(sport); session.getTransaction().commit();
		 * session.close();
		 */

		/*
		 * TennisResultUpdate tupd=new TennisResultUpdate(); tupd.updateResults();
		 */
		/*
		 * SessionFactory factory=HibSessionFactory.getFactory(); Session
		 * session=factory.openSession(); session.beginTransaction(); Subscription
		 * subscription=new Subscription(1,new
		 * GetTipsterByProperty().byName("tipstertest"),new
		 * GetUserByProperty().byName("test"),30.00);
		 * System.out.println(subscription.getStart_date());
		 * System.out.println(subscription.getEnd_date()); session.save(subscription);
		 * session.getTransaction().commit(); session.close();
		 */
	}

}
