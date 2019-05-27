package com.cmx.cloud.weather.data.acquisition;

import com.cmx.cloud.weather.data.acquisition.service.CityCodeParseService;
import com.cmx.cloud.weather.data.acquisition.service.WeatherInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataAcquisitionApplicationTests {

	@Autowired
	private CityCodeParseService cityCodeParseService;

	@Autowired
	private WeatherInfoService weatherInfoService;

	@Test
	public void contextLoads() {
//		try {
//			cityCodeParseService.getCityCode();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		System.out.println(weatherInfoService.getWeather());
	}

}
