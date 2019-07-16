package com.juno.spring.cloud.weather.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.juno.spring.cloud.weather.service.WeatherDataService;

public class WeatherDataSyncJob extends QuartzJobBean {
	
	private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);
	
	@Autowired
	private WeatherDataService weatherDataService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		weatherDataService.syncDataToRedis();
	}

}
