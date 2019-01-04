package com.dongzj.spring.cloud.weather.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/8/29
 * Time: 10:32
 */
@Data
public class Weather implements Serializable {

    private static final long serialVersionUID = -1991501742368675358L;

    /**
     * 城市
     */
    private String city;

    /**
     * 空气质量指数
     */
    private String aqi;

    /**
     * 温度
     */
    private String wendu;

    /**
     * 感冒指数
     */
    private String ganmao;

    private Yesterday yesterday;

    private List<Forecast> forecast;
}
