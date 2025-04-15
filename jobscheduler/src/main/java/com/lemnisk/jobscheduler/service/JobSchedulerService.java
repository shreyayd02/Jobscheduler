package com.lemnisk.jobscheduler.service;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;
import java.time.ZoneId;
import java.util.List;


import com.lemnisk.jobscheduler.model.ScheduledJob;
import com.lemnisk.jobscheduler.repository.ScheduledJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;




@Service
public class JobSchedulerService {

    @Autowired
    private ScheduledJobRepository jobRepository;

    @Scheduled(fixedRate = 60000)
    public void runScheduledJobs() {
        List<ScheduledJob> dueJobs = jobRepository.findAll().stream()
            .filter(job ->
    job.getScheduleTime() != null &&
    job.getTimezone() != null &&
    job.getStatus() != null &&
    job.getStatus().equals("scheduled") &&
    ZoneId.getAvailableZoneIds().contains(job.getTimezone()) &&
    job.getScheduleTime().isBefore(ZonedDateTime.now(ZoneId.of(job.getTimezone())))
)

            .collect(Collectors.toList());

        for (ScheduledJob job : dueJobs) {
            try {
                // Replace with actual execution logic
                System.out.println("Running binary: " + job.getBinaryPath());
                job.setStatus("success");
            } catch (Exception e) {
                job.setStatus("failed");
            }
            jobRepository.save(job);
        }
    }
}