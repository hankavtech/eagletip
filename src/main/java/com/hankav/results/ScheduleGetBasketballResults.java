package com.hankav.results;
import java.io.IOException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.hankav.dao.BasketballResultUpdate;


public class ScheduleGetBasketballResults implements Job{

	 @Override
	 public void execute(JobExecutionContext pArg0) throws JobExecutionException {
	  
	   BasketballResultUpdate basketballresults=new BasketballResultUpdate();
	   try {
		basketballresults.updateResults();
	   } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	 
	 
	 }
}
