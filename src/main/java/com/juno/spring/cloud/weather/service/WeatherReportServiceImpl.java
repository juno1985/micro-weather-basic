package com.juno.spring.cloud.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juno.spring.cloud.weather.vo.Weather;
import com.juno.spring.cloud.weather.vo.WeatherResponse;
@Service
public class WeatherReportServiceImpl implements WeatherReportService {

	@Autowired
	private WeatherDataService weatherDataService;
	@Override
	public Weather reportByCityId(String cityId) {
		WeatherResponse wresp = weatherDataService.getDataByCityId(cityId);
		Weather weather = wresp.getData();
		return weather;
	}

}
