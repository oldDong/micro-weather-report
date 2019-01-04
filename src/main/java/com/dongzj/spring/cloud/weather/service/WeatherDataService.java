package com.dongzj.spring.cloud.weather.service;

import com.dongzj.spring.cloud.weather.vo.WeatherResponse;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/8/29
 * Time: 10:45
 */
public interface WeatherDataService {

    /**
     * 根据城市ID来查询天气数据
     *
     * @param cityId
     * @return
     */
    WeatherResponse getDataByCityId(String cityId);

    /**
     * 根据城市名称来查询天气数据
     * @param cityName
     * @return
     */
    WeatherResponse getDataByCityName(String cityName);

    /**
     * 根据城市ID同步天气数据
     *
     * @param cityId
     */
    void syncDataByCityId(String cityId);
}
