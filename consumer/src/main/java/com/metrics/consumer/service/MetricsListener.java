package com.metrics.consumer.service;

import com.metrics.common.dto.MetricsDto;
import com.metrics.consumer.model.Metrics;
import com.metrics.consumer.repository.MetricsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MetricsListener {

    private final MetricsRepository metricsRepository;

    @KafkaListener(id = "metricsGroup", topics = "metrics-topic")
    public void listen(MetricsDto metricDescriptor) {
        log.info("Received name: {}", metricDescriptor.getName());
        log.info("Received description: {}", metricDescriptor.getDescription());
        log.info("Received baseUnit: {}", metricDescriptor.getBaseUnit());
        log.info("OK");
        metricsRepository.save(new Metrics(metricDescriptor));
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        log.info("Received unknown: {}", object);
    }
}
