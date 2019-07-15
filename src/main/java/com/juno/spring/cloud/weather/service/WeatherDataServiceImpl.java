package com.juno.spring.cloud.weather.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juno.spring.cloud.weather.vo.WeatherResponse;
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
	
	private static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);
	//set time out for redis
	private final long timeout = 1800L;

	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate; 

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
		
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		String key = uri;
		WeatherResponse resp  = null;
		String respStr = null;
		if(stringRedisTemplate.hasKey(key)) {
			respStr = ops.get(key);
			logger.info("Hits Redis!");
		}else {
			ResponseEntity<String> wresp = restTemplate.getForEntity(uri, String.class);
			if (wresp.getStatusCodeValue() != 200) {
				return null;
			}
			respStr = wresp.getBody();
			logger.info("Miss hitting Redis");
			
			ops.set(key, respStr, timeout, TimeUnit.SECONDS);
		}
		
		try {
			ObjectMapper jsonMapper = new ObjectMapper();
			resp = jsonMapper.readValue(respStr, WeatherResponse.class);
		} catch (Exception e) {
			logger.error("Error","Json to Object Conversion Error");
		} 
		
		return resp;
	}

}
