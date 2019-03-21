package com.hankav.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subscription_id;
	private Double subscription_price;
	@ManyToOne(fetch = FetchType.LAZY)
	private Tipster subscribed_tipster;
	@ManyToOne(fetch = FetchType.LAZY)
	private User subscriber;
	private Integer subscription_plan = 1;
	private Date start_date;
	private Date end_date;
	private Double discount = 0.0;
	private String subscription_status = "active";
	private boolean switchable = false;
	private boolean tipster_paymentmade = false;
	private boolean emailsent = false;
	private Date tipster_paymentdate;
	@ElementCollection(fetch = FetchType.LAZY)
	private List<Date> pauses = new ArrayList<>();

	private String subscription_type;

	public Subscription() {

	}

	public Subscription(Integer plan, Tipster tipster, User user, Double price) throws ParseException {
		this.subscription_plan = plan;
		this.subscribed_tipster = tipster;
		this.subscriber = user;
		this.subscription_price = price;

		/*
		 * SimpleDateFormat fm=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		 * fm.setTimeZone(TimeZone.getTimeZone("GMT+00:00")); String txt=fm.format(new
		 * Date());
		 * 
		 * 
		 * Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+00:00"));
		 * c.setTime(fm.parse(txt)); this.start_date=fm.parse(txt);
		 * c.add(Calendar.DATE,30); this.end_date=fm.parse(fm.format(c.getTime()));
		 */
	}

	public int getSubscription_id() {
		return subscription_id;
	}

	public void setSubscription_id(int subscription_id) {
		this.subscription_id = subscription_id;
	}

	public Double getSubscription_price() {
		return subscription_price;
	}

	public void setSubscription_price(Double subscription_price) {
		this.subscription_price = subscription_price;
	}

	public Tipster getSubscribed_tipster() {
		return subscribed_tipster;
	}

	public void setSubscribed_tipster(Tipster subscribed_tipster) {
		this.subscribed_tipster = subscribed_tipster;
	}

	public User getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(User subscriber) {
		this.subscriber = subscriber;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		/*
		 * Calendar c = Calendar.getInstance(); c.setTime(new
		 * SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse("12/12/1992 00:00:00")); switch
		 * (this.subscription_plan) { case 1: c.add(Calendar.DATE, 30); this.end_date =
		 * c.getTime(); break; case 3: this.end_date = new
		 * Date((this.getStart_date().getTime() + (3 * 30 * 24 * 60 * 60 * 1000)));
		 * break; case 6: this.end_date = new Date((this.getStart_date().getTime() + (6
		 * * 30 * 24 * 60 * 60 * 1000))); break; case 12: this.end_date = new
		 * Date((this.getStart_date().getTime() + (12 * 30 * 24 * 60 * 60 * 1000)));
		 * break; }
		 */
		return this.end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getSubscription_status() {
		return subscription_status;
	}

	public void setSubscription_status(String subscription_status) {
		this.subscription_status = subscription_status;
	}

	public boolean isSwitchable() {
		return switchable;
	}

	public void setSwitchable(boolean switchable) {
		this.switchable = switchable;
	}

	public boolean isTipster_paymentmade() {
		return tipster_paymentmade;
	}

	public void setTipster_paymentmade(boolean tipster_paymentmade) {
		this.tipster_paymentmade = tipster_paymentmade;
	}

	public Integer getSubscription_plan() {
		return subscription_plan;
	}

	public void setSubscription_plan(Integer subscription_plan) {
		this.subscription_plan = subscription_plan;
	}

	public List<Date> getPauses() {
		return pauses;
	}

	public void setPauses(List<Date> pauses) {
		this.pauses = pauses;
	}

	public Date getTipster_paymentdate() {
		return tipster_paymentdate;
	}

	public void setTipster_paymentdate(Date tipster_paymentdate) {
		this.tipster_paymentdate = tipster_paymentdate;
	}

	public boolean getEmailsent() {
		return emailsent;
	}

	public void setEmailsent(boolean emailsent) {
		this.emailsent = emailsent;
	}

	public String getSubscription_type() {
		return subscription_type;
	}

	public void setSubscription_type(String subscription_type) {
		this.subscription_type = subscription_type;
	}

}
