package com.scone.CurrencyConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/convert")
    public ResponseEntity<Double> convertCurrency(
        @RequestParam String sourceCurrency,
        @RequestParam String targetCurrency,
        @RequestParam double amount) {

        double convertedAmount = currencyService.convertCurrency(sourceCurrency, targetCurrency, amount);
        return ResponseEntity.ok(convertedAmount);
    }
}
