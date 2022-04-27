package com.example.bookstore.webClient.model;

import lombok.Data;

@Data
public class Book {
    private String id;
    private String title;
    private String author;

    public Book() {
    }

    public Book(String title, String author) {
        this.id = null;
        this.title = title;
        this.author = author;
    }

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String validate(){
        String message = null;
        if(title == null){
            message = "Tytuł nie może być pusty.";
        }

        if (author == null){
            message = "Autor nie może być pusty.";
        }
        return message;

    }
}
