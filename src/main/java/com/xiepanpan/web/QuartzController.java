package com.xiepanpan.web;

import com.xiepanpan.entity.JobEntity;
import com.xiepanpan.entity.TriggerEntity;
import com.xiepanpan.service.QuartzService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("quartz")
public class QuartzController {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private QuartzService quartzService1;


    @RequestMapping(value = "/listJob")
    public String listJob(HttpServletRequest request, HttpServletResponse response) throws SchedulerException {
        List<JobEntity> jobEntities = this.getSchedulerJobInfo();
        request.setAttribute("jobEntities",jobEntities);
        return "quartz/listJob";
    }

    /**
     * 获取所有的定时任务
     * @return
     */
    public List<JobEntity> getSchedulerJobInfo() throws SchedulerException {
        List<JobEntity> jobEntities = new ArrayList<>();
        List<String> triggerGroupNames = scheduler.getTriggerGroupNames();
        for (String triggerGroupName:triggerGroupNames) {
            Set<TriggerKey> triggerKeySet = scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(triggerGroupName));
            for (TriggerKey triggerKey:triggerKeySet){
                Trigger trigger = scheduler.getTrigger(triggerKey);
                //判断触发器类型
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    JobKey jobKey = cronTrigger.getJobKey();
                    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                    JobEntity jobEntity = new JobEntity();
                    //设置任务名称
                    jobEntity.setJobName(jobKey.getName());
                    //设置任务组名称
                    jobEntity.setJobGroup(jobKey.getGroup());

                    //设置触发器名称
                    jobEntity.setTriggerName(triggerKey.getName());
                    //设置触发器组名称
                    jobEntity.setTriggerGroupName(triggerKey.getGroup());
                    //设置时间表达式
                    jobEntity.setCronExpression( cronTrigger.getCronExpression());
                    //设置下次运行时间
                    jobEntity.setNextFireTime(cronTrigger.getNextFireTime());
                    //设置上次运行时间
                    jobEntity.setPreviousFireTime(cronTrigger.getPreviousFireTime());
                    //设置开始时间
                    jobEntity.setStartTime(cronTrigger.getStartTime());
                    //获取结束时间
                    jobEntity.setEndTime(cronTrigger.getEndTime());
                    jobEntity.setJobClass(jobDetail.getJobClass().getCanonicalName());
                    //获取任务状态
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(cronTrigger.getKey());
                    jobEntity.setJobStatus(String.valueOf(triggerState));
                    /**
                     *  NONE ,
                     NORMAL 正常,
                     PAUSED 暂停,
                     COMPLETE 完成状态,
                     ERROR 错误状态,
                     BLOCKED 锁定状态;
                     */
                    JobDataMap map = scheduler.getJobDetail(jobKey).getJobDataMap();
                    if (map!=null&&map.size()!=0) {
                        jobEntity.setCount((Integer) map.get("count"));
                        jobEntity.setJobDataMap(map);
                    } else {
                        jobEntity.setJobDataMap(new JobDataMap());
                    }
                    jobEntities.add(jobEntity);
                }
            }
        }
        return jobEntities;
    }

    /**
     * 跳转到新增页面
     * @return
     */
    @RequestMapping(value = "/toAdd")
    public String toAdd(Model model) {
        TriggerEntity triggerEntity = new TriggerEntity();
        model.addAttribute("triggerEntity",triggerEntity);
        return "quartz/addJob";
    }

    @RequestMapping(value = "add")
    public String add(TriggerEntity triggerEntity,Model model){
        quartzService1.addJob(triggerEntity);
        return "redirect:/quartz/listJob";
    }

    @RequestMapping("toEdit")
    public String toEdit(@RequestParam("jobName")String jobName,
                         @RequestParam("jobGroup")String jobGroup,
                         Model model) throws SchedulerException {

        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        List<CronTrigger> triggers = (List<CronTrigger>) scheduler.getTriggersOfJob(jobKey);
        CronTrigger cronTrigger = triggers.get(0);
        TriggerEntity triggerEntity = new TriggerEntity();
        triggerEntity.setJobName(jobKey.getName());
        triggerEntity.setJobGroupName(jobKey.getGroup());
        triggerEntity.setTriggerName(cronTrigger.getKey().getName());
        triggerEntity.setTriggerGroupName(cronTrigger.getKey().getGroup());
        triggerEntity.setCronExpression(cronTrigger.getCronExpression());
        triggerEntity.setClazz(jobDetail.getJobClass().getCanonicalName());
        model.addAttribute("triggerEntity",triggerEntity);
        return "quartz/editJob";
    }
}
