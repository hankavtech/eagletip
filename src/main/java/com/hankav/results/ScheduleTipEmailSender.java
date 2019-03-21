package com.hankav.results;
import java.text.ParseException;

import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hankav.dao.EmailSender;


public class ScheduleTipEmailSender implements Job {

	 Logger log = Logger.getLogger(ScheduleTipEmailSender.class);
	
	 @Override
	 public void execute(JobExecutionContext pArg0) throws JobExecutionException {
		 log.info("The mail sender job triggerd");
	   EmailSender sender=new EmailSender();
	   try {
		sender.sendemails();
	   } catch (AddressException | ParseException e) {
		e.printStackTrace();
	   }
	 
	 
	 }
}
