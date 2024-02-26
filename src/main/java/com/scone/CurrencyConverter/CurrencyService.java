package com.scone.CurrencyConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
        try{
            String requestUrl = apiUrl + "?base=" + sourceCurrency + "&symbols=" + targetCurrency;

            ResponseEntity<CurrencyApiResponse> responseEntity = restTemplate
                    .getForEntity(requestUrl, CurrencyApiResponse.class);
            if(responseEntity.getStatusCode() == HttpStatus.OK) {
                CurrencyApiResponse responseBody = responseEntity.getBody();
                double exchangeRate = responseBody.getRates().get(targetCurrency);

                return amount * exchangeRate;
            } else {
                System.err.println("Error: " + responseEntity.getStatusCode());
                return -1;
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Client error: " + e.getRawStatusCode() + " - " + e.getStatusText());
            return -1;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return -1;
        }

    }
}
