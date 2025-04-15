package com.lemnisk.jobscheduler.controller;

import com.lemnisk.jobscheduler.model.ScheduledJob;
import com.lemnisk.jobscheduler.repository.ScheduledJobRepository;
import com.lemnisk.jobscheduler.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private ScheduledJobRepository repo;

    @Autowired
    private KafkaProducerService kafkaProducer;

    // Schedule a new job
    @PostMapping("/schedule")
    public ResponseEntity<ScheduledJob> scheduleJob(@RequestBody ScheduledJob job) {
        // Set the job status to "scheduled" before saving
        job.setStatus("scheduled");

        // Save the job to the repository and return the saved job
        ScheduledJob savedJob = repo.save(job);
        
        // Return the saved job along with a success response
        return ResponseEntity.ok(savedJob);
    }

    // Schedule a reminder using Kafka (for delayed jobs)
    @PostMapping("/reminder")
    public ResponseEntity<String> scheduleReminder(@RequestBody Map<String, Object> payload) {
        // Extract metadata, delay in minutes, and topic from the request payload
        String metadata = payload.get("metadata").toString();
        int delayMinutes = (int) payload.get("delayMinutes");
        String topic = payload.get("topic").toString();

        // Schedule the reminder using a single-threaded executor to send a message after the delay
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            kafkaProducer.sendMessage(topic, metadata);  // Kafka message sending logic
        }, delayMinutes, TimeUnit.MINUTES);

        // Return a response indicating the reminder was scheduled
        return ResponseEntity.ok("Reminder scheduled!");
    }
}
