package com.hankav.results;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hankav.dao.FootballResultUpdate;

public class ScheduleGetFootballResults implements Job {

	 @Override
	 public void execute(JobExecutionContext pArg0) throws JobExecutionException {
		 Logger log = Logger.getLogger(ScheduleGetFootballResults.class);
		 log.info("The mail sender job triggerd");
	   FootballResultUpdate footballresults=new FootballResultUpdate();
	   try {
		footballresults.updateResults();
	   } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	 
	 
	 }
}
