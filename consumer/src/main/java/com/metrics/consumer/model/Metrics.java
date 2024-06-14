package com.metrics.consumer.model;

import com.metrics.common.dto.MetricsDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "metrics")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "baseUnit")
    private String baseUnit;

    public Metrics(MetricsDto metricsDto) {
        this.name = metricsDto.getName();
        this.description = metricsDto.getDescription();
        this.baseUnit = metricsDto.getBaseUnit();
    }
}
