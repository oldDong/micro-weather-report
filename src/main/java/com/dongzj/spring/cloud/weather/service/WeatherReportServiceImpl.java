package com.dongzj.spring.cloud.weather.service;

import com.dongzj.spring.cloud.weather.vo.Weather;
import com.dongzj.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/8/30
 * Time: 17:06
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService{

    @Autowired
    private WeatherDataService weatherDataServiceImpl;

    @Override
    public Weather getDataByCityId(String cityId) {
        WeatherResponse response = weatherDataServiceImpl.getDataByCityId(cityId);
        return response.getData();
    }
}
