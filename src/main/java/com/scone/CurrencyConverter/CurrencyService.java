package com.scone.CurrencyConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {
    //@Value annotation is injecting the URL from application.properties to variable
    //Placeholder for base URL of API endpoint
    @Value("${exchange.api.url}")
    private String apiUrl;

    //This is a Spring class used to simplify making HTTP requests, injected into scope via @Autowired
    //This is used to send an HTTP GET request to currency exchange rate API
    @Autowired
    private RestTemplate restTemplate;

    public double convertCurrency(String sourceCurrency, String targetCurrency, double amount){
        String url = apiUrl + "?base=" + sourceCurrency + "&symbols=" + targetCurrency;

        ResponseEntity<CurrencyApiResponse> responseEntity = restTemplate.getForEntity(url, CurrencyApiResponse.class);
        CurrencyApiResponse response = responseEntity.getBody();

        if (response != null && response.getRates() != null && response.getRates().containsKey(targetCurrency)) {
            double exchangeRate = response.getRates().get(targetCurrency);
            return amount * exchangeRate;
        } else {
            throw new RuntimeException("Failed to fetch exchange rate or invalid currency code");
        }
    }
}
