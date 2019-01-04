package com.dongzj.spring.cloud.weather.config;

import com.dongzj.spring.cloud.weather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Quartz 配置类
 * <p>
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/8/29
 * Time: 20:25
 */
@Configuration
public class QuartzConfiguration {

    /**
     * 更新频率
     */
    private final int TIME = 1800;

    /**
     * 定义Job
     *
     * @return
     */
    @Bean
    public JobDetail weatherDataSyncJobJobDetail() {
        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob")
                .storeDurably().build();
    }

    /**
     * 定义了一个执行频率为每2秒执行一次的Trigger
     *
     * @return
     */
    @Bean
    public Trigger simpleJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(TIME).repeatForever();
        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobJobDetail())
                .withIdentity("weatherDataSyncTrigger")
                .withSchedule(scheduleBuilder).build();
    }
}
