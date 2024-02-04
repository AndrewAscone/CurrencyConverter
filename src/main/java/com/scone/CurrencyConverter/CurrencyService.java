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
        // Use restTemplate to make HTTP requests to the currency exchange rate API
        // Implement the logic to parse the API response and perform the currency conversion
        // Return the converted amount
        return 0;
    }
}
