package com.juno.spring.cloud.weather.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.juno.spring.cloud.weather.job.WeatherDataSyncJob;

@Configuration
public class QuarzConfiguration {

	private static final int INTERVAL = 1800;
	
	//JobDetail
	@Bean
	public JobDetail weatherDataSyncJobDetail() {
		return JobBuilder.newJob(WeatherDataSyncJob.class)
		.withIdentity("WeatherDataSyncJob").storeDurably().build();
	}
	//Trigger
	@Bean
	public Trigger weatherDataSyncTrigger() {
		//每两秒执行
		SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(INTERVAL).repeatForever();
		return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
				.withIdentity("weatherDataSyncTrigger").startNow().withSchedule(schedBuilder).build();
	}
}