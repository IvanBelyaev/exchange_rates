package com.example.exchangerates.api;

import com.example.exchangerates.dto.RatesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "openExchangeRates", url = "${open-exchange-rates.api.base-url}")
public interface OpenExchangeRatesClient {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "${open-exchange-rates.api.historical.path.prefix}{data}${open-exchange-rates.api.historical.path.suffix}" +
                    "?app_id=${open-exchange-rates.api.app-id}&base=${open-exchange-rates.api.base}")
    RatesDTO getHistorical(@PathVariable String data);

    @RequestMapping(
            method = RequestMethod.GET,
            value = "${open-exchange-rates.api.latest.path.prefix}${open-exchange-rates.api.latest.path.suffix}" +
                    "?app_id=${open-exchange-rates.api.app-id}&base=${open-exchange-rates.api.base}")
    RatesDTO getLatest();
}
