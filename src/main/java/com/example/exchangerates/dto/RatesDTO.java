package com.example.exchangerates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

public class RatesDTO {
    @JsonProperty("rates")
    private Map<String, BigDecimal> rates;

    public BigDecimal getRate(String currencyCode) {
        return rates.get(currencyCode.toUpperCase());
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }
}