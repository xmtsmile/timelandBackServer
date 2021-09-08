package com.study.note.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class TimerConfiguration {

    private Logger logger = LoggerFactory.getLogger(TimerConfiguration.class);

    @Scheduled(cron = "0 30 10 * * ?")
    private void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        logger.info("执行任务");
    }
}