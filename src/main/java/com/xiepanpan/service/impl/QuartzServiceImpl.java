package com.xiepanpan.service.impl;

import com.xiepanpan.entity.TriggerEntity;
import com.xiepanpan.service.QuartzService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: quartz_ssm
 * @description: quartz业务层
 * @author: xiepanpan
 * @create: 2018-10-27 21:41
 **/
@Service("quartzService1")
public class QuartzServiceImpl implements QuartzService{

    //调度器
    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(TriggerEntity triggerEntity) {

        try {
            //创建作业
            JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(triggerEntity.getClazz()))
                                    .withIdentity(triggerEntity.getJobName(),triggerEntity.getJobGroupName())
                                    .build();
            //创建触发器
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerEntity.getTriggerName(), triggerEntity.getTriggerGroupName())
                    .withSchedule(CronScheduleBuilder.cronSchedule(triggerEntity.getCronExpression()))
                    .build();
            //告诉调度器使用该触发器来安排作业
            scheduler.scheduleJob(jobDetail,cronTrigger);
            //启动
            if (!scheduler.isShutdown()){
                scheduler.start();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
