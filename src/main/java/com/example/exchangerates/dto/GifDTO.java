package com.example.exchangerates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class GifDTO {
    private String url;

    @JsonProperty("data")
    private void unpackNameFromNestedObject(Map<String, Object> data) {
        url = (String) data.get("url");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
