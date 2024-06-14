package com.metrics.consumer.service;

import com.metrics.common.dto.MetricsDto;
import com.metrics.consumer.model.Metrics;
import com.metrics.consumer.repository.MetricsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricsService {

    private final MetricsRepository metricsRepository;

    public List<MetricsDto> getAllMetrics() {
        return metricsRepository.findAll().stream().map(this::toDto).toList();
    }

    public MetricsDto getMetricById(Long id) {
        if (id == null) {
            return null;
        }

        return metricsRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public MetricsDto toDto(Metrics metrics) {
        return MetricsDto.builder()
                .name(metrics.getName())
                .description(metrics.getDescription())
                .baseUnit(metrics.getBaseUnit())
                .build();
    }
}
