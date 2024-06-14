package com.metrics.consumer.controller;

import com.metrics.common.dto.MetricsDto;
import com.metrics.consumer.service.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MetricsController {

    private final MetricsService metricsService;

    @GetMapping("/metrics")
    public ResponseEntity<List<MetricsDto>> getAllMetrics() {
        return ResponseEntity.ok(metricsService.getAllMetrics());
    }

    @GetMapping("/metrics/{id}")
    public ResponseEntity<MetricsDto> getMetricById(@PathVariable Long id) {
        MetricsDto metricsDto = metricsService.getMetricById(id);
        if (metricsDto == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(metricsDto);
    }
}
