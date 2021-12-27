package com.example.exchangerates.service;

import com.example.exchangerates.api.GiphyClient;
import com.example.exchangerates.api.OpenExchangeRatesClient;
import com.example.exchangerates.dto.GifDTO;
import com.example.exchangerates.dto.RatesDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CurrencyServiceTests {
    @MockBean
    private GiphyClient mockGiphyClient;

    @MockBean
    private OpenExchangeRatesClient mockOpenExchangeRatesClient;

    @Autowired
    private CurrencyService currencyService;

    @Test
    public void whenYesterdayRateWasHigherThenMethodReturnsBroke() {
        setRates(2, 1.5);

        String methodReturns = currencyService.getGifUrlForCurrencyChange("RUB");

        assertEquals("broke", methodReturns);
    }

    @Test
    public void whenYesterdayRateWasLowerThenMethodReturnsRich() {
        setRates(1.5, 2);

        String methodReturns = currencyService.getGifUrlForCurrencyChange("RUB");

        assertEquals("rich", methodReturns);
    }

    private void setRates(double yesterday, double today) {
        RatesDTO yesterdayRatesDTO = new RatesDTO();
        yesterdayRatesDTO.setRates(new HashMap<>() {{ put("RUB", new BigDecimal(yesterday)); }});
        RatesDTO todayRatesDTO = new RatesDTO();
        todayRatesDTO.setRates(new HashMap<>() {{ put("RUB", new BigDecimal(today)); }});
        when(mockOpenExchangeRatesClient.getHistorical(any())).thenReturn(yesterdayRatesDTO);
        when(mockOpenExchangeRatesClient.getLatest()).thenReturn(todayRatesDTO);
        when(mockGiphyClient.getRandomGif(any()))
                .thenAnswer(i -> {
                    GifDTO gifDTO = new GifDTO();
                    gifDTO.setUrl((String) i.getArguments()[0]);
                    return gifDTO;
                });
    }
}
