package com.juno.spring.cloud.weather.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.juno.spring.cloud.weather.util.XmlBuilder;
import com.juno.spring.cloud.weather.vo.City;
import com.juno.spring.cloud.weather.vo.CityList;
@Service
public class CitiDataServiceImpl implements CitiDataService {

	@Override
	public List<City> listCity() throws Exception {
		//读取XML文件
		Resource resource = new ClassPathResource("citylist.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(),"utf-8"));
		StringBuffer buff = new StringBuffer();
		String line = "";
		while((line=br.readLine())!=null) {
			buff.append(line);
		}
		br.close();
		//XML转化为java对象
		CityList cityList = (CityList) XmlBuilder.xmlStringToObject(CityList.class, buff.toString());
		return cityList.getCityList();
	}
}
