package com.lemnisk.jobscheduler.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "scheduled_jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduledJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobName;       // Name or description of the job
    private String binaryPath;    // URL or path to the binary file (jar/npm)
    private ZonedDateTime scheduleTime; // Scheduled execution time
    private String status;        // Job status (scheduled, running, failed, etc.)
    private boolean recurring;    // Whether the job is recurring or not
    private String frequency;     // Frequency if recurring job (Hourly, Daily, etc.)
    private String timezone;      // Timezone for the scheduled time
}
