package com.example.bookstore.restApi.api;

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
class IllegalExceptionsControllerAdviceTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @BeforeEach
    void setUp() {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void getBook_getBookWithJsonRequest_whenIllegalBookId() throws Exception {
        HashMap hashMap = new HashMap<>();
        hashMap.put("id","5");

        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/bookstore-web/book/{id}",hashMap)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andExpect(MockMvcResultMatchers.content().string("Book not found"));


    }

    @Test
    void deleteBook_deleteBookWithJsonRequest_whenIllegalBookId() throws Exception {
        HashMap hashMap = new HashMap<>();
        hashMap.put("id","5");

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("http://localhost:8080/bookstore-web/book/{id}",hashMap)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andExpect(MockMvcResultMatchers.content().string("Book not found"));


    }
}
