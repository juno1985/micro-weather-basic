package com.juno.spring.cloud.weather.service;

import com.juno.spring.cloud.weather.vo.WeatherResponse;

public interface WeatherDataService {
	
	WeatherResponse getDataByCityId(String cityId);
	WeatherResponse getDataByCityName(String cityName);
	void syncDataToRedis();
}
