package com.grupocorin.alfincorp.exchange_rateapi.service.impl;

import com.grupocorin.alfincorp.exchange_rateapi.models.dto.ExchangeRequest;
import com.grupocorin.alfincorp.exchange_rateapi.models.dto.ExchangeResponse;
import com.grupocorin.alfincorp.exchange_rateapi.models.entity.AuditLog;
import com.grupocorin.alfincorp.exchange_rateapi.models.entity.ExchangeRate;
import com.grupocorin.alfincorp.exchange_rateapi.repository.AuditLogRepository;
import com.grupocorin.alfincorp.exchange_rateapi.repository.ExchangeRateRepository;
import com.grupocorin.alfincorp.exchange_rateapi.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeRateRepository repository;
    private final AuditLogRepository auditLogRepository;

    @Override
    public Mono<ExchangeResponse> convert(ExchangeRequest request) {
        return repository.findBySourceCurrencyAndTargetCurrency(
                        request.getSourceCurrency(), request.getTargetCurrency())
                .flatMap(rate -> {
                    BigDecimal converted = request.getAmount().multiply(rate.getRate());

                    ExchangeResponse response = new ExchangeResponse();
                    response.setRate(rate.getRate());
                    response.setConvertedAmount(converted);
                    response.setSourceCurrency(rate.getSourceCurrency());
                    response.setTargetCurrency(rate.getTargetCurrency());

                    return getAuthenticatedUsername()
                            .flatMap(username -> {
                                AuditLog audit = new AuditLog(
                                        null,
                                        username,
                                        rate.getSourceCurrency(),
                                        rate.getTargetCurrency(),
                                        request.getAmount(),
                                        converted,
                                        LocalDateTime.now()
                                );
                                return auditLogRepository.save(audit).thenReturn(response);
                            });
                });
    }

    @Override
    public Mono<ExchangeRate> save(ExchangeRate rate) {
        return repository.save(rate);
    }

    @Override
    public Mono<ExchangeRate> update(Long id, ExchangeRate rate) {
        return repository.findById(id)
                .flatMap(existing -> {
                    existing.setRate(rate.getRate());
                    return repository.save(existing);
                });
    }

    @Override
    public Flux<ExchangeRate> getAll() {
        return repository.findAll();
    }

    private Mono<String> getAuthenticatedUsername() {
        return ReactiveSecurityContextHolder.getContext()
                .map(context -> {
                    Authentication auth = context.getAuthentication();
                    return (auth != null) ? auth.getName() : "anonymous";
                })
                .defaultIfEmpty("anonymous");
    }
}
