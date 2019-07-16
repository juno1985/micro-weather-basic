package com.juno.spring.cloud.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.juno.spring.cloud.weather.service.CitiDataService;
import com.juno.spring.cloud.weather.service.WeatherReportService;
import com.juno.spring.cloud.weather.vo.City;
import com.juno.spring.cloud.weather.vo.Weather;
@Controller
@RequestMapping("/report")
public class WeatherReportController {
	@Autowired
	private WeatherReportService weatherReportService;
	@Autowired
	private CitiDataService citiDataService;
	@GetMapping("/cityId/{cityId}")
	public ModelAndView reportByCityId(@PathVariable("cityId") String cityId, Model model) {
		Weather weather = weatherReportService.reportByCityId(cityId);
		List<City> cityList = null;
		try {
			cityList = citiDataService.listCity();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("title", "Juno's Weather Report");
		model.addAttribute("cityId", cityId);
		model.addAttribute("cityList", cityList);
		model.addAttribute("report", weather);
		return new ModelAndView("weather/report", "reportModel", model);
	}
}
