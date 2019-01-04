package com.dongzj.spring.cloud.weather.service;

import com.dongzj.spring.cloud.weather.vo.Weather;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/8/30
 * Time: 17:05
 */
public interface WeatherReportService {

    /**
     * 根据城市ID查询天气信息
     *
     * @param cityId
     * @return
     */
    Weather getDataByCityId(String cityId);
}
