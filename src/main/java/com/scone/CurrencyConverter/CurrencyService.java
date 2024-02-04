package com.scone.CurrencyConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    @Value("${exchange.api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    public double convertCurrency(String sourceCurrency, String targetCurrency, double amount){
        return 0;
    }
}
