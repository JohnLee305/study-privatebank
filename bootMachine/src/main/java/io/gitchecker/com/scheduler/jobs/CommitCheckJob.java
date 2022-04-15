package io.gitchecker.com.scheduler.jobs;
//실제로 커밋이 되어있는지 확인해주는 잡.

import io.gitchecker.com.gitCheckManager.service.GitCheckerService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;


public class CommitCheckJob implements Job {
    @Autowired
    GitCheckerService gitCheckerService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("## 잡이 실행되었음.");

        //매일단위로 실행되는 오토커밋
        gitCheckerService.autoCommitDaily();
        String name = jobExecutionContext.getJobDetail().getJobDataMap().get("jobName").toString();
        try {
            Job job = jobLocator.getJob(jobName);
            Map<String, JobParameter> parametersMap = new HashMap<>();
            parametersMap.put("timestamp", new JobParameter(System.currentTimeMillis()));
            JobParameters jobParameters = new JobParameters(parametersMap);
            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
            log.info("{}_{} was completed successfully", job.getName(), jobExecution.getId());
        } catch (Exception e) {
            log.error("Encountered job execution exception!", e);
        }
        System.out.println("## Job Name : "+name);

    }
}
