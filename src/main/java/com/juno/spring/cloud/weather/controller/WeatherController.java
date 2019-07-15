package com.juno.spring.cloud.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juno.spring.cloud.weather.service.WeatherDataService;
import com.juno.spring.cloud.weather.vo.WeatherResponse;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	@Autowired
	private WeatherDataService weatherDataService;

	@GetMapping("/cityId/{cityId}")
	public WeatherResponse getWeatherByCitiId(@PathVariable("cityId") String cityId) {
		return weatherDataService.getDataByCityId(cityId);
	}
	
	@GetMapping("/cityName/{cityName}")
	public WeatherResponse getWeatherByCitiName(@PathVariable("cityName") String cityName) {
		return weatherDataService.getDataByCityName(cityName);
	}
	
}
