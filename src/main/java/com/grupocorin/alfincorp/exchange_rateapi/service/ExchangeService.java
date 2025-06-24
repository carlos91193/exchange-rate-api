package com.grupocorin.alfincorp.exchange_rateapi.service;

import com.grupocorin.alfincorp.exchange_rateapi.models.dto.ExchangeRequest;
import com.grupocorin.alfincorp.exchange_rateapi.models.dto.ExchangeResponse;
import com.grupocorin.alfincorp.exchange_rateapi.models.entity.ExchangeRate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeService {
    Mono<ExchangeResponse> convert(ExchangeRequest request);
    Mono<ExchangeRate> save(ExchangeRate rate);
    Mono<ExchangeRate> update(Long id, ExchangeRate rate);
    Flux<ExchangeRate> getAll();
}
