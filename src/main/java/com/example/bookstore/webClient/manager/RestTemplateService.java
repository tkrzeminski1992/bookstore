package com.example.bookstore.webClient.manager;

import com.example.bookstore.webClient.model.RestTemplateServiceModel;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

    private final RestTemplate restTemplate;

    private ResponseEntity<String> responseEntity;

    public RestTemplateService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<String> connectToJsonApi(RestTemplateServiceModel restTemplateServiceModel) throws HttpClientErrorException {
        HttpEntity entity = new HttpEntity(restTemplateServiceModel.getBody(), restTemplateServiceModel.getHeaders());

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                restTemplateServiceModel.getLink(),
                restTemplateServiceModel.getHttpMethod(),
                entity,
                String.class,
                restTemplateServiceModel.getParams());
        return checkResponseStatus(responseEntity, restTemplateServiceModel.getCorrectStatus());
    }

    public ResponseEntity<String> checkResponseStatus(ResponseEntity<String> responseEntity, HttpStatus correctStatus) throws HttpClientErrorException{
        if(responseEntity.getStatusCode() != correctStatus){
            throw new HttpClientErrorException(responseEntity.getStatusCode(), responseEntity.getBody());
        }
        return responseEntity;
    }
}
