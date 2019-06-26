package com.my.Springquartzdemo.dao;

import com.my.Springquartzdemo.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ScheduleJobDao {
    List<ScheduleJobEntity> queryList(HashMap<String, Object> stringObjectHashMap);

    ScheduleJobEntity queryObject(Long jobId);

    int queryTotal(Map<String, Object> map);

    void save(ScheduleJobEntity scheduleJob);

    void update(ScheduleJobEntity scheduleJob);

    void deleteBatch(Long[] jobIds);

    int updateBatch(Map<String, Object> map);
}
