package com.grupocorin.alfincorp.exchange_rateapi.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("audit_log")
public class AuditLog {
    @Id
    private Long id;

    private String username;
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal originalAmount;
    private BigDecimal convertedAmount;
    private LocalDateTime timestamp;
}
