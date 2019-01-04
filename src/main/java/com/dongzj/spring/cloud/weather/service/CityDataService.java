package com.dongzj.spring.cloud.weather.service;

import com.dongzj.spring.cloud.weather.vo.City;

import java.util.List;

/**
 * 城市数据服务接口
 *
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/8/30
 * Time: 14:17
 */
public interface CityDataService {

    /**
     * 获取城市列表
     *
     * @return
     * @throws Exception
     */
    List<City> listCity() throws Exception;
}
