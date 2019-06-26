package com.my.Springquartzdemo.service;

import com.my.Springquartzdemo.dao.ScheduleJobDao;
import com.my.Springquartzdemo.entity.ScheduleJobEntity;
import com.my.Springquartzdemo.utils.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleJobService {
    @Autowired
    @SuppressWarnings("all")
    private Scheduler scheduler;
    @Autowired
    @SuppressWarnings("all")
    private ScheduleJobDao schedulerJobDao;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() throws SchedulerException {
        scheduler.start();
        List<ScheduleJobEntity> scheduleJobList = schedulerJobDao.queryList(new HashMap<String, Object>());
        for (ScheduleJobEntity scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    /**
     * 根据ID，查询定时任务
     */
    public ScheduleJobEntity queryObject(Long jobId) {
        return schedulerJobDao.queryObject(jobId);
    }

    /**
     * 查询定时任务列表
     */
    public List<ScheduleJobEntity> queryList(Map<String, Object> map) {
        return schedulerJobDao.queryList(null);
    }

    /**
     * 查询总数
     */
    public int queryTotal(Map<String, Object> map) {
        return schedulerJobDao.queryTotal(map);
    }

    /**
     * 保存定时任务
     */
    @Transactional
    public void save(ScheduleJobEntity scheduleJob) {
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus(0);
        schedulerJobDao.save(scheduleJob);
        // 创建定时任务
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    /**
     * 更新定时任务
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(ScheduleJobEntity scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);

        schedulerJobDao.update(scheduleJob);
    }

    /**
     * 批量删除定时任务
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }

        //删除数据
        schedulerJobDao.deleteBatch(jobIds);
    }

    /**
     * 批量更新定时任务状态
     */
    public int updateBatch(Long[] jobIds, int status) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", jobIds);
        map.put("status", status);
        return schedulerJobDao.updateBatch(map);
    }

    /**
     * 立即执行
     */
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.run(scheduler, queryObject(jobId));
        }
    }

    /**
     * 暂停运行
     */
    @Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.pauseJob(scheduler, jobId);
        }

        updateBatch(jobIds, 1);
    }

    /**
     * 恢复运行
     */
    @Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.resumeJob(scheduler, jobId);
        }

        updateBatch(jobIds, 0);
    }
}
