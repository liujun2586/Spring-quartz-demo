package com.my.Springquartzdemo.controller;

import com.my.Springquartzdemo.entity.ScheduleJobEntity;
import com.my.Springquartzdemo.service.ScheduleJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description="定时任务管理")
@RestController
@RequestMapping("/schedule")
public class ScheduleJobController {


    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     */
    @ApiOperation(value = "查询", notes = "查询定时任务列表", httpMethod = "GET")
    @GetMapping("/list")
    public Object list() {
        //查询列表数据
        List<ScheduleJobEntity> jobList = scheduleJobService.queryList(null);
        return jobList;
    }

    /**
     * 定时任务信息
     */
    @ApiOperation(value = "查询", notes = "查询单个定时任务信息", httpMethod = "GET")
    @GetMapping("/info/{jobId}")
    public Object info(@PathVariable("jobId") Long jobId) {
        ScheduleJobEntity schedule = scheduleJobService.queryObject(jobId);
        return schedule;
    }

    /**
     * 保存定时任务
     */
    @ApiOperation(value = "保存", notes = "保存新的定时任务", httpMethod = "POST")
    @PostMapping("/save")
    public Object save(@RequestBody ScheduleJobEntity scheduleJob) {
        scheduleJobService.save(scheduleJob);
        return "success";
    }

    /**
     * 修改定时任务
     */
    @ApiOperation(value = "更新", notes = "更新的定时任务", httpMethod = "PATCH")
    @PatchMapping("/update")
    public Object update(@RequestBody ScheduleJobEntity scheduleJob) {
        scheduleJobService.update(scheduleJob);
        return "success";
    }

    /**
     * 删除定时任务
     */
    @ApiOperation(value = "删除", notes = "批量删除指定定时任务", httpMethod = "DELETE")
    @DeleteMapping("/delete")
    public Object delete(@RequestBody Long[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);
        return "success";
    }

    /**
     * 立即执行任务
     */
    @ApiOperation(value = "立即执行", notes = "立即执行一个定时任务", httpMethod = "POST")
    @PostMapping("/run")
    public Object run(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);
        return "success";
    }

    /**
     * 暂停定时任务
     */
    @ApiOperation(value = "暂停", notes = "暂停定时任务", httpMethod = "POST")
    @PostMapping("/pause")
    public Object pause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);
        return "success";
    }

    /**
     * 恢复定时任务
     */
    @ApiOperation(value = "恢复", notes = "恢复一个定时任务", httpMethod = "POST")
    @PostMapping("/resume")
    public Object resume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);
        return "success";
    }
}
