package com.lemnisk.jobscheduler.scheduler;

import com.lemnisk.jobscheduler.model.ScheduledJob;
import com.lemnisk.jobscheduler.service.KafkaProducerService;
import com.lemnisk.jobscheduler.repository.ScheduledJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class JobSchedulerChecker {

    @Autowired
    private ScheduledJobRepository jobRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Scheduled(fixedRate = 60000) // every 60 seconds
    public void checkAndSendKafkaMessages() {
        ZonedDateTime now = ZonedDateTime.now();
        List<ScheduledJob> dueJobs = jobRepository.findByScheduleTimeBeforeAndStatus(now, "PENDING");

        for (ScheduledJob job : dueJobs) {
            String message = "Job due: "  + job.getJobName();
            kafkaProducerService.sendMessage("job-events", message);

            job.setStatus("SENT"); 
        }
    }
}
