package com.grupocorin.alfincorp.exchange_rateapi.repository;

import com.grupocorin.alfincorp.exchange_rateapi.models.entity.ExchangeRate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ExchangeRateRepository extends ReactiveCrudRepository<ExchangeRate, Long> {
    Mono<ExchangeRate> findBySourceCurrencyAndTargetCurrency(String source, String target);
}
