package io.gitchecker.com.scheduler.jobs;
//실제로 커밋이 되어있는지 확인해주는 잡.

import io.gitchecker.com.gitCheckManager.service.GitCheckerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommitCheckJob extends QuartzJobBean {

    private GitCheckerService gitCheckerService;

    @Autowired
    public void setGitCheckerService(GitCheckerService gitCheckerService){
        this.gitCheckerService = gitCheckerService;
    }

    @Override
    public void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("## 잡이 실행되었음.");

        this.gitCheckerService.autoCommitDaily();

    }
}
