package com.example.exchangerates.api;

import com.example.exchangerates.dto.GifDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphy", url = "${giphy.api.base-url}")
public interface GiphyClient {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "${giphy.api.gif.random.path}" + "?api_key=${giphy.api.key}&rating=g")
    GifDTO getRandomGif(@RequestParam(value = "tag") String tag);
}
