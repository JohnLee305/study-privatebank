package io.gitchecker.com.scheduler.jobs;
//실제로 커밋이 되어있는지 확인해주는 잡.

import io.gitchecker.com.gitCheckManager.service.GitCheckerService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public class CommitCheckJob implements Job {

    private final GitCheckerService gitCheckerService;

    @Autowired
    public  CommitCheckJob(GitCheckerService  gitCheckerService ){
        this.gitCheckerService = gitCheckerService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("## 잡이 실행되었음.");

        String name = "";
        //매일단위로 실행되는 오토커밋
//        try {
//            gitCheckerService.autoCommitDaily();
//            name = jobExecutionContext.getJobDetail().getJobDataMap().get("jobName").toString();

//        } catch (Exception e) {
//            log.error("Encountered job execution exception!", e);
//        }finally {
//            System.out.println("## Job Name : " + name);
//        }

            gitCheckerService.autoCommitDaily();
            name = jobExecutionContext.getJobDetail().getJobDataMap().get("jobName").toString();

    }
}
