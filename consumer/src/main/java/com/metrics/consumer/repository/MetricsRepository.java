package com.metrics.consumer.repository;

import com.metrics.consumer.model.Metrics;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MetricsRepository extends JpaRepository<Metrics, Long> {

    @Nonnull
    Optional<Metrics> findById(@Nonnull Long id);
}
