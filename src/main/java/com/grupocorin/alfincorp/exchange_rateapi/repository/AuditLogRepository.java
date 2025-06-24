package com.grupocorin.alfincorp.exchange_rateapi.repository;

import com.grupocorin.alfincorp.exchange_rateapi.models.entity.AuditLog;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AuditLogRepository extends ReactiveCrudRepository<AuditLog, Long> {
}
