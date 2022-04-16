package io.gitchecker.com.scheduler;
// TODO : 정해진 시간에 뭔가를 해줄 구조 필요
// TODO : Spring boot에 bean 형태로 스케줄러 어떻게 살리는건지 좀 알아봐야함.
// 스케줄러 작성 후에 미비된 쉘 스크립트 보강 필요...
// 모바일 서버를 쓸거니까 혹시 Request로도 동작이 가능한 형태가 나오면 아주 좋음.

import io.gitchecker.com.scheduler.jobs.CommitCheckJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
public class Crontab {  //쿼츠 크론탭으로 정해진 시간에 job 실행

    private SchedulerFactory schedulerFactory;
    private Scheduler scheduler;

    @PostConstruct
    public void start() throws SchedulerException{

        //JobDataMap은 Quartz에서 실행되는 Job에 Key-value 형식으로 값을 전달할수 있다.
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobName", "CommitCheckJob");
        JobDetail jobDetail = newJob(CommitCheckJob.class) //job Data 주입
                .usingJobData(jobDataMap)
                .build();
        Trigger trigger = newTrigger()
                .withSchedule(cronSchedule("5 * * * * ?")) // 0 15 20 ? * * (매일 오후 8시 15분)
                .build();// 스케줄러 실행 및 JobDetail과 Trigger 정보로 스케줄링

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }



    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // TODO Auto-generated method stub

    }

}
