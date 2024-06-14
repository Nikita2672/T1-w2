package com.metrics.producer.controller;

import com.metrics.common.dto.MetricsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MetricsController {

    private final MetricsEndpoint metricsEndpoint;

    private final KafkaTemplate<Object, Object> template;

    private static final String JVM_MEMORY_METRIC_NAME = "jvm.threads.live";

    private static final String TOPIC_NAME = "metrics-topic";

    @PostMapping("/metrics")
    public void sendMemoryMetrics() {
        log.info("sendMemoryMetrics was called");
        template.send(TOPIC_NAME, new MetricsDto(metricsEndpoint.metric(JVM_MEMORY_METRIC_NAME, null)));
    }
}
