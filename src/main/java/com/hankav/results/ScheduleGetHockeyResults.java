package com.hankav.results;

import java.io.IOException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hankav.dao.HockeyResultUpdate;

public class ScheduleGetHockeyResults implements Job {

	@Override
	public void execute(JobExecutionContext pArg0) throws JobExecutionException {

		HockeyResultUpdate hockeyresults = new HockeyResultUpdate();
		try {
			hockeyresults.updateResults();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
