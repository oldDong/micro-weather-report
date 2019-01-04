package com.dongzj.spring.cloud.weather.service;

import com.dongzj.spring.cloud.weather.common.WeatherConstants;
import com.dongzj.spring.cloud.weather.vo.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/8/29
 * Time: 10:46
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    private final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini";

    /**
     * 缓存超时时间
     */
    private final Long TIME_OUT = 1800L;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_API + "?citykey=" + cityId;
        return this.doGetWeatherData(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_API + "?city=" + cityName;
        return this.doGetWeatherData(uri);
    }

    @Override
    public void syncDataByCityId(String cityId) {
        String uri = WEATHER_API + "?citykey=" + cityId;
        this.saveWeatherData(uri);
    }

    /**
     * 查询数据，并将数据存储进Redis
     *
     * @param uri
     */
    private void saveWeatherData(String uri) {
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        String key = uri;
        String strBody = null;

        key = WeatherConstants.PRE_KEY + key;
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        if (response.getStatusCodeValue() == 200) {
            strBody = response.getBody();
        }
        logger.info("redis, -key:{}", key);
        ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
    }

    /**
     * 查询天气数据
     *
     * @param uri
     * @return
     */
    private WeatherResponse doGetWeatherData(String uri) {
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        String key = uri;
        String strBody = null;
        //先查缓存，如果没有再查服务
        key = WeatherConstants.PRE_KEY + key;
        if (!this.redisTemplate.hasKey(key)) {
            logger.info("未找到 key: " + key);
            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
            if (response.getStatusCodeValue() == 200) {
                strBody = response.getBody();
            }
            key = WeatherConstants.PRE_KEY + key;
            ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
        } else {
            logger.info("找到 key: " + key + ", value=" + ops.get(key));
            strBody = ops.get(key);
        }

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weather = null;
        try {
            weather = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            logger.error("JSON反序列化异常！", e);
        }
        return weather;
    }
}
