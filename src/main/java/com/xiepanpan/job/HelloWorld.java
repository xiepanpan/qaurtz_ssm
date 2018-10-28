package com.xiepanpan.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @program: quartz_ssm
 * @description:
 * @author: xiepanpan
 * @create: 2018-10-27 20:46
 **/
public class HelloWorld implements Job{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("======hello world======="+new Date());
    }
}
