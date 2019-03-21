package com.hankav.results;

import java.io.IOException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hankav.dao.TennisResultUpdate;

public class ScheduleGetTennisResults implements Job {
	
	 @Override
	 public void execute(JobExecutionContext pArg0) throws JobExecutionException {
	  
	   TennisResultUpdate tennisresults=new TennisResultUpdate();
	   try {
		tennisresults.updateResults();
	   } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	 
	 
	 }

}
