package com.example.exchangerates.controller;

import com.example.exchangerates.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/currencies/{currencyCode}/last-day-change/gif")
    public String getGifForCurrencyChange(@PathVariable String currencyCode) {
        return "redirect:" + currencyService.getGifUrlForCurrencyChange(currencyCode);
    }
}
