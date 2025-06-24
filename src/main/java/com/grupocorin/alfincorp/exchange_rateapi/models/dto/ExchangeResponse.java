package com.grupocorin.alfincorp.exchange_rateapi.models.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ExchangeResponse {
    private BigDecimal convertedAmount;
    private BigDecimal rate;
    private String sourceCurrency;
    private String targetCurrency;
}
