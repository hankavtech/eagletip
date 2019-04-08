package com.hankav.results;

import java.io.IOException;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hankav.dao.MatchReader;

public class ScheduleMatchesUpdater implements Job {

	@Override
	public void execute(JobExecutionContext pArg0) throws JobExecutionException {
		Logger log = Logger.getLogger(ScheduleGetFootballResults.class);
		log.info("The matches updater triggered");
		for (int i = 1; i <= 5; i++) {
			String sport = null;
			if (i == 1) {
				sport = "football";
			} else if (i == 2) {
				sport = "tennis";
			} else if (i == 3) {
				sport = "basketball";
			} else if (i == 4) {
				sport = "hockey";
			} else if (i == 5) {
				sport = "cricket";
			}

			MatchReader reader = new MatchReader();
			try {
				reader.readmatches(sport);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println(sport + "  done");

		}

	}
}
