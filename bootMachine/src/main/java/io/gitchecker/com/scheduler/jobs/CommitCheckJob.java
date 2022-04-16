package io.gitchecker.com.scheduler.jobs;
//실제로 커밋이 되어있는지 확인해주는 잡.

import io.gitchecker.com.gitCheckManager.service.GitCheckerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommitCheckJob implements Job {

    @Autowired
    GitCheckerService gitCheckerService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("## 잡이 실행되었음.");
        try{
            gitCheckerService.autoCommitDaily();
        }catch (NullPointerException ne){
            log.error("NE!!!!");
            ne.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
