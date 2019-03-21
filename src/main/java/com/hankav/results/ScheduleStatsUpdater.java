package com.hankav.results;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScheduleStatsUpdater implements Job {
	@Override
	public void execute(JobExecutionContext pArg0) throws JobExecutionException {

		UpdateStats stats = new UpdateStats();
		stats.execute();

	}

}
