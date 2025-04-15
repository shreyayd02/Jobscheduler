package com.lemnisk.jobscheduler.model;

public class JobRequest {
    private String jobName;
    private String scheduleType;
    private String date;
    private String time;
    private String timeZone;
    private String status;

    // Getters and Setters
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status;}
    public String toString() {
        return "JobRequest{" +
                "jobName='" + jobName + '\'' +
                ", scheduleType='" + scheduleType + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}
