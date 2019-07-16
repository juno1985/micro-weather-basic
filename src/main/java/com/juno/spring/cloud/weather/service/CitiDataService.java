package com.juno.spring.cloud.weather.service;

import java.util.List;

import com.juno.spring.cloud.weather.vo.City;

public interface CitiDataService {

	/**
	 * 获取城市列表
	 */
	
	List<City> listCity() throws Exception	;
}
