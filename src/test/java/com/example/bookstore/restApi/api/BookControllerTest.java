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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@WebAppConfiguration

class BookControllerTest {


    @Autowired
    private WebApplicationContext webApplicationContext;


    private MockMvc mvc;

    @BeforeEach
    void setup() {

        mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void getAllBooks_whenStatus200WithJsonResponse() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/bookstore-web/book")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getAllBooks_whenStatus200WithXmlResponse() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/bookstore-web/book")
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML));
    }

    @Test
    void addBook_whenStatus201JsonResponse() throws Exception {
        String json = new JSONObject()
                .put("title","Java")
                .put("author","Cay S. Horstmann")
                .toString();

        mvc.perform(MockMvcRequestBuilders
                        .post("/bookstore-web/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));


    }

    @Test
    void addBook_thenStatus201XmlResponse() throws Exception {
        String xml = "<book>" +
                        "<title>Java</title>" +
                        "<author>Cay S. Horstmann</author>" +
                    "</book>";

        mvc.perform(MockMvcRequestBuilders
                        .post("/bookstore-web/book")
                        .contentType(MediaType.APPLICATION_XML)
                        .accept(MediaType.APPLICATION_XML)
                        .content(xml))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML));
    }
}
