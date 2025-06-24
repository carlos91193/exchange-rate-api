package com.grupocorin.alfincorp.exchange_rateapi.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("exchange_rate")
public class ExchangeRate {
    @Id
    private Long id;
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal rate;
}
