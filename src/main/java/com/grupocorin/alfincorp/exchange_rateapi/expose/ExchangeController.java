package com.grupocorin.alfincorp.exchange_rateapi.expose;

import com.grupocorin.alfincorp.exchange_rateapi.models.dto.ExchangeRequest;
import com.grupocorin.alfincorp.exchange_rateapi.models.dto.ExchangeResponse;
import com.grupocorin.alfincorp.exchange_rateapi.models.entity.ExchangeRate;
import com.grupocorin.alfincorp.exchange_rateapi.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService service;

    @PostMapping("/convert")
    public Mono<ExchangeResponse> convert(@RequestBody ExchangeRequest request) {
        return service.convert(request);
    }

    @PostMapping("/rate")
    public Mono<ExchangeRate> saveRate(@RequestBody ExchangeRate rate) {
        return service.save(rate);
    }

    @PutMapping("/rate/{id}")
    public Mono<ExchangeRate> updateRate(@PathVariable Long id, @RequestBody ExchangeRate rate) {
        return service.update(id, rate);
    }

    @GetMapping("/rates")
    public Flux<ExchangeRate> getAllRates() {
        return service.getAll();
    }
}
