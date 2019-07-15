package com.juno.spring.cloud.weather.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juno.spring.cloud.weather.vo.WeatherResponse;
@Service
public class WeatherDataServiceImpl implements WeatherDataService {

	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		// http://wthrcdn.etouch.cn/weather_mini?citykey=10128060
		String uri = WEATHER_URI + "citykey=" + cityId;
		return doGetWeather(uri);
	}

	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		// http://wthrcdn.etouch.cn/weather_mini?city=深圳
		String uri = WEATHER_URI + "city=" + cityName;
		return doGetWeather(uri);
	}

	private WeatherResponse doGetWeather(String uri) {
		ResponseEntity<String> wresp = restTemplate.getForEntity(uri, String.class);
		String respStr = wresp.getBody();
		ObjectMapper jsonMapper = new ObjectMapper();
		
		WeatherResponse resp  = null;
		if (wresp.getStatusCodeValue() == 200) {
			try {
				resp = jsonMapper.readValue(respStr, WeatherResponse.class);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resp;
	}

}
