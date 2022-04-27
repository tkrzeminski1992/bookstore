package com.example.bookstore.webClient.model;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class RestTemplateServiceModel {
    private String link;
    private HttpMethod httpMethod;
    private HttpHeaders headers;
    private Map<String,String> params;
    private Object body;
    private HttpStatus correctStatus;

    public RestTemplateServiceModel(String link, HttpMethod httpMethod, HttpHeaders headers, Map<String, String> params, Object body, HttpStatus correctStatus) {
        this.link = link;
        this.httpMethod = httpMethod;
        this.headers = headers;
        this.params = params;
        this.body = body;
        this.correctStatus = correctStatus;
    }

    public String getLink() {
        return link;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Object getBody() {
        return body;
    }

    public HttpStatus getCorrectStatus() {
        return correctStatus;
    }
}
