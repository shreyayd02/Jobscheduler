package com.lemnisk.jobscheduler.repository;

import com.lemnisk.jobscheduler.model.ScheduledJob;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.ZonedDateTime;
import java.util.List;

public interface ScheduledJobRepository extends JpaRepository<ScheduledJob, Long> {
    // Correcting return type and parameter types to match the ScheduledJob entity
    List<ScheduledJob> findByScheduleTimeBeforeAndStatus(ZonedDateTime time, String status);
}
