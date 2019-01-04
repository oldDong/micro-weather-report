package com.dongzj.spring.cloud.weather.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/8/29
 * Time: 10:42
 */
@Data
public class WeatherResponse implements Serializable {

    private static final long serialVersionUID = 3603534230291569309L;

    /**
     * 消息数据
     */
    private Weather data;

    /**
     * 消息状态
     */
    private String status;

    /**
     * 消息描述
     */
    private String desc;
}
