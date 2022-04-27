package com.example.bookstore.restApi.api;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@WebAppConfiguration
class InvalidInputExceptionControllerAdviceTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @BeforeEach
    void setUp() {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void saveBook_saveBookWithJsonRequest_whenIllegalTitleValue() throws Exception {
        String json = new JSONObject()
                .put("author","Jan Kowalski")
                .toString();
        HashMap<String,String> hashMap = new HashMap<>();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8080/bookstore-web/book",hashMap)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is(422))
                .andExpect(MockMvcResultMatchers.content().string("Invalid input"));
    }

    @Test
    void saveBook_saveBookWithJsonRequest_whenIllegalAuthorValue() throws Exception {
        String json = new JSONObject()
                .put("title","Java")
                .toString();
        HashMap<String,String> hashMap = new HashMap<>();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8080/bookstore-web/book",hashMap)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is(422))
                .andExpect(MockMvcResultMatchers.content().string("Invalid input"));
    }

    @Test
    void saveBook_saveBookWithJsonRequest_whenIllegalTitleAndAuthorValue() throws Exception {
        String json = new JSONObject()
                .put("author",null)
                .put("title",null)
                .toString();
        HashMap<String,String> hashMap = new HashMap<>();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8080/bookstore-web/book",hashMap)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is(422))
                .andExpect(MockMvcResultMatchers.content().string("Invalid input"));
    }

    @Test
    void updateBook_updateBookWithJsonRequest_whenIllegalTitleValue() throws Exception {
        String json = new JSONObject()
                .put("id","5")
                .put("author","Jan Kowalski")
                .toString();
        HashMap<String,String> hashMap = new HashMap<>();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("http://localhost:8080/bookstore-web/book",hashMap)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is(422))
                .andExpect(MockMvcResultMatchers.content().string("Invalid input"));
    }

    @Test
    void updateBook_updateBookWithJsonRequest_whenIllegalAuthorValue() throws Exception {
        String json = new JSONObject()
                .put("id","5")
                .put("title","Java")
                .toString();
        HashMap<String,String> hashMap = new HashMap<>();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("http://localhost:8080/bookstore-web/book",hashMap)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is(422))
                .andExpect(MockMvcResultMatchers.content().string("Invalid input"));
    }

    @Test
    void updateBook_updateBookWithJsonRequest_whenIllegalTitleAndAuthorValue() throws Exception {
        String json = new JSONObject()
                .put("id","5")
                .put("author",null)
                .put("title",null)
                .toString();
        HashMap<String,String> hashMap = new HashMap<>();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8080/bookstore-web/book",hashMap)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is(422))
                .andExpect(MockMvcResultMatchers.content().string("Invalid input"));
    }
}
