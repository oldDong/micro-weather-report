package com.dongzj.spring.cloud.weather.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 未来天气
 *
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/8/29
 * Time: 10:36
 */
@Data
public class Forecast implements Serializable {

    private static final long serialVersionUID = 6892127246172211783L;

    /**
     * 日期
     */
    private String date;

    /**
     * 高温
     */
    private String high;

    /**
     * 风向
     */
    private String fengxiang;

    /**
     * 低温
     */
    private String low;

    /**
     * 风力
     */
    private String fengli;

    /**
     * 类型：大雨、阵雨、多云
     */
    private String type;
}
