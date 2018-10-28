package com.xiepanpan.entity;

import lombok.Data;

/**
 * @program: quartz_ssm
 * @description: 触发器实体类
 * @author: xiepanpan
 * @create: 2018-10-27 21:20
 **/
@Data
public class TriggerEntity {

    /**
     * 时间表达式
     */
    private String cronExpression;
    /**
     * 任务类名
     */
    private String clazz;
    /**
     * 任务名
     */
    private String jobName;
    /**
     * 任务组名
     */
    private String jobGroupName;
    /**
     * 触发器名
     */
    private String triggerName;
    /**
     * 触发器组名
     */
    private String triggerGroupName;
}
