package io.gitchecker.com.scheduler.jobs;
//실제로 커밋이 되어있는지 확인해주는 잡.

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CommitCheckJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("## 잡이 실행되었음.");
        String name = jobExecutionContext.getJobDetail().getJobDataMap().get("jobName").toString();
        System.out.println("## Job Name : "+name);

    }
}
