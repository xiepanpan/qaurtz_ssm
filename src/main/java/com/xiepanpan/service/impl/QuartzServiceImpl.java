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
public class QuartzServiceImpl implements QuartzService {

    //调度器
    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(TriggerEntity triggerEntity) {

        try {
            //创建作业
            JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(triggerEntity.getClazz()))
                    .withIdentity(triggerEntity.getJobName(), triggerEntity.getJobGroupName())
                    .build();
            //创建触发器
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerEntity.getTriggerName(), triggerEntity.getTriggerGroupName())
                    .withSchedule(CronScheduleBuilder.cronSchedule(triggerEntity.getCronExpression()))
                    .build();
            //告诉调度器使用该触发器来安排作业
            scheduler.scheduleJob(jobDetail, cronTrigger);
            //启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改定时任务信息
     *  @param oldJobName
     * @param oldJobGroupName
     * @param oldTriggerName
     * @param oldTriggerGroup
     * @param triggerEntity
     */
    @Override
    public void editJob(String oldJobName, String oldJobGroupName, String oldTriggerName, String oldTriggerGroup, TriggerEntity triggerEntity) {
        //先把原有的删掉 再调用addJob()方法添加新的
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(oldTriggerName, oldTriggerGroup);
            JobKey jobKey = JobKey.jobKey(oldJobName, oldJobGroupName);
            //暂停触发器
            scheduler.pauseTrigger(triggerKey);
            //移除触发器
            scheduler.unscheduleJob(triggerKey);
            //删除
            scheduler.deleteJob(jobKey);
            addJob(triggerEntity);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停定时任务
     * @param jobName
     * @param jobGroup
     */
    @Override
    public void pauseJob(String jobName, String jobGroup) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复定时任务
     * @param jobName
     * @param jobGroup
     */
    @Override
    public void resumeJob(String jobName, String jobGroup) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName,jobGroup));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除定时任务
     * @param triggerEntity
     */
    @Override
    public void deleteJob(TriggerEntity triggerEntity) {
        try {
            //暂停触发器
            scheduler.pauseTrigger(TriggerKey.triggerKey(triggerEntity.getTriggerName(),triggerEntity.getTriggerGroupName()));
            //删除触发器
            scheduler.unscheduleJob(TriggerKey.triggerKey(triggerEntity.getTriggerName(),triggerEntity.getTriggerGroupName()));
            //删除任务
            scheduler.deleteJob(JobKey.jobKey(triggerEntity.getJobName(),triggerEntity.getJobGroupName()));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
