package com.my.Springquartzdemo.service;

import com.my.Springquartzdemo.entity.ScheduleJobLogEntity;
import org.springframework.stereotype.Service;

@Service("scheduleJobLogService")
public class ScheduleJobLogService {
    public void save(ScheduleJobLogEntity log) {
        System.out.println("=====开始保存定时任务日志=====");
    }
}
