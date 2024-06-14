package com.metrics.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetricsDto {
    private String name;
    private String description;
    private String baseUnit;

    public MetricsDto(MetricsEndpoint.MetricDescriptor metricDescriptor) {
        this.name = metricDescriptor.getName();
        this.description = metricDescriptor.getDescription();
        this.baseUnit = metricDescriptor.getBaseUnit();
    }
}
