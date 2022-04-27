package com.example.bookstore.webClient.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void validate_validateBook_whenTitleIsNull() {
        String result;
        Book book = new Book();
        book.setAuthor("Jan Kowalski");

        result = book.validate();

        assertNotNull(result);
        assertEquals(result,"Tytuł nie może być pusty.");
    }

    @Test
    void validate_validateBook_whenAuthorIsNull() {
        String result;
        Book book = new Book();
        book.setTitle("Java");

        result = book.validate();

        assertNotNull(result);
        assertEquals(result,"Autor nie może być pusty.");
    }

    @Test
    void validate_validateBook_whenAuthorAndTitleIsNotNull() {
        String result;
        Book book = new Book();
        book.setTitle("Java");
        book.setAuthor("Cay S. Horstmann");

        result = book.validate();

        assertNull(result);
    }
}
