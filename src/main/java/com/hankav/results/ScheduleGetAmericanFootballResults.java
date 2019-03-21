package com.hankav.results;

import java.io.IOException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hankav.dao.AmericanFootballResultUpdate;

public class ScheduleGetAmericanFootballResults implements Job {
	@Override
	public void execute(JobExecutionContext pArg0) throws JobExecutionException {

		AmericanFootballResultUpdate americanfootballresults = new AmericanFootballResultUpdate();
		try {
			americanfootballresults.updateResults();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
