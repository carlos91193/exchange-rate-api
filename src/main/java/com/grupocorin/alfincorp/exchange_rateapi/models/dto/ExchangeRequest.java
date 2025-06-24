package com.grupocorin.alfincorp.exchange_rateapi.models.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ExchangeRequest {
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal amount;
}
