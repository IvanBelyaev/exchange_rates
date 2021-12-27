package com.example.exchangerates.service;

import com.example.exchangerates.api.GiphyClient;
import com.example.exchangerates.api.OpenExchangeRatesClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CurrencyService {
    private final GiphyClient giphyClient;
    private final OpenExchangeRatesClient openExchangeRatesClient;

    public CurrencyService(GiphyClient giphyClient,
                           OpenExchangeRatesClient openExchangeRatesClient) {
        this.giphyClient = giphyClient;
        this.openExchangeRatesClient = openExchangeRatesClient;
    }


    public String getGifUrlForCurrencyChange(String currencyCode) {
        String yesterday = LocalDate.now().minusDays(1).toString();
        BigDecimal yesterdayCurrencyRate = openExchangeRatesClient.getHistorical(yesterday).getRate(currencyCode);
        BigDecimal todayCurrencyRate = openExchangeRatesClient.getLatest().getRate(currencyCode);
        String tag = "broke";
        if (todayCurrencyRate.compareTo(yesterdayCurrencyRate) > 0) {
            tag = "rich";
        }
        return giphyClient.getRandomGif(tag).getUrl();
    }
}
