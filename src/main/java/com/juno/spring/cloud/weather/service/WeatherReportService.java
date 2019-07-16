package com.juno.spring.cloud.weather.service;

import com.juno.spring.cloud.weather.vo.Weather;

public interface WeatherReportService {
	
	public Weather reportByCityId(String cityId);

}
