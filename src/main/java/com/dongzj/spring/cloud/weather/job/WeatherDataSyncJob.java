package com.dongzj.spring.cloud.weather.job;

import com.dongzj.spring.cloud.weather.service.CityDataService;
import com.dongzj.spring.cloud.weather.service.WeatherDataService;
import com.dongzj.spring.cloud.weather.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * 同步天气数据
 * <p>
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/8/29
 * Time: 20:23
 */
public class WeatherDataSyncJob extends QuartzJobBean {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private CityDataService cityDataServiceImpl;

    @Autowired
    private WeatherDataService weatherDataServiceImpl;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("天气数据同步任务 start");

        //读取城市列表
        List<City> cityList = null;
        try {
            cityList = cityDataServiceImpl.listCity();
        } catch (Exception e) {
            logger.error("获取城市信息异常！", e);
        }

        for (City city : cityList) {
            String cityId = city.getCityId();
            logger.info("天气数据同步任务中， -cityId:{}", cityId);

            weatherDataServiceImpl.syncDataByCityId(cityId);
        }

        logger.info("天气数据同步任务 end");
    }
}
