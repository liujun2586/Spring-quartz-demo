package com.my.Springquartzdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("testSchedule")
public class TestSchedule {

    private Logger logger = LoggerFactory.getLogger(TestSchedule.class);

    public void testSchedulerFunction(){
        logger.info("定时测试定时任务执行,当前时间:{}"+new Date().toLocaleString());
    }

}
