package com.example.bookstore.webClient.manager;

import com.example.bookstore.webClient.model.Book;
import com.example.bookstore.webClient.model.RestTemplateServiceModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;

@Service
public class BookstoreService {

    private final RestTemplateService restTemplateService;
    private final String BASE_URL = "http://localhost:8080/bookstore-web/book";
    private final HttpHeaders httpHeaders = new HttpHeaders();

    public BookstoreService(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;

        httpHeaders.set("Accept",MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set("Content-Type",MediaType.APPLICATION_JSON_VALUE);
    }

    public List<Book> getBooksList()throws HttpClientErrorException, JsonProcessingException {
        String body = "";
        HashMap<String,String> params = new HashMap<>();
        String json;
        List<Book> bookList;
        RestTemplateServiceModel restTemplateServiceModel = new RestTemplateServiceModel(
                BASE_URL,
                HttpMethod.GET,
                httpHeaders,
                params,
                body,
                HttpStatus.OK
        );
        json = restTemplateService.connectToJsonApi(restTemplateServiceModel).getBody();
        bookList = new ObjectMapper().readValue(json, new TypeReference<List<Book>>(){});

        return bookList;
    }

    public Book getBook(String id) throws HttpClientErrorException, JsonProcessingException {
        String body = "";
        String link = BASE_URL + "/{bookId}";
        HashMap<String,String> params = new HashMap<>();
        String json;
        Book book;

        params.put("bookId", id);

        RestTemplateServiceModel restTemplateServiceModel = new RestTemplateServiceModel(
                link,
                HttpMethod.GET,
                httpHeaders,
                params,
                body,
                HttpStatus.OK
        );
        json = restTemplateService.connectToJsonApi(restTemplateServiceModel).getBody();
        book = new ObjectMapper().readValue(json, Book.class);

        return book;
    }

    public String saveBook(Book book) throws HttpClientErrorException{
        HashMap<String,String> params = new HashMap<>();
        HttpMethod httpMethod;
        String validationError = book.validate();

        if(validationError==null){
            if(book.getId().isEmpty()){
                httpMethod = HttpMethod.POST;
            }else{
                httpMethod = HttpMethod.PUT;
            }

            RestTemplateServiceModel restTemplateServiceModel = new RestTemplateServiceModel(
                    BASE_URL,
                    httpMethod,
                    httpHeaders,
                    params,
                    book,
                    HttpStatus.CREATED
            );
            restTemplateService.connectToJsonApi(restTemplateServiceModel).getBody();
        }

        return validationError;
    }

    public void deleteBook(String id) throws HttpClientErrorException{
        String body = "";
        String link = BASE_URL + "/{bookId}";
        HashMap<String,String> params = new HashMap<>();

        params.put("bookId", id);

        RestTemplateServiceModel restTemplateServiceModel = new RestTemplateServiceModel(
                link,
                HttpMethod.DELETE,
                httpHeaders,
                params,
                body,
                HttpStatus.NO_CONTENT
        );
        restTemplateService.connectToJsonApi(restTemplateServiceModel);
    }

}
