package com.xiepanpan.service;

import com.xiepanpan.entity.TriggerEntity;

public interface QuartzService {
    void addJob(TriggerEntity triggerEntity);
}
