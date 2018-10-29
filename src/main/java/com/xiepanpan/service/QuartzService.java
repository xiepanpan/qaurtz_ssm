package com.xiepanpan.service;

import com.xiepanpan.entity.TriggerEntity;

public interface QuartzService {
    void addJob(TriggerEntity triggerEntity);

    void editJob(String oldJobName, String oldJobGroupName, String oldTriggerName, String oldTriggerGroup, TriggerEntity triggerEntity);

    void pauseJob(String jobName, String jobGroup);

    void resumeJob(String jobName, String jobGroup);

    void deleteJob(TriggerEntity triggerEntity);
}
