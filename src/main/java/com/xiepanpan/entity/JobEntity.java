package com.xiepanpan.entity;

import lombok.Data;
import org.quartz.JobDataMap;

import java.util.Date;

@Data
public class JobEntity {

    /**
     * 定时任务名称
     */
    private String jobName;
    /**
     * 任务组名称
     */
    private String jobGroup;
    /**
     * 触发器名称
     */
    private String triggerName;
    /**
     * 触发器组名称
     */
    private String triggerGroupName;
    /**
     * 时间表达式
     */
    private String cronExpression;
    /**
     * 上次运行时间
     */
    private Date previousFireTime;
    /**
     * 下次运行时间
     */
    private Date nextFireTime;
    /**
     * 任务状态
     */
    private String jobStatus;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 运行次数
     */
    private int count;
    private JobDataMap jobDataMap;
    /**
     * 任务类名
     */
    private String jobClass;
}
