package com.dongzj.spring.cloud.weather.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 昨日天气
 *
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/8/29
 * Time: 10:33
 */
@Data
public class Yesterday implements Serializable {

    private static final long serialVersionUID = 6024473610669205374L;

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
    private String fx;

    /**
     * 低温
     */
    private String low;

    /**
     * 风力
     */
    private String fl;

    /**
     * 类型：大雨、多云、晴...
     */
    private String type;
}
