package com.lemnisk.jobscheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaTestController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "test-topic";

    @GetMapping("/send")
    public String sendMessage(@RequestParam String msg) {
        kafkaTemplate.send(TOPIC, msg);
        return "Message sent to Kafka: " + msg;
    }
}
