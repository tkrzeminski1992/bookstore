package com.example.bookstore.webClient.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
class RestTemplateServiceTest {

    private RestTemplateService restTemplateService;

    @Test
    @BeforeEach
    void initRestTemplateService(){
        RestTemplateBuilder restTemplateBuilder = mock(RestTemplateBuilder.class);
        this.restTemplateService = new RestTemplateService(restTemplateBuilder);
    }

    @Test
    public void checkResponseStatus_whenCorrectResponse(){
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity responseEntityCreated = new ResponseEntity<>("Test response body", HttpStatus.OK);

        ResponseEntity responseEntityAfterTest = restTemplateService.checkResponseStatus(responseEntityCreated, HttpStatus.OK);

        assertEquals(responseEntityCreated, responseEntityAfterTest);
    }

    @Test
    public void checkResponseStatus_whenResponseWithCode404(){
        ResponseEntity responseEntityCreated = new ResponseEntity<>("Test response body", HttpStatus.NOT_FOUND);

        HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> {
            restTemplateService.checkResponseStatus(responseEntityCreated, HttpStatus.OK);
        });

        assertEquals(exception.getMessage(), "404 Test response body");

    }

}
